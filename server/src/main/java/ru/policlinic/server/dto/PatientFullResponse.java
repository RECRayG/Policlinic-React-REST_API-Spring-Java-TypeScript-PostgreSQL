package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientFullResponse {
    private int id;
    private String lastname;
    private String firstname;
    private String middlename;
    private String city;
    private String street;
    private String building;
    private String apartment;
}
