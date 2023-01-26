package ru.policlinic.server.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.policlinic.server.model.Doctors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsersResponse {
    private int id;
    private String role;
    private String password;
    private String username;
    private int idDoctor;
    private String doctorShortInfo;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }
}
