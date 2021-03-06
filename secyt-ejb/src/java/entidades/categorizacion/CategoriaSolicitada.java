/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.categorizacion;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author wbivanco
 */
@Entity
@Table(name="categoriasolicitada")
public class CategoriaSolicitada extends Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
        if (!(object instanceof CategoriaSolicitada)) {
            return false;
        }
        CategoriaSolicitada other = (CategoriaSolicitada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
