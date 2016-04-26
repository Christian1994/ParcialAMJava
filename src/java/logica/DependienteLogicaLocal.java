/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.Dependiente;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface DependienteLogicaLocal {
    public void registrarDependiente(Dependiente dependiente) throws Exception;
    public void modificarDependiente(Dependiente dependiente) throws Exception;
    public void eliminarDependiente(Dependiente dependiente) throws Exception;
    public List<Dependiente> consultarTodos() throws Exception;   
}
