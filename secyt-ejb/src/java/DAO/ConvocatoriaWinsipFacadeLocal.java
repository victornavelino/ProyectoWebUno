/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.Proyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface ConvocatoriaWinsipFacadeLocal {

    void create(ConvocatoriaWinsip convocatoriaWinsip);

    void edit(ConvocatoriaWinsip convocatoriaWinsip);

    void remove(ConvocatoriaWinsip convocatoriaWinsip);

    ConvocatoriaWinsip find(Object id);

    List<ConvocatoriaWinsip> findAll();

    List<ConvocatoriaWinsip> findRange(int[] range);

    int count();
    
    List<ConvocatoriaWinsip> findAllOrderByApertura() throws Exception;
    
    ConvocatoriaWinsip findHabilitadaProyecto(Proyecto proyecto) throws Exception;
    
}
