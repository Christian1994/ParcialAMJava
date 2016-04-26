/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Empleado;
import org.apache.commons.codec.digest.DigestUtils;
import persistencia.EmpleadoFacadeLocal;

/**
 *
 * @author Empleado Univalle
 */
@Stateless
public class SesionLogica implements SesionLogicaLocal {

    @EJB
    private EmpleadoFacadeLocal empleadoDAO;
    
    @Override
    public void buscarCamposInvalidosOVacios(Long nss, String clave) throws Exception {
        if(nss == null) {
            throw new Exception("Ingrese un usuario válido.");
        }
        if(clave.equals("")) {
            throw new Exception("La clave es obligatoria.");
        }
    }

    @Override
    public Empleado iniciarSesionEmpleado(Long nss, String clave) throws Exception {
        Empleado e = empleadoDAO.find(nss);
            String claveEncriptada = DigestUtils.md5Hex(clave);
            if(!e.getClave().equals(claveEncriptada)) {
                throw new Exception("La clave es incorrecta.");
            }
        return e;  
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
