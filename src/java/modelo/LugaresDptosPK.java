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
public class LugaresDptosPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "numerod")
    private long numerod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "lugard")
    private String lugard;

    public LugaresDptosPK() {
    }

    public LugaresDptosPK(long numerod, String lugard) {
        this.numerod = numerod;
        this.lugard = lugard;
    }

    public long getNumerod() {
        return numerod;
    }

    public void setNumerod(long numerod) {
        this.numerod = numerod;
    }

    public String getLugard() {
        return lugard;
    }

    public void setLugard(String lugard) {
        this.lugard = lugard;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numerod;
        hash += (lugard != null ? lugard.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugaresDptosPK)) {
            return false;
        }
        LugaresDptosPK other = (LugaresDptosPK) object;
        if (this.numerod != other.numerod) {
            return false;
        }
        if ((this.lugard == null && other.lugard != null) || (this.lugard != null && !this.lugard.equals(other.lugard))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.LugaresDptosPK[ numerod=" + numerod + ", lugard=" + lugard + " ]";
    }
    
}
