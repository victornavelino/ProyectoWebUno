/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanConvocatoria;

import entidades.proyecto.AreaTematica;
import entidades.proyecto.LineaPrioritaria;
import entidades.proyecto.RN.LineaPrioritariaRNLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author diego
 */
@ManagedBean
@SessionScoped
public class LineaPrioritariaLstBean implements Serializable {

    private List<LineaPrioritaria> lstLineaPrioritaria;

    private List<SelectItem> lstSILineaPrioritaria;

    @EJB
    private LineaPrioritariaRNLocal lineaPrioritariaRNLocal;

    public LineaPrioritariaLstBean() {
    }

    public LineaPrioritariaRNLocal getLineaPrioritariaRNLocal() {
        return lineaPrioritariaRNLocal;
    }

    public void setLineaPrioritariaRNLocal(LineaPrioritariaRNLocal lineaPrioritariaRNLocal) {
        this.lineaPrioritariaRNLocal = lineaPrioritariaRNLocal;
    }

    public List<LineaPrioritaria> getLstLineaPrioritaria() {
        return lstLineaPrioritaria;
    }

    public void setLstLineaPrioritaria(List<LineaPrioritaria> lstLineaPrioritaria) {
        this.lstLineaPrioritaria = lstLineaPrioritaria;
    }

    public List<SelectItem> getLstSILineaPrioritaria() {
        return lstSILineaPrioritaria;
    }

    public void setLstSILineaPrioritaria(List<SelectItem> lstSILineaPrioritaria) {
        this.lstSILineaPrioritaria = lstSILineaPrioritaria;
    }

    public void findLineasDePrioritaria() throws Exception {
        this.setLstLineaPrioritaria(this.lineaPrioritariaRNLocal.findLineasPrioritaria());
    }//fin findLineasDePrioritaria

    public void cargarSILineaPrioritaria() throws Exception {
        this.setLstSILineaPrioritaria(new ArrayList<SelectItem>());
        int i = 0;
        for (LineaPrioritaria li : this.getLstLineaPrioritaria()) {
            if (i <= 7) {
                if (!li.getDescripcion().trim().isEmpty()) {
                    SelectItem si = new SelectItem(li, li.getDescripcion());
                    this.getLstSILineaPrioritaria().add(si);
                }//fin if
            } else {
                break;
            }
            i++;

        }//fin for
    }//fin cargarSILineaPrioritaria

    public void cargarSILineaPrioritariaAreaTematica(AreaTematica areaTematica) throws Exception {
        this.setLstLineaPrioritaria(this.lineaPrioritariaRNLocal.findLineasPrioritariaAreaTematica(areaTematica));
        this.setLstSILineaPrioritaria(new ArrayList<SelectItem>());
        int i = 0;
        for (LineaPrioritaria li : this.getLstLineaPrioritaria()) {
            if (i <= 7) {
                if (!li.getDescripcion().trim().isEmpty()) {
                    SelectItem si = new SelectItem(li, li.getDescripcion());
                    this.getLstSILineaPrioritaria().add(si);
                }//fin if
            } else {
                break;
            }
            i++;

        }//fin for
    }//fin cargarSILineaPrioritaria
}
