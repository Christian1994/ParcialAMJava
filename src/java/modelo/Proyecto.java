/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Estudiante Univalle
 */
@Entity
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByNombrep", query = "SELECT p FROM Proyecto p WHERE p.nombrep = :nombrep"),
    @NamedQuery(name = "Proyecto.findByNumerop", query = "SELECT p FROM Proyecto p WHERE p.numerop = :numerop")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "nombrep")
    private String nombrep;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numerop")
    private Long numerop;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private List<TrabajaEn> trabajaEnList;
    @JoinColumn(name = "numd", referencedColumnName = "numerod")
    @ManyToOne(optional = false)
    private Departamento numd;

    public Proyecto() {
    }

    public Proyecto(Long numerop) {
        this.numerop = numerop;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public Long getNumerop() {
        return numerop;
    }

    public void setNumerop(Long numerop) {
        this.numerop = numerop;
    }

    @XmlTransient
    public List<TrabajaEn> getTrabajaEnList() {
        return trabajaEnList;
    }

    public void setTrabajaEnList(List<TrabajaEn> trabajaEnList) {
        this.trabajaEnList = trabajaEnList;
    }

    public Departamento getNumd() {
        return numd;
    }

    public void setNumd(Departamento numd) {
        this.numd = numd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerop != null ? numerop.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.numerop == null && other.numerop != null) || (this.numerop != null && !this.numerop.equals(other.numerop))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Proyecto[ numerop=" + numerop + " ]";
    }
    
}
