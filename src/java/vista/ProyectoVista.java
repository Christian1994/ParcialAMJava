/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import logica.ProyectoLogicaLocal;
import modelo.Proyecto;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Estudiante Univalle
 */
@Named(value = "proyectoVista")
@RequestScoped
public class ProyectoVista {
    
    private List<Proyecto> listaProyectos;
    private Proyecto selectedProyecto;

    @EJB
    private ProyectoLogicaLocal proyectoLogica;
    
    public List<Proyecto> getListaProyectos() {
        if(listaProyectos == null){
            try {
                listaProyectos = proyectoLogica.consultarTodos();
            } catch (Exception ex) {
                Logger.getLogger(ProyectoVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public Proyecto getSelectedProyecto() {
        return selectedProyecto;
    }

    public void setSelectedProyecto(Proyecto selectedProyecto) {
        this.selectedProyecto = selectedProyecto;
    }

    public void handleFileUpload(FileUploadEvent event) {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = servletContext.getRealPath("/");
        File f = new File (path + "excel");
        f.mkdir();
        String rutaDestino = (String) servletContext.getRealPath("/excel");

        try {
            copyFile(rutaDestino, event.getFile().getFileName(), event.getFile().getInputstream());
            String resultado = proyectoLogica.importarProyectos(rutaDestino + "\\" + event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información de importación", resultado));
           
        } catch(Exception ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage()));                
        }
    }
    
    public void copyFile(String rutaDestino, String fileName, InputStream in) {
        try {
            OutputStream out = new FileOutputStream(new File(rutaDestino + "\\" + fileName));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch(Exception ex) {
            Logger.getLogger(ProyectoVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
    
    public ProyectoVista() {
    }
    
}
