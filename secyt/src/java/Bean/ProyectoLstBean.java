/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import entidades.convocatoriawinsip.ConvocatoriaWinsip;
import entidades.convocatoriawinsip.RN.ConvocatoriaWinsipRNLocal;
import entidades.proyecto.LogProyecto;
import entidades.proyecto.Participacion;
import entidades.proyecto.Proyecto;
import entidades.proyecto.RN.LogProyectoRNLocal;
import entidades.proyecto.RN.ProyectoRNLocal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author AFerSor
 */
@ManagedBean
@SessionScoped
public class ProyectoLstBean implements Serializable {

    @EJB
    private ProyectoRNLocal proyectoRNLocal;
    @ManagedProperty(value = "#{investigadorLoginBean}")
    private InvestigadorLoginBean investigadorLoginBean;
    private List<Proyecto> lstProyecto;
    private List<Proyecto> proyectosFiltrados;
    private List<String> aniosDeProyecto;
    private ConvocatoriaWinsip convocatoriaWinsip;
    

    @EJB
    private LogProyectoRNLocal logProyectoRNLocal;
    
    @EJB
    private ConvocatoriaWinsipRNLocal convocatoriaWinsipRNLocal;

    public ProyectoLstBean() {
    }

    public ConvocatoriaWinsip getConvocatoriaWinsip() {
        return convocatoriaWinsip;
    }

    public void setConvocatoriaWinsip(ConvocatoriaWinsip convocatoriaWinsip) {
        this.convocatoriaWinsip = convocatoriaWinsip;
    }

    public ConvocatoriaWinsipRNLocal getConvocatoriaWinsipRNLocal() {
        return convocatoriaWinsipRNLocal;
    }

    public void setConvocatoriaWinsipRNLocal(ConvocatoriaWinsipRNLocal convocatoriaWinsipRNLocal) {
        this.convocatoriaWinsipRNLocal = convocatoriaWinsipRNLocal;
    }
    

    public List<Proyecto> getProyectosFiltrados() {
        return proyectosFiltrados;
    }

    public void setProyectosFiltrados(List<Proyecto> proyectosFiltrados) {
        this.proyectosFiltrados = proyectosFiltrados;
    }

    @PostConstruct
    public void initIt() {
        cargarAniosDeProyecto();
    }

    public List<String> getAniosDeProyecto() {
        return aniosDeProyecto;
    }

    public void setAniosDeProyecto(List<String> aniosDeProyecto) {
        this.aniosDeProyecto = aniosDeProyecto;
    }

    public List<Proyecto> getLstProyecto() {
        return lstProyecto;
    }

    public void setLstProyecto(List<Proyecto> lstProyecto) {
        this.lstProyecto = lstProyecto;
    }

    public InvestigadorLoginBean getInvestigadorLoginBean() {
        return investigadorLoginBean;
    }

    public void setInvestigadorLoginBean(InvestigadorLoginBean investigadorLoginBean) {
        this.investigadorLoginBean = investigadorLoginBean;
    }
    


