/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

import entidades.localidad.Localidad;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author franco
 */
@Inheritance(strategy= InheritanceType.JOINED)
@Entity
@Table(name = "resultado_libro")
@NamedQueries({
    @NamedQuery(name="Libro.findByLibro", 
        query="SELECT p FROM Libro p")
})
public class Libro extends Publicacion implements Serializable {
    @OneToMany(mappedBy = "libro")
    private List<CapituloLibro> capitulos;


    private String editor;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEnviado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaAceptado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPublicado;
    @Enumerated(EnumType.STRING)
    private TipoEdicion tipoEdicion;
    private String lugarPublicacion;
    private String anioPublicacion;
    private String ISBN;
    
    @Enumerated(EnumType.STRING)
    private TipoReferato tipoReferato;
    @Enumerated(EnumType.STRING)
    private TipoAmbitoPublicacion tipoAmbitoPublicacion;
    @OneToOne
    private Localidad localidad;

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    

    public TipoReferato getTipoReferato() {
        return tipoReferato;
    }

    public void setTipoReferato(TipoReferato tipoReferato) {
        this.tipoReferato = tipoReferato;
    }

    public TipoAmbitoPublicacion getTipoAmbitoPublicacion() {
        return tipoAmbitoPublicacion;
    }

    public void setTipoAmbitoPublicacion(TipoAmbitoPublicacion tipoAmbitoPublicacion) {
        this.tipoAmbitoPublicacion = tipoAmbitoPublicacion;
    }
    

    public List<CapituloLibro> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<CapituloLibro> capitulos) {
        this.capitulos = capitulos;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Date getFechaAceptado() {
        return fechaAceptado;
    }

    public void setFechaAceptado(Date fechaAceptado) {
        this.fechaAceptado = fechaAceptado;
    }

    public Date getFechaEnviado() {
        return fechaEnviado;
    }

    public void setFechaEnviado(Date fechaEnviado) {
        this.fechaEnviado = fechaEnviado;
    }

    public Date getFechaPublicado() {
        return fechaPublicado;
    }

    public void setFechaPublicado(Date fechaPublicado) {
        this.fechaPublicado = fechaPublicado;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public TipoEdicion getTipoEdicion() {
        return tipoEdicion;
    }

    public void setTipoEdicion(TipoEdicion tipoEdicion) {
        this.tipoEdicion = tipoEdicion;
    }
 
    @Override
    public String toString() {
        return super.getTitulo();
    }
}
