/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Dependiente;
import modelo.DependientePK;
import persistencia.DependienteFacadeLocal;

/**
 *
 * @author Estudiante Univalle
 */
@Stateless
public class DependienteLogica implements DependienteLogicaLocal {

    @EJB
    DependienteFacadeLocal dependienteDAO;

    DependientePK dependientePK;
    
    @Override
    public void registrarDependiente(Dependiente dependiente) throws Exception {
        
        if(dependiente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(dependiente.getDependientePK().getNsse() == 0){
                throw new Exception("Campo Documento Dependiente Obligatorio.");
            }
            if(dependiente.getDependientePK().getNombreDependiente().equals("") || dependiente.getDependientePK().getNombreDependiente() == null){
                throw new Exception("Campo Nombre Dependiente Obligatorio.");
            }
            if(dependiente.getSexo().equals("") || dependiente.getSexo() == null){
                throw new Exception("Campo Sexo Obligatorio.");
            }
            if(dependiente.getFechan() == null){
                throw new Exception("Campo Fecha de Nacimiento Obligatorio.");
            }
            if(dependiente.getParentesco().equals("") || dependiente.getParentesco() == null){
                throw new Exception("Campo Parentesco Obligatorio.");
            }
        }
        
        Dependiente objDependiente = dependienteDAO.find(dependiente.getDependientePK());
        if(objDependiente != null){
            throw new Exception("Dependiente ya existe.");
        }
        else{
            dependienteDAO.create(dependiente);
        }
    }

    @Override
    public void modificarDependiente(Dependiente dependiente) throws Exception {
        if(dependiente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(dependiente.getDependientePK().getNsse() == 0){
                throw new Exception("Campo Documento Dependiente Obligatorio.");
            }
            if(dependiente.getDependientePK().getNombreDependiente().equals("") || dependiente.getDependientePK().getNombreDependiente() == null){
                throw new Exception("Campo Nombre Dependiente Obligatorio.");
            }
            if(dependiente.getSexo().equals("") || dependiente.getSexo() == null){
                throw new Exception("Campo Sexo Obligatorio.");
            }
            if(dependiente.getFechan() == null){
                throw new Exception("Campo Fecha de Nacimiento Obligatorio.");
            }
            if(dependiente.getParentesco().equals("") || dependiente.getParentesco() == null){
                throw new Exception("Campo Parentesco Obligatorio.");
            }
        }
        
        Dependiente objDependiente = dependienteDAO.find(dependiente.getDependientePK());
        if(objDependiente == null){
            throw new Exception("Dependiente a modificar no existe.");
        }
        else{
            objDependiente.setSexo(dependiente.getSexo());
            objDependiente.setFechan(dependiente.getFechan());
            objDependiente.setParentesco(dependiente.getParentesco());
            dependienteDAO.edit(dependiente);
        }
    }

    @Override
    public void eliminarDependiente(Dependiente dependiente) throws Exception {
        if(dependiente == null){
            throw new Exception("Campos vacíos.");
        }
        else{
            if(dependiente.getDependientePK() == null){
                throw new Exception("El documento de Dependiente es Obligatorio.");
            }
        }
        
        Dependiente objDependiente = dependienteDAO.find(dependiente.getDependientePK());
        if(objDependiente == null){
            throw new Exception("El Dependiente a eliminar no existe.");
        }
        else{
            dependienteDAO.remove(dependiente);
        }
    }

    @Override
    public List<Dependiente> consultarTodos() throws Exception {
        return dependienteDAO.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
