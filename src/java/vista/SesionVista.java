/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import logica.SesionLogicaLocal;
import modelo.Empleado;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

/**
 *
 * @author Empleado Univalle
 */
@Named(value = "sesionVista")
@RequestScoped
public class SesionVista {

    @EJB
    private SesionLogicaLocal sesionLogica;
    
    private InputText txtNSS;
    private Password txtClave;
    private CommandButton btnIngresar;

    public InputText getTxtNSS() {
        return txtNSS;
    }

    public void setTxtNSS(InputText txtNSS) {
        this.txtNSS = txtNSS;
    }

    public Password getTxtClave() {
        return txtClave;
    }

    public void setTxtClave(Password txtClave) {
        this.txtClave = txtClave;
    }

    public CommandButton getBtnIngresar() {
        return btnIngresar;
    }

    public void setBtnIngresar(CommandButton btnIngresar) {
        this.btnIngresar = btnIngresar;
    }

    public void funcion_ingresar(){
        try {
            String urlP, urlC;
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext = context.getExternalContext();
            
            urlP = extContext.encodeActionURL(context.getApplication().
                    getViewHandler().getActionURL(context, "/planta/gestionProyectos.xhtml"));

            urlC = extContext.encodeActionURL(context.getApplication().
                    getViewHandler().getActionURL(context, "/contratista/gestionDependientes.xhtml"));
            
            Long nss = null;
            try {
                nss = Long.parseLong(txtNSS.getValue().toString());
            } catch (Exception ex) {}
            String clave = txtClave.getValue().toString();
            
            sesionLogica.buscarCamposInvalidosOVacios(nss, clave);
            Empleado e = sesionLogica.iniciarSesionEmpleado(nss, clave);
            if(e!=null){
                if(e.getTipo().equals("PLANTA")){
                    extContext.getSessionMap().put("tipo", "planta");
                    extContext.getSessionMap().put("usuario", e);
                    extContext.redirect(urlP);                    
                }
                else{
                    extContext.getSessionMap().put("tipo", "contratista");
                    extContext.getSessionMap().put("usuario", e);
                    extContext.redirect(urlC);
                }
            }            
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));
        }
    }

    public void cerrarSesion_action()
    {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext extContext= context.getExternalContext();
            extContext.getSessionMap().remove("tipo");
            extContext.getSessionMap().remove("usuario");
            String url=extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,"/index.xhtml"));
            extContext.redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(SesionVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates a new instance of SesionVista
     */
    public SesionVista() {
    }
    
}
