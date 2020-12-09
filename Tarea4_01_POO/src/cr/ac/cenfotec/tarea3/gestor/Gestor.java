/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.gestor;

import cr.ac.cenfotec.tarea3.bl.entidades.Administrativo;
import cr.ac.cenfotec.tarea3.bl.entidades.Audio;
import cr.ac.cenfotec.tarea3.bl.entidades.Estudiante;
import cr.ac.cenfotec.tarea3.bl.entidades.Material;
import cr.ac.cenfotec.tarea3.bl.entidades.Otro;
import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import cr.ac.cenfotec.tarea3.bl.entidades.Prestamo;
import cr.ac.cenfotec.tarea3.bl.entidades.Profesor;
import cr.ac.cenfotec.tarea3.bl.entidades.Texto;
import cr.ac.cenfotec.tarea3.bl.entidades.Video;
import cr.ac.cenfotec.tarea3.dao.AdministrativoDAO;
import cr.ac.cenfotec.tarea3.dao.AudioDAO;
import cr.ac.cenfotec.tarea3.dao.EstudianteDAO;
import cr.ac.cenfotec.tarea3.dao.OtroDAO;
import cr.ac.cenfotec.tarea3.dao.PrestamoDAO;
import cr.ac.cenfotec.tarea3.dao.ProfesorDAO;
import cr.ac.cenfotec.tarea3.dao.TextoDAO;
import cr.ac.cenfotec.tarea3.dao.VideoDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor {
    
    private AdministrativoDAO administrativoDao;
    private EstudianteDAO estudianteDAO;
    private ProfesorDAO profesorDAO;
    private TextoDAO textoDAO;
    private AudioDAO audioDAO;
    private VideoDAO videoDAO;
    private OtroDAO otroDAO;
    private PrestamoDAO prestamoDAO;
    
    private ArrayList<Material> listaMaterial = new ArrayList<>();
    private ArrayList<Persona> usuarios = new ArrayList<>();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private ArrayList<Material> materiales = new ArrayList<>();

    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>();
    private ArrayList<Administrativo> administrativos = new ArrayList<>();
    private ArrayList<Texto> textos = new ArrayList<>();
    private ArrayList<Audio> audios = new ArrayList<>();
    private ArrayList<Video> videos = new ArrayList<>();
    private Connection connection;
    
    
      public Gestor() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tarea3ejercicio1"
                    , "root", "root");
            this.administrativoDao = new AdministrativoDAO(this.connection);
            this.estudianteDAO = new EstudianteDAO(this.connection);
            this.profesorDAO = new ProfesorDAO(this.connection);
            this.textoDAO = new TextoDAO(this.connection);
            this.audioDAO = new AudioDAO(this.connection);
            this.videoDAO = new VideoDAO(this.connection);
            this.otroDAO = new OtroDAO(this.connection);
            this.prestamoDAO = new PrestamoDAO(this.connection);
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            System.out.println(e.getMessage());
        }
    }

    //Registra la personaDAO
      /*
    public void registrar(Persona unaPersona) {
        PersonaDAO tmp = this.determinarPersonaDAO(unaPersona);
        for(Persona nuevoUsuario: usuarios){
            Estudiante nuevo = (Estudiante) nuevoUsuario;
        }
        tmp.save(unaPersona);
        this.usuarios.add(unaPersona);
        
    }*/

    public ArrayList<Persona> getListaPersona() {
        return this.usuarios;
    }

    //Registra el materialDAO 
  /*  public void registrarMaterial(Material unMaterial) {
        try {
            MaterialDAO tmp = this.determinarObjetoDAO(unMaterial);
            tmp.save(unMaterial);

        } catch (Exception ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.materiales.add(unMaterial);
    }*/

    public ArrayList<Material> getListaMaterial() {
        return this.materiales;
    }

    public void crearEstudiante(String nombreEst, String apellidoEst, int identificacionEst, String carrera, int creditos) {
        Estudiante nuevoEstudiante = new Estudiante(carrera, creditos, nombreEst, apellidoEst, identificacionEst);
        try {
            this.estudianteDAO.save(nuevoEstudiante);
            // this.registrar(nuevoEstudiante);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearProfesor(String nombreProf, String apellidoProf, int identificacionProf, String contrato, String fechaContratacion) {
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaContratacion);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Profesor nuevoProfesor = new Profesor(contrato, fecha, nombreProf, apellidoProf, identificacionProf);
        try {
            //this.registrar(nuevoProfesor);
            this.profesorDAO.save(nuevoProfesor);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearAdministrativo(String nombreAdm, String apellidoAdm, int identificacionAdm, String nombramiento, int cantidadHoras) {
        Administrativo nuevoAdministrativo = new Administrativo(nombramiento, cantidadHoras, nombreAdm, apellidoAdm, identificacionAdm);
        try {
            // this.registrar(nuevoAdministrativo);
            this.administrativoDao.save(nuevoAdministrativo);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearTexto(String fechaDeCompra, boolean restringido, String tema, String idioma, String titulo, String nombreAutor, String fechaPublicacion, int paginas) {
        Date fechaC = null;
        Date fechaP = null;
        
        try {
            fechaC = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDeCompra);
            fechaP = new SimpleDateFormat("yyyy-MM-dd").parse(fechaPublicacion);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Texto nuevoTexto = new Texto(titulo, nombreAutor, fechaP, paginas, fechaC, restringido, tema, idioma);
        try {
            //this.registrarMaterial(nuevoTexto);
            this.textoDAO.save(nuevoTexto);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearAudio(String nombreMaterial,String fechaDeCompra, boolean restringido, String tema, String idioma, String formato, String duracion) {
        Date fechaC = null;
        try {
            fechaC = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDeCompra);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Audio nuevoAudio = new Audio(nombreMaterial,formato, duracion, fechaC, restringido, tema, idioma);
        try {
            //this.registrarMaterial(nuevoAudio);
            this.audioDAO.save(nuevoAudio);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creadorVideo(String nombreMaterial,String fechaDeCompra, boolean restringido, String tema, String idioma, String formato, String duracion, String director) {
        Date fechaC = null;
        
        try {
            fechaC = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDeCompra);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Video nuevoVideo = new Video(nombreMaterial,formato, duracion, director, fechaC, restringido, tema, idioma);
        try {
            this.videoDAO.save(nuevoVideo);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void creatOtro(String nombreMaterial,String fechaDeCompra, boolean restringido, String tema, String idioma, String descripcion) {
        Date fechaC = null;
        try {
            fechaC = new SimpleDateFormat("yyyy-MM-dd").parse(fechaDeCompra);
        } catch (ParseException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Otro nuevoOtro = new Otro(nombreMaterial,descripcion, fechaC, restringido, tema, idioma);
        try {
            // this.registrarMaterial(nuevoOtro);
            this.otroDAO.save(nuevoOtro);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Persona seleccionarUsuario(int idUsuario, int tipoUsuario) {
        Date fechaR = null;
        Persona usuario = null;
        
        switch (tipoUsuario) {
            //profesor
            case 1:
        {
            try {
                usuario=   this.profesorDAO.findByProfesorID(idUsuario);
            } catch (SQLException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
                //estudiante
                case 2:
        {
            try {
                usuario=  this.estudianteDAO.findByEstudianteID(idUsuario);
            } catch (SQLException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

                break;
                
                case 3:
        {
            try {
                usuario= this.administrativoDao.findByAdministrativoID(idUsuario);
            } catch (SQLException ex) {
                Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            
               
            
        }
    
        
         return usuario;
    }
    
   
    
    public List<Texto>  listTexto() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return textoDAO.obtenerTodosLosTextos();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public List<Audio>  listAudio() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return audioDAO.obtenerTodosLosTextos();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<Video>  listVideo() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return videoDAO.obtenerTodosLosVideos();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public List<Otro>  listOtro() throws InstantiationException, IllegalAccessException, SQLException {

        try {
            return otroDAO.obtenerTodosLosOtros();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void crearPrestamo(Persona persona,Material materiale,String retorno) throws ParseException{
        
        Date   fechaC = new SimpleDateFormat("yyyy-MM-dd").parse(retorno);
         
         Prestamo prestamo = new Prestamo(persona, materiale, fechaC);
        try {
            // this.registrar(nuevoAdministrativo);
            this.prestamoDAO.save(prestamo);
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    

    //Calcula el tipo de materialDao
   /* private MaterialDAO determinarObjetoDAO(Material nuevoMaterial) throws Exception {
        if (nuevoMaterial instanceof Audio) {
            return new AudioDAO();
        }
        if (nuevoMaterial instanceof Video) {
            return new VideoDAO();
        }
        if (nuevoMaterial instanceof Texto) {
            return new TextoDAO();
        }
        if (nuevoMaterial instanceof Otro) {
            return new OtroDAO();
        }
        throw new Exception("Tipo de Material Desconocido");
    }*/

    //Calcula el tipo de personaDAO
    /*private PersonaDAO determinarPersonaDAO(Persona unaPersona) {
        if (unaPersona instanceof Estudiante) {
            return new EstudianteDAO();
        }
        if (unaPersona instanceof Administrativo) {
            return new AdministrativoDAO();
        }
        if (unaPersona instanceof Profesor) {
            return new ProfesorDAO();
        }
        return null;
    }*/

    public List<Material> listAll(Material tipoMaterial) {
        try {
         //   MaterialDAO objPersistente = determinarObjetoDAO(tipoMaterial);
            //return objPersistente.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Material>();
    }

    public List<Persona> listAllPersona(Persona tipoPersona) {
        try {
          //  PersonaDAO objPersistente = determinarPersonaDAO(tipoPersona);
          //  return objPersistente.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Persona>();
    }

    public ArrayList<Material> obtenerListaMaterial() {
        ArrayList<Material> listaMaterial = new ArrayList<>();
        listaMaterial.add(new Audio());
        listaMaterial.add(new Texto());
        listaMaterial.add(new Video());
        listaMaterial.add(new Otro());
        return listaMaterial;
    }

    public List<Prestamo> getListaPrestamos() {
        
        try {
            return prestamoDAO.obtenerTodosLosPrestamos();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return this.prestamos;
    }

}
