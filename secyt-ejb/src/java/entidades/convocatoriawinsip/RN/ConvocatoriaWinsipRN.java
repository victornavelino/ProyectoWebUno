/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.convocatoriawinsip.RN;

import DAO.ConvocatoriaWinsipFacadeLocal;
import entidades.convocatoriawinsip.ConvocatoriaWinsip;
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



    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
