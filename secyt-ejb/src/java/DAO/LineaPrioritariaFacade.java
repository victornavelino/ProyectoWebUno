/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.proyecto.AreaTematica;
import entidades.proyecto.LineaPrioritaria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author diego
 */
@Stateless
public class LineaPrioritariaFacade extends AbstractFacade<LineaPrioritaria> implements LineaPrioritariaFacadeLocal {

    @PersistenceContext(unitName = "secyt-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LineaPrioritariaFacade() {
        super(LineaPrioritaria.class);
    }

    @Override
    public List<LineaPrioritaria> findLineasPrioritaria() throws Exception {
        Query q = em.createNamedQuery("LineaPrioritaria.findLineasPrioritaria");
        return q.getResultList();
    }

    @Override
    public List<LineaPrioritaria> findLineasPrioritariaAreaTematica(AreaTematica areaTematica) throws Exception {
        Query q = em.createNamedQuery("LineaPrioritaria.findLineasPrioritariaAreaTematica");
        q.setParameter("areatematica",areaTematica);
        return q.getResultList();
    }

}
