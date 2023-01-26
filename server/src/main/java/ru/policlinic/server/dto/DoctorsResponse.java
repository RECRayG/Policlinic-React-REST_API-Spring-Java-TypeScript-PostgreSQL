package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class DoctorsResponse {
    private int id;
    private String fullName;
    private String specialization;
    private int plot;
    private String cabinet;
    private List<DwDocIdentityResponse> timetable;
}
