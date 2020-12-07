/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import cr.ac.cenfotec.tarea3.interfaces.SerializacionCSV;

public class Cliente implements SerializacionCSV {

    private String nombre;
    private int identificacion;
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente() {
    }

    public Cliente(String nombre, int identificacion, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }

    public Cliente(String sourceLines) {
        String[] datos = sourceLines.split(",");
        this.nombre = datos[0];
        this.identificacion = Integer.parseInt(datos[1]);
        this.direccion = datos[2];
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", identificacion=" + identificacion + ", direccion=" + direccion + '}';
    }

    @Override
    public String toCSVLine() {
        return this.nombre + "," + this.identificacion + "," + this.direccion;
    }
}
