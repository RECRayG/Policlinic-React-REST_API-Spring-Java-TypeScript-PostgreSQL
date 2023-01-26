package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "days_of_week", schema = "public", catalog = "policlinic")
public class DaysOfWeek {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_day_of_week")
    private int idDayOfWeek;
    @Basic
    @Column(name = "day_of_week")
    private String dayOfWeek;
    @OneToMany(mappedBy = "daysOfWeekByIdDayOfWeek", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DwDocIdentity> dwDocIdentitiesByIdDayOfWeek;

    public int getIdDayOfWeek() {
        return idDayOfWeek;
    }

    public void setIdDayOfWeek(int idDayOfWeek) {
        this.idDayOfWeek = idDayOfWeek;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        DaysOfWeek that = (DaysOfWeek) o;
//
//        if (idDayOfWeek != that.idDayOfWeek) return false;
//        if (dayOfWeek != null ? !dayOfWeek.equals(that.dayOfWeek) : that.dayOfWeek != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idDayOfWeek;
//        result = 31 * result + (dayOfWeek != null ? dayOfWeek.hashCode() : 0);
//        return result;
//    }

    public Collection<DwDocIdentity> getDwDocIdentitiesByIdDayOfWeek() {
        return dwDocIdentitiesByIdDayOfWeek;
    }

    public void setDwDocIdentitiesByIdDayOfWeek(Collection<DwDocIdentity> dwDocIdentitiesByIdDayOfWeek) {
        this.dwDocIdentitiesByIdDayOfWeek = dwDocIdentitiesByIdDayOfWeek;
    }
}
