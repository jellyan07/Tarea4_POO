/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Otro;
import cr.ac.cenfotec.tarea3.tipos.TipoMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josem
 */
public class OtroDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into otro(fechacompra,restringido,tema, idioma,descripcion,fechacompra) "
            + "values (?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSOTROS = "select * from otro";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosOtros;

    public OtroDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosOtros = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSOTROS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Otro encontrarPorId(String identificacion) {
        return null;
    }

    public void save(Otro nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setDate(1, convertToSqlDate(nuevo.getFechaCompra()));
            this.cmdInsertar.setBoolean(2, nuevo.isRestringido());
            this.cmdInsertar.setString(3, nuevo.getTema());
            this.cmdInsertar.setString(4, nuevo.getIdioma());
            this.cmdInsertar.setString(5, nuevo.getDescripcion());
            this.cmdInsertar.setString(6, nuevo.getNombreMaterial());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Otro> obtenerTodosLosOtros() throws SQLException {
        ArrayList<Otro> listaOtros = new ArrayList<>();
        ResultSet resultado = this.queryTodosLosOtros.executeQuery();
        while (resultado.next()) {
            Otro leido = new Otro();
            leido.setFechaCompra(resultado.getDate("fechacompra"));
            leido.setRestringido(resultado.getBoolean("restringido"));
            leido.setTema(resultado.getString("tema"));
            leido.setIdioma(resultado.getString("idioma"));
            leido.setDescripcion(resultado.getString("descripcion"));
            leido.setNombreMaterial(resultado.getString("fechacompra"));
          leido.setIdTipoMaterial(TipoMaterial.OTRO.getid());
            listaOtros.add(leido);
        }

        return listaOtros;

    }
}
