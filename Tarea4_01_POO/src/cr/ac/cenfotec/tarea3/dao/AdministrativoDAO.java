/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Administrativo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into administrativo(nombre,apellido,identificacion, tipo,cantidadhoras,idTipoUsuario) "
            + "values (?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSADMINISTRATIVOS = "select * from administrativo";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosAdministrativos;

    public AdministrativoDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosAdministrativos = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSADMINISTRATIVOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Administrativo findByAdministrativoID(int identificacion) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from administrativo");
        buildSentence.append(" where identificacion = ");
        buildSentence.append(identificacion);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Administrativo administrativo = new Administrativo();
            administrativo.setIdentificacion(result.getInt("identificacion"));
            administrativo.setNombre(result.getString("nombre"));
            administrativo.setApellido(result.getString("apellido"));
            return administrativo;
        }
        return null;
    }
    
    
    public Administrativo findByID(int identificacion) throws SQLException {

        Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from administrativo");
        buildSentence.append(" where idadministrativo = ");
        buildSentence.append(identificacion);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Administrativo administrativo = new Administrativo();
            administrativo.setIdentificacion(result.getInt("identificacion"));
            administrativo.setNombre(result.getString("nombre"));
            administrativo.setApellido(result.getString("apellido"));
            return administrativo;
        }
        return null;
    }

    public void save(Administrativo nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setString(1, nuevo.getNombre());
            this.cmdInsertar.setString(2, nuevo.getApellido());
            this.cmdInsertar.setInt(3, nuevo.getIdentificacion());
            this.cmdInsertar.setString(4, nuevo.getTipoNombramiento());
            this.cmdInsertar.setInt(5, nuevo.getCantidadHoras());
            this.cmdInsertar.setInt(6, nuevo.getIdTipoUsuario());
            this.cmdInsertar.execute();
        }
    }

    public List<Administrativo> obtenerTodosLosClientes() throws SQLException {
        ArrayList<Administrativo> listaAdministrativo = new ArrayList<>();

        ResultSet resultado = this.queryTodosLosAdministrativos.executeQuery();
        while (resultado.next()) {
            Administrativo leido = new Administrativo();
            leido.setNombre(resultado.getString("nombre"));
            leido.setApellido(resultado.getString("apellido"));
            leido.setIdentificacion(resultado.getInt("identificacion"));
            leido.setTipoNombramiento(resultado.getString("tipo"));
            leido.setCantidadHoras(resultado.getInt("cantidadhoras"));
            leido.setIdTipoUsuario(resultado.getInt("tipoUsuario"));
            leido.setId(resultado.getInt("idadministrativo"));
            listaAdministrativo.add(leido);
        }

        return listaAdministrativo;

    }
}
