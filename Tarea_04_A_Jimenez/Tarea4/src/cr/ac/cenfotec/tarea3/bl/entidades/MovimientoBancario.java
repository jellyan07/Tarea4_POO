/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.time.LocalDate;

public class MovimientoBancario {

    protected LocalDate fecha;
    protected String descripcion;
    protected int monto;

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getMonto() {
        return monto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public MovimientoBancario() {
    }

    public MovimientoBancario(LocalDate fecha, String descripcion, int monto) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "MovimientoBancario{" + "fecha=" + fecha + ", descripcion=" + descripcion + ", monto=" + monto + '}';
    }

}
