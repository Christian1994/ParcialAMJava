/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.TrabajaEn;
import persistencia.TrabajaEnFacadeLocal;

/**
 *
 * @author Estudiante Univalle
 */
@Stateless
public class TrabajaEnLogica implements TrabajaEnLogicaLocal {

    @EJB
    TrabajaEnFacadeLocal trabajaEnDAO;
    
    @Override
    public void registrarTrabajaEn(TrabajaEn trabajaEn) throws Exception {
        if(trabajaEn.getProyecto().getNombrep().equals("") || trabajaEn.getProyecto().getNombrep() == null){
            throw new Exception("Campo proyecto obligatorio.");
        }
        if(trabajaEn.getEmpleado().getNombrep().equals("") || trabajaEn.getEmpleado().getNombrep() == null){
            throw new Exception("Campo empleado obligatorio.");
        }
        if(trabajaEn.getHoras() == 0 || trabajaEn.getHoras() == null){
            throw new Exception("Campo horas obligatorio.");
        }
        
        TrabajaEn objTrabajaEn = trabajaEnDAO.find(trabajaEn.getTrabajaEnPK());
        if(objTrabajaEn != null){
            throw new Exception("Relación Empleado - Proyecto ya existe.");
        }
        else{
            trabajaEnDAO.create(trabajaEn);
        }
    }

    @Override
    public List<TrabajaEn> consultarTodos() throws Exception {
        return trabajaEnDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
