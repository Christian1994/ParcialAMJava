/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Local;
import modelo.Dependiente;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface DependienteFacadeLocal {

    void create(Dependiente dependiente);

    void edit(Dependiente dependiente);

    void remove(Dependiente dependiente);

    Dependiente find(Object id);

    List<Dependiente> findAll();

    List<Dependiente> findRange(int[] range);

    int count();
    
}
