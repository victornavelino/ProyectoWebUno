/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.RN;

import DAO.LineaPrioritariaFacadeLocal;
import entidades.proyecto.LineaPrioritaria;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author diego
 */
@Stateless
public class LineaPrioritariaRN implements LineaPrioritariaRNLocal {

    @EJB
    private LineaPrioritariaFacadeLocal lineaPrioritariaFacadeLocal;
    
    @Override
    public List<LineaPrioritaria> findLineasPrioritaria() throws Exception {
         return lineaPrioritariaFacadeLocal.findLineasPrioritaria();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
