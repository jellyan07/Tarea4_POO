/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import cr.ac.cenfotec.tarea3.tipos.TipoUsuario;


public class Administrativo extends Persona{
    
    private String tipoNombramiento;
    private int cantidadHoras;

    public int getCantidadHoras() {
        return cantidadHoras;
    }

    public String getTipoNombramiento() {
        return tipoNombramiento;
    }

    public void setCantidadHoras(int cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setTipoNombramiento(String tipoNombramiento) {
        this.tipoNombramiento = tipoNombramiento;
    }

    public Administrativo() {
    }

    public Administrativo(String tipoNombramiento, int cantidadHoras,String nombre, String apellido, int identificacion) {
        super(nombre, apellido, identificacion,TipoUsuario.ADMINISTRATIVO.getid());
        this.tipoNombramiento = tipoNombramiento;
        this.cantidadHoras = cantidadHoras;
    }
    
      public Administrativo (String sourceLines) {
        String[] datos = sourceLines.split(",");
        this.tipoNombramiento = datos[0];
        this.cantidadHoras = Integer.parseInt(datos[1]);
        super.id = Integer.parseInt(datos[2]);
        this.nombre = datos[3];
        this.apellido = datos[4];
        this.identificacion = Integer.parseInt(datos[5]);
    }

    @Override
    public String toString() {
        return "Administrativo{" + "tipoNombramiento=" + tipoNombramiento + ", cantidadHoras=" + cantidadHoras + super.toString() + '}';
    }

}
