/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import logica.EmpleadoLogicaLocal;
import logica.ProyectoLogicaLocal;
import logica.TrabajaEnLogica;
import logica.TrabajaEnLogicaLocal;
import modelo.Empleado;
import modelo.Proyecto;
import modelo.TrabajaEn;
import modelo.TrabajaEnPK;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author Proyecto Univalle
 */
@Named(value = "trabajaEnVista")
@RequestScoped
public class TrabajaEnVista {

    @EJB
    TrabajaEnLogicaLocal trabajaEnLogica;
    
    @EJB
    ProyectoLogicaLocal proyectoLogica;
    
    @EJB
    EmpleadoLogicaLocal empleadoLogica;
    
    private SelectOneMenu cmbProyectos;
    private ArrayList<SelectItem> itemsProyectos;
    
    private SelectOneMenu cmbEmpleados;
    private ArrayList<SelectItem> itemsEmpleados;
    
    private InputText txtHoras;
    
    private CommandButton btnRegistrar;    
    
    public SelectOneMenu getCmbProyectos() {
        return cmbProyectos;
    }

    public void setCmbProyectos(SelectOneMenu cmbProyectos) {
        this.cmbProyectos = cmbProyectos;
    }

    public ArrayList<SelectItem> getItemsProyectos() {
        try {
            List<Proyecto> listaE = proyectoLogica.consultarTodos();
            itemsProyectos = new ArrayList<>();
            
            for(Proyecto p: listaE){
                itemsProyectos.add(new SelectItem(p.getNumerop(), p.getNombrep()));
            }
        } catch (Exception ex) {
            Logger.getLogger(TrabajaEnVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemsProyectos;
    }

    public void setItemsProyectos(ArrayList<SelectItem> itemsProyectos) {
        this.itemsProyectos = itemsProyectos;
    }

    public SelectOneMenu getCmbEmpleados() {
        return cmbEmpleados;
    }

    public void setCmbEmpleados(SelectOneMenu cmbEmpleados) {
        this.cmbEmpleados = cmbEmpleados;
    }

    public ArrayList<SelectItem> getItemsEmpleados() {
        try {
            List<Empleado> listaE = empleadoLogica.consultarTodos();
            itemsEmpleados = new ArrayList<>();
            
            for(Empleado e: listaE){
                itemsEmpleados.add(new SelectItem(e.getNss(), e.getNombrep()));
            }
        } catch (Exception ex) {
            Logger.getLogger(TrabajaEnVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemsEmpleados;
    }

    public void setItemsEmpleados(ArrayList<SelectItem> itemsEmpleados) {
        this.itemsEmpleados = itemsEmpleados;
    }

    public InputText getTxtHoras() {
        return txtHoras;
    }

    public void setTxtHoras(InputText txtHoras) {
        this.txtHoras = txtHoras;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    // Método registrar
    public void action_registrar(){
        try {
            Proyecto objProyecto = new Proyecto();
            objProyecto.setNombrep(this.cmbProyectos.getValue().toString());
            
            Empleado objEmpleado = new Empleado();
            objEmpleado.setNombrep(this.cmbEmpleados.getValue().toString());
            
            TrabajaEn objTrabajaEn = new TrabajaEn();
            try {
                TrabajaEnPK objTrabajaEnPK = new TrabajaEnPK(objProyecto.getNumerop(), objEmpleado.getNss());
                objTrabajaEn.setTrabajaEnPK(objTrabajaEnPK);
            } catch(Exception ex) {}
            objTrabajaEn.setProyecto(objProyecto);
            objTrabajaEn.setEmpleado(objEmpleado);
            objTrabajaEn.setHoras();
            
            TrabajaEnLogica.registrarTrabajaEn(objTrabajaEn);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de creación de matrícula", "La matrícula fue hecha con éxito."));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }    
    
    public TrabajaEnVista() {
    }
    
}
