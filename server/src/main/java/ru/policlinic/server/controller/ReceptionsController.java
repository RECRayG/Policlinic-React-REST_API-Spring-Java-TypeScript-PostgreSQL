package ru.policlinic.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.policlinic.server.dto.*;
import ru.policlinic.server.exception.ResourceNotFoundException;
import ru.policlinic.server.model.*;
import ru.policlinic.server.repository.*;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/receptions")
@Api(value = "receptions")
public class ReceptionsController {
    @Autowired
    private ReceptionsRepository receptionsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private AnalysesRepository analysesRepository;

    @Autowired
    private ProceduresRepository proceduresRepository;

    @Autowired
    private MedicationsRepository medicationsRepository;
    @Autowired
    private AnalysesResultsRepository analysesResultsRepository;

    @Autowired
    private RecMedIdentityRepository recMedIdentityRepository;

    @Autowired
    private RecProcIdentityRepository recProcIdentityRepository;

    @Autowired
    private RecAnIdentityRepository recAnIdentityRepository;

    @GetMapping
    @ApiOperation(value = "getAllReceptions", response = Receptions.class)
    public ResponseEntity<List<ReceptionResponse>> getAllReceptions() {
        List<Receptions> listRec = receptionsRepository.findAll(); // Поиск приёмов из базы

        List<ReceptionResponse> receptionResponseList = new ArrayList<>();
        listRec.forEach(rec -> {
            ReceptionResponse receptionResponse = new ReceptionResponse();
            List<AnalysesResponse> analysesResponseList = new ArrayList<>();
            List<MedicationResponse> medicationsList = new ArrayList<>();
            List<ProceduresResponse> proceduresResponseList = new ArrayList<>();
            List<String> analysesResult = new ArrayList<>();
            DoctorsResponse doctor = new DoctorsResponse();
            PatientsResponse patient = new PatientsResponse();

            // id
            receptionResponse.setId(rec.getIdReception());
            // Детали врача, если врач был удалён
            receptionResponse.setDoctorDetails(rec.getDoctorDetails());
            // Дата приёма
            // 12-01-2023 -> 2023-01-12
            String formatDate = rec.getDateOfReception().toString().split("-")[2] + "-" +
                    rec.getDateOfReception().toString().split("-")[1] + "-" +
                    rec.getDateOfReception().toString().split("-")[0];
            receptionResponse.setDateOfReception(formatDate);
            // Время приёма
            receptionResponse.setTimeOfReception(rec.getTimeOfReception().toString());
            // Дата закрытия приёма
            if(rec.getDateOfExtract() != null) {
                formatDate = rec.getDateOfExtract().toString().split("-")[2] + "-" +
                        rec.getDateOfExtract().toString().split("-")[1] + "-" +
                        rec.getDateOfExtract().toString().split("-")[0];
                receptionResponse.setDateOfExtract(formatDate);
            } else
                receptionResponse.setDateOfExtract("");
            // Диагноз
            if(rec.getDiagnosis() != null)
                receptionResponse.setDiagnosis(rec.getDiagnosis());
            else
                receptionResponse.setDiagnosis("");
            // Жалобы
            if(rec.getComplaints() != null)
                receptionResponse.setComplaints(rec.getComplaints());
            else
                receptionResponse.setComplaints("");
            // Врач
            for(DocRecIdentity docRecIdentity : rec.getDocRecIdentitiesByIdReception()) {
                doctor.setId(docRecIdentity.getDoctorsByIdDoctor().getIdDoctor());
                doctor.setSpecialization(docRecIdentity.getDoctorsByIdDoctor().getSpecializationsByIdSpecialization().getSpecializationName());
                doctor.setCabinet(docRecIdentity.getDoctorsByIdDoctor().getCabinetsByIdCabinet().getCabinetNumber());
                doctor.setPlot(docRecIdentity.getDoctorsByIdDoctor().getPlotsByIdPlot().getPlotNumber());
                doctor.setFullName(docRecIdentity.getDoctorsByIdDoctor().getLastName() + " " +
                                    docRecIdentity.getDoctorsByIdDoctor().getFirstName() + " " +
                                    docRecIdentity.getDoctorsByIdDoctor().getMiddleName());
                doctor.setTimetable(new ArrayList<>());
                break;
            }
            receptionResponse.setDoctor(doctor);
            // Пациент
            for(PatRecIdentity patRecIdentity : rec.getPatRecIdentitiesByIdReception()) {
                patient.setId(patRecIdentity.getPatientsByIdPatient().getIdPatient());
                patient.setFullName(patRecIdentity.getPatientsByIdPatient().getLastName() + " " +
                                    patRecIdentity.getPatientsByIdPatient().getFirstName() + " " +
                                    patRecIdentity.getPatientsByIdPatient().getMiddleName());
                patient.setFullAddress("г." + patRecIdentity.getPatientsByIdPatient().getCity() + ", ул." +
                                        patRecIdentity.getPatientsByIdPatient().getStreet() + ", д." +
                                        patRecIdentity.getPatientsByIdPatient().getBuilding() + ", кв." +
                                        patRecIdentity.getPatientsByIdPatient().getApartment());
                break;
            }
            receptionResponse.setPatient(patient);
            // Медикаменты
            for(RecMedIdentity recMedIdentity : rec.getRecMedIdentitiesByIdReception()) {
                MedicationResponse medicationResponse = new MedicationResponse();
                medicationResponse.setId(recMedIdentity.getMedicationsByIdMedication().getIdMedication());
                medicationResponse.setMedication(recMedIdentity.getMedicationsByIdMedication().getMedicationName());
                medicationsList.add(medicationResponse);
            }
            receptionResponse.setMedications(medicationsList);
            // Процедуры
            for(RecProcIdentity recProcIdentity : rec.getRecProcIdentitiesByIdReception()) {
                ProceduresResponse proceduresResponse = new ProceduresResponse();
                proceduresResponse.setId(recProcIdentity.getProceduressByIdProcedure().getIdProcedure());
                proceduresResponse.setProcedure(recProcIdentity.getProceduressByIdProcedure().getProcedureName());
                proceduresResponseList.add(proceduresResponse);
            }
            receptionResponse.setProcedures(proceduresResponseList);

            // Анализы и их результаты
            for(RecAnIdentity recAnIdentity : rec.getRecAnIdentitiesByIdReception()) {
                AnalysesResponse analysesResponse = new AnalysesResponse();

                analysesResponse.setAnalysis(recAnIdentity.getAnalysesByIdAnalysis().getAnalysisName());
                analysesResponse.setIdAnalysis(recAnIdentity.getAnalysesByIdAnalysis().getIdAnalysis());

                recAnIdentity.getAnalysesByIdAnalysis().getAnalysesResultsByIdAnalysis().forEach(ar -> {
                    // Если результаты анализов являются результатами текущего анализа
                    if(ar.getAnalysesByIdAnalysis().getIdAnalysis() == recAnIdentity.getAnalysesByIdAnalysis().getIdAnalysis()) {
                        // Добавление только тех результатов, которые привязаны к текущему приёму
                        rec.getPatRecIdentitiesByIdReception().forEach(pat -> {
                            pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(arr -> {
                                if(pat.getReceptionsByIdReception().getIdReception() == rec.getIdReception()) {
                                    analysesResult.add(ar.getAnalysisResult());
                                    analysesResponse.setIdAnalysisResult(ar.getIdAnalysisResult());
                                    analysesResponse.setAnalysisResult(ar.getAnalysisResult());
                                }
                            });
                        });
                    }
                });

                analysesResponseList.add(analysesResponse);
            }
            receptionResponse.setAnalyses(analysesResponseList);


            receptionResponseList.add(receptionResponse);
        });

        return new ResponseEntity<List<ReceptionResponse>>(receptionResponseList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceptionResponse> getReceptionById(@PathVariable int id) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get(); // Поиск приёма по id

        ReceptionResponse receptionResponse = new ReceptionResponse();

        List<AnalysesResponse> analysesResponseList = new ArrayList<>();
        List<MedicationResponse> medicationsList = new ArrayList<>();
        List<ProceduresResponse> proceduresResponseList = new ArrayList<>();
        List<String> analysesResult = new ArrayList<>();
        DoctorsResponse doctor = new DoctorsResponse();
        PatientsResponse patient = new PatientsResponse();

        // id
        receptionResponse.setId(reception.getIdReception());
        // Детали врача, если врач был удалён
        receptionResponse.setDoctorDetails(reception.getDoctorDetails());
        // Дата приёма
        // 12-01-2023 -> 2023-01-12
        String formatDate = reception.getDateOfReception().toString().split("-")[2] + "-" +
                reception.getDateOfReception().toString().split("-")[1] + "-" +
                reception.getDateOfReception().toString().split("-")[0];
        receptionResponse.setDateOfReception(formatDate);
        // Время приёма
        receptionResponse.setTimeOfReception(reception.getTimeOfReception().toString());
        // Дата закрытия приёма
        if(reception.getDateOfExtract() != null){
            formatDate = reception.getDateOfExtract().toString().split("-")[2] + "-" +
                    reception.getDateOfExtract().toString().split("-")[1] + "-" +
                    reception.getDateOfExtract().toString().split("-")[0];
            receptionResponse.setDateOfExtract(formatDate);
        } else
            receptionResponse.setDateOfExtract("");
        // Диагноз
        if(reception.getDiagnosis() != null)
            receptionResponse.setDiagnosis(reception.getDiagnosis());
        else
            receptionResponse.setDiagnosis("");
        // Жалобы
        if(reception.getComplaints() != null)
            receptionResponse.setComplaints(reception.getComplaints());
        else
            receptionResponse.setComplaints("");
        // Врач
        for(DocRecIdentity docRecIdentity : reception.getDocRecIdentitiesByIdReception()) {
            doctor.setId(docRecIdentity.getDoctorsByIdDoctor().getIdDoctor());
            doctor.setSpecialization(docRecIdentity.getDoctorsByIdDoctor().getSpecializationsByIdSpecialization().getSpecializationName());
            doctor.setCabinet(docRecIdentity.getDoctorsByIdDoctor().getCabinetsByIdCabinet().getCabinetNumber());
            doctor.setPlot(docRecIdentity.getDoctorsByIdDoctor().getPlotsByIdPlot().getPlotNumber());
            doctor.setFullName(docRecIdentity.getDoctorsByIdDoctor().getLastName() + " " +
                    docRecIdentity.getDoctorsByIdDoctor().getFirstName() + " " +
                    docRecIdentity.getDoctorsByIdDoctor().getMiddleName());
            doctor.setTimetable(new ArrayList<>());
            break;
        }
        receptionResponse.setDoctor(doctor);
        // Пациент
        for(PatRecIdentity patRecIdentity : reception.getPatRecIdentitiesByIdReception()) {
            patient.setId(patRecIdentity.getPatientsByIdPatient().getIdPatient());
            patient.setFullName(patRecIdentity.getPatientsByIdPatient().getLastName() + " " +
                    patRecIdentity.getPatientsByIdPatient().getFirstName() + " " +
                    patRecIdentity.getPatientsByIdPatient().getMiddleName());
            patient.setFullAddress("г." + patRecIdentity.getPatientsByIdPatient().getCity() + ", ул." +
                    patRecIdentity.getPatientsByIdPatient().getStreet() + ", д." +
                    patRecIdentity.getPatientsByIdPatient().getBuilding() + ", кв." +
                    patRecIdentity.getPatientsByIdPatient().getApartment());
            break;
        }
        receptionResponse.setPatient(patient);
        // Медикаменты
        for(RecMedIdentity recMedIdentity : reception.getRecMedIdentitiesByIdReception()) {
            MedicationResponse medicationResponse = new MedicationResponse();
            medicationResponse.setId(recMedIdentity.getMedicationsByIdMedication().getIdMedication());
            medicationResponse.setMedication(recMedIdentity.getMedicationsByIdMedication().getMedicationName());
            medicationsList.add(medicationResponse);
        }
        receptionResponse.setMedications(medicationsList);
        // Процедуры
        for(RecProcIdentity recProcIdentity : reception.getRecProcIdentitiesByIdReception()) {
            ProceduresResponse proceduresResponse = new ProceduresResponse();
            proceduresResponse.setId(recProcIdentity.getProceduressByIdProcedure().getIdProcedure());
            proceduresResponse.setProcedure(recProcIdentity.getProceduressByIdProcedure().getProcedureName());
            proceduresResponseList.add(proceduresResponse);
        }
        receptionResponse.setProcedures(proceduresResponseList);
        // Анализы и их результаты
//        reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
//            pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(ar -> {
//                if(pat.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
//                    AnalysesResponse analysesResponse = new AnalysesResponse();
//
//                    analysesResponse.setAnalysis(ar.getAnalysesByIdAnalysis().getAnalysisName());
//                    analysesResponse.setIdAnalysis(ar.getAnalysesByIdAnalysis().getIdAnalysis());
//                    analysesResponse.setAnalysisResult(ar.getAnalysisResult());
//                    analysesResponse.setIdAnalysisResult(ar.getIdAnalysisResult());
//
//                    analysesResponseList.add(analysesResponse);
//                }
//            });
//        });
//
//        receptionResponse.setAnalyses(analysesResponseList);

        for(RecAnIdentity recAnIdentity : reception.getRecAnIdentitiesByIdReception()) {
            AnalysesResponse analysesResponse = new AnalysesResponse();

            analysesResponse.setAnalysis(recAnIdentity.getAnalysesByIdAnalysis().getAnalysisName());
            analysesResponse.setIdAnalysis(recAnIdentity.getAnalysesByIdAnalysis().getIdAnalysis());

            recAnIdentity.getAnalysesByIdAnalysis().getAnalysesResultsByIdAnalysis().forEach(ar -> {
                // Если результаты анализов являются результатами текущего анализа
                if(ar.getAnalysesByIdAnalysis().getIdAnalysis() == recAnIdentity.getAnalysesByIdAnalysis().getIdAnalysis()) {
                    // Добавление только тех результатов, которые привязаны к текущему приёму
                    reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
                        pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(arr -> {
                            if(pat.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                                analysesResult.add(ar.getAnalysisResult());
                                analysesResponse.setIdAnalysisResult(ar.getIdAnalysisResult());
                                analysesResponse.setAnalysisResult(ar.getAnalysisResult());
                            }
                        });
                    });
                }
            });
//            if(!analysesResult.isEmpty()) {
//                analysesResponse.setAnalysisResult(analysesResult.get(0));
//            } else {
//                analysesResponse.setAnalysisResult("");
//            }

            analysesResponseList.add(analysesResponse);
        }
        receptionResponse.setAnalyses(analysesResponseList);

