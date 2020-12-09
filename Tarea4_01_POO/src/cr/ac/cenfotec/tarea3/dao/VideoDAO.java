package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Video;
import cr.ac.cenfotec.tarea3.tipos.TipoMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class VideoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into video(nombrematerial,fechacompra,restringido,tema, idioma,formato,duracion,director) "
            + "values (?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSVIDEOS = "select * from video";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosVideos;

    public VideoDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosVideos = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSVIDEOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Video encontrarPorId(String identificacion) throws SQLException {
          Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from video");
        buildSentence.append(" where idvideo = ");
        buildSentence.append(identificacion);

        ResultSet result = stmt.executeQuery(buildSentence.toString());

        while (result.next()) {
            Video leido = new Video();

            leido.setNombreMaterial(result.getString("nombrematerial"));
            leido.setFechaCompra(result.getDate("fechacompra"));
            leido.setRestringido(result.getBoolean("restringido"));
            leido.setTema(result.getString("tema"));
            leido.setIdioma(result.getString("idioma"));
            leido.setFormato(result.getString("formato"));
            leido.setDuracion(result.getString("duracion"));
            leido.setDirector(result.getString("director"));
            leido.setIdTipoMaterial(TipoMaterial.VIDEO.getid());
            return leido;
        }
        return null;
    }

    public void save(Video nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            
             this.cmdInsertar.setString(1, nuevo.getNombreMaterial());
            this.cmdInsertar.setDate(2, convertToSqlDate(nuevo.getFechaCompra()));
            this.cmdInsertar.setBoolean(3, nuevo.isRestringido());
            this.cmdInsertar.setString(4, nuevo.getTema());
            this.cmdInsertar.setString(5, nuevo.getIdioma());
            this.cmdInsertar.setString(6, nuevo.getFormato());
            this.cmdInsertar.setString(7, nuevo.getDuracion());
            this.cmdInsertar.setString(8, nuevo.getDirector());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Video> obtenerTodosLosVideos() throws SQLException {
        ArrayList<Video> listaVideos = new ArrayList<>();
        ResultSet resultado = this.queryTodosLosVideos.executeQuery();
        while (resultado.next()) {
            Video leido = new Video();
            
            
            leido.setNombreMaterial(resultado.getString("nombrematerial"));
            leido.setFechaCompra(resultado.getDate("fechacompra"));
            leido.setRestringido(resultado.getBoolean("restringido"));
            leido.setTema(resultado.getString("tema"));
            leido.setIdioma(resultado.getString("idioma"));
            leido.setFormato(resultado.getString("formato"));
            leido.setDuracion(resultado.getString("duracion"));
            leido.setDirector(resultado.getString("director"));
            leido.setIdTipoMaterial(TipoMaterial.VIDEO.getid());
            listaVideos.add(leido);
        }

        return listaVideos;

    }

}
