/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Estudiante Univalle
 */
@Entity
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByNombrep", query = "SELECT e FROM Empleado e WHERE e.nombrep = :nombrep"),
    @NamedQuery(name = "Empleado.findByApellido", query = "SELECT e FROM Empleado e WHERE e.apellido = :apellido"),
    @NamedQuery(name = "Empleado.findByNss", query = "SELECT e FROM Empleado e WHERE e.nss = :nss"),
    @NamedQuery(name = "Empleado.findByFechan", query = "SELECT e FROM Empleado e WHERE e.fechan = :fechan"),
    @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleado.findBySexo", query = "SELECT e FROM Empleado e WHERE e.sexo = :sexo"),
    @NamedQuery(name = "Empleado.findBySalario", query = "SELECT e FROM Empleado e WHERE e.salario = :salario"),
    @NamedQuery(name = "Empleado.findByTipo", query = "SELECT e FROM Empleado e WHERE e.tipo = :tipo")})
public class Empleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "nombrep")
    private String nombrep;
    @Size(max = 50)
    @Column(name = "apellido")
    private String apellido;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "nss")
    private Long nss;
    @Column(name = "fechan")
    @Temporal(TemporalType.DATE)
    private Date fechan;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 20)
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "salario")
    private Integer salario;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "clave")
    private String clave;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<TrabajaEn> trabajaEnList;
    @JoinColumn(name = "nd", referencedColumnName = "numerod")
    @ManyToOne
    private Departamento nd;
    @OneToMany(mappedBy = "nsssuper")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "nsssuper", referencedColumnName = "nss")
    @ManyToOne
    private Empleado nsssuper;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<Dependiente> dependienteList;

    public Empleado() {
    }

    public Empleado(Long nss) {
        this.nss = nss;
    }

    public String getNombrep() {
        return nombrep;
    }

    public void setNombrep(String nombrep) {
        this.nombrep = nombrep;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getNss() {
        return nss;
    }

    public void setNss(Long nss) {
        this.nss = nss;
    }

    public Date getFechan() {
        return fechan;
    }

    public void setFechan(Date fechan) {
        this.fechan = fechan;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @XmlTransient
    public List<TrabajaEn> getTrabajaEnList() {
        return trabajaEnList;
    }

    public void setTrabajaEnList(List<TrabajaEn> trabajaEnList) {
        this.trabajaEnList = trabajaEnList;
    }

    public Departamento getNd() {
        return nd;
    }

    public void setNd(Departamento nd) {
        this.nd = nd;
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Empleado getNsssuper() {
        return nsssuper;
    }

    public void setNsssuper(Empleado nsssuper) {
        this.nsssuper = nsssuper;
    }

    @XmlTransient
    public List<Dependiente> getDependienteList() {
        return dependienteList;
    }

    public void setDependienteList(List<Dependiente> dependienteList) {
        this.dependienteList = dependienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nss != null ? nss.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.nss == null && other.nss != null) || (this.nss != null && !this.nss.equals(other.nss))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empleado[ nss=" + nss + " ]";
    }
    
}
