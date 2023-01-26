package ru.policlinic.server.controller;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import ru.policlinic.server.dto.*;
import ru.policlinic.server.exception.ResourceNotFoundException;
import ru.policlinic.server.model.*;
import ru.policlinic.server.repository.*;

@CrossOrigin("*")//origins = {"http://localhost:3000/"}) // Для доступности порта 8080 (сервер) для 3000 (клиент), либо всем ("*")
@RestController
@RequestMapping("/api/doctors")
@Api(value = "doctors")
public class DoctorsController {
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private SpecializationsRepository specializationsRepository;

    @Autowired
    private PlotsRepository plotsRepository;

    @Autowired
    private CabinetsRepository cabinetsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DaysOfWeekRepository daysOfWeekRepository;

    @Autowired
    private TimeOfJobRepository timeOfJobRepository;

    @Autowired
    private DwDocIdentityRepository dwDocIdentityRepository;

    @GetMapping
    @ApiOperation(value = "getAllDoctors", response = Doctors.class)
    public ResponseEntity<List<DoctorsResponse>> getAllDoctors() {
        List<Doctors> listDoc = doctorsRepository.findAll(); // Поиск врачей из базы

        List<DoctorsResponse> doctorsResponseList = new ArrayList<>(); // Полное описание врача со всеми связями
        listDoc.forEach(doc -> {
            DoctorsResponse doctorsResponse = new DoctorsResponse();
            List<DwDocIdentityResponse> dwDocIdentityResponseList = new ArrayList<>();

            // Установка в Response существующих полей:
            doctorsResponse.setId(doc.getIdDoctor());
            doctorsResponse.setFullName(doc.getLastName() + " " + doc.getFirstName() + " " + doc.getMiddleName());

            // Установка в Response зависимых полей:
            // Расписание
            for(DwDocIdentity dwDocIdentity : doc.getDwDocIdentitiesByIdDoctor()) {
                DwDocIdentityResponse dwDocIdentityResponse = new DwDocIdentityResponse();

                dwDocIdentityResponse.setDayOfWeek(dwDocIdentity.getDaysOfWeekByIdDayOfWeek().getDayOfWeek());
                dwDocIdentityResponse.setTimeBegin(dwDocIdentity.getTimesOfJobByIdBegin().getTimeValue().toString());
                dwDocIdentityResponse.setTimeEnd(dwDocIdentity.getTimesOfJobByIdEnd().getTimeValue().toString());

                dwDocIdentityResponseList.add(dwDocIdentityResponse);
            }
            doctorsResponse.setTimetable(dwDocIdentityResponseList);

            // Специальность
            doctorsResponse.setSpecialization(doc.getSpecializationsByIdSpecialization().getSpecializationName());

            // Участок
            doctorsResponse.setPlot(doc.getPlotsByIdPlot().getPlotNumber());

            // Кабинет
            doctorsResponse.setCabinet(doc.getCabinetsByIdCabinet().getCabinetNumber());

            doctorsResponseList.add(doctorsResponse);
        });

        return new ResponseEntity<List<DoctorsResponse>>(doctorsResponseList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DoctorsResponse> addDoctor(@RequestBody DoctorResponseWithoutTimetable insertDoctor) {
        var doctor = Doctors.builder().build();

//        List<Doctors> listDoc = new ArrayList<>();
//        listDoc.add(doctor);

        var specializations = Specializations.builder().build();
        try {
            specializations = specializationsRepository.findBySpecializationName(insertDoctor.getSpecialization()).get();
        } catch (NoSuchElementException error) {
            specializations = Specializations.builder()
                    .specializationName(insertDoctor.getSpecialization())
                    .build();

            specializationsRepository.save(specializations);
        }

        var plot = Plots.builder().build();
        try {
            plot = plotsRepository.findByPlotNumber(insertDoctor.getPlot()).get();
        } catch (NoSuchElementException error) {
            plot = Plots.builder()
                    .plotNumber(insertDoctor.getPlot())
                    .build();

            plotsRepository.save(plot);
        }

        var cabinet = Cabinets.builder().build();
        try {
            cabinet = cabinetsRepository.findByCabinetNumber(insertDoctor.getCabinet()).get();
        } catch (NoSuchElementException error) {
            cabinet = Cabinets.builder()
                    .cabinetNumber(insertDoctor.getCabinet())
                    .build();

            cabinetsRepository.save(cabinet);
        }

        cabinet = cabinetsRepository.findByCabinetNumber(insertDoctor.getCabinet()).get();
        plot = plotsRepository.findByPlotNumber(insertDoctor.getPlot()).get();
        specializations = specializationsRepository.findBySpecializationName(insertDoctor.getSpecialization()).get();

//        specializations.setDoctorsByIdSpecialization(listDoc);
//        cabinet.setDoctorsByIdCabinet(listDoc);
//        plot.setDoctorsByIdPlot(listDoc);

//        doctor.setSpecializationsByIdSpecialization(new Specializations());
//        doctor.setCabinetsByIdCabinet(new Cabinets());
//        doctor.setPlotsByIdPlot(new Plots());
//
//        doctor.getSpecializationsByIdSpecialization().setSpecializationName(specializations.getSpecializationName());
//        doctor.getSpecializationsByIdSpecialization().setIdSpecialization(specializations.getIdSpecialization());
//        doctor.getSpecializationsByIdSpecialization().setDoctorsByIdSpecialization(specializations.getDoctorsByIdSpecialization());
//
//        doctor.getCabinetsByIdCabinet().setDoctorsByIdCabinet(cabinet.getDoctorsByIdCabinet());
//        doctor.getCabinetsByIdCabinet().setIdCabinet(cabinet.getIdCabinet());
//        doctor.getCabinetsByIdCabinet().setCabinetNumber(cabinet.getCabinetNumber());
//
//        doctor.getPlotsByIdPlot().setDoctorsByIdPlot(plot.getDoctorsByIdPlot());
//        doctor.getPlotsByIdPlot().setIdPlot(plot.getIdPlot());
//        doctor.getPlotsByIdPlot().setPlotNumber(plot.getPlotNumber());

        if(insertDoctor.getMiddlename().equals("")) {
            doctor = Doctors.builder()
                    .lastName(insertDoctor.getLastname())
                    .firstName(insertDoctor.getFirstname())
                    .specializationsByIdSpecialization(specializations)
                    .cabinetsByIdCabinet(cabinet)
                    .plotsByIdPlot(plot)
                    .build();
        } else {
            doctor = Doctors.builder()
                    .lastName(insertDoctor.getLastname())
                    .firstName(insertDoctor.getFirstname())
                    .middleName(insertDoctor.getMiddlename())
                    .specializationsByIdSpecialization(specializations)
                    .cabinetsByIdCabinet(cabinet)
                    .plotsByIdPlot(plot)
                    .build();
        }

        doctorsRepository.save(doctor);

        DoctorsResponse doctorsResponse = new DoctorsResponse();
        doctorsResponse.setSpecialization(doctor.getSpecializationsByIdSpecialization().getSpecializationName());
        doctorsResponse.setPlot(doctor.getPlotsByIdPlot().getPlotNumber());
        doctorsResponse.setCabinet(doctor.getCabinetsByIdCabinet().getCabinetNumber());
        doctorsResponse.setFullName(doctor.getLastName() + " " + doctor.getFirstName() + " " + doctor.getMiddleName());

        return ResponseEntity.ok(doctorsResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorsResponse> updateDoctor(@PathVariable int id, @RequestBody DoctorResponseWithoutTimetable newDoctor) {
        Doctors doctor = doctorsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Врач не найден"));

        var specializations = Specializations.builder().build();
        try {
            specializations = specializationsRepository.findBySpecializationName(newDoctor.getSpecialization()).get();
        } catch (NoSuchElementException error) {
            specializations = Specializations.builder()
                    .specializationName(newDoctor.getSpecialization())
                    .build();

            specializationsRepository.save(specializations);
        }

        var plot = Plots.builder().build();
        try {
            plot = plotsRepository.findByPlotNumber(newDoctor.getPlot()).get();
        } catch (NoSuchElementException error) {
            plot = Plots.builder()
                    .plotNumber(newDoctor.getPlot())
                    .build();

            plotsRepository.save(plot);
        }

        var cabinet = Cabinets.builder().build();
        try {
            cabinet = cabinetsRepository.findByCabinetNumber(newDoctor.getCabinet()).get();
        } catch (NoSuchElementException error) {
            cabinet = Cabinets.builder()
                    .cabinetNumber(newDoctor.getCabinet())
                    .build();

            cabinetsRepository.save(cabinet);
        }

        cabinet = cabinetsRepository.findByCabinetNumber(newDoctor.getCabinet()).get();
        plot = plotsRepository.findByPlotNumber(newDoctor.getPlot()).get();
        specializations = specializationsRepository.findBySpecializationName(newDoctor.getSpecialization()).get();


        doctor.setFirstName(newDoctor.getFirstname());
        doctor.setLastName(newDoctor.getLastname());
        doctor.setMiddleName(newDoctor.getMiddlename());

        doctor.setSpecializationsByIdSpecialization(specializations);
        doctor.setPlotsByIdPlot(plot);
        doctor.setCabinetsByIdCabinet(cabinet);

        doctorsRepository.save(doctor);

        DoctorsResponse doctorsResponse = new DoctorsResponse();
        doctorsResponse.setSpecialization(doctor.getSpecializationsByIdSpecialization().getSpecializationName());
        doctorsResponse.setPlot(doctor.getPlotsByIdPlot().getPlotNumber());
        doctorsResponse.setCabinet(doctor.getCabinetsByIdCabinet().getCabinetNumber());
        doctorsResponse.setFullName(doctor.getLastName() + " " + doctor.getFirstName() + " " + doctor.getMiddleName());

        return ResponseEntity.ok(doctorsResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<DoctorsResponse> getDoctorById(@PathVariable int id) {
        Doctors doc = doctorsRepository.findById(Long.valueOf(id)).get(); // Поиск врача по id

        DoctorsResponse doctorsResponse = new DoctorsResponse();
        List<DwDocIdentityResponse> dwDocIdentityResponseList = new ArrayList<>();

        // Установка в Response существующих полей:
        doctorsResponse.setId(doc.getIdDoctor());
        doctorsResponse.setFullName(doc.getLastName() + " " + doc.getFirstName() + " " + doc.getMiddleName());

        // Установка в Response зависимых полей:
        // Расписание
        for(DwDocIdentity dwDocIdentity : doc.getDwDocIdentitiesByIdDoctor()) {
            DwDocIdentityResponse dwDocIdentityResponse = new DwDocIdentityResponse();

            dwDocIdentityResponse.setDayOfWeek(dwDocIdentity.getDaysOfWeekByIdDayOfWeek().getDayOfWeek());
            dwDocIdentityResponse.setTimeBegin(dwDocIdentity.getTimesOfJobByIdBegin().getTimeValue().toString());
            dwDocIdentityResponse.setTimeEnd(dwDocIdentity.getTimesOfJobByIdEnd().getTimeValue().toString());

            dwDocIdentityResponseList.add(dwDocIdentityResponse);
        }
        doctorsResponse.setTimetable(dwDocIdentityResponseList);

        // Специальность
        doctorsResponse.setSpecialization(doc.getSpecializationsByIdSpecialization().getSpecializationName());

        // Участок
        doctorsResponse.setPlot(doc.getPlotsByIdPlot().getPlotNumber());

        // Кабинет
        doctorsResponse.setCabinet(doc.getCabinetsByIdCabinet().getCabinetNumber());

        return new ResponseEntity<DoctorsResponse>(doctorsResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable int id) {
        Doctors doctor = doctorsRepository.findById(Long.valueOf(id)).get(); // Поиск врача по id

        // При удалении в поле запишется информация о враче, который больше не существует в базе
        doctor.getDocRecIdentitiesByIdDoctor().forEach(use -> {
            use.getReceptionsByIdReception().setDoctorDetails(use.getDoctorsByIdDoctor().getLastName() + " " +
                    use.getDoctorsByIdDoctor().getFirstName() + " " +
                    use.getDoctorsByIdDoctor().getMiddleName() + " - " +
                    use.getDoctorsByIdDoctor().getSpecializationsByIdSpecialization().getSpecializationName());
        });

        if(doctor.getUsersByIdUser() != null) {
            Users user = usersRepository.findByUsername(doctor.getUsersByIdUser().getUsername()).get();
            usersRepository.delete(user);
        }

        doctorsRepository.delete(doctor);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Расписание
    @PostMapping("/add/timetable/{id}")
    public ResponseEntity<Doctors> addTimetableToDoctor(@PathVariable int id, @RequestBody TimetableResponse insertTimetable) {
        Doctors doctor = doctorsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Врач не найден"));

        // Вставка в базу нового введённого времени, если таковое было отправлено с клиента
        checkTimesOfJob(insertTimetable);

        // Список дней недели
        List<String> daysOfWeekListString = new ArrayList<>();

        // Присвоение толко отправленных переменных
        var timeOfJobMondayBegin = TimesOfJob.builder().build();
        var timeOfJobMondayEnd = TimesOfJob.builder().build();

        var timeOfJobTuesdayBegin = TimesOfJob.builder().build();
        var timeOfJobTuesdayEnd = TimesOfJob.builder().build();

        var timeOfJobWednesdayBegin = TimesOfJob.builder().build();
        var timeOfJobWednesdayEnd = TimesOfJob.builder().build();

        var timeOfJobThursdayBegin = TimesOfJob.builder().build();
        var timeOfJobThursdayEnd = TimesOfJob.builder().build();

        var timeOfJobFridayBegin = TimesOfJob.builder().build();
        var timeOfJobFridayEnd = TimesOfJob.builder().build();

        var timeOfJobSaturdayBegin = TimesOfJob.builder().build();
        var timeOfJobSaturdayEnd = TimesOfJob.builder().build();

        if(insertTimetable.getMonday() != null && !insertTimetable.getMonday().equals("")) {
            timeOfJobMondayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[0])).get();
            timeOfJobMondayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobMondayBegin.getTimeValue().toString() + "-" + timeOfJobMondayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Понедельник");
        }

        if(insertTimetable.getTuesday() != null && !insertTimetable.getTuesday().equals("")) {
            timeOfJobTuesdayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[0])).get();
            timeOfJobTuesdayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobTuesdayBegin.getTimeValue().toString() + "-" + timeOfJobTuesdayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Вторник");
        }

        if(insertTimetable.getWednesday() != null && !insertTimetable.getWednesday().equals("")) {
            timeOfJobWednesdayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[0])).get();
            timeOfJobWednesdayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobWednesdayBegin.getTimeValue().toString() + "-" + timeOfJobWednesdayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Среда");
        }

        if(insertTimetable.getThursday() != null && !insertTimetable.getThursday().equals("")) {
            timeOfJobThursdayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[0])).get();
            timeOfJobThursdayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobThursdayBegin.getTimeValue().toString() + "-" + timeOfJobThursdayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Четверг");
        }

        if(insertTimetable.getFriday() != null && !insertTimetable.getFriday().equals("")) {
            timeOfJobFridayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[0])).get();
            timeOfJobFridayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobFridayBegin.getTimeValue().toString() + "-" + timeOfJobFridayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Пятница");
        }

        if(insertTimetable.getSaturday() != null && !insertTimetable.getSaturday().equals("")) {
            timeOfJobSaturdayBegin = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[0])).get();
            timeOfJobSaturdayEnd = timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[1])).get();