        return new ResponseEntity<ReceptionResponse>(receptionResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ReceptionResponse> addReception(@RequestBody ReceptionsShortResponse insertReception) {
        var reception = Receptions.builder().build();
        DoctorsResponse doctorResponse = new DoctorsResponse();
        PatientsResponse patientResponse = new PatientsResponse();
        ReceptionResponse receptionResponse = new ReceptionResponse();

        Doctors doctor = doctorsRepository.findById(Long.valueOf(insertReception.getIdDoctor())).get();
        Patients patient = patientsRepository.findById(Long.valueOf(insertReception.getIdPatient())).get();

        // 12-01-2023 -> 2023-01-12
        String formatDate = insertReception.getDateOfReception().split("-")[2] + "-" +
                insertReception.getDateOfReception().split("-")[1] + "-" +
                insertReception.getDateOfReception().split("-")[0];

        // Проверка на существование записи на это время в эту дату
        Date test = Date.valueOf(formatDate);
        receptionsRepository.findByDateOfReception(Date.valueOf(formatDate)).get().forEach(rec -> {
            rec.getDocRecIdentitiesByIdReception().forEach(doc -> {
                if(rec.getTimeOfReception()
                        .equals(LocalTime.parse(insertReception.getTimeOfReception())) && doc.getDoctorsByIdDoctor().equals(doctor)) {
                    throw new UsernameNotFoundException("В это время уже есть запись");
                }
            });
        });

        reception.setDateOfReception(Date.valueOf(formatDate));
        reception.setTimeOfReception(LocalTime.parse(insertReception.getTimeOfReception()));
        reception.setDone(false);

        List<DocRecIdentity> docRecIdentityList = new ArrayList<>();
        var docRecIdentity = DocRecIdentity.builder()
                .doctorsByIdDoctor(doctor)
                .receptionsByIdReception(reception)
                .build();
        docRecIdentityList.add(docRecIdentity);

        reception.setDocRecIdentitiesByIdReception(docRecIdentityList);


        List<PatRecIdentity> patRecIdentityList = new ArrayList<>();
        var patRecIdentity = PatRecIdentity.builder()
                .patientsByIdPatient(patient)
                .receptionsByIdReception(reception)
                .build();
        patRecIdentityList.add(patRecIdentity);

        reception.setPatRecIdentitiesByIdReception(patRecIdentityList);



        receptionsRepository.save(reception);

        receptionResponse.setTimeOfReception(reception.getTimeOfReception().toString());
        receptionResponse.setDateOfReception(String.valueOf(reception.getDateOfReception()));

        doctorResponse.setId(doctor.getIdDoctor());
        doctorResponse.setFullName(doctor.getLastName() + " " + doctor.getFirstName() + " " + doctor.getMiddleName());
        doctorResponse.setSpecialization(doctor.getSpecializationsByIdSpecialization().getSpecializationName());

        patientResponse.setFullName(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getMiddleName());
        patientResponse.setFullAddress("г." + patient.getCity() + ", ул." + patient.getStreet() + ", д." + patient.getBuilding() + ", кв." + patient.getApartment());

        receptionResponse.setDoctor(doctorResponse);
        receptionResponse.setPatient(patientResponse);

        return ResponseEntity.ok(receptionResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReceptionResponse> updateReception(@PathVariable int id, @RequestBody ReceptionsShortResponse newReception) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Приём не найден"));
        DoctorsResponse doctorResponse = new DoctorsResponse();
        PatientsResponse patientResponse = new PatientsResponse();
        ReceptionResponse receptionResponse = new ReceptionResponse();

        // 12-01-2023 -> 2023-01-12
        String formatDate = newReception.getDateOfReception().split("-")[2] + "-" +
                newReception.getDateOfReception().split("-")[1] + "-" +
                newReception.getDateOfReception().split("-")[0];

        Doctors doctor = doctorsRepository.findById(Long.valueOf(newReception.getIdDoctor())).get();
        Patients patient = patientsRepository.findById(Long.valueOf(newReception.getIdPatient())).get();

        // Проверка на существование записи на это время в эту дату
        receptionsRepository.findByDateOfReception(Date.valueOf(formatDate)).get().forEach(rec -> {
            rec.getDocRecIdentitiesByIdReception().forEach(doc -> {
                if(rec.getTimeOfReception()
                        .equals(LocalTime.parse(newReception.getTimeOfReception())) && doc.getDoctorsByIdDoctor().equals(doctor)) {
                    throw new UsernameNotFoundException("В это время уже есть запись");
                }
            });
        });

        reception.setDateOfReception(Date.valueOf(formatDate));
        reception.setTimeOfReception(LocalTime.parse(newReception.getTimeOfReception()));


        reception.getDocRecIdentitiesByIdReception().forEach(doc -> {
            if(doc.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                doc.getDoctorsByIdDoctor().setDocRecIdentitiesByIdDoctor(new ArrayList<>());
                doc.setDoctorsByIdDoctor(doctor);
            }
        });

        reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
            if(pat.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                pat.getPatientsByIdPatient().setPatRecIdentitiesByIdPatient(new ArrayList<>());
                pat.setPatientsByIdPatient(patient);
            }
        });

        receptionsRepository.save(reception);

        receptionResponse.setTimeOfReception(reception.getTimeOfReception().toString());
        receptionResponse.setDateOfReception(String.valueOf(reception.getDateOfReception()));

        doctorResponse.setId(doctor.getIdDoctor());
        doctorResponse.setFullName(doctor.getLastName() + " " + doctor.getFirstName() + " " + doctor.getMiddleName());
        doctorResponse.setSpecialization(doctor.getSpecializationsByIdSpecialization().getSpecializationName());

        patientResponse.setFullName(patient.getLastName() + " " + patient.getFirstName() + " " + patient.getMiddleName());
        patientResponse.setFullAddress("г." + patient.getCity() + ", ул." + patient.getStreet() + ", д." + patient.getBuilding() + ", кв." + patient.getApartment());

        receptionResponse.setDoctor(doctorResponse);
        receptionResponse.setPatient(patientResponse);

        return ResponseEntity.ok(receptionResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteReception(@PathVariable int id) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get(); // Поиск приёма по id

        reception.getDocRecIdentitiesByIdReception().forEach(doc -> {
            doc.getDoctorsByIdDoctor().setDocRecIdentitiesByIdDoctor(new ArrayList<>());
            doctorsRepository.save(doc.getDoctorsByIdDoctor());
        });

        reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
            pat.getPatientsByIdPatient().setPatRecIdentitiesByIdPatient(new ArrayList<>());
            patientsRepository.save(pat.getPatientsByIdPatient());
        });

