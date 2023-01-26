package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DwDocIdentityResponse {
    private String dayOfWeek;
    private String timeBegin;
    private String timeEnd;
}
