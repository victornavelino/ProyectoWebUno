/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.convocatoriawinsip.RN;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface ConvocatoriaWinsipRNLocal {

    List<ConvocatoriaWinsip> findAllOrderByApertura() throws Exception;


}
