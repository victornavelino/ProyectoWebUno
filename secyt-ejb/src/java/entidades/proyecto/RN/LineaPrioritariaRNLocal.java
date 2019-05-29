/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.RN;

import entidades.proyecto.AreaTematica;
import entidades.proyecto.LineaPrioritaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface LineaPrioritariaRNLocal {
    List<LineaPrioritaria> findLineasPrioritaria() throws Exception;
    List<LineaPrioritaria> findLineasPrioritariaAreaTematica(AreaTematica areaTematica) throws Exception;
}
