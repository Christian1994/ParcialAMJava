/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Estudiante Univalle
 */
@Entity
@Table(name = "lugares_dptos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LugaresDptos.findAll", query = "SELECT l FROM LugaresDptos l"),
    @NamedQuery(name = "LugaresDptos.findByNumerod", query = "SELECT l FROM LugaresDptos l WHERE l.lugaresDptosPK.numerod = :numerod"),
    @NamedQuery(name = "LugaresDptos.findByLugard", query = "SELECT l FROM LugaresDptos l WHERE l.lugaresDptosPK.lugard = :lugard")})
public class LugaresDptos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LugaresDptosPK lugaresDptosPK;
    @JoinColumn(name = "numerod", referencedColumnName = "numerod", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Departamento departamento;

    public LugaresDptos() {
    }

    public LugaresDptos(LugaresDptosPK lugaresDptosPK) {
        this.lugaresDptosPK = lugaresDptosPK;
    }

    public LugaresDptos(long numerod, String lugard) {
        this.lugaresDptosPK = new LugaresDptosPK(numerod, lugard);
    }

    public LugaresDptosPK getLugaresDptosPK() {
        return lugaresDptosPK;
    }

    public void setLugaresDptosPK(LugaresDptosPK lugaresDptosPK) {
        this.lugaresDptosPK = lugaresDptosPK;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lugaresDptosPK != null ? lugaresDptosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LugaresDptos)) {
            return false;
        }
        LugaresDptos other = (LugaresDptos) object;
        if ((this.lugaresDptosPK == null && other.lugaresDptosPK != null) || (this.lugaresDptosPK != null && !this.lugaresDptosPK.equals(other.lugaresDptosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.LugaresDptos[ lugaresDptosPK=" + lugaresDptosPK + " ]";
    }
    
}
