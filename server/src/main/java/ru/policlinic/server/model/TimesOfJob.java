package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;

@Data
@ToString
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "times_of_job", schema = "public", catalog = "policlinic")
public class TimesOfJob {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_time_of_job")
    private int idTimeOfJob;
    @Basic
    @Column(name = "time_value")
    private LocalTime timeValue;
    @OneToMany(mappedBy = "timesOfJobByIdBegin", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DwDocIdentity> dwDocIdentitiesByIdTimeOfJob;
    @OneToMany(mappedBy = "timesOfJobByIdEnd", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DwDocIdentity> dwDocIdentitiesByIdTimeOfJob_0;

    public int getIdTimeOfJob() {
        return idTimeOfJob;
    }

    public void setIdTimeOfJob(int idTimeOfJob) {
        this.idTimeOfJob = idTimeOfJob;
    }

    public LocalTime getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(LocalTime timeValue) {
        this.timeValue = timeValue;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        TimesOfJob that = (TimesOfJob) o;
//
//        if (idTimeOfJob != that.idTimeOfJob) return false;
//        if (timeValue != null ? !timeValue.equals(that.timeValue) : that.timeValue != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idTimeOfJob;
//        result = 31 * result + (timeValue != null ? timeValue.hashCode() : 0);
//        return result;
//    }

    public Collection<DwDocIdentity> getDwDocIdentitiesByIdTimeOfJob() {
        return dwDocIdentitiesByIdTimeOfJob;
    }

    public void setDwDocIdentitiesByIdTimeOfJob(Collection<DwDocIdentity> dwDocIdentitiesByIdTimeOfJob) {
        this.dwDocIdentitiesByIdTimeOfJob = dwDocIdentitiesByIdTimeOfJob;
    }

    public Collection<DwDocIdentity> getDwDocIdentitiesByIdTimeOfJob_0() {
        return dwDocIdentitiesByIdTimeOfJob_0;
    }

    public void setDwDocIdentitiesByIdTimeOfJob_0(Collection<DwDocIdentity> dwDocIdentitiesByIdTimeOfJob_0) {
        this.dwDocIdentitiesByIdTimeOfJob_0 = dwDocIdentitiesByIdTimeOfJob_0;
    }
}
