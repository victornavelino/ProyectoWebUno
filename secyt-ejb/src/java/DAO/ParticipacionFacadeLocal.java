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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author nago
 */
@Local
public interface ParticipacionFacadeLocal {

    void create(Participacion participacion);

    void edit(Participacion participacion);

    void remove(Participacion participacion);

    Participacion find(Object id);

    List<Participacion> findAll();

    List<Participacion> findRange(int[] range);

    int count();
    
    List<Participacion> buscarParticipacionesActivas(Proyecto proyecto,ConvocatoriaWinsip convocatoriaWinsip) throws Exception;
    List<Investigador> buscarInvestigadoresActivos(Proyecto proyecto,ConvocatoriaWinsip convocatoriaWinsip) throws Exception;
}
