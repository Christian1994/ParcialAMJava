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

/**
 *
 * @author Estudiante Univalle
 */
@Embeddable
public class TrabajaEnPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "nsse")
    private long nsse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nump")
    private long nump;

    public TrabajaEnPK() {
    }

    public TrabajaEnPK(long nsse, long nump) {
        this.nsse = nsse;
        this.nump = nump;
    }

    public long getNsse() {
        return nsse;
    }

    public void setNsse(long nsse) {
        this.nsse = nsse;
    }

    public long getNump() {
        return nump;
    }

    public void setNump(long nump) {
        this.nump = nump;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) nsse;
        hash += (int) nump;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrabajaEnPK)) {
            return false;
        }
        TrabajaEnPK other = (TrabajaEnPK) object;
        if (this.nsse != other.nsse) {
            return false;
        }
        if (this.nump != other.nump) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TrabajaEnPK[ nsse=" + nsse + ", nump=" + nump + " ]";
    }
    
}
