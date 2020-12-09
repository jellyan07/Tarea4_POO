/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.util.Date;

public class Prestamo {

    private Persona persona;
    private Material materiale;
    private Date retorno;

    public Material getMateriale() {
        return materiale;
    }

    public Persona getPersona() {
        return persona;
    }

    public Date getRetorno() {
        return retorno;
    }

    public void setMateriale(Material materiale) {
        this.materiale = materiale;
    }

    public void setPersonas(Persona persona) {
        this.persona = persona;
    }

    public void setRetorno(Date retorno) {
        this.retorno = retorno;
    }

    public Prestamo() {
    }

    public Prestamo(Persona persona, Material materiale, Date retorno) {
        this.persona = persona;
        this.materiale = materiale;
        this.retorno = retorno;
    }
    

    @Override
    public String toString() {
        return "Prestamo{" + "personas=" + persona.getNombre() + ", materiales=" + materiale.nombreMaterial + ", retorno=" + retorno + '}';
    }

}
