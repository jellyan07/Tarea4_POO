/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;


import java.util.Date;


public class Otro extends Material {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Otro() {
    }

    public Otro(String nombreMaterial,String descripcion, Date fechaCompra, boolean restringido, String tema, String idioma) {
        super(nombreMaterial,fechaCompra, restringido, tema, idioma);
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Otro{" + "descripcion=" + descripcion + super.toString() + '}';
    }

}
