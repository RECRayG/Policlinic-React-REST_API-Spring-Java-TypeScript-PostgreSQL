package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Objects;

@Data
@ToString
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dw_doc_identity", schema = "public", catalog = "policlinic")
public class DwDocIdentity {
    @ManyToOne(optional = false)//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_day_of_week", referencedColumnName = "id_day_of_week", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private DaysOfWeek daysOfWeekByIdDayOfWeek;
    @ManyToOne//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Doctors doctorsByIdDoctor;
    @ManyToOne(optional = false)//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_begin", referencedColumnName = "id_time_of_job", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private TimesOfJob timesOfJobByIdBegin;
    @ManyToOne(optional = false)//(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_end", referencedColumnName = "id_time_of_job", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private TimesOfJob timesOfJobByIdEnd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dw_doc_identity")
    private int idDwDocIdentity;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DwDocIdentity that = (DwDocIdentity) o;
//
//        if (idDwDocIdentity != that.idDwDocIdentity) return false;
//        if (daysOfWeekByIdDayOfWeek != that.daysOfWeekByIdDayOfWeek) return false;
//        if (doctorsByIdDoctor != that.doctorsByIdDoctor) return false;
//        if (!Objects.equals(timesOfJobByIdBegin, that.timesOfJobByIdBegin)) return false;
//        if (!Objects.equals(timesOfJobByIdEnd, that.timesOfJobByIdEnd)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = daysOfWeekByIdDayOfWeek.getIdDayOfWeek();
//        result = 31 * result + doctorsByIdDoctor.getIdDoctor();
//        result = 31 * result + (timesOfJobByIdBegin != null ? timesOfJobByIdBegin.hashCode() : 0);
//        result = 31 * result + (timesOfJobByIdEnd != null ? timesOfJobByIdEnd.hashCode() : 0);
//        result = 31 * result + idDwDocIdentity;
//        return result;
//    }

    public DaysOfWeek getDaysOfWeekByIdDayOfWeek() {
        return daysOfWeekByIdDayOfWeek;
    }

    public void setDaysOfWeekByIdDayOfWeek(DaysOfWeek daysOfWeekByIdDayOfWeek) {
        this.daysOfWeekByIdDayOfWeek = daysOfWeekByIdDayOfWeek;
    }

    public Doctors getDoctorsByIdDoctor() {
        return doctorsByIdDoctor;
    }

    public void setDoctorsByIdDoctor(Doctors doctorsByIdDoctor) {
        this.doctorsByIdDoctor = doctorsByIdDoctor;
    }

    public TimesOfJob getTimesOfJobByIdBegin() {
        return timesOfJobByIdBegin;
    }

    public void setTimesOfJobByIdBegin(TimesOfJob timesOfJobByIdBegin) {
        this.timesOfJobByIdBegin = timesOfJobByIdBegin;
    }

    public TimesOfJob getTimesOfJobByIdEnd() {
        return timesOfJobByIdEnd;
    }

    public void setTimesOfJobByIdEnd(TimesOfJob timesOfJobByIdEnd) {
        this.timesOfJobByIdEnd = timesOfJobByIdEnd;
    }

    public void setIdDwDocIdentity(int idDwDocIdentity) {
        this.idDwDocIdentity = idDwDocIdentity;
    }

    public int getIdDwDocIdentity() {
        return idDwDocIdentity;
    }
}
