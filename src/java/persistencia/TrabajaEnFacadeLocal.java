/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.ejb.Local;
import modelo.TrabajaEn;

/**
 *
 * @author Estudiante Univalle
 */
@Local
public interface TrabajaEnFacadeLocal {

    void create(TrabajaEn trabajaEn);

    void edit(TrabajaEn trabajaEn);

    void remove(TrabajaEn trabajaEn);

    TrabajaEn find(Object id);

    List<TrabajaEn> findAll();

    List<TrabajaEn> findRange(int[] range);

    int count();
    
}
