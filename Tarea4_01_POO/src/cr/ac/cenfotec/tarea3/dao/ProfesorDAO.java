/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Administrativo;
import cr.ac.cenfotec.tarea3.bl.entidades.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO /*extends PersonaDAO*/ {

    private final String TEMPLATE_CMD_INSERTAR = "insert into profesor(nombre,apellido,identificacion, tipocontrato,fechacontratacion,idTipoUsuario) "
            + "values (?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSPROFESORES = "select * from profesor";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosProfesores;

    public ProfesorDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosProfesores = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSPROFESORES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save(Profesor nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido());
            this.cmdInsertar.setInt(3, nuevo.getIdentificacion());
            this.cmdInsertar.setString(4, nuevo.getTipoContrato());
            this.cmdInsertar.setDate(5, convertToSqlDate(nuevo.getFechaContratacion()));
            this.cmdInsertar.setInt(6, nuevo.getIdTipoUsuario());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public Profesor findByProfesorID(int identificacion) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from profesor");
        buildSentence.append(" where identificacion = ");
        buildSentence.append(identificacion);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Profesor profesor = new Profesor();
            profesor.setIdentificacion(result.getInt("identificacion"));
            profesor.setNombre(result.getString("nombre"));
            profesor.setApellido(result.getString("apellido"));
            return profesor;
        }
        return null;
    }
    
    
    public Profesor findByID(int identificacion) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from profesor");
        buildSentence.append(" where idprofesor = ");
        buildSentence.append(identificacion);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Profesor profesor = new Profesor();
            profesor.setIdentificacion(result.getInt("identificacion"));
            profesor.setNombre(result.getString("nombre"));
            profesor.setApellido(result.getString("apellido"));
            return profesor;
        }
        return null;
    }

    public List<Administrativo> obtenerTodosLosClientes() throws SQLException {
        ArrayList<Administrativo> listaAdministrativo = new ArrayList<>();

        ResultSet resultado = this.queryTodosLosProfesores.executeQuery();
        while (resultado.next()) {
            Administrativo leido = new Administrativo();
            leido.setNombre(resultado.getString("nombre"));
            leido.setApellido(resultado.getString("apellido"));
            leido.setIdentificacion(resultado.getInt("identificacion"));
            leido.setTipoNombramiento(resultado.getString("tipo"));
            leido.setCantidadHoras(resultado.getInt("cantidadhoras"));
            leido.setIdTipoUsuario(resultado.getInt("tipoUsuario"));
            leido.setId(resultado.getInt("idprofesor"));
            listaAdministrativo.add(leido);
        }

        return listaAdministrativo;

    }

}
