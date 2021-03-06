/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.economico.PagoEconomico;
import entidades.economico.Rendicion;
import entidades.persona.investigador.Investigador;
import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AFerSor
 */
@Stateless
public class ProyectoFacade extends AbstractFacade<Proyecto> implements ProyectoFacadeLocal {

    @PersistenceContext(unitName = "secyt-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }

    @Override
    public List<Proyecto> findByPublicacionYInvestigador(Long idInvestigador) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByPublicacionYInvestigador");
        q.setParameter("idInvestigador", idInvestigador);
        return q.getResultList();
    }//fin findByPublicacionYInvestigador

    @Override
    public List<Proyecto> findByInvestigadorYRol(Long idInvestigador, String rol) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByInvestigadorYRol");
        q.setParameter("idInvestigador", idInvestigador);
        q.setParameter("rol", rol);
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByInvestigadorRolYProyecto(Long idInvestigador, String rol, List<Long> listaProyectos) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByInvestigadorRolYProyecto");
        q.setParameter("idInvestigador", idInvestigador);
        q.setParameter("rol", rol);
        q.setParameter("listaProyectos", listaProyectos);
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByAnio(Integer anio) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByAnio");
        q.setParameter("anio", String.valueOf(anio));
        return q.getResultList();
    }//fin findByAnio

    @Override
    public List<Integer> getAnios() throws Exception {
        Query q = em.createNamedQuery("Proyecto.getAnios");
        return q.getResultList();
    }//fin getAnios

    @Override
    public String getDirector(Proyecto proy) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM Participacion pa "
                    + "WHERE pa.rol.descripcion=:car AND "
                    + "((pa.fechaHasta IS NULL  OR pa.fechaHasta > :fecha ) OR pa.proyecto.fechaFinalizacion < :fecha ) AND pa.rol.descripcion=:car AND "
                    + "pa.proyecto=:proye ORDER BY pa.id DESC");
            quDirector.setParameter("car", "Director");
            quDirector.setParameter("fecha", new Date());
            quDirector.setParameter("proye", proy);
            Participacion pa = (Participacion) quDirector.getResultList().get(0);
            if (pa != null) {
                return pa.getInvestigador().toString();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Rendicion> getRendicionesOrdenadasPorFecha(PagoEconomico pe) throws Exception {
        Query qRendicion = em.createQuery("SELECT r FROM Rendicion r WHERE r.pagoEconomico=:pe ORDER BY r.fechaRendicion DESC");
        qRendicion.setParameter("pe", pe);
        return qRendicion.getResultList();
    }

    @Override
    public List<Proyecto> findByNombre(String nombre) {
        Query q = em.createNamedQuery("Proyecto.findByNombre");
        q.setParameter("nombre", "%" + nombre.trim() + "%");
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByNombreCodigo(String nombre, String codigo) {
        Query q = em.createNamedQuery("Proyecto.findByNombreCodigo");
        q.setParameter("nombre", "%" + nombre.trim() + "%");
        q.setParameter("codigo", "%" + codigo.trim() + "%");
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByAnioNombreCodigo(Integer anio, String nombre, String codigo) {
        Query q = em.createNamedQuery("Proyecto.findByNombreCodigoyAnio");
        q.setParameter("nombre", "%" + nombre.trim() + "%");
        q.setParameter("codigo", "%" + codigo.trim() + "%");
        q.setParameter("anio", String.valueOf(anio));
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByCodigo(String codigo) {
        Query q = em.createNamedQuery("Proyecto.findByCodigo");
        q.setParameter("codigo", "%" + codigo.trim() + "%");
        return q.getResultList();
    }

    @Override
    public List<Proyecto> findByAnioCodigo(Integer anio, String codigo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Proyecto> findByAnioNombre(Integer anio, String nombre) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByNombreyAnio");
        q.setParameter("nombre", "%" + nombre.trim() + "%");
        q.setParameter("anio", String.valueOf(anio));
        return q.getResultList();
    }

    @Override
    public Investigador getDirectorInvestigador(Proyecto proyecto) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM Participacion pa "
                    + "WHERE pa.rol.descripcion=:car AND "
                    + "((pa.fechaHasta IS NULL  OR pa.fechaHasta > :fecha ) OR pa.proyecto.fechaFinalizacion < :fecha ) AND pa.rol.descripcion=:car AND "
                    + "pa.proyecto=:proye ORDER BY pa.id DESC");
            quDirector.setParameter("car", "Director");
            quDirector.setParameter("fecha", new Date());
            quDirector.setParameter("proye", proyecto);
            Participacion pa = (Participacion) quDirector.getResultList().get(0);
            if (pa != null) {
                return pa.getInvestigador();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Investigador getDirectorInvestigadorSinFiltro(Proyecto proyecto) {
        try {
            Query quDirector = em.createQuery("SELECT pa FROM Participacion pa "
                    + "WHERE pa.rol.descripcion=:car AND pa.proyecto=:proye ORDER BY pa.id DESC");
            quDirector.setParameter("car", "Director");
            quDirector.setParameter("proye", proyecto);
            Participacion pa = (Participacion) quDirector.getResultList().get(0);
            if (pa != null) {
                return pa.getInvestigador();
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Proyecto> findByAnioNombreCodigoResumen(Integer anio,
            String codigo, String nombre, String resumen) throws Exception {
        String[] palabras = nombre.split("\\s+");
        Query q = null;
        if (palabras.length < 2 || palabras.length > 3) {
            q = em.createNamedQuery("Proyecto.findByAnioNombreCodigoResumen");
            q.setParameter("anio", String.valueOf(anio));
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + nombre + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        } else if (palabras.length == 2) {
            q = em.createNamedQuery("Proyecto.findByAnioNombreCodigoResumen2");
            q.setParameter("anio", String.valueOf(anio));
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + palabras[0] + "%");
            q.setParameter("nombre2", "%" + palabras[1] + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        } else if (palabras.length == 3) {
            q = em.createNamedQuery("Proyecto.findByAnioNombreCodigoResumen3");
            q.setParameter("anio", String.valueOf(anio));
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + palabras[0] + "%");
            q.setParameter("nombre2", "%" + palabras[1] + "%");
            q.setParameter("nombre3", "%" + palabras[2] + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        }
        return null;
    }

    @Override
    public List<Proyecto> findByNombreCodigoResumen(String codigo, String nombre, String resumen) throws Exception {

        String[] palabras = nombre.split("\\s+");
        Query q = null;
        if (palabras.length < 2 || palabras.length > 3) {
            q = em.createNamedQuery("Proyecto.findByNombreCodigoResumen");
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + nombre + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        } else if (palabras.length == 2) {
            q = em.createNamedQuery("Proyecto.findByNombreCodigoResumen2");
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + palabras[0] + "%");
            q.setParameter("nombre2", "%" + palabras[1] + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        } else if (palabras.length == 3) {
            q = em.createNamedQuery("Proyecto.findByNombreCodigoResumen3");
            q.setParameter("codigo", "%" + codigo + "%");
            q.setParameter("nombre", "%" + palabras[0] + "%");
            q.setParameter("nombre2", "%" + palabras[1] + "%");
            q.setParameter("nombre3", "%" + palabras[2] + "%");
            q.setParameter("resumen", "%" + resumen + "%");
            return q.getResultList();
        }
        return null;
    }

    @Override
    public List<Proyecto> findByAnioResumen(Integer anio, String resumen) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByAnioResumen");
        q.setParameter("anio", "%" + anio + "%");
        q.setParameter("resumen", "%" + resumen + "%");
        return q.getResultList();

    }

    @Override
    public List<Proyecto> findByResumen(String resumen) throws Exception {
        Query q = em.createNamedQuery("Proyecto.findByResumen");
        q.setParameter("resumen", "%" + resumen + "%");
        return q.getResultList();
    }

    @Override
    public List<Investigador> getTodosIntegrantes(Proyecto proyecto) throws Exception {
        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa "
                + "WHERE pa.proyecto.id=" + proyecto.getId());
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {
            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());
            }
        }
        return integrantes;

    }

    @Override
    public Proyecto findByID(Long id) {
        Query q = em.createQuery("SELECT p FROM Proyecto p where p.id=:id");
        q.setParameter("id", id);
        return (Proyecto) q.getSingleResult();
    }

    @Override
    public List<Investigador> getTodosIntegrantesVigentes(Proyecto proyecto) {
        proyecto = this.findByID(proyecto.getId());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date inicioWinsip = new Date();
        Date finWinsip = new Date();

        Query quParticipaciones = em.createQuery("SELECT pa FROM Participacion pa WHERE pa.proyecto.id=:proyectoId and pa.fechaHasta BETWEEN  pa.proyecto.fechaInicio AND :fechaFinProyecto and pa.fechaHasta BETWEEN :inicioWinsip AND :finWinsip OR pa.fechaDesde BETWEEN :inicioWinsip AND :finWinsip");
        quParticipaciones.setParameter("proyectoId", proyecto.getId());
        try {
            inicioWinsip = formatoFecha.parse("2016-01-01");
            finWinsip = formatoFecha.parse("2017-12-31");
        } catch (ParseException ex) {
            Logger.getLogger(ProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("inicio fecha winsip: " + inicioWinsip);
        System.out.println("fin fecha winsip: " + finWinsip);
        quParticipaciones.setParameter("inicioWinsip", inicioWinsip);
        quParticipaciones.setParameter("finWinsip", finWinsip);

        if (!proyecto.getProrrogas().isEmpty()) {
            int cantidadProrrogas = proyecto.getProrrogas().size();
            quParticipaciones.setParameter("fechaFinProyecto", proyecto.getProrrogas().get(cantidadProrrogas - 1).getFecha());
        } else {
            quParticipaciones.setParameter("fechaFinProyecto", proyecto.getFechaFinalizacion());
        }
        List<Participacion> participaciones = quParticipaciones.getResultList();
        List<Investigador> integrantes = new ArrayList<Investigador>();
        for (Participacion participacion : participaciones) {
            if (!integrantes.contains(participacion.getInvestigador())) {
                integrantes.add(participacion.getInvestigador());
            }
        }
        return integrantes;
    }
}
