/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto;

import entidades.persona.investigador.Investigador;
import entidades.proyecto.vinculacion.ProyectoVinculacion;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "participacion")
@NamedQueries({
    @NamedQuery(name = "Participacion.buscarParticipacionesActivas",
            query = "SELECT p FROM Participacion p WHERE p.proyecto=:proyecto and ((p.fechaDesde BETWEEN :inicioWinsip AND :finWinsip OR p.fechaHasta BETWEEN :inicioWinsip AND :finWinsip) OR (p.fechaDesde <=:inicioWinsip AND p.fechaHasta >=:finWinsip))")})
public class Participacion implements Serializable, Comparable<Participacion> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "FECHADESDE")
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Column(name = "FECHAHASTA")
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    @JoinColumn(name = "INVESTIGADOR_ID")
    @OneToOne
    private Investigador investigador;
    @JoinColumn(name = "PROYECTO_ID")
    @OneToOne
    private Proyecto proyecto;
    @JoinColumn(name = "ROL_ID")
    @OneToOne
    private Rol rol;
    @Column(name = "DEDICACIONSEMANAL")
    private Integer dedicacionSemanal;

    public Integer getDedicacionSemanal() {
        return dedicacionSemanal;
    }

    public void setDedicacionSemanal(Integer dedicacionSemanal) {
        this.dedicacionSemanal = dedicacionSemanal;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public Investigador getInvestigador() {
        return investigador;
    }

    public void setInvestigador(Investigador investigador) {
        this.investigador = investigador;
    }

    @XmlTransient
    public Proyecto getProyecto() {
        return proyecto;
    }

    @XmlElement(name = "proyecto")
    public Long getProyectoFromRest() {
        try {
            return proyecto.getId();
        } catch (Exception e) {
            return null;
        }
    }

    @XmlElement(name = "proyectoNombre")
    public String getProyectoNombreFromRest() {
        try {
            return proyecto.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participacion)) {
            return false;
        }
        Participacion other = (Participacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String fechaDesd = "";
        String fechaHast = "";
        if (fechaDesde != null) {
            fechaDesd = new SimpleDateFormat("dd/MM/yyyy").format(fechaDesde);
        }
        if (fechaHasta != null) {
            fechaHast = new SimpleDateFormat("dd/MM/yyyy").format(fechaHasta);
        } else {
            fechaHast = "Actualidad";
        }
        String codigoProyecto = "";
        try {
            if (proyecto.getCodigoIncentivos() != null || !proyecto.getCodigoIncentivos().equals("")) {
                codigoProyecto = proyecto.getCodigoIncentivos();
            } else if (proyecto.getCodigo() != null || !proyecto.getCodigo().equals("")) {
                codigoProyecto = proyecto.getCodigo();
            }
        } catch (Exception ex) {
        }
        try {
            if (!codigoProyecto.equals("")) {
                return "Proyecto: " + codigoProyecto + ": " + investigador.getPersona().toString() + " - " + rol + " - " + fechaDesd + " - " + fechaHast;
            } else {
                return investigador.getPersona().toString() + " - " + rol + " - " + fechaDesd + " - " + fechaHast;
            }
        } catch (Exception ex) {
            return "";
        }

    }
    @Override
    public int compareTo(Participacion p) {
        
        Long idRol = this.getRol().getId();
        if (idRol < p.getRol().getId()) {
            return -1;
        } 
            
        return 1;

    }
}
