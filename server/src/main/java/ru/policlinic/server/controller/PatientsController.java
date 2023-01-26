package ru.policlinic.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.policlinic.server.dto.*;
import ru.policlinic.server.exception.ResourceNotFoundException;
import ru.policlinic.server.model.*;
import ru.policlinic.server.repository.AnalysesResultsRepository;
import ru.policlinic.server.repository.PatientsRepository;
import ru.policlinic.server.repository.ReceptionsRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/patients")
@Api(value = "patients")
public class PatientsController {
    @Autowired
    PatientsRepository patientsRepository;

    @Autowired
    private AnalysesResultsRepository analysesResultsRepository;
    @Autowired
    private ReceptionsRepository receptionsRepository;

    @GetMapping
    @ApiOperation(value = "getAllPatients", response = Patients.class)
    public ResponseEntity<List<PatientsResponse>> getAllPatients() {
        List<Patients> listPat = patientsRepository.findAll();
        List<PatientsResponse> patientsResponseList = new ArrayList<>(); // Полное расписание врача
        listPat.forEach(pat -> {
            PatientsResponse patientsResponse = new PatientsResponse();
            patientsResponse.setId(pat.getIdPatient());
            patientsResponse.setFullName(pat.getLastName() + " " + pat.getFirstName() + " " + pat.getMiddleName());
            patientsResponse.setFullAddress("г." + pat.getCity() + ", ул." + pat.getStreet() + ", д." + pat.getBuilding() + ", кв." + pat.getApartment());

            patientsResponseList.add(patientsResponse);
        });

        return new ResponseEntity<List<PatientsResponse>>(patientsResponseList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PatientsResponse> addPatient(@RequestBody PatientFullResponse insertPatient) {
        var patient = Patients.builder().build();

        if(insertPatient.getMiddlename().equals("")) {
            patient = Patients.builder()
                    .lastName(insertPatient.getLastname())
                    .firstName(insertPatient.getFirstname())
                    .city(insertPatient.getCity())
                    .street(insertPatient.getStreet())
                    .building(insertPatient.getBuilding())
                    .apartment(insertPatient.getApartment())
                    .build();
        } else {
            patient = Patients.builder()
                    .lastName(insertPatient.getLastname())
                    .firstName(insertPatient.getFirstname())
                    .middleName(insertPatient.getMiddlename())
                    .city(insertPatient.getCity())
                    .street(insertPatient.getStreet())
                    .building(insertPatient.getBuilding())
                    .apartment(insertPatient.getApartment())
                    .build();
        }

        patientsRepository.save(patient);

       PatientsResponse patientsResponse = new PatientsResponse();
       patientsResponse.setFullName(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getMiddleName());
       patientsResponse.setFullAddress("г." + patient.getCity() + ", ул." + patient.getStreet() + ", д." + patient.getBuilding() + ", кв." + patient.getApartment());

       return ResponseEntity.ok(patientsResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PatientsResponse> updatePatient(@PathVariable int id, @RequestBody PatientFullResponse newPatient) {
        Patients patient = patientsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Пациент не найден"));

        patient.setFirstName(newPatient.getFirstname());
        patient.setLastName(newPatient.getLastname());
        patient.setMiddleName(newPatient.getMiddlename());

        patient.setCity(newPatient.getCity());
        patient.setStreet(newPatient.getStreet());
        patient.setBuilding(newPatient.getBuilding());
        patient.setApartment(newPatient.getApartment());

        patientsRepository.save(patient);

        PatientsResponse patientsResponse = new PatientsResponse();
        patientsResponse.setFullName(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getMiddleName());
        patientsResponse.setFullAddress("г." + patient.getCity() + ", ул." + patient.getStreet() + ", д." + patient.getBuilding() + ", кв." + patient.getApartment());

        return ResponseEntity.ok(patientsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<PatientsResponse> getPatientById(@PathVariable int id) {
        Patients patient = patientsRepository.findById(Long.valueOf(id)).get(); // Поиск пациента по id

        PatientsResponse patientResponse = new PatientsResponse();

        patientResponse.setId(patient.getIdPatient());
        patientResponse.setFullName(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getMiddleName());
        patientResponse.setFullAddress("г." + patient.getCity() + ", ул." + patient.getStreet() + ", д." + patient.getBuilding() + ", кв." + patient.getApartment());

        return new ResponseEntity<PatientsResponse>(patientResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable int id) {
        Patients patient = patientsRepository.findById(Long.valueOf(id)).get(); // Поиск пациента по id

        patientsRepository.delete(patient);

        // При удалении пациента удаляются соответствующие результаты анализов и приёмы
        if(!patient.getPatRecIdentitiesByIdPatient().isEmpty()) {
            patient.getPatRecIdentitiesByIdPatient().forEach(rec -> {
                Receptions reception = receptionsRepository.findById(Long.valueOf(rec.getReceptionsByIdReception().getIdReception())).get();
                reception.setPatRecIdentitiesByIdReception(new ArrayList<>());
                receptionsRepository.delete(reception);
            });
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
