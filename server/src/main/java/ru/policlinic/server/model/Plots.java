package ru.policlinic.server.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@EqualsAndHashCode
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plots", schema = "public", catalog = "policlinic")
public class Plots implements Serializable {
    @Override
    public String toString() {
        return String.valueOf(plotNumber);
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_plot")
    private int idPlot;
    @Basic
    @Column(name = "plot_number")
    private int plotNumber;
    @OneToMany(mappedBy = "plotsByIdPlot", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Doctors> doctorsByIdPlot;

    public int getIdPlot() {
        return idPlot;
    }

    public void setIdPlot(int idPlot) {
        this.idPlot = idPlot;
    }

    public int getPlotNumber() {
        return plotNumber;
    }

    public void setPlotNumber(int plotNumber) {
        this.plotNumber = plotNumber;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Plots plots = (Plots) o;
//
//        if (idPlot != plots.idPlot) return false;
//        if (plotNumber != plots.plotNumber) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idPlot;
//        result = 31 * result + plotNumber;
//        return result;
//    }

    public Collection<Doctors> getDoctorsByIdPlot() {
        return doctorsByIdPlot;
    }

    public void setDoctorsByIdPlot(Collection<Doctors> doctorsByIdPlot) {
        this.doctorsByIdPlot = doctorsByIdPlot;
    }
}