    public void cargarProyectosByPublicacionYInvestigador() {

        try {

            this.setLstProyecto(proyectoRNLocal.findByPublicacionYInvestigador(
                    this.getInvestigadorLoginBean().getInvestigador().getId()));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarFindByPublicacionYInvestigador

    public void cargarProyectosByIngestigadorYRol() {

        try {

            this.setLstProyecto(proyectoRNLocal.findByInvestigadorYRol(
                    this.getInvestigadorLoginBean().getInvestigador().getId(), "Director"));

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarFindByPublicacionYInvestigador
    
    public void cargarProyectosByIngestigadorRolYProyecto() {

        try {
            //consultar la ultima convocatoria winzip y traer el listado de proyectos
            List<ConvocatoriaWinsip> cwAux = this.getConvocatoriaWinsipRNLocal().findAllOrderByApertura();
            
            if(cwAux != null){
                
                //verifico que este entre la fecha actual
                SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
                
                Calendar fechaActual = Calendar.getInstance();
                fechaActual.setTime(new Date());
                
                Date fechaAct = DATE_FORMAT.parse(fechaActual.get(Calendar.DATE) + "-" + 
                        fechaActual.get(Calendar.MONTH) + "-" + 
                        fechaActual.get(Calendar.YEAR));
                

                
                Calendar fechaAper = Calendar.getInstance();
                fechaAper.setTime(cwAux.get(0).getApertura());
                
                Date fechaA = DATE_FORMAT.parse(fechaAper.get(Calendar.DATE) + "-" + 
                        fechaAper.get(Calendar.MONTH) + "-" + 
                        fechaAper.get(Calendar.YEAR));

                
                Calendar fechaCierre = Calendar.getInstance();
                fechaCierre.setTime(cwAux.get(0).getCierre());
                
                Date fechaC = DATE_FORMAT.parse(fechaCierre.get(Calendar.DATE) + "-" + 
                        fechaCierre.get(Calendar.MONTH) + "-" + 
                        fechaCierre.get(Calendar.YEAR));
                
                
                if(fechaAct.before(fechaA)){
                    throw new Exception("Todavia no esta habilitado la convocatoria winsip.");
                }
                
                if(fechaAct.after(fechaC)){
                    throw new Exception("La convocatoria winsip ya esta cerrada");
                }
                
                List<Long> lstId = new ArrayList<Long>();
                
                List<Proyecto> lstProyAux = cwAux.get(0).getProyectos();
                
                
                for(Proyecto p : lstProyAux){

                    lstId.add(p.getId());
                }
                //System.out.println("lista :"+lstId);
                //System.out.println("investigador :"+this.getInvestigadorLoginBean().getInvestigador());
                this.setConvocatoriaWinsip(cwAux.get(0));
                this.setLstProyecto(proyectoRNLocal.findByInvestigadorRolYProyecto(
                    this.getInvestigadorLoginBean().getInvestigador().getId(), "Director", 
                        lstId));
            }//fin if
            
            
            

        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
    }//fin cargarFindByPublicacionYInvestigador

    public String cargarProyectosPorCodigo(String codigo) {
        this.setLstProyecto(null);
        try {

            if (codigo != null) {
                this.setLstProyecto(proyectoRNLocal.findByCodigo(codigo));

                if (this.getLstProyecto().isEmpty()) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron proyectos", null);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, fm);
                    return null;
                }

                return "" + this.getLstProyecto().size() + " proyectos encontrados con codigo " + codigo;
            }
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
        return null;

    }

    public String cargarProyectosPorTitulo(String nombre) {
        //System.out.println("entro a cargar por nombre: " + nombre);
        this.setLstProyecto(null);
        try {

            if (nombre != null) {
                this.setLstProyecto(proyectoRNLocal.findByNombre(nombre));
                //System.out.println(this.getLstProyecto());

                if (this.getLstProyecto().isEmpty()) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron proyectos", null);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, fm);
                    return null;
                }

                return "" + this.getLstProyecto().size() + " proyectos encontrados que contienen " + nombre;
            }
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }
        return null;

    }

    public String cargarProyectosByAño(Integer anio) {
        this.setLstProyecto(null);
        try {
            if (anio != null) {
                this.setLstProyecto(proyectoRNLocal.findByAnio(anio));
                if (this.getLstProyecto().isEmpty()) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron proyectos", null);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, fm);
                    return null;
                }

                return "" + this.getLstProyecto().size() + " proyectos encontrados del año " + anio;

            } else {
                this.setLstProyecto(proyectoRNLocal.findAll());

                if (this.getLstProyecto().isEmpty()) {
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron proyectos", null);
                    FacesContext fc = FacesContext.getCurrentInstance();
                    fc.addMessage(null, fm);
                    return null;
                }

                return this.getLstProyecto().size() + " proyectos encontrados";
            }
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

        return null;
    }

    public String cargarProyectosByAñoCodigoNombre(Integer anio, String codigo, String nombre, String resumen) {
        this.setLstProyecto(null);
        try {
            //consulta
            if (anio != null) {
                //System.out.println("Entro busca con año:");
                if (codigo.isEmpty() & nombre.isEmpty() & resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByAnioNombreCodigoResumen(anio, codigo, nombre, resumen));
                    //System.out.println("entro trae todos:");
                }
                if (!codigo.isEmpty() & !nombre.isEmpty() & !resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByAnioNombreCodigoResumen(anio, codigo, nombre, resumen));
                    //System.out.println("entro todos con datos:");
                }
                if (!codigo.isEmpty() & nombre.isEmpty() & resumen.isEmpty()) {
                   //System.out.println("entro codigo:");
                    this.setLstProyecto(proyectoRNLocal.findByAnioNombreCodigo(anio, nombre, codigo));
                }
                if (codigo.isEmpty() & !nombre.isEmpty() & resumen.isEmpty()) {
                    //System.out.println("entro nombre:");
                    this.setLstProyecto(proyectoRNLocal.findByAnioNombre(anio, nombre));
                }
                if (codigo.isEmpty() & nombre.isEmpty() & !resumen.isEmpty()) {
                    //System.out.println("entro resumneen:");
                    //System.out.println("añott"+anio);
                    this.setLstProyecto(proyectoRNLocal.findByAnioResumen(anio, resumen));
                }

            } else {
                //System.out.println("Entro busca Sin año:");
                if (codigo.isEmpty() & nombre.isEmpty() & resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByNombreCodigoResumen(codigo, nombre, resumen));
                }
                if (!codigo.isEmpty() & !nombre.isEmpty() & !resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByNombreCodigoResumen(codigo, nombre, resumen));
                }
                if (!codigo.isEmpty() & nombre.isEmpty() & resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByCodigo(codigo));
                }
                if (codigo.isEmpty() & !nombre.isEmpty() & resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByNombre(nombre));
                }
                if (codigo.isEmpty() & nombre.isEmpty() & !resumen.isEmpty()) {
                    this.setLstProyecto(proyectoRNLocal.findByResumen(resumen));
                }

            }

            if (this.getLstProyecto().isEmpty()) {
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "No se encontraron proyectos", null);
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, fm);
                return null;
            }

            //alta del log
            LogProyecto logProyecto = new LogProyecto();
            logProyecto.setFecha(new Date());
            logProyecto.setAnio(anio);
            logProyecto.setCodigo((codigo.isEmpty() ? null : codigo));
            logProyecto.setNombre((nombre.isEmpty() ? null : nombre));

            logProyectoRNLocal.create(logProyecto);

            return this.getLstProyecto().size() + " proyectos encontrados";
        } catch (Exception ex) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + ex.getMessage(), null);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, fm);
        }

        return null;
    }

    private boolean isNumeric(String cadena) {
        try {
            int parseInt = Integer.parseInt(cadena);

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void cargarAniosDeProyecto() {
        try {
            List<String> anios = new ArrayList<String>();

            for (Integer prueba : proyectoRNLocal.getAnios()) {
                if (prueba != null) {
                    anios.add(prueba.toString());
                }
            }
            setAniosDeProyecto(anios);
        } catch (Exception ex) {
            //System.out.println("Error: " + ex);
        }
    }//fin cargar anios
}//FIN CLASE
