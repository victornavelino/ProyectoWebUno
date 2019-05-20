/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.proyecto.resultado.RN;

import DAO.PublicacionFacadeLocal;
import entidades.proyecto.resultado.ArticuloRevista;
import entidades.proyecto.resultado.CapituloLibro;
import entidades.proyecto.resultado.Congreso;
import entidades.proyecto.resultado.Libro;
import entidades.proyecto.resultado.Publicacion;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
import recursos.cadenas;

/**
 *
 * @author AFerSor
 */
@Stateless
public class PublicacionRN implements PublicacionRNLocal {

    @EJB
    private PublicacionFacadeLocal publicacionFacadeLocal;

    @Override
    public List<Publicacion> findByInvestigador(Long idProyecto, Long idInvestigador, Class tipo) throws Exception {
        return publicacionFacadeLocal.findByInvestigador(idProyecto, idInvestigador, tipo);
    }

    @Override
    public void create(Publicacion publicacion, String clase,Date fechaIniEvalaucionWinsip) throws Exception {
        //    if (!"CapituloLibro".equals(clase)) {
        this.validar(publicacion, 0, clase, fechaIniEvalaucionWinsip);
        //    }
        this.publicacionFacadeLocal.create(publicacion);

    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void edit(Publicacion publicacion, String clase,Date fechaIniEvalaucionWinsip) throws Exception {
        //  if (!"CapituloLibro".equals(clase)) {
        this.validar(publicacion, 0, clase, fechaIniEvalaucionWinsip);
        // }
        this.publicacionFacadeLocal.edit(publicacion);
    }

    @Override
    public void activate(Publicacion al, Boolean bEstado) throws Exception {
        this.publicacionFacadeLocal.activate(al, bEstado);
    }

    @Override
    public void remove(Publicacion publicacion) throws Exception {
        this.publicacionFacadeLocal.remove(publicacion);
    }

    private void validar(Publicacion p, int op, String clase, Date fechaIniEvalaucionWinsip) throws Exception {
        System.out.println("ENTRO A VALIDAR la clase es ---->" + clase);
            Calendar calIni = Calendar.getInstance();
            calIni.setTime(fechaIniEvalaucionWinsip);
            String anioInicio = String.valueOf(calIni.get(Calendar.YEAR));
            System.out.println("EL AÑO DE INICIO ES: "+anioInicio);
        if (clase.equals("ArticuloRevista")) {
            ArticuloRevista ar = (ArticuloRevista) p;

            if (ar.getNombreRevista().trim().length() == 0) {
                throw new Exception("Debe ingresar un nombre de revista");
            }
            if (ar.getVolumen().trim().length() == 0) {
                throw new Exception("Debe ingresar el volumen");
            }
            if (!cadenas.es_numero(ar.getVolumen())) {
                throw new Exception("El volumen del articulo debe contener solo caracteres numéricos");
            }
            if (ar.getFechaEnviado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
            if (ar.getFechaAceptado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
            if (ar.getFechaPublicado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
//            if (!ar.getAnioEdicion().equals(anioInicio)) {
//                throw new Exception("El año debe ser: "+anioInicio);
//            }

            //if (validateIsbn13(ar.getISBN()) != true) {
//                if(ar.getISBN().length()!= 8 || !ar.getISBN().matches("[0-8]*\\w")){
//                   
//                        throw new Exception("El número de ISNN (8 Digitos o 7 Digitos y una letra) ingresado no es correcto");
//                        //throw new Exception("El número de ISBN (13 Digitos) O ISNN (8 Digitos) ingresado no es correcto");
//                   
//                }//fin if
            //}//fin if
        }
        if (clase.equals("Libro")) {
            Libro libro = (Libro) p;

            if (libro.getEditor().trim().length() == 0) {
                throw new Exception("Debe ingresar un Editor");
            }
            if (libro.getAnioPublicacion().trim().length() == 0) {
                throw new Exception("Debe ingresar el año de publicación");
            }
            if (!cadenas.es_numero(libro.getAnioPublicacion())) {
                throw new Exception("El año de publicación del libro debe contener solo caracteres numéricos");
            }
            if (libro.getLugarPublicacion().trim().length() == 0) {
                throw new Exception("Debe ingresar un lugar de publicación");
            }
            if (libro.getISBN() != null) {
                if (!libro.getISBN().isEmpty()) {
                    if (validateIsbn13(libro.getISBN()) != true) {
                        throw new Exception("El número de ISBN (13 Digitos) ingresado no es correcto");
                    }
                }
            }
            if (libro.getFechaEnviado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
            if (libro.getFechaAceptado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
            if (libro.getFechaPublicado() == null) {
                throw new Exception("La fecha debe ser cargada");
            }
//            if (!libro.getAnioPublicacion().equals(anioInicio)) {
//                throw new Exception("El año debe ser: "+anioInicio);
//            }

        }

        if (clase.equals("Congreso")) {
            Congreso con = (Congreso) p;

            if (con.getNombreEvento().trim().length() == 0) {
                throw new Exception("Debe ingresar un nombre de evento");
            }
            if (con.getTitulo().trim().length() == 0) {
                throw new Exception("Debe ingresar el tema");
            }
            if (!cadenas.es_numero(con.getVolumen())) {
                throw new Exception("El volumen del Congreso debe contener solo caracteres numéricos");
            }
            if (!cadenas.es_numero(con.getAnio())) {
                throw new Exception("El Año del Congreso debe contener solo caracteres numéricos");
            }
            if (!cadenas.es_numero(con.getPagina())) {
                throw new Exception("La Pagina del Congreso debe contener solo caracteres numéricos");
            }
            if (con.getInvestigadores() == null) {
                throw new Exception("Debe contener al menos un participante");
            }
            if (con.getReferato() == null) {
                throw new Exception("Debe Seleccionar un Tipo en Referato");
            }
            if (con.getCaracter() == null) {
                throw new Exception("Debe Seleccionar Tipo de Caracter");
            }
            if (con.getPublicadoEnActas() == null) {
                throw new Exception("Debe Seleccionar un Valor en Actas Mmemorias");
            }
            if (con.getFecha() == null) {
                throw new Exception("Debe Cargar Fecha");
            }
//            if (!con.getAnio().equals(anioInicio)) {
//                throw new Exception("El año debe ser: "+anioInicio);
//            }

        }
        if (clase.equals("CapituloLibro")) {
            System.out.println("entro en clase" + "capituloLibro");
            CapituloLibro cap = (CapituloLibro) p;

            if (cap.getNombreLibro().trim().length() == 0) {
                throw new Exception("Debe ingresar el Nombre del Libro");
            }
            if (cap.getTitulo().trim().length() == 0) {
                throw new Exception("Debe ingresar el Titulo");
            }
            if (cap.getEditor().trim().length() == 0) {
                throw new Exception("Debe ingresar el Editor");
            }

            if (!cadenas.es_numero(cap.getAnioPublicacion())) {
                throw new Exception("El Año del Capitulo debe contener solo caracteres numéricos");
            }
            if (cap.getFechaAceptado() == null) {
                throw new Exception("Ingrese Fecha Aceptado");
            }
            if (cap.getFechaEnviado() == null) {
                throw new Exception("Ingrese Fecha Enviado");
            }
            if (cap.getFechaPublicado() == null) {
                throw new Exception("Ingrese Fecha Publicado");
            }
            if (cap.getInvestigadores() == null) {
                throw new Exception("Debe seleccionar al menos un Participante");
            }
            if (!cadenas.validateIsbn13(cap.getISBN())) {
                throw new Exception("Verificar Formato de Codigo ISBN");
            }
//            if (!cap.getAnioPublicacion().equals(anioInicio)) {
//                throw new Exception("El año debe ser: "+anioInicio);
//            }

        }

    }//fin validar

    public boolean validateIsbn13(String isbn) {
        if (isbn == null) {
            return false;
        }

        //remove any hyphens
        isbn = isbn.replaceAll("-", "");

        //must be a 13 digit ISBN
        if (isbn.length() != 13) {
            return false;
        }

        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }

            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }
}
