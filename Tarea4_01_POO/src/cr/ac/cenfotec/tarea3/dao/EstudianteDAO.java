/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO /*extends PersonaDAO*/ {

    private final String TEMPLATE_CMD_INSERTAR = "insert into estudiante(nombre,apellido,identificacion, carrera,creditosmatriculados,,idTipoUsuario) "
            + "values (?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSESTUDIANTES = "select * from estudiante";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosEstudiantes;

    public EstudianteDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosEstudiantes = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSESTUDIANTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Estudiante findByEstudianteID(int identificacion) throws SQLException {
      
        Statement stmt = cnx.createStatement();
        
        StringBuilder buildSentence = new StringBuilder("select * from estudiante");
        buildSentence.append(" where identificacion = ");
        buildSentence.append(identificacion);   
        
        ResultSet result = stmt.executeQuery(buildSentence.toString());
         
        while (result.next()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setIdentificacion(result.getInt("identificacion"));
            estudiante.setNombre(result.getString("nombre"));
            estudiante.setApellido(result.getString("apellido"));
            return estudiante;
        }
        return null;
    }
    
    public Estudiante findByID(int identificacion) throws SQLException {
      
        Statement stmt = cnx.createStatement();
        
        StringBuilder buildSentence = new StringBuilder("select * from estudiante");
        buildSentence.append(" where idestudiante = ");
        buildSentence.append(identificacion);   
        
        ResultSet result = stmt.executeQuery(buildSentence.toString());
         
        while (result.next()) {
            Estudiante estudiante = new Estudiante();
            estudiante.setIdentificacion(result.getInt("identificacion"));
            estudiante.setNombre(result.getString("nombre"));
            estudiante.setApellido(result.getString("apellido"));
            return estudiante;
        }
        return null;
    }
    
    

    public void save(Estudiante nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido());
            this.cmdInsertar.setInt(3, nuevo.getIdentificacion());
            this.cmdInsertar.setString(4, nuevo.getCarrera());
            this.cmdInsertar.setInt(5, nuevo.getCreditosMatriculados());
            this.cmdInsertar.setInt(6, nuevo.getIdTipoUsuario());
            this.cmdInsertar.execute();
        }
    }

    public List<Estudiante> obtenerTodosLosClientes() throws SQLException {
        ArrayList<Estudiante> listaEstudiante = new ArrayList<>();

        ResultSet resultado = this.queryTodosLosEstudiantes.executeQuery();
        while (resultado.next()) {
            Estudiante leido = new Estudiante();
            leido.setNombre(resultado.getString("nombre"));
            leido.setApellido(resultado.getString("apellido"));
            leido.setIdentificacion(resultado.getInt("identificacion"));
            leido.setCarrera(resultado.getString("carrera"));
            leido.setCreditosMatriculados(resultado.getInt("creditosmatriculados"));
            leido.setIdTipoUsuario(resultado.getInt("tipoUsuario"));
            leido.setId(resultado.getInt("idestudiante"));
            listaEstudiante.add(leido);
        }

        return listaEstudiante;

    }
}
