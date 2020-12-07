/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.time.LocalDate;

public class Retiro extends MovimientoBancario {

    public Retiro() {
    }

    public Retiro(LocalDate fecha, String descripcion, int monto) {
        super(fecha, descripcion, monto);
    }

    public Retiro(String sourceLines) {
        String[] datos = sourceLines.split(",");
        this.fecha = LocalDate.parse(datos[0]);
        this.descripcion = datos[1];
        this.monto = Integer.parseInt(datos[2]);
    }

    @Override
    public String toString() {
        return "Retiro{" + super.toString() + '}';
    }

}
