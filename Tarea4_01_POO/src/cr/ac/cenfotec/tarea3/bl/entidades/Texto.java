/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import java.util.Date;

public class Texto extends Material {

   
    private String nombreAutor;
    private Date fechaPublicacion;
    private int paginas;

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public int getPaginas() {
        return paginas;
    }

  

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

  

    public Texto() {
    }

    public Texto(String titulo, String nombreAutor, Date fechaPublicacion, int paginas, Date fechaCompra, boolean restringido, String tema, String idioma) {
        super(titulo,fechaCompra, restringido, tema, idioma);
        
        this.nombreAutor = nombreAutor;
        this.fechaPublicacion = fechaPublicacion;
        this.paginas = paginas;
    }

    @Override
    public String toString() {
        return "Texto{" + "titulo=" + super.nombreMaterial + ", nombreAutor=" + nombreAutor + ", fechaPublicacion=" + fechaPublicacion + ", paginas=" + paginas + super.toString() + '}';
    }

}
