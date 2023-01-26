package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsResponse {
    private int id;
    private String fullName;
    private String fullAddress;
}
