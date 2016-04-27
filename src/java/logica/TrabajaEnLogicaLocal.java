/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.Local;
import modelo.TrabajaEn;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface TrabajaEnLogicaLocal {
    
    public void registrarTrabajaEn(TrabajaEn trabajaEn) throws Exception;
    public List<TrabajaEn> consultarTodos() throws Exception;
}
