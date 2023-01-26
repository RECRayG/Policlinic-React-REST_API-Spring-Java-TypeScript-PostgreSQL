package ru.policlinic.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.policlinic.server.dto.*;
import ru.policlinic.server.exception.ResourceNotFoundException;
import ru.policlinic.server.model.Doctors;
import ru.policlinic.server.model.Users;
import ru.policlinic.server.repository.DoctorsRepository;
import ru.policlinic.server.repository.UsersRepository;
import ru.policlinic.server.security.config.JwtService;
import ru.policlinic.server.services.AuthenticationService;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
@AllArgsConstructor
//@RequiredArgsConstructor
@Api(value = "users")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    private static Integer countAcc = 0;

    @GetMapping
    @ApiOperation(value = "getAllUsers", response = Users.class)
    public ResponseEntity<List<UsersResponse>> getAllUsers() {
        List<Users> listUs = usersRepository.findAll(); // Поиск всех пользователей

        List<UsersResponse> usersResponseList = new ArrayList<>();
        listUs.forEach(user -> {
            UsersResponse usersResponse = new UsersResponse();

            usersResponse.setId(user.getIdUser());
            usersResponse.setRole(user.getRole());
            usersResponse.setUsername(user.getUsername());
            usersResponse.setPassword(user.getPassword());

            user.getDoctorsByIdUser().forEach(doc -> {
                if(!doc.getUsersByIdUser().getRole().equals("Админ")) {
//                    doctor.setId(doc.getIdDoctor());
//                    doctor.setSpecialization(doc.getSpecializationsByIdSpecialization().getSpecializationName());
//                    doctor.setCabinet(doc.getCabinetsByIdCabinet().getCabinetNumber());
//                    doctor.setPlot(doc.getPlotsByIdPlot().getPlotNumber());
//                    doctor.setFullName(doc.getLastName() + " " +
//                                        doc.getFirstName() + " " +
//                                        doc.getMiddleName());
//                    doctor.setTimetable(new ArrayList<>());

                    usersResponse.setIdDoctor(doc.getIdDoctor());
                    usersResponse.setDoctorShortInfo(doc.getLastName() + " " + doc.getFirstName() + " " + doc.getMiddleName() + " - " + doc.getSpecializationsByIdSpecialization().getSpecializationName());
                }
            });


            usersResponseList.add(usersResponse);
        });

        return new ResponseEntity<List<UsersResponse>>(usersResponseList, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable int id) {
        Users user = usersRepository.findById(Long.valueOf(id)).get(); // Поиск пользователя по id

        UsersResponse usersResponse = new UsersResponse();

        usersResponse.setId(user.getIdUser());
        usersResponse.setRole(user.getRole());
        usersResponse.setUsername(user.getUsername());
        usersResponse.setPassword(user.getPassword());

        user.getDoctorsByIdUser().forEach(doc -> {
            if(!doc.getUsersByIdUser().getRole().equals("Админ")) {
                usersResponse.setIdDoctor(doc.getIdDoctor());
                usersResponse.setDoctorShortInfo(doc.getLastName() + " " + doc.getFirstName() + " " + doc.getMiddleName() + " - " + doc.getSpecializationsByIdSpecialization().getSpecializationName());
            }
        });

        return new ResponseEntity<UsersResponse>(usersResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Users> updateUser(@RequestBody UsersResponse newUser) {
        Users user = usersRepository.findById(Long.valueOf(newUser.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        if(user.getRole().equals("Админ") && newUser.getRole().equals("Врач") && newUser.getIdDoctor() == 0)
            throw new UsernameNotFoundException("Не выбран врач для привязки");

        List<Users> usersList = usersRepository.findAll();
        countAcc = 0;
        usersList.forEach(us -> {
            if(us.getUsername().equals(newUser.getUsername()) && countAcc == 0) {
                countAcc++;
            } else if(us.getUsername().equals(newUser.getUsername()) && countAcc > 0) {
                throw new UsernameNotFoundException("Ползователь с таким именем уже существует");
            }
        });
        countAcc = 0;

        if(newUser.getIdDoctor() != 0) {
            // Проверка на корректность id врача
            if(doctorsRepository.findById(Long.valueOf(newUser.getIdDoctor())).get() == null) {
                throw new UsernameNotFoundException("Врач не найден");
            }
        }

        user.setUsername(newUser.getUsername());
        user.setIdUser(newUser.getId());

        if(newUser.getIdDoctor() != 0) {
            // Получить врача из базы по id
            Doctors doctor = doctorsRepository.findById(Long.valueOf(newUser.getIdDoctor())).get();

            // (Врач -> Врач) -> обновить врача для аккаунта
            if(user.getRole().equals(newUser.getRole())) {

                // Установить аккаунт новому врачу, если у него не было аккаунта до этого
                if(doctor.getUsersByIdUser() == null) {
                    // Установить врачу текущий аккаунт
                    doctor.setUsersByIdUser(user);
                    // Обновить врача в базе данных
                    doctorsRepository.save(doctor);

                    // Удалить аккаунт у старого врача:
                    user.getDoctorsByIdUser().forEach(doc -> {
                        // Удалить привязку только в том случае, если врач был изменён
                        if(doc.getIdDoctor() != doctor.getIdDoctor()) {
                            // Очистить привязку аккаунта
                            doc.setUsersByIdUser(null);
                            // Обновить врача в базе данных
                            doctorsRepository.save(doc);
                        }
                    });
                } else {
                    // Если id врача не совпадает с его же id, то исключение
                    user.getDoctorsByIdUser().forEach(doc -> {
                        if(doc.getIdDoctor() != doctor.getIdDoctor()) {
                            throw new UsernameNotFoundException("У врача уже есть аккаунт");
                        }
                    });
                }
            } else // (Админ -> Врач) -> добавить привязку аккаунта
                if(doctor.getUsersByIdUser() == null &&
                        user.getRole().equals("Админ") && newUser.getRole().equals("Врач")) {

                    // Установить врачу текущий аккаунт
                    doctor.setUsersByIdUser(user);
                    // Обновить врача в базе данных
                    doctorsRepository.save(doctor);

                } else // (Врач -> Админ) -> удалить у врача аккаунт
                    if(user.getRole().equals("Врач") && newUser.getRole().equals("Админ")) {
                        user.getDoctorsByIdUser().forEach(doc -> {
                            // Очистить привязку аккаунта
                            doctor.setUsersByIdUser(null);
                            // Обновить врача в базе данных
                            doctorsRepository.save(doctor);
                        });
                    }
        }
        else // (Врач -> Админ) -> удалить у врача аккаунт
            if(user.getRole().equals("Врач") && newUser.getRole().equals("Админ")) {
                user.getDoctorsByIdUser().forEach(doc -> {
                    // Получить врача из базы по id
                    Doctors doctor = doctorsRepository.findById(Long.valueOf(doc.getIdDoctor())).get();

                    // Очистить привязку аккаунта
                    doctor.setUsersByIdUser(null);
                    // Обновить врача в базе данных
                    doctorsRepository.save(doctor);
                });
            }

        user.setRole(newUser.getRole());

        usersRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        Users user = usersRepository.findById(Long.valueOf(id)).get(); // Поиск пользователя по id

        user.setDoctorsByIdUser(null);
        usersRepository.delete(user);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
