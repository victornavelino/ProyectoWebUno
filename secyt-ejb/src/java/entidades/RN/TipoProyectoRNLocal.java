/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.RN;

import entidades.TipoProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface TipoProyectoRNLocal {
    List<TipoProyecto> findTiposProyectos() throws Exception;
}