        reception.getRecAnIdentitiesByIdReception().forEach(an -> {
            an.getAnalysesByIdAnalysis().setRecAnIdentitiesByIdAnalysis(new ArrayList<>());
            analysesRepository.save(an.getAnalysesByIdAnalysis());
        });

        reception.getRecMedIdentitiesByIdReception().forEach(med -> {
            med.getMedicationsByIdMedication().setRecMedIdentitiesByIdMedication(new ArrayList<>());
            medicationsRepository.save(med.getMedicationsByIdMedication());
        });

        reception.getRecProcIdentitiesByIdReception().forEach(proc -> {
            proc.getProceduressByIdProcedure().setRecProcIdentitiesByIdProcedure(new ArrayList<>());
            proceduresRepository.save(proc.getProceduressByIdProcedure());
        });


        receptionsRepository.delete(reception);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}/dateOfExtract")
    public ResponseEntity<HttpStatus> updateDateOfExtract(@PathVariable int id, @RequestBody ReceptionResponse dateOfExtract) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.setDateOfExtract(
                Date.valueOf(
                        dateOfExtract.getDateOfExtract()
                )
        );

        reception.setDone(true);

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}/complaints")
    public ResponseEntity<HttpStatus> updateComplaints(@PathVariable int id, @RequestBody ReceptionResponse complaints) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.setComplaints(complaints.getComplaints());

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}/diagnosis")
    public ResponseEntity<HttpStatus> updateDiagnosis(@PathVariable int id, @RequestBody ReceptionResponse diagnosis) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.setDiagnosis(diagnosis.getDiagnosis());

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add/{id}/procedure")
    public ResponseEntity<HttpStatus> addProcedure(@PathVariable int id, @RequestBody ProceduresResponse insertProcedure) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        var procedures = Proceduress.builder().build();
        try {
            proceduresRepository.findByProcedureName(insertProcedure.getProcedure()).get();
        } catch (NoSuchElementException error) {
            procedures = Proceduress.builder()
                    .procedureName(insertProcedure.getProcedure())
                    .build();

            proceduresRepository.save(procedures);
        }
        procedures = proceduresRepository.findByProcedureName(insertProcedure.getProcedure()).get();

        var recProcIdentity = RecProcIdentity.builder().build();
        List<RecProcIdentity> recProcIdentityList = new ArrayList<>();

        recProcIdentity.setReceptionsByIdReception(reception);
        recProcIdentity.setProceduressByIdProcedure(procedures);

        recProcIdentityList.add(recProcIdentity);

        reception.setRecProcIdentitiesByIdReception(recProcIdentityList);


        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}/procedure")
    public ResponseEntity<HttpStatus> updateProcedure(@PathVariable int id, @RequestBody ProceduresResponse updateProcedure) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.getRecProcIdentitiesByIdReception().forEach(proc -> {
            if(proc.getProceduressByIdProcedure().getIdProcedure() == updateProcedure.getId()) {
                proc.getProceduressByIdProcedure().setProcedureName(updateProcedure.getProcedure());
            }
        });

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idReception}/procedure/{idProcedure}")
    public ResponseEntity<HttpStatus> deleteProcedure(@PathVariable int idReception, @PathVariable int idProcedure) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(idReception)).get(); // Поиск приёма по id
        Proceduress procedure = proceduresRepository.findById(Long.valueOf(idProcedure)).get(); // Поиск процедуры по id

        procedure.getRecProcIdentitiesByIdProcedure().forEach(proc -> {
            if(proc.getProceduressByIdProcedure().getIdProcedure() == procedure.getIdProcedure() &&
                    proc.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                proc.getProceduressByIdProcedure().setRecProcIdentitiesByIdProcedure(new ArrayList<>());
                proceduresRepository.save(proc.getProceduressByIdProcedure());
                recProcIdentityRepository.delete(proc);
            }
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add/{id}/medication")
    public ResponseEntity<HttpStatus> addMedication(@PathVariable int id, @RequestBody MedicationResponse insertMedication) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        var medication = Medications.builder().build();
        try {
            medicationsRepository.findByMedicationName(insertMedication.getMedication()).get();
        } catch (NoSuchElementException error) {
            medication = Medications.builder()
                    .medicationName(insertMedication.getMedication())
                    .build();

            medicationsRepository.save(medication);
        }
        medication = medicationsRepository.findByMedicationName(insertMedication.getMedication()).get();

        var recMedIdentity = RecMedIdentity.builder().build();
        List<RecMedIdentity> recMedIdentityList = new ArrayList<>();

        recMedIdentity.setReceptionsByIdReception(reception);
        recMedIdentity.setMedicationsByIdMedication(medication);

        recMedIdentityList.add(recMedIdentity);

        reception.setRecMedIdentitiesByIdReception(recMedIdentityList);


        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}/medication")
    public ResponseEntity<HttpStatus> updateMedication(@PathVariable int id, @RequestBody MedicationResponse updateMedication) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.getRecMedIdentitiesByIdReception().forEach(med -> {
            if(med.getMedicationsByIdMedication().getIdMedication() == updateMedication.getId()) {
                med.getMedicationsByIdMedication().setMedicationName(updateMedication.getMedication());
            }
        });

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idReception}/medication/{idMedication}")
    public ResponseEntity<HttpStatus> deleteMedication(@PathVariable int idReception, @PathVariable int idMedication) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(idReception)).get();
        Medications medication = medicationsRepository.findById(Long.valueOf(idMedication)).get(); // Поиск медикамента по id

        medication.getRecMedIdentitiesByIdMedication().forEach(med -> {
            if(med.getMedicationsByIdMedication().getIdMedication() == medication.getIdMedication() &&
                med.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                med.getMedicationsByIdMedication().setRecMedIdentitiesByIdMedication(new ArrayList<>());
                medicationsRepository.save(med.getMedicationsByIdMedication());
                recMedIdentityRepository.delete(med);
            }
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add/{id}/analysis")
    public ResponseEntity<HttpStatus> addAnalysis(@PathVariable int id, @RequestBody AnalysesResponse insertAnalysis) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        var analysis = Analyses.builder().build();
        try {
            analysesRepository.findByAnalysisName(insertAnalysis.getAnalysis()).get();
        } catch (NoSuchElementException error) {
            analysis = Analyses.builder()
                    .analysisName(insertAnalysis.getAnalysis())
                    .build();

            analysesRepository.save(analysis);
        }
        analysis = analysesRepository.findByAnalysisName(insertAnalysis.getAnalysis()).get();

        var recAnIdentity = RecAnIdentity.builder().build();
        List<RecAnIdentity> recAnIdentityList = new ArrayList<>();

        recAnIdentity.setReceptionsByIdReception(reception);
        recAnIdentity.setAnalysesByIdAnalysis(analysis);

        recAnIdentityList.add(recAnIdentity);

        reception.setRecAnIdentitiesByIdReception(recAnIdentityList);


        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}/analysis")
    public ResponseEntity<HttpStatus> updateAnalysis(@PathVariable int id, @RequestBody AnalysesResponse updateAnalysis) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(id)).get();

        reception.getRecAnIdentitiesByIdReception().forEach(an -> {
            if(an.getAnalysesByIdAnalysis().getIdAnalysis() == updateAnalysis.getIdAnalysis()) {
                an.getAnalysesByIdAnalysis().setAnalysisName(updateAnalysis.getAnalysis());
            }
        });

        receptionsRepository.save(reception);


        Analyses analysis = analysesRepository.findByAnalysisName(updateAnalysis.getAnalysis()).get();

        reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
            boolean isNewAnalysis = analysesResultsRepository.findByAnalysesByIdAnalysis(analysis).isEmpty();
            // Если вставка резултата происходит в первый раз
            if(pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().isEmpty()) {
                pat.getPatientsByIdPatient().setAnalysesResultsByIdPatient(new ArrayList<>());
                var analysisResult = AnalysesResults.builder()
                        .analysisResult(updateAnalysis.getAnalysisResult())
                        .analysesByIdAnalysis(analysis)
                        .patientsByIdPatient(pat.getPatientsByIdPatient())
                        .build();
                List<AnalysesResults> analysesResultsList = new ArrayList<>();
                analysesResultsList.add(analysisResult);

                pat.getPatientsByIdPatient().setAnalysesResultsByIdPatient(analysesResultsList);
            } // Если в таблице analyses_results уже есть какие-то записи и в ней нет записи о текущем анализе
            else if(isNewAnalysis) {
                var analysisResult = AnalysesResults.builder()
                        .analysisResult(updateAnalysis.getAnalysisResult())
                        .analysesByIdAnalysis(analysis)
                        .patientsByIdPatient(pat.getPatientsByIdPatient())
                        .build();

                pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().add(analysisResult);
            } // Обновление существующего результата анализа
            else {
                pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(ar -> {
                    if(!isNewAnalysis && ar.getAnalysesByIdAnalysis().equals(analysis)) {
                        ar.setAnalysisResult(updateAnalysis.getAnalysisResult());
                    }
                });
            }
        });

        receptionsRepository.save(reception);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idReception}/analysis/{idAnalysis}")
    public ResponseEntity<HttpStatus> deleteAnalysis(@PathVariable int idReception, @PathVariable int idAnalysis) {
        Receptions reception = receptionsRepository.findById(Long.valueOf(idReception)).get();
        Analyses analysis = analysesRepository.findById(Long.valueOf(idAnalysis)).get();

//        // Удаление результатов анализа для пациента
//        for(RecAnIdentity recAnIdentity : reception.getRecAnIdentitiesByIdReception()) {
//            recAnIdentity.getAnalysesByIdAnalysis().getAnalysesResultsByIdAnalysis().forEach(ar -> {
//                // Если результаты анализов являются результатами текущего анализа
//                if (ar.getAnalysesByIdAnalysis().getIdAnalysis() == recAnIdentity.getAnalysesByIdAnalysis().getIdAnalysis()) {
//                    // Добавление только тех результатов, которые привязаны к текущему приёму
//                    reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
//                        pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(arr -> {
//                            if (pat.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
//                                pat.getPatientsByIdPatient().setAnalysesResultsByIdPatient(new ArrayList<>());
//                                patientsRepository.save(pat.getPatientsByIdPatient());
//
//                                ar.getAnalysesByIdAnalysis().setAnalysesResultsByIdAnalysis(new ArrayList<>());
//                                analysesRepository.save(ar.getAnalysesByIdAnalysis());
//
//                                analysesResultsRepository.delete(arr);
//                            }
//                        });
//                    });
//                }
//            });
//        }

        // Удаление привязки анализа из таблицы-справочника к приёму
        analysis.getRecAnIdentitiesByIdAnalysis().forEach(an -> {
            if(an.getAnalysesByIdAnalysis().getIdAnalysis() == analysis.getIdAnalysis() &&
                an.getReceptionsByIdReception().getIdReception() == reception.getIdReception()) {
                an.getAnalysesByIdAnalysis().setRecAnIdentitiesByIdAnalysis(new ArrayList<>());
                analysesRepository.save(an.getAnalysesByIdAnalysis());
                recAnIdentityRepository.delete(an);
            }
        });

//        reception.getPatRecIdentitiesByIdReception().forEach(pat -> {
//            pat.getPatientsByIdPatient().getAnalysesResultsByIdPatient().forEach(ar -> {
//                if() {
//
//                }
//            });
//        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
