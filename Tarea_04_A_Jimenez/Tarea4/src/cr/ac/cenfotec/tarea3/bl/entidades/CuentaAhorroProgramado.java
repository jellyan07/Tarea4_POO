/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.time.LocalDate;

public class CuentaAhorroProgramado extends Cuenta {

    private Cuenta cuentaAsociada;
    private LocalDate fecha_creacion;

    public Cuenta getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public CuentaAhorroProgramado() {
    }

    public CuentaAhorroProgramado(int id, double saldo, Cuenta cuentaAsociada, LocalDate fecha_creacion) {
        super(id, saldo);
        this.cuentaAsociada = cuentaAsociada;
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "CuentaAhorroProgramado{" + super.toString() + '}';
    }

}
