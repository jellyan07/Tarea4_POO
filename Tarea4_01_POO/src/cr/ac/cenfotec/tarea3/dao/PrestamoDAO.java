/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Material;
import cr.ac.cenfotec.tarea3.bl.entidades.Otro;
import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import cr.ac.cenfotec.tarea3.bl.entidades.Prestamo;
import cr.ac.cenfotec.tarea3.tipos.TipoMaterial;
import cr.ac.cenfotec.tarea3.tipos.TipoUsuario;
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
public class PrestamoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into prestamo(tipoUsuario,idUsuario,tipoMaterial, idMaterial,fechaPrestamo) "
            + "values (?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSOTROS = "select * from prestamo";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosOtros;

    public PrestamoDAO(Connection conexion) {
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

    public void save(Prestamo nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setInt(1, nuevo.getPersona().getIdTipoUsuario());
            this.cmdInsertar.setInt(2, nuevo.getPersona().getId());
            this.cmdInsertar.setInt(3, nuevo.getMateriale().getIdTipoMaterial());
            this.cmdInsertar.setInt(4, nuevo.getMateriale().getSignatura());
            this.cmdInsertar.setDate(5, convertToSqlDate(nuevo.getRetorno()));
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Prestamo> obtenerTodosLosPrestamos() throws SQLException {
        ArrayList<Prestamo> listaOtros = new ArrayList<>();
        ResultSet resultado = this.queryTodosLosOtros.executeQuery();

      
        while (resultado.next()) {
            Prestamo leido = new Prestamo();
            int tipoUsuario = resultado.getInt("tipoUsuario");
            leido.setPersonas(getUsuario(tipoUsuario));
            int tipoMaterial = resultado.getInt("tipoMaterial");
            leido.setMateriale(getMaterial(tipoMaterial));
            leido.setRetorno(resultado.getDate("fechaPrestamo"));
            listaOtros.add(leido);
        }

        return listaOtros;

    }

    public Persona getUsuario(int tipoUsuario) throws SQLException {

        if (tipoUsuario == TipoUsuario.ADMINISTRATIVO.getid()) {

            AdministrativoDAO dao = new AdministrativoDAO(this.cnx);
            return dao.findByID(tipoUsuario);
        }

        if (tipoUsuario == TipoUsuario.PROFESOR.getid()) {
            ProfesorDAO dao = new ProfesorDAO(this.cnx);
            return dao.findByID(tipoUsuario);
        }

        if (tipoUsuario == TipoUsuario.ESTUDIANTE.getid()) {
            EstudianteDAO estudianteDAO = new EstudianteDAO(this.cnx);
            return estudianteDAO.findByID(tipoUsuario);
        }

        return null;
    }
    
    
    public Material getMaterial (int tipoMaterial) throws SQLException {

        if (tipoMaterial == TipoMaterial.TEXTO.getid()) {

            TextoDAO dao = new TextoDAO(this.cnx);
            return dao.encontrarPorId(tipoMaterial);
        }

       /* if (tipoMaterial == TipoUsuario.PROFESOR.getid()) {
            ProfesorDAO dao = new ProfesorDAO(this.cnx);
            return dao.findByID(tipoMaterial);
        }

        if (tipoMaterial == TipoUsuario.ESTUDIANTE.getid()) {
            EstudianteDAO estudianteDAO = new EstudianteDAO(this.cnx);
            return estudianteDAO.findByID(tipoMaterial);
        }*/

        return null;
    }
}
