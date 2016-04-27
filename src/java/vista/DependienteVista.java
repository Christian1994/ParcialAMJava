/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import logica.DependienteLogicaLocal;
import modelo.Dependiente;
import modelo.DependientePK;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Estudiante Univalle
 */
@Named(value = "dependienteVista")
@RequestScoped
public class DependienteVista {
       
    private InputText txtNSSE;
    private InputText txtNombre;
    private SelectOneMenu cmbSexos;
    private InputText txtFechan;
    private InputText txtParentesco;
    
    private CommandButton btnRegistrar;
    private CommandButton btnModificar;
    private CommandButton btnEliminar;
    private CommandButton btnLimpiar;
    
    private List<Dependiente> listaDependientes;
    private Dependiente selectedDependiente;

    @EJB
    private DependienteLogicaLocal dependienteLogica;
    
    public InputText getTxtNSSE() {
        return txtNSSE;
    }

    public void setTxtNSSE(InputText txtNSSE) {
        this.txtNSSE = txtNSSE;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public SelectOneMenu getCmbSexos() {
        return cmbSexos;
    }

    public void setCmbSexos(SelectOneMenu cmbSexos) {
        this.cmbSexos = cmbSexos;
    }

    public InputText getTxtFechan() {
        return txtFechan;
    }

    public void setTxtFechan(InputText txtFechan) {
        this.txtFechan = txtFechan;
    }

    public InputText getTxtParentesco() {
        return txtParentesco;
    }

    public void setTxtParentesco(InputText txtParentesco) {
        this.txtParentesco = txtParentesco;
    }

    public CommandButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void setBtnRegistrar(CommandButton btnRegistrar) {
        this.btnRegistrar = btnRegistrar;
    }

    public CommandButton getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(CommandButton btnModificar) {
        this.btnModificar = btnModificar;
    }

    public CommandButton getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(CommandButton btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public CommandButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(CommandButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public List<Dependiente> getListaDependientes() {
        if(listaDependientes == null) {
            try {
                listaDependientes = dependienteLogica.consultarTodos();
            } catch (Exception ex) {
                Logger.getLogger(DependienteVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listaDependientes;
    }

    public void setListaDependientes(List<Dependiente> listaDependientes) {
        this.listaDependientes = listaDependientes;
    }

    public Dependiente getSelectedDependiente() {
        return selectedDependiente;
    }

    public void setSelectedDependiente(Dependiente selectedDependiente) {
        this.selectedDependiente = selectedDependiente;
    }

    //Mostrar por interfaz la matrícula seleccionada
    public void onRowSelect(SelectEvent event) {
        this.selectedDependiente = (Dependiente) event.getObject();
        
        this.txtNSSE.setValue(selectedDependiente.getDependientePK().getNsse());
        this.txtNombre.setValue(selectedDependiente.getDependientePK().getNombreDependiente());
        this.cmbSexos.setValue(selectedDependiente.getSexo());
        this.txtFechan.setValue(selectedDependiente.getFechan());
        this.txtParentesco.setValue(selectedDependiente.getParentesco());
        
        // Se deshabilita el botón registrar para permitir que la matricula se puede modificar o eliminar       
        this.btnRegistrar.setDisabled(true);
        this.btnModificar.setDisabled(false);
        this.btnEliminar.setDisabled(false);
    }

    //Limpia los campos y reinicia los valores
    public void limpiar(){
        this.txtNSSE.setValue("");
        this.txtNombre.setValue("");
        this.cmbSexos.setValue("");
        this.txtFechan.setValue("");
        this.txtParentesco.setValue("");
        
        this.btnRegistrar.setDisabled(false);
        this.btnModificar.setDisabled(true);
        this.btnEliminar.setDisabled(true);
    }
    
  // Método registrar
    public void action_registrar(){
        try {
            SimpleDateFormat fechaCadena = new SimpleDateFormat("yyyy-MM-dd");
            Dependiente objDependiente = new Dependiente();
            DependientePK objDependientePK = new DependientePK();
            
            objDependientePK.setNsse(Long.parseLong(this.txtNSSE.getValue().toString())); 
            objDependientePK.setNombreDependiente(this.txtNombre.getValue().toString());
            
            objDependiente.setDependientePK(objDependientePK);
            objDependiente.setSexo(this.cmbSexos.getValue().toString());
            objDependiente.setFechan(fechaCadena.parse(this.txtFechan.getValue().toString()));
            objDependiente.setParentesco(this.txtParentesco.getValue().toString());
            
            dependienteLogica.registrarDependiente(objDependiente);
            listaDependientes = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de creación de dependiente", "El dependiente fue registrado con éxito."));
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    // Método modificar
    public void action_modificar(){
        try{
            SimpleDateFormat fechaCadena = new SimpleDateFormat("yyyy-MM-dd");
            Dependiente objDependiente = new Dependiente();
            DependientePK objDependientePK = new DependientePK();
            
            objDependientePK.setNsse(Long.parseLong(this.txtNSSE.getValue().toString())); 
            objDependientePK.setNombreDependiente(this.txtNombre.getValue().toString());
            
            objDependiente.setDependientePK(objDependientePK);
            objDependiente.setSexo(this.cmbSexos.getValue().toString());
            objDependiente.setFechan(fechaCadena.parse(this.txtFechan.getValue().toString()));
            objDependiente.setParentesco(this.txtParentesco.getValue().toString());
            
            dependienteLogica.modificarDependiente(objDependiente);
            listaDependientes = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de modificación de dependiente", "El dependiente fue modificado con éxito."));
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    // Método eliminar
    public void action_eliminar(){
        try{
            SimpleDateFormat fechaCadena = new SimpleDateFormat("yyyy-MM-dd");
            Dependiente objDependiente = new Dependiente();
            DependientePK objDependientePK = new DependientePK();
            
            objDependientePK.setNsse(Long.parseLong(this.txtNSSE.getValue().toString())); 
            objDependientePK.setNombreDependiente(this.txtNombre.getValue().toString());
            
            objDependiente.setDependientePK(objDependientePK);
            objDependiente.setSexo(this.cmbSexos.getValue().toString());
            objDependiente.setFechan(fechaCadena.parse(this.txtFechan.getValue().toString()));
            objDependiente.setParentesco(this.txtParentesco.getValue().toString());
            
            dependienteLogica.eliminarDependiente(objDependiente);
            listaDependientes = null;
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de eliminación de dependiente", "El dependiente fue eliminado con éxito."));
            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", ex.getMessage()));
        }
    }
    
    /**
     * Creates a new instance of DependienteVista
     */
    public DependienteVista() {
    }
    
}