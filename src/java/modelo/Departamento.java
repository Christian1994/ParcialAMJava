/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Estudiante Univalle
 */
@Entity
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d"),
    @NamedQuery(name = "Departamento.findByNombred", query = "SELECT d FROM Departamento d WHERE d.nombred = :nombred"),
    @NamedQuery(name = "Departamento.findByNumerod", query = "SELECT d FROM Departamento d WHERE d.numerod = :numerod"),
    @NamedQuery(name = "Departamento.findByNssgte", query = "SELECT d FROM Departamento d WHERE d.nssgte = :nssgte"),
    @NamedQuery(name = "Departamento.findByFechaninicgt", query = "SELECT d FROM Departamento d WHERE d.fechaninicgt = :fechaninicgt")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "nombred")
    private String nombred;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "numerod")
    private Long numerod;
    @Column(name = "nssgte")
    private BigInteger nssgte;
    @Column(name = "fechaninicgt")
    @Temporal(TemporalType.DATE)
    private Date fechaninicgt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private List<LugaresDptos> lugaresDptosList;
    @OneToMany(mappedBy = "nd")
    private List<Empleado> empleadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numd")
    private List<Proyecto> proyectoList;

    public Departamento() {
    }

    public Departamento(Long numerod) {
        this.numerod = numerod;
    }

    public String getNombred() {
        return nombred;
    }

    public void setNombred(String nombred) {
        this.nombred = nombred;
    }

    public Long getNumerod() {
        return numerod;
    }

    public void setNumerod(Long numerod) {
        this.numerod = numerod;
    }

    public BigInteger getNssgte() {
        return nssgte;
    }

    public void setNssgte(BigInteger nssgte) {
        this.nssgte = nssgte;
    }

    public Date getFechaninicgt() {
        return fechaninicgt;
    }

    public void setFechaninicgt(Date fechaninicgt) {
        this.fechaninicgt = fechaninicgt;
    }

    @XmlTransient
    public List<LugaresDptos> getLugaresDptosList() {
        return lugaresDptosList;
    }

    public void setLugaresDptosList(List<LugaresDptos> lugaresDptosList) {
        this.lugaresDptosList = lugaresDptosList;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numerod != null ? numerod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.numerod == null && other.numerod != null) || (this.numerod != null && !this.numerod.equals(other.numerod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Departamento[ numerod=" + numerod + " ]";
    }
    
}
