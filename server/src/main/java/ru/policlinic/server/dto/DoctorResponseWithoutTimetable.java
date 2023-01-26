package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DoctorResponseWithoutTimetable {
    private String lastname;
    private String firstname;
    private String middlename;
    private String specialization;
    private int plot;
    private String cabinet;
}