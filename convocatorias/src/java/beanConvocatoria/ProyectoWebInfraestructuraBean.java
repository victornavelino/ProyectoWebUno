/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanConvocatoria;

import entidades.persona.investigador.Investigador;
import entidades.persona.investigador.RN.InvestigadorRNLocal;
import entidades.proyectoWeb.ProyectoWebInfraestructura;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;
import recursos.MensajeBean;


/**
 *
 * @author diego
 */
@ManagedBean
@RequestScoped
public class ProyectoWebInfraestructuraBean {

    private ProyectoWebInfraestructura proyectoWebInfraestructura;
    
    @ManagedProperty("#{proyectoWebBean}")
    private ProyectoWebBean proyectoWebBean;
    
    @ManagedProperty("#{mensajeBean}")
    private MensajeBean mensajeBean;
    
    
    public ProyectoWebInfraestructuraBean() {
        proyectoWebInfraestructura = new ProyectoWebInfraestructura();
    }

    public MensajeBean getMensajeBean() {
        return mensajeBean;
    }

    public void setMensajeBean(MensajeBean mensajeBean) {
        this.mensajeBean = mensajeBean;
    }
    
    

    public ProyectoWebBean getProyectoWebBean() {
        return proyectoWebBean;
    }

    public void setProyectoWebBean(ProyectoWebBean proyectoWebBean) {
        this.proyectoWebBean = proyectoWebBean;
    }
    
    

    public ProyectoWebInfraestructura getProyectoWebInfraestructura() {
        return proyectoWebInfraestructura;
    }

    public void setProyectoWebInfraestructura(ProyectoWebInfraestructura proyectoWebInfraestructura) {
        this.proyectoWebInfraestructura = proyectoWebInfraestructura;
    }
    
    public void agregarInfraestructura() {
        try {
            if (this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura() == null) {
                this.getProyectoWebBean().getProyectoWeb().setListaProyectoWebInfraestructura(new ArrayList<ProyectoWebInfraestructura>());
            }//fn if
            
            if(this.getProyectoWebInfraestructura().getAnoEjecucion() == null){
                throw new Exception("No ingreso el año");
            }
            
            if(this.getProyectoWebInfraestructura().getInfraEdilicia() == null){
                throw new Exception("No ingreso el campo edilicia");
            }
            
            if(this.getProyectoWebInfraestructura().getInfraEdilicia().isEmpty()){
                throw new Exception("No ingreso el campo edilicia");
            }
            
            if(this.getProyectoWebInfraestructura().getEquipamiento() == null){
                throw new Exception("No ingreso el campo equipamiento");
            }
            
            if(this.getProyectoWebInfraestructura().getEquipamiento().isEmpty()){
                throw new Exception("No ingreso el campo equipamiento");
            }
            
            
            //verificar que no esten repetidas
             int tamanio = this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura().size();
        for(int i=0; i < tamanio; i++){
            if(this.getProyectoWebInfraestructura().getAnoEjecucion().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getAnoEjecucion()) && 
               this.getProyectoWebInfraestructura().getInfraEdilicia().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getInfraEdilicia()) &&
               this.getProyectoWebInfraestructura().getEquipamiento().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getEquipamiento())){
                
                throw new Exception("Ya existe un dato cargado con los datos ingresados");
                
            }//fin if
        }//fin for
            

            this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura().add(
                    this.getProyectoWebInfraestructura());
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("frmUser:dtInfraestructura");
            
        } catch (Exception ex) {
            this.getMensajeBean().setMensaje("Error: " + ex.getMessage());

            RequestContext.getCurrentInstance().update("frmAdmin:dMensaje");
            RequestContext.getCurrentInstance().execute("dlgMensaje.show()");
        }

    }//fin agregarInfraestructura
    
    public void quitarInfraestructura(ProyectoWebInfraestructura pi) {
        System.out.println("Listado infra: " + this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura());
        int tamanio = this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura().size();
        for(int i=0; i < tamanio; i++){
            if(pi.getAnoEjecucion().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getAnoEjecucion()) && 
               pi.getInfraEdilicia().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getInfraEdilicia()) &&
               pi.getEquipamiento().equals(this.getProyectoWebBean().getProyectoWeb().
                    getListaProyectoWebInfraestructura().get(i).getEquipamiento())){
                
                this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura().remove(i);
                
            }//fin if
        }//fin for
        
        
        System.out.println("Listado infra 2: " + this.getProyectoWebBean().getProyectoWeb().getListaProyectoWebInfraestructura());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("frmUser:dtInfraestructura");
    }//fin quitarUnidadInvestigacion
    
    
    
}
