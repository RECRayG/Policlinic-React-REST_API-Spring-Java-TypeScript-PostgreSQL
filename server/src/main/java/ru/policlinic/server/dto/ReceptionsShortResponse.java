package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceptionsShortResponse {
    private int id;
    private String dateOfReception;
    private String timeOfReception;
    private int idDoctor;
    private int idPatient;
}
