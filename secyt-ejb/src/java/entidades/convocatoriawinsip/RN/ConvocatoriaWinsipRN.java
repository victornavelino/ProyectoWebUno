/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.convocatoriawinsip.RN;

import DAO.ConvocatoriaWinsipFacadeLocal;
import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.Proyecto;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author diego
 */
@Stateless
public class ConvocatoriaWinsipRN implements ConvocatoriaWinsipRNLocal {

    @EJB
    private ConvocatoriaWinsipFacadeLocal convocatoriaWinsipFacadeLocal;

    @Override
    public List<ConvocatoriaWinsip> findAllOrderByApertura() throws Exception {
        return convocatoriaWinsipFacadeLocal.findAllOrderByApertura();
    }

    @Override
    public ConvocatoriaWinsip findHabilitada() throws Exception {
        return convocatoriaWinsipFacadeLocal.findHabilitada();
    }

    @Override
    public ConvocatoriaWinsip findHabilitadaProyecto(Proyecto proyecto) throws Exception {
        return convocatoriaWinsipFacadeLocal.findHabilitadaProyecto(proyecto);
    }

}
