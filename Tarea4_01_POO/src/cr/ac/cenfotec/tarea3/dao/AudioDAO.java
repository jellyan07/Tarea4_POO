package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Audio;
import cr.ac.cenfotec.tarea3.tipos.TipoMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class AudioDAO {

   private final String TEMPLATE_CMD_INSERTAR = "insert into audio(fechacompra,restringido,tema, idioma,formato,duracion,nombrematerial) "
            + "values (?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSAUDIO = "select * from audio";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosAudios;

    public AudioDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosAudios = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSAUDIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Audio encontrarPorId(String identificacion) {
        return null;
    }

    public void save(Audio nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setDate(1, convertToSqlDate(nuevo.getFechaCompra()));
            this.cmdInsertar.setBoolean(2, nuevo.isRestringido());
            this.cmdInsertar.setString(3, nuevo.getTema());
            this.cmdInsertar.setString(4, nuevo.getIdioma());
            this.cmdInsertar.setString(5, nuevo.getFormato());
            this.cmdInsertar.setString(6, nuevo.getDuracion());
            this.cmdInsertar.setString(6, nuevo.getNombreMaterial());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Audio> obtenerTodosLosTextos() throws SQLException {
        ArrayList<Audio> listaAudio = new ArrayList<>();
        ResultSet resultado = this.queryTodosLosAudios.executeQuery();
        while (resultado.next()) {
            Audio leido = new Audio();
            leido.setFechaCompra(resultado.getDate("fechacompra"));
            leido.setRestringido(resultado.getBoolean("restringido"));
            leido.setTema(resultado.getString("tema"));
            leido.setIdioma(resultado.getString("idioma"));
            leido.setFormato(resultado.getString("formato"));
            leido.setDuracion(resultado.getString("duracion"));
            leido.setNombreMaterial(resultado.getString("fechacompra"));
            leido.setIdTipoMaterial(TipoMaterial.AUDIO.getid());
            
            listaAudio.add(leido);
        }

        return listaAudio;

    }

}
