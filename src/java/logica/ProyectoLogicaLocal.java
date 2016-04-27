/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Proyecto;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface ProyectoLogicaLocal {
    
    public List<Proyecto> consultarTodos() throws Exception;
    public String importarProyectos(String archivo) throws Exception;
}
