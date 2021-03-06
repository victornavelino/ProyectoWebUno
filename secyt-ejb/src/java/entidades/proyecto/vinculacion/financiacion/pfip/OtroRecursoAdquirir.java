/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.vinculacion.financiacion.pfip;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Panchi
 */
@Entity
@Table(name="otrorecursoadquirir")
public class OtroRecursoAdquirir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Float precioTotal;
    private Float financiaM;
    private Float financiaC;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getFinanciaC() {
        return financiaC;
    }

    public void setFinanciaC(Float financiaC) {
        this.financiaC = financiaC;
    }

    public Float getFinanciaM() {
        return financiaM;
    }

    public void setFinanciaM(Float financiaM) {
        this.financiaM = financiaM;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof OtroRecursoAdquirir)) {
            return false;
        }
        OtroRecursoAdquirir other = (OtroRecursoAdquirir) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.proyecto.vinculacion.financiacion.pfip.OtroRecursoAdquirir[ id=" + id + " ]";
    }
    
}
