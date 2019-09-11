/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado;

/**
 *
 * @author franco
 */
public enum TipoAmbitoPublicacion {
    
NACIONAL ("Nacional"),
INTERNACIONAL ("Internacional");

private String name ;

private TipoAmbitoPublicacion(String name){
this.name = name;
}

public String getName() {
return name;
}

@Override
public String toString(){
//para llenar combo box
return name;
}
}
