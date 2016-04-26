/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Dependiente;

/**
 *
 * @author Estudiante Univalle
 */
@Stateless
public class DependienteFacade extends AbstractFacade<Dependiente> implements DependienteFacadeLocal {

    @PersistenceContext(unitName = "ParcialAMJavaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DependienteFacade() {
        super(Dependiente.class);
    }
    
}