//            timesOfJobListString.add(timeOfJobSaturdayBegin.getTimeValue().toString() + "-" + timeOfJobSaturdayEnd.getTimeValue().toString());
            daysOfWeekListString.add("Суббота");
        }

        // Алгоритм связывания всех таблиц для успешной вставки
        var dayOfWeek = DaysOfWeek.builder().build();

        var timesOfJobBegin = TimesOfJob.builder().build();
        var timesOfJobEnd = TimesOfJob.builder().build();

        var dwDocIdentity = DwDocIdentity.builder().build();
        List<DwDocIdentity> dwDocIdentityList = new ArrayList<>();

        for(int i = 0; i < daysOfWeekListString.size(); i++) {
            dwDocIdentity = DwDocIdentity.builder().build();

            timesOfJobBegin = TimesOfJob.builder().build();
            timesOfJobEnd = TimesOfJob.builder().build();

            try {
                dayOfWeek = daysOfWeekRepository.findByDayOfWeek(daysOfWeekListString.get(i)).get();
            } catch (NoSuchElementException error) {
                dayOfWeek = DaysOfWeek.builder()
                        .dayOfWeek(daysOfWeekListString.get(i))
                        .build();
                daysOfWeekRepository.save(dayOfWeek);
                dayOfWeek = daysOfWeekRepository.findByDayOfWeek(daysOfWeekListString.get(i)).get();
            }

            switch (dayOfWeek.getDayOfWeek()) {
                case "Понедельник":
                    timesOfJobBegin = timeOfJobMondayBegin;
                    timesOfJobEnd = timeOfJobMondayEnd;
                    break;
                case "Вторник":
                    timesOfJobBegin = timeOfJobTuesdayBegin;
                    timesOfJobEnd = timeOfJobTuesdayEnd;
                    break;
                case "Среда":
                    timesOfJobBegin = timeOfJobWednesdayBegin;
                    timesOfJobEnd = timeOfJobWednesdayEnd;
                    break;
                case "Четверг":
                    timesOfJobBegin = timeOfJobThursdayBegin;
                    timesOfJobEnd = timeOfJobThursdayEnd;
                    break;
                case "Пятница":
                    timesOfJobBegin = timeOfJobFridayBegin;
                    timesOfJobEnd = timeOfJobFridayEnd;
                    break;
                case "Суббота":
                    timesOfJobBegin = timeOfJobSaturdayBegin;
                    timesOfJobEnd = timeOfJobSaturdayEnd;
                    break;
            }


            dwDocIdentity.setDoctorsByIdDoctor(doctor);
            dwDocIdentity.setDaysOfWeekByIdDayOfWeek(dayOfWeek);
            dwDocIdentity.setTimesOfJobByIdBegin(timesOfJobBegin);
            dwDocIdentity.setTimesOfJobByIdEnd(timesOfJobEnd);

            dwDocIdentityList.add(dwDocIdentity);
        }

        doctor.setDwDocIdentitiesByIdDoctor(dwDocIdentityList);
        doctorsRepository.save(doctor);

        return ResponseEntity.ok(doctor);
    }
    private void checkTimesOfJob(TimetableResponse insertTimetable) {
        var timeOfJobMondayBegin = TimesOfJob.builder().build();
        var timeOfJobMondayEnd = TimesOfJob.builder().build();

        var timeOfJobTuesdayBegin = TimesOfJob.builder().build();
        var timeOfJobTuesdayEnd = TimesOfJob.builder().build();

        var timeOfJobWednesdayBegin = TimesOfJob.builder().build();
        var timeOfJobWednesdayEnd = TimesOfJob.builder().build();

        var timeOfJobThursdayBegin = TimesOfJob.builder().build();
        var timeOfJobThursdayEnd = TimesOfJob.builder().build();

        var timeOfJobFridayBegin = TimesOfJob.builder().build();
        var timeOfJobFridayEnd = TimesOfJob.builder().build();

        var timeOfJobSaturdayBegin = TimesOfJob.builder().build();
        var timeOfJobSaturdayEnd = TimesOfJob.builder().build();

        if(insertTimetable.getMonday() != null && !insertTimetable.getMonday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobMondayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobMondayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobMondayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getMonday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobMondayEnd);
            }
        }

        if(insertTimetable.getTuesday() != null && !insertTimetable.getTuesday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobTuesdayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobTuesdayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobTuesdayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getTuesday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobTuesdayEnd);
            }
        }

        if(insertTimetable.getWednesday() != null && !insertTimetable.getWednesday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobWednesdayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobWednesdayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobWednesdayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getWednesday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobWednesdayEnd);
            }
        }

        if(insertTimetable.getThursday() != null && !insertTimetable.getThursday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobThursdayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobThursdayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobThursdayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getThursday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobThursdayEnd);
            }
        }

        if(insertTimetable.getFriday() != null && !insertTimetable.getFriday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobFridayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobFridayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobFridayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getFriday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobFridayEnd);
            }
        }

        if(insertTimetable.getSaturday() != null && !insertTimetable.getSaturday().equals("")) {
            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[0])).get();
            } catch (NoSuchElementException error) {
                timeOfJobSaturdayBegin = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[0]))
                        .build();
                timeOfJobRepository.save(timeOfJobSaturdayBegin);
            }

            try {
                timeOfJobRepository.findByTimeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[1])).get();
            } catch (NoSuchElementException error) {
                timeOfJobSaturdayEnd = TimesOfJob.builder()
                        .timeValue(LocalTime.parse(insertTimetable.getSaturday().split("-")[1]))
                        .build();
                timeOfJobRepository.save(timeOfJobSaturdayEnd);
            }
        }
    }

    @PutMapping("/update/timetable/{id}")
    public ResponseEntity<Doctors> updateTimetableOfDoctor(@PathVariable int id, @RequestBody TimetableResponse updateTimetable) {
        Doctors doctor = doctorsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResourceNotFoundException("Врач не найден"));

        deleteTimetableOfDoctor(id);
        addTimetableToDoctor(id, updateTimetable);

        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/timetable/{id}")
    public ResponseEntity<HttpStatus> deleteTimetableOfDoctor(@PathVariable int id) {
        Doctors doctor = doctorsRepository.findById(Long.valueOf(id)).get(); // Поиск врача по id

        doctor.getDwDocIdentitiesByIdDoctor().forEach(ident -> {
            ident.getDoctorsByIdDoctor().setDwDocIdentitiesByIdDoctor(new ArrayList<>());
            ident.getDaysOfWeekByIdDayOfWeek().setDwDocIdentitiesByIdDayOfWeek(new ArrayList<>());
            ident.getTimesOfJobByIdBegin().setDwDocIdentitiesByIdTimeOfJob(new ArrayList<>());
            ident.getTimesOfJobByIdBegin().setDwDocIdentitiesByIdTimeOfJob_0(new ArrayList<>());
            ident.getTimesOfJobByIdEnd().setDwDocIdentitiesByIdTimeOfJob(new ArrayList<>());
            ident.getTimesOfJobByIdEnd().setDwDocIdentitiesByIdTimeOfJob_0(new ArrayList<>());

            daysOfWeekRepository.save(ident.getDaysOfWeekByIdDayOfWeek());
            timeOfJobRepository.save(ident.getTimesOfJobByIdBegin());
            timeOfJobRepository.save(ident.getTimesOfJobByIdEnd());
            doctorsRepository.save(ident.getDoctorsByIdDoctor());

            dwDocIdentityRepository.delete(ident);
        });

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
