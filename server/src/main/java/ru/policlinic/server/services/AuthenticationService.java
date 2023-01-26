package ru.policlinic.server.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.policlinic.server.dto.*;
import ru.policlinic.server.exception.ResourceNotFoundException;
import ru.policlinic.server.model.Doctors;
import ru.policlinic.server.model.Users;
import ru.policlinic.server.repository.DoctorsRepository;
import ru.policlinic.server.repository.UsersRepository;
import ru.policlinic.server.security.config.JwtService;

import javax.print.Doc;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DoctorsRepository doctorsRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder().build();
        String role = "";
        if(request.getRole().equals("Врач")) {
            role = "Врач";
            // Проверка на корректность id
            if(doctorsRepository.findById(Long.valueOf(request.getIdDoctor())).get() == null) {
                throw new UsernameNotFoundException("Врач не найден");
            }
        } else {
            role = "Админ";
        }

        user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .doctorsByIdUser(new ArrayList<>())
                .build();

        // Сначала сохранить ползователя (Админ или Врач), если Врач, то привязка этого аккаунта из базы потом
        // Проверка на существование такого имени аккаунта
        List<Users> usersList = usersRepository.findAll();
        usersList.forEach(us -> {
            if(us.getUsername().equals(request.getUsername())) {
                throw new UsernameNotFoundException("Ползователь с таким именем уже существует");
            }
        });
        usersRepository.save(user);

        // Если создаётся пользователь с ролью "Врач"
        if(request.getRole().equals("Врач")) {
            // Если врач был выбран
            if(request.getIdDoctor() != 0) {
                List<Doctors> listDoc = doctorsRepository.findAll(); // Поиск всех врачей
                listDoc.forEach(doc -> {
                    if(doc.getIdDoctor() == request.getIdDoctor()) {
                        // Найти врача из базы с таким id
                        Doctors doctor = doctorsRepository.findById(Long.valueOf(request.getIdDoctor()))
                                .orElseThrow(() -> new ResourceNotFoundException("Врач не найден по id: " + request.getIdDoctor()));

                        // Если врачу не присвоен аккаунт
                        if(doctor.getUsersByIdUser() == null) {
                            // Установить врачу текущий аккаунт
                            doctor.setUsersByIdUser(usersRepository.findByUsername(request.getUsername()).get());
                            // Обновить врача в базе данных
                            doctorsRepository.save(doctor);
                        } else {
                            usersRepository.deleteById(Long.valueOf(usersRepository.findByUsername(request.getUsername()).get().getIdUser()));
                            throw new UsernameNotFoundException("У врача уже есть аккаунт");
                        }
                    }
                });
            }
        }

        String jwtToken = null;
        try {
            jwtToken = jwtService.generateToken(user);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UsersResponse usersResponse = new UsersResponse();
        var user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String jwtToken = null;
        try {
            jwtToken = jwtService.generateToken(user);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }

        if(!user.getDoctorsByIdUser().isEmpty()) {
            user.getDoctorsByIdUser().forEach(doc -> {
                usersResponse.setIdDoctor(doc.getIdDoctor());
            });
        }

        usersResponse.setRole(user.getRole());
        usersResponse.setPassword(user.getPassword());
        usersResponse.setUsername(user.getUsername());
        usersResponse.setId(user.getIdUser());

        return AuthenticationResponse.builder().token(jwtToken).user(usersResponse).build();
    }
}
