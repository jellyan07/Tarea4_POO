/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;


public class Persona {

    protected static int numId = 0;
    
    protected int id;
    protected String nombre;
    protected String apellido;
    protected int identificacion;
    
     protected int idTipoUsuario;

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
    

    public String getApellido() {
        return apellido;
    }

    public int getId() {
        return this.id;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona() {
    }

    public Persona(String nombre, String apellido, int identificacion,int idTipoUsuario) {
        this.id = numId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacion = identificacion;
        this.idTipoUsuario=idTipoUsuario;
    }

    @Override
    public String toString() {
        return "Persona{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", identificacion=" + identificacion + '}';
    }
    

}
