/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author nago
 */
@Stateless
public class ParticipacionFacade extends AbstractFacade<Participacion> implements ParticipacionFacadeLocal {

    @PersistenceContext(unitName = "secyt-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipacionFacade() {
        super(Participacion.class);
    }

    @Override
    public List<Participacion> buscarParticipacionesActivas(Proyecto proyecto, ConvocatoriaWinsip convocatoriaWinsip) throws Exception {
        //SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Query q = em.createNamedQuery("Participacion.buscarParticipacionesActivas");
        q.setParameter("proyecto", proyecto);
        q.setParameter("inicioWinsip", convocatoriaWinsip.getFechaEvaluadaInicio());
        q.setParameter("finWinsip", convocatoriaWinsip.getFechaEvaluadaFin());

//        if (proyecto.getProrrogas()!=null) {
//            int cantidadProrrogas = proyecto.getProrrogas().size();
//            q.setParameter("fechaFinProyecto", proyecto.getProrrogas().get(cantidadProrrogas - 1).getFecha());
//        } else {
//            q.setParameter("fechaFinProyecto", proyecto.getFechaFinalizacion());
//        }
        List<Participacion> listaParticipaciones = q.getResultList();
//        for (ProyectoWeb pw : lstP) {
//            em.refresh(pw);
//        }//fin for

        return listaParticipaciones;
    }

    @Override
    public List<Investigador> buscarInvestigadoresActivos(Proyecto proyecto, ConvocatoriaWinsip convocatoriaWinsip) throws Exception {
        List<Participacion> listaParticipaciones = this.buscarParticipacionesActivas(proyecto, convocatoriaWinsip);
        List<Investigador> investigadores = new ArrayList<>();
        for (Participacion p : listaParticipaciones) {
            if (p.getInvestigador() != null) {
                investigadores.add(p.getInvestigador());
            }
        }
        System.out.println("LISTA PICKLIST INVESTIGADORES: "+investigadores);
        return investigadores;
    }

}
