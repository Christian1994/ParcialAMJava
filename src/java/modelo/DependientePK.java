/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Estudiante Univalle
 */
@Embeddable
public class DependientePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nsse")
    private long nsse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_dependiente")
    private String nombreDependiente;

    public DependientePK() {
    }

    public DependientePK(long nsse, String nombreDependiente) {
        this.nsse = nsse;
        this.nombreDependiente = nombreDependiente;
    }

    public long getNsse() {
        return nsse;
    }

    public void setNsse(long nsse) {
        this.nsse = nsse;
    }

    public String getNombreDependiente() {
        return nombreDependiente;
    }

    public void setNombreDependiente(String nombreDependiente) {
        this.nombreDependiente = nombreDependiente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nsse;
        hash += (nombreDependiente != null ? nombreDependiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DependientePK)) {
            return false;
        }
        DependientePK other = (DependientePK) object;
        if (this.nsse != other.nsse) {
            return false;
        }
        if ((this.nombreDependiente == null && other.nombreDependiente != null) || (this.nombreDependiente != null && !this.nombreDependiente.equals(other.nombreDependiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DependientePK[ nsse=" + nsse + ", nombreDependiente=" + nombreDependiente + " ]";
    }
    
}
