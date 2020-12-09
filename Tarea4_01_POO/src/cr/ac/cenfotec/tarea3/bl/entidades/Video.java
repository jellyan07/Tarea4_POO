/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.util.Date;

public class Video extends Material {

    private String formato;
    private String duracion;
    private String director;

    public String getDirector() {
        return director;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Video() {
    }

    public Video(String nombreMaterial,String formato, String duracion, String director, Date fechaCompra, boolean restringido, String tema, String idioma) {
        super(nombreMaterial,fechaCompra, restringido, tema, idioma);
        this.formato = formato;
        this.duracion = duracion;
        this.director = director;
    }

    @Override
    public String toString() {
        return "Video{" + "formato=" + formato + ", duracion=" + duracion + ", director=" + director + super.toString() + '}';
    }

}
