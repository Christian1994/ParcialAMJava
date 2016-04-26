/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "trabaja_en")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrabajaEn.findAll", query = "SELECT t FROM TrabajaEn t"),
    @NamedQuery(name = "TrabajaEn.findByNsse", query = "SELECT t FROM TrabajaEn t WHERE t.trabajaEnPK.nsse = :nsse"),
    @NamedQuery(name = "TrabajaEn.findByNump", query = "SELECT t FROM TrabajaEn t WHERE t.trabajaEnPK.nump = :nump"),
    @NamedQuery(name = "TrabajaEn.findByHoras", query = "SELECT t FROM TrabajaEn t WHERE t.horas = :horas")})
public class TrabajaEn implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrabajaEnPK trabajaEnPK;
    @Column(name = "horas")
    private Integer horas;
    @JoinColumn(name = "nsse", referencedColumnName = "nss", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;
    @JoinColumn(name = "nump", referencedColumnName = "numerop", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public TrabajaEn() {
    }

    public TrabajaEn(TrabajaEnPK trabajaEnPK) {
        this.trabajaEnPK = trabajaEnPK;
    }

    public TrabajaEn(long nsse, long nump) {
        this.trabajaEnPK = new TrabajaEnPK(nsse, nump);
    }

    public TrabajaEnPK getTrabajaEnPK() {
        return trabajaEnPK;
    }

    public void setTrabajaEnPK(TrabajaEnPK trabajaEnPK) {
        this.trabajaEnPK = trabajaEnPK;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trabajaEnPK != null ? trabajaEnPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrabajaEn)) {
            return false;
        }
        TrabajaEn other = (TrabajaEn) object;
        if ((this.trabajaEnPK == null && other.trabajaEnPK != null) || (this.trabajaEnPK != null && !this.trabajaEnPK.equals(other.trabajaEnPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TrabajaEn[ trabajaEnPK=" + trabajaEnPK + " ]";
    }
    
}
