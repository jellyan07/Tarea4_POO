/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.util.Date;

public  class Material  {
    
    protected static int numSignatura = 0;

    protected int signatura;
    protected Date fechaCompra;
    protected boolean restringido;
    protected String tema;
    protected String nombreMaterial;
    protected int idTipoMaterial;

    public int getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(int idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }
    

    public static int getNumSignatura() {
        return numSignatura;
    }

    public static void setNumSignatura(int numSignatura) {
        Material.numSignatura = numSignatura;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }
    protected String idioma;
    

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public String getIdioma() {
        return idioma;
    }

    public int getSignatura() {
        return this.signatura;
    }

    public String getTema() {
        return tema;
    }

    public boolean isRestringido() {
        return restringido;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setRestringido(boolean restringido) {
        this.restringido = restringido;
    }

    public void setSignatura(int signatura) {
        this.signatura = signatura;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Material() {
    }

    public Material(String  nombreMaterial,Date fechaCompra, boolean restringido, String tema, String idioma) {
        
        this.signatura = numSignatura++;
        this.nombreMaterial = nombreMaterial;
        this.fechaCompra = fechaCompra;
        this.restringido = restringido;
        this.tema = tema;
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "Material{" + "signatura=" + signatura + "nombreMaterial=" + nombreMaterial + ", fechaCompra=" + fechaCompra + ", restringido=" + restringido + ", tema=" + tema + ", idioma=" + idioma + '}';
    }

   
}
