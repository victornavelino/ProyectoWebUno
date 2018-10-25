/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.Proyecto;
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
public class ConvocatoriaWinsipFacade extends AbstractFacade<ConvocatoriaWinsip> implements ConvocatoriaWinsipFacadeLocal {

    @PersistenceContext(unitName = "secyt-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConvocatoriaWinsipFacade() {
        super(ConvocatoriaWinsip.class);
    }

    @Override
    public List<ConvocatoriaWinsip> findAllOrderByApertura() throws Exception {
        Query q = em.createNamedQuery("ConvocatoriaWinsip.findAllOrderByApertura");
        return q.getResultList();
    }

    @Override
    public ConvocatoriaWinsip findHabilitadaProyecto(Proyecto proyecto) throws Exception {
        Query q = em.createNamedQuery("ConvocatoriaWinsip.findHabilitadaProyecto");
        q.setParameter("proyecto", proyecto);
        if(q.getResultList().size()>1){
            return (ConvocatoriaWinsip)q.getResultList().get(0);
        }else{
           return (ConvocatoriaWinsip)q.getSingleResult();
        }
        
    }

}
