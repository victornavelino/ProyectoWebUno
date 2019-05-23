/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entidades.proyecto.LineaPrioritaria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author diego
 */
@Local
public interface LineaPrioritariaFacadeLocal {

    void create(LineaPrioritaria lineaPrioritaria);

    void edit(LineaPrioritaria lineaPrioritaria);

    void remove(LineaPrioritaria lineaPrioritaria);

    LineaPrioritaria find(Object id);

    List<LineaPrioritaria> findAll();

    List<LineaPrioritaria> findRange(int[] range);

    int count();
    
    List<LineaPrioritaria> findLineasPrioritaria() throws Exception;
    
}
