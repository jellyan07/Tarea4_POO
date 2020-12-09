/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.controlador;

import cr.ac.cenfotec.tarea3.bl.entidades.Audio;
import cr.ac.cenfotec.tarea3.bl.entidades.Material;
import cr.ac.cenfotec.tarea3.bl.entidades.Otro;
import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import cr.ac.cenfotec.tarea3.bl.entidades.Prestamo;
import cr.ac.cenfotec.tarea3.bl.entidades.Texto;
import cr.ac.cenfotec.tarea3.bl.entidades.Video;
import cr.ac.cenfotec.tarea3.gestor.Gestor;
import cr.ac.cenfotec.tarea3.tipos.Tema;
import cr.ac.cenfotec.tarea3.tipos.TipoAudio;
import cr.ac.cenfotec.tarea3.tipos.TipoContrato;
import cr.ac.cenfotec.tarea3.tipos.TipoNombramiento;
import cr.ac.cenfotec.tarea3.tipos.TipoVideo;
import cr.ac.cenfotec.tarea3.ui.UI;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador {

    UI interfaz = new UI();
    Gestor gestor = new Gestor();

    /*final static int USUARIO_ESTUDIANTE = 1;
    final static int USUARIO_ADMINISTRADOR = 2;
    final static int USUARIO_PROFESOR = 3;*/
    public void mostrarMenu() {
        int opcion = 0;
        do {
            interfaz.mostrarMenu();
            opcion = interfaz.leerEntero();
            procesarOpcion(opcion);
        } while (opcion != 5);
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                opcionUsuario();
                break;
            case 2:
                opcionMaterial();
                break;
            case 3:
                crearPrestamo();
                break;
             case 4:
                listarPrestamo();
                break;
            case 5:
                break;
            default:
                interfaz.mostrarMensaje("Opcion invalida");
        }
    }

    private void opcionUsuario() {
        int opcion = 0;
        do {
            interfaz.mostrarMenuUsuario();
            opcion = interfaz.leerEntero();
            procesarUsuario(opcion);
        } while (opcion != 4);
    }

    private void procesarUsuario(int opcion) {
        switch (opcion) {
            case 1:
                crearEstudiante();
                break;
            case 2:
                crearProfesor();
                break;
            case 3:
                crearAdministrativo();
                break;
            case 4:
                break;
            default:
                interfaz.mostrarMensaje("Opcion invalida");
        }
    }

    private void opcionMaterial() {
        int opcion = 0;
        do {
            interfaz.mostrarMenuMaterial();
            opcion = interfaz.leerEntero();
            procesarMaterial(opcion);
        } while (opcion != 5);
    }

    private void crearEstudiante() {
        interfaz.mostrarMensaje("Escriba nombre del estudiante");
        String nombreEst = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el apellido");
        String apellidoEst = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba numero de identificacion");
        int identificacionEst = interfaz.leerEntero();
        interfaz.mostrarMensaje("Escriba el nombre de la carrera");
        String carrera = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba la cantidad de creditos matriculados");
        int creditos = interfaz.leerEntero();
        this.gestor.crearEstudiante(nombreEst, apellidoEst, identificacionEst, carrera, creditos);
    }

    private void crearProfesor() {
        interfaz.mostrarMensaje("Escriba nombre del profesor");
        String nombreProf = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el apellido");
        String apellidoProf = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba numero de identificacion");
        int identificacionProf = interfaz.leerEntero();
        interfaz.mostrarMensaje("Escriba su tipo de contrato");
        for (TipoContrato e : TipoContrato.values()) {
            System.out.println(e);
        }
        String contrato = interfaz.leerTexto();
        interfaz.mostrarMensaje("Cual es la fecha de contratacion(yyyy-MM-dd)");
        String fechaDeContratacion = interfaz.leerTexto();
        this.gestor.crearProfesor(nombreProf, apellidoProf, identificacionProf, contrato, fechaDeContratacion);
    }

    /*public LocalDate fecha() {
        interfaz.mostrarMensaje("Escriba la fecha dd/MM/yyyy");
        String fecha = interfaz.leerTexto();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaDato = LocalDate.parse(fecha, fmt);
        return fechaDato;
    }*/
    private void crearAdministrativo() {
        interfaz.mostrarMensaje("Escriba nombre del administrativo");
        String nombreAdm = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el apellido");
        String apellidoAdm = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba numero de identificacion");
        int identificacionAdm = interfaz.leerEntero();
        interfaz.mostrarMensaje("Tipo nombramiento");
        for (TipoNombramiento e : TipoNombramiento.values()) {
            System.out.println(e);
        }
        String nombramiento = interfaz.leerTexto();
        interfaz.mostrarMensaje("Cantidad de horas");
        int cantidadHoras = interfaz.leerEntero();
        this.gestor.crearAdministrativo(nombreAdm, apellidoAdm, identificacionAdm, nombramiento, cantidadHoras);
    }

    private void procesarMaterial(int opcion) {
        switch (opcion) {
            case 1:
                crearTexto();
                break;
            case 2:
                crearAudio();
                break;
            case 3:
                crearVideo();
                break;
            case 4:
                crearOtro();
                break;
            case 5:
                break;
            default:
                interfaz.mostrarMensaje("Opcion invalida");
        }
    }

    private void crearTexto() {
        int cont = 1;
        interfaz.mostrarMensaje("Fecha de la compra(yyyy-MM-dd)");
        String fecha = interfaz.leerTexto();
        boolean restringido = true;
        interfaz.mostrarMensaje("Seleccione un tema");
        for (Tema temas : Tema.values()) {
            System.out.println(cont++ + ". " + temas);
        }
        String tema = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el idioma");
        String idioma = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el titulo");
        String titulo = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el nombre del autor");
        String nombreAutor = interfaz.leerTexto();
        interfaz.mostrarMensaje("Fecha de publicacion(yyyy-MM-dd)");
        String fechaPublicacion = interfaz.leerTexto();
        interfaz.mostrarMensaje("Numero de paginas");
        int paginas = interfaz.leerEntero();
        this.gestor.crearTexto(fecha, restringido, tema, idioma, titulo, nombreAutor, fechaPublicacion, paginas);
    }

    private void crearAudio() {
        int cont = 1;
        
        interfaz.mostrarMensaje("Escriba el nombre del material");
        String nombreMaterial = interfaz.leerTexto();
        
        interfaz.mostrarMensaje("Fecha de la compra(yyyy-MM-dd)");
        String fechaDeCompra = interfaz.leerTexto();
        boolean restringido = true;
        interfaz.mostrarMensaje("Seleccione un tema");
        for (Tema temas : Tema.values()) {
            System.out.println(cont++ + ". " + temas);
        }
        String tema = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el idioma");
        String idioma = interfaz.leerTexto();
        interfaz.mostrarMensaje("Tipo de formato");
        for (TipoAudio audios : TipoAudio.values()) {
            System.out.println(audios);
        }
        String formato = interfaz.leerTexto();
        interfaz.mostrarMensaje("Duracion de audio");
        String duracion = interfaz.leerTexto();
        this.gestor.crearAudio(nombreMaterial,fechaDeCompra, restringido, tema, idioma, formato, duracion);
    }

    private void crearVideo() {
        int cont = 1;
        
        interfaz.mostrarMensaje("Escriba el nombre del material");
        String nombreMaterial = interfaz.leerTexto();
        interfaz.mostrarMensaje("Fecha de la compra(yyyy-MM-dd)");
        String fechaDeCompra = interfaz.leerTexto();
        boolean restringido = true;
        interfaz.mostrarMensaje("Seleccione un tema");
        for (Tema temas : Tema.values()) {
            System.out.println(cont++ + ". " + temas);
        }
        String tema = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el idioma");
        String idioma = interfaz.leerTexto();
        interfaz.mostrarMensaje("Tipo de formato");
        for (TipoVideo videos : TipoVideo.values()) {
            System.out.println(videos);
        }
        String formato = interfaz.leerTexto();
        interfaz.mostrarMensaje("Duracion del video");
        String duracion = interfaz.leerTexto();
        interfaz.mostrarMensaje("Director del video");
        String director = interfaz.leerTexto();
        this.gestor.creadorVideo(nombreMaterial,fechaDeCompra, restringido, tema, idioma, formato, duracion, director);
    }

    private void crearOtro() {
        int cont = 1;
        interfaz.mostrarMensaje("Escriba el nombre del material");
        String nombreMaterial = interfaz.leerTexto();
        
        interfaz.mostrarMensaje("Fecha de la compra(yyyy-MM-dd)");
        String fechaDeCompra = interfaz.leerTexto();
        boolean restringido = true;
        interfaz.mostrarMensaje("Seleccione un tema");
        for (Tema temas : Tema.values()) {
            System.out.println(cont++ + ". " + temas);
        }
        String tema = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba el idioma");
        String idioma = interfaz.leerTexto();
        interfaz.mostrarMensaje("Escriba la descripcion del material");
        String descripcion = interfaz.leerTexto();
        this.gestor.creatOtro(nombreMaterial,fechaDeCompra, restringido, tema, idioma, descripcion);
    }

    private void crearPrestamo() {
        Persona persona = new Persona();
        persona = seleccionarUsarioPrestamos();
        String nombre = persona.getNombre();
        int identificacion = persona.getIdentificacion();
        seleccionarMaterialPrestamos(persona);
    }
    
    public Persona seleccionarUsarioPrestamos() {

        Persona persona = new Persona();
        interfaz.mostrarMensaje("Escriba el tipo de usuario \n 1.Profesor \n 2.Estudiante \n 3.administrativo");
        int opcion = interfaz.leerEntero();

        interfaz.mostrarMensaje("Escriba el numero de cedula de usuario");
        int cedula = interfaz.leerEntero();

        switch (opcion) {
            case 1:
                persona = this.gestor.seleccionarUsuario(cedula, opcion);
                break;

            case 2:
                persona = this.gestor.seleccionarUsuario(cedula, opcion);

                break;

            case 3:
                persona = this.gestor.seleccionarUsuario(cedula, opcion);
                break;

        }
        return persona;
    }

    public void seleccionarMaterialPrestamos(Persona persona) {

        interfaz.mostrarMensaje("Escriba el tipo de material \n 1.texto \n 2.video \n 3.audio 4.otro");
        int opcion = interfaz.leerEntero();
        //    this.gestor.crearPrestamo(opcion);
        ArrayList  listMateriales = new ArrayList<>();
        try {
            switch (opcion) {
                case 1: {

                    listMateriales= (ArrayList) this.gestor.listTexto();
                }
                break;

                case 2:
                    listMateriales= (ArrayList) this.gestor.listVideo();

                    break;

                case 3:
                    listMateriales= (ArrayList) this.gestor.listAudio();
                    break;

                case 4:
                    listMateriales= (ArrayList) this.gestor.listOtro();
                    break;

            }
            

                if (listMateriales.get(0) instanceof Audio){
                   SeleccionarMaterialAudio(persona,listMateriales);
                }
                
                if (listMateriales.get(0) instanceof Texto){
                   SeleccionarMaterialTexto(persona,listMateriales);
                }
                
                if (listMateriales.get(0) instanceof Video){
                   SeleccionarMaterialVideo(persona,listMateriales);
                }
                
                 if (listMateriales.get(0) instanceof Otro){
                  SeleccionarMaterialOtro(persona,listMateriales);
                }
             
             
              
              

        } catch (InstantiationException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void crearNuevoPrestamo(Persona persona, Material materiale){
        
        try {
              interfaz.mostrarMensaje("Fecha de Reportono (yyyy-MM-dd)");
        String fecha = interfaz.leerTexto();
            
            
            this.gestor.crearPrestamo(persona, materiale, fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void SeleccionarMaterialAudio( Persona persona,ArrayList  listMateriales){
        
        for (Object unMaterial : listMateriales) {
             Audio tmpAudio =  (Audio) unMaterial;
              System.out.println(+tmpAudio.getSignatura()+". "+tmpAudio.getNombreMaterial());
        }
        interfaz.mostrarMensaje("Seleccione el numero del material");
           int opcion = interfaz.leerEntero();
          Audio tmpAudio= (Audio) listMateriales.get(opcion-1);
   
        this.crearNuevoPrestamo(persona,tmpAudio);
         
    }
    
     public void SeleccionarMaterialTexto(Persona persona, ArrayList  listMateriales){
        
        for (Object unMaterial : listMateriales) {
             Texto tmpAudio =  (Texto) unMaterial;
              System.out.println(+tmpAudio.getSignatura()+". "+tmpAudio.getNombreMaterial());
        }
        interfaz.mostrarMensaje("Seleccione el numero del material");
           int opcion = interfaz.leerEntero();
          Texto tmp= (Texto) listMateriales.get(opcion-1);
          
          this.crearNuevoPrestamo(persona,tmp);
    }
    
     
      public void SeleccionarMaterialOtro(Persona persona, ArrayList  listMateriales){
        
        for (Object unMaterial : listMateriales) {
             Otro tmpAudio =  (Otro) unMaterial;
              System.out.println(+tmpAudio.getSignatura()+". "+tmpAudio.getNombreMaterial());
        }
        interfaz.mostrarMensaje("Seleccione el numero del material");
           int opcion = interfaz.leerEntero();
          Otro tmp= (Otro) listMateriales.get(opcion-1);
          
           this.crearNuevoPrestamo(persona,tmp);
    }
       
      
      public void SeleccionarMaterialVideo( Persona persona,ArrayList  listMateriales){
        
        for (Object unMaterial : listMateriales) {
             Video tmpAudio =  (Video) unMaterial;
              System.out.println(+tmpAudio.getSignatura()+". "+tmpAudio.getNombreMaterial());
        }
        interfaz.mostrarMensaje("Seleccione el numero del material");
           int opcion = interfaz.leerEntero();
          Video tmp= (Video) listMateriales.get(opcion-1);
      
            this.crearNuevoPrestamo(persona,tmp);
      
      }
    
    
    
  

    private Material listaMateriales() {
        String opcion = null;
        gestor.obtenerListaMaterial();

        int contMaterial = 0;
        // for (Material unMaterial : gestor.getListaMaterial()) {
        for (Material unMaterial : gestor.obtenerListaMaterial()) {

            for (Material otroMaterial : gestor.listAll(unMaterial)) {
                System.out.println(contMaterial++ + ". " + otroMaterial.getTema());
            }

        }
        int seleccionMaterial = interfaz.leerEntero();
        Material materialPrestamo = gestor.obtenerListaMaterial().get(seleccionMaterial);

        return materialPrestamo;
    }

    public void listarPrestamo() {
        for (Prestamo nuevoPrestamo : gestor.getListaPrestamos()) {
            System.out.println(nuevoPrestamo);
        }
    }
}
