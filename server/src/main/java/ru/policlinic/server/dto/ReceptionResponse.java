package ru.policlinic.server.dto;

import lombok.Getter;
import lombok.Setter;
import ru.policlinic.server.model.Doctors;
import ru.policlinic.server.model.Medications;
import ru.policlinic.server.model.Patients;

import java.util.List;

@Getter
@Setter
public class ReceptionResponse {
    private int id;
    private DoctorsResponse doctor;
    private PatientsResponse patient;
    private String dateOfReception;
    private String timeOfReception;
    private String complaints;
    private String diagnosis;
    private List<AnalysesResponse> analyses;
    private List<MedicationResponse> medications;
    private String dateOfExtract;
    private String doctorDetails;
    private List<ProceduresResponse> procedures;
}
