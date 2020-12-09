/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.util.Date;



public class Audio extends Material {

    private String formato;
    private String duracion;

    public String getDuracion() {
        return duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Audio() {
    }

    public Audio(String nombreMaterial,String formato, String duracion, Date fechaCompra, boolean restringido, String tema, String idioma) {
        super(nombreMaterial,fechaCompra, restringido, tema, idioma);
        this.formato = formato;
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Audio{" + "formato=" + formato + ", duracion=" + duracion + super.toString() + '}';
    }

}
