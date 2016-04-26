/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Local;
import modelo.LugaresDptos;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface LugaresDptosFacadeLocal {

    void create(LugaresDptos lugaresDptos);

    void edit(LugaresDptos lugaresDptos);

    void remove(LugaresDptos lugaresDptos);

    LugaresDptos find(Object id);

    List<LugaresDptos> findAll();

    List<LugaresDptos> findRange(int[] range);

    int count();
    
}
