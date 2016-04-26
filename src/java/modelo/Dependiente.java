/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Estudiante Univalle
 */
@Entity
@Table(name = "dependiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependiente.findAll", query = "SELECT d FROM Dependiente d"),
    @NamedQuery(name = "Dependiente.findByNsse", query = "SELECT d FROM Dependiente d WHERE d.dependientePK.nsse = :nsse"),
    @NamedQuery(name = "Dependiente.findByNombreDependiente", query = "SELECT d FROM Dependiente d WHERE d.dependientePK.nombreDependiente = :nombreDependiente"),
    @NamedQuery(name = "Dependiente.findBySexo", query = "SELECT d FROM Dependiente d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "Dependiente.findByFechan", query = "SELECT d FROM Dependiente d WHERE d.fechan = :fechan"),
    @NamedQuery(name = "Dependiente.findByParentesco", query = "SELECT d FROM Dependiente d WHERE d.parentesco = :parentesco")})
public class Dependiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DependientePK dependientePK;
    @Size(max = 10)
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "fechan")
    @Temporal(TemporalType.DATE)
    private Date fechan;
    @Size(max = 50)
    @Column(name = "parentesco")
    private String parentesco;
    @JoinColumn(name = "nsse", referencedColumnName = "nss", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Dependiente() {
    }

    public Dependiente(DependientePK dependientePK) {
        this.dependientePK = dependientePK;
    }

    public Dependiente(long nsse, String nombreDependiente) {
        this.dependientePK = new DependientePK(nsse, nombreDependiente);
    }

    public DependientePK getDependientePK() {
        return dependientePK;
    }

    public void setDependientePK(DependientePK dependientePK) {
        this.dependientePK = dependientePK;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechan() {
        return fechan;
    }

    public void setFechan(Date fechan) {
        this.fechan = fechan;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dependientePK != null ? dependientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependiente)) {
            return false;
        }
        Dependiente other = (Dependiente) object;
        if ((this.dependientePK == null && other.dependientePK != null) || (this.dependientePK != null && !this.dependientePK.equals(other.dependientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Dependiente[ dependientePK=" + dependientePK + " ]";
    }
    
}
