package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalysesResponse {
    private int idAnalysis;
    private String analysis;
    private int idAnalysisResult;
    private String analysisResult;
}
