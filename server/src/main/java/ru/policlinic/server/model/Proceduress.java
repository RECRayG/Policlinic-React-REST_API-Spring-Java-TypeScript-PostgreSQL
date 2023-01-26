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
@Table(name = "proceduress", schema = "public", catalog = "policlinic")
public class Proceduress {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_procedure")
    private int idProcedure;
    @Basic
    @Column(name = "procedure_name")
    private String procedureName;
    @OneToMany(mappedBy = "proceduressByIdProcedure", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = false)
    private Collection<RecProcIdentity> recProcIdentitiesByIdProcedure;

    public int getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(int idProcedure) {
        this.idProcedure = idProcedure;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Proceduress that = (Proceduress) o;
//
//        if (idProcedure != that.idProcedure) return false;
//        if (procedureName != null ? !procedureName.equals(that.procedureName) : that.procedureName != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idProcedure;
//        result = 31 * result + (procedureName != null ? procedureName.hashCode() : 0);
//        return result;
//    }

    public Collection<RecProcIdentity> getRecProcIdentitiesByIdProcedure() {
        return recProcIdentitiesByIdProcedure;
    }

    public void setRecProcIdentitiesByIdProcedure(Collection<RecProcIdentity> recProcIdentitiesByIdProcedure) {
        this.recProcIdentitiesByIdProcedure = recProcIdentitiesByIdProcedure;
    }
}
