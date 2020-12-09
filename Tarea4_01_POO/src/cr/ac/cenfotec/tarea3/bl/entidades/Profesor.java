/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import cr.ac.cenfotec.tarea3.tipos.TipoUsuario;
import java.util.Date;



public class Profesor extends Persona {

    private String tipoContrato;
    private Date fechaContratacion;

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Profesor() {
    }

    public Profesor(String tipoContrato, Date fechaContratacion, String nombre, String apellido, int identificacion) {
        super(nombre, apellido, identificacion,TipoUsuario.PROFESOR.getid());
        this.tipoContrato = tipoContrato;
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Profesor{" + "tipoContrato=" + tipoContrato + ", fechaContratacion=" + fechaContratacion + super.toString() + '}';
    }

}
