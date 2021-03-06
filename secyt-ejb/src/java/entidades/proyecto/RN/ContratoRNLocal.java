/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.RN;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.resultado.Contrato;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alumno
 */
@Local
public interface ContratoRNLocal {
    public List<Contrato> findByTypeYProyecto(Long idProyecto,Long idInvestigador) throws Exception;
    public void create (Contrato contrato, ConvocatoriaWinsip convocatoriaWinsip) throws Exception;
    public void edit (Contrato contrato,  ConvocatoriaWinsip convocatoriaWinsip) throws Exception;

    public void remove(Contrato contrato) throws Exception;
}
