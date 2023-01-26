package ru.policlinic.server.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Objects;

@EqualsAndHashCode
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receptions", schema = "public", catalog = "policlinic")
public class Receptions implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_reception")
    private int idReception;
    @Basic
    @Column(name = "date_of_reception")
    private Date dateOfReception;
    @Basic
    @Column(name = "complaints")
    private String complaints;
    @Basic
    @Column(name = "diagnosis")
    private String diagnosis;
    @Basic
    @Column(name = "date_of_extract")
    private Date dateOfExtract;
    @Basic
    @Column(name = "is_done")
    private boolean isDone;
    @Basic
    @Column(name = "time_of_reception")
    private LocalTime timeOfReception;
    @Basic
    @Column(name = "doctorDetails", nullable = true)
    private String doctorDetails;

    @OneToMany(mappedBy = "receptionsByIdReception", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<DocRecIdentity> docRecIdentitiesByIdReception;
//    @Fetch(value = FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name="doc_rec_identity",
//            joinColumns = @JoinColumn(name= "id_reception"),
//            inverseJoinColumns = @JoinColumn(name= "id_doctor")
//    )
//    private Collection<Doctors> docRecIdentitiesByIdReception;

    @OneToMany(mappedBy = "receptionsByIdReception", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<PatRecIdentity> patRecIdentitiesByIdReception;
//    @Fetch(value = FetchMode.SUBSELECT)
//    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name="pat_rec_identity",
//            joinColumns = @JoinColumn(name= "id_reception"),
//            inverseJoinColumns = @JoinColumn(name= "id_patient")
//    )
//    private Collection<Patients> patRecIdentitiesByIdReception;
    @OneToMany(mappedBy = "receptionsByIdReception", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<RecAnIdentity> recAnIdentitiesByIdReception;
    @OneToMany(mappedBy = "receptionsByIdReception", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<RecMedIdentity> recMedIdentitiesByIdReception;
    @OneToMany(mappedBy = "receptionsByIdReception", cascade = CascadeType.ALL, orphanRemoval = false)
    private Collection<RecProcIdentity> recProcIdentitiesByIdReception;

    public int getIdReception() {
        return idReception;
    }

    public void setIdReception(int idReception) {
        this.idReception = idReception;
    }

    public Date getDateOfReception() {
        return dateOfReception;
    }

    public void setDateOfReception(Date dateOfReception) {
        this.dateOfReception = dateOfReception;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDateOfExtract() {
        return dateOfExtract;
    }

    public void setDateOfExtract(Date dateOfExtract) {
        this.dateOfExtract = dateOfExtract;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public LocalTime getTimeOfReception() {
        return timeOfReception;
    }

    public void setTimeOfReception(LocalTime timeOfReception) {
        this.timeOfReception = timeOfReception;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Receptions that = (Receptions) o;
//
//        if (idReception != that.idReception) return false;
//        if (isDone != that.isDone) return false;
//        if (!Objects.equals(dateOfReception, that.dateOfReception))
//            return false;
//        if (!Objects.equals(complaints, that.complaints)) return false;
//        if (!Objects.equals(diagnosis, that.diagnosis)) return false;
//        if (!Objects.equals(dateOfExtract, that.dateOfExtract))
//            return false;
//        if (!Objects.equals(timeOfReception, that.timeOfReception))
//            return false;
//
//        return true;
//    }
//
//    public boolean equalsWithoutId(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Receptions that = (Receptions) o;
//
//        if (isDone != that.isDone) return false;
//        if (!Objects.equals(dateOfReception, that.dateOfReception))
//            return false;
//        if (!Objects.equals(complaints, that.complaints)) return false;
//        if (!Objects.equals(diagnosis, that.diagnosis)) return false;
//        if (!Objects.equals(dateOfExtract, that.dateOfExtract))
//            return false;
//        if (!Objects.equals(timeOfReception, that.timeOfReception))
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idReception;
//        result = 31 * result + (dateOfReception != null ? dateOfReception.hashCode() : 0);
//        result = 31 * result + (complaints != null ? complaints.hashCode() : 0);
//        result = 31 * result + (diagnosis != null ? diagnosis.hashCode() : 0);
//        result = 31 * result + (dateOfExtract != null ? dateOfExtract.hashCode() : 0);
//        result = 31 * result + (isDone ? 1 : 0);
//        result = 31 * result + (timeOfReception != null ? timeOfReception.hashCode() : 0);
//        return result;
//    }

    public Collection<DocRecIdentity> getDocRecIdentitiesByIdReception() {
        return docRecIdentitiesByIdReception;
    }

    public void setDocRecIdentitiesByIdReception(Collection<DocRecIdentity> docRecIdentitiesByIdReception) {
        this.docRecIdentitiesByIdReception = docRecIdentitiesByIdReception;
    }

    public Collection<PatRecIdentity> getPatRecIdentitiesByIdReception() {
        return patRecIdentitiesByIdReception;
    }

    public void setPatRecIdentitiesByIdReception(Collection<PatRecIdentity> patRecIdentitiesByIdReception) {
        this.patRecIdentitiesByIdReception = patRecIdentitiesByIdReception;
    }

    public Collection<RecAnIdentity> getRecAnIdentitiesByIdReception() {
        return recAnIdentitiesByIdReception;
    }

    public void setRecAnIdentitiesByIdReception(Collection<RecAnIdentity> recAnIdentitiesByIdReception) {
        this.recAnIdentitiesByIdReception = recAnIdentitiesByIdReception;
    }

    public Collection<RecMedIdentity> getRecMedIdentitiesByIdReception() {
        return recMedIdentitiesByIdReception;
    }

    public void setRecMedIdentitiesByIdReception(Collection<RecMedIdentity> recMedIdentitiesByIdReception) {
        this.recMedIdentitiesByIdReception = recMedIdentitiesByIdReception;
    }

    public Collection<RecProcIdentity> getRecProcIdentitiesByIdReception() {
        return recProcIdentitiesByIdReception;
    }

    public void setRecProcIdentitiesByIdReception(Collection<RecProcIdentity> recProcIdentitiesByIdReception) {
        this.recProcIdentitiesByIdReception = recProcIdentitiesByIdReception;
    }
}
