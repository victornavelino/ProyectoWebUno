/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.RN;

import DAO.ContratoFacadeLocal;
import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.proyecto.resultado.Contrato;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author alumno
 */
@Stateless
public class ContratoRN implements ContratoRNLocal {

    @EJB
    private ContratoFacadeLocal contratoFacadeLocal;

    @Override
    public List<Contrato> findByTypeYProyecto(Long idProyecto, Long idInvestigador) throws Exception {
        return (this.contratoFacadeLocal.findByTypeYProyecto(idProyecto, idInvestigador));

    }

    @Override
    public void create(Contrato contrato,  ConvocatoriaWinsip convocatoriaWinsip) throws Exception {
        this.validar(contrato, 0,convocatoriaWinsip);
        this.contratoFacadeLocal.create(contrato);
    }

    @Override
    public void edit(Contrato contrato,  ConvocatoriaWinsip convocatoriaWinsip) throws Exception {
        this.validar(contrato, 1,convocatoriaWinsip);
        this.contratoFacadeLocal.edit(contrato);
    }

    @Override
    public void remove(Contrato contrato) throws Exception {
        this.contratoFacadeLocal.remove(contrato);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private void validar(Contrato con, int op,ConvocatoriaWinsip convocatoriaWinsip) throws Exception {
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
        if (con.getObjeto().trim().length() == 0) {
            throw new Exception("Debe ingresar Objeto del contrato");
        }
        if (con.getDestinatarios().trim().length() == 0) {
            throw new Exception("Debe ingresar Destinatarios");
        }
        if (con.getFechaContrato() == null) {
            throw new Exception("Debe Ingresar fecha de Contrato");
        }
        if (con.getTipoContrato() == null) {
            throw new Exception("Debe Seleccionar Tipo de Contrato");
        }
//        if (con.getFechaContrato().after(convocatoriaWinsip.getFechaEvaluadaFin()) && con.getFechaContrato().before(convocatoriaWinsip.getFechaEvaluadaInicio())) {
//            throw new Exception("La fecha debe ser en el año 2015");
//        }

    }//fin validar
}
