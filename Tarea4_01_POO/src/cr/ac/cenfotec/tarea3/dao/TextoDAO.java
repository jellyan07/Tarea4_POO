package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Texto;
import cr.ac.cenfotec.tarea3.tipos.TipoMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class TextoDAO {

    private final String TEMPLATE_CMD_INSERTAR = "insert into texto(fechacompra,restringido,tema, idioma,titulo,nombreautor,fechapublicacion,paginas,nombrematerial) "
            + "values (?,?,?,?,?,?,?,?,?)";
    private final String TEMPLATE_QRY_TODOSLOSTEXTOS = "select * from texto";

    private Connection cnx;
    private PreparedStatement cmdInsertar;
    private PreparedStatement queryTodosLosTextos;

    public TextoDAO(Connection conexion) {
        this.cnx = conexion;
        try {
            this.cmdInsertar = cnx.prepareStatement(TEMPLATE_CMD_INSERTAR);
            this.queryTodosLosTextos = cnx.prepareStatement(TEMPLATE_QRY_TODOSLOSTEXTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Texto encontrarPorId(int identificacion) throws SQLException {
          Statement stmt = cnx.createStatement();

        StringBuilder buildSentence = new StringBuilder("select * from texto");
        buildSentence.append(" where idtexto = ");
        buildSentence.append(identificacion);

        ResultSet resultado = stmt.executeQuery(buildSentence.toString());

        while (resultado.next()) {
            Texto leido = new Texto();
           leido.setNombreMaterial(resultado.getString("nombrematerial"));
            leido.setFechaCompra(resultado.getDate("fechacompra"));
            leido.setRestringido(resultado.getBoolean("restringido"));
            leido.setTema(resultado.getString("tema"));
            leido.setIdioma(resultado.getString("idioma"));
            leido.setSignatura(resultado.getInt("idtexto"));
          //  leido.(resultado.getString("titulo"));
            leido.setNombreAutor(resultado.getString("nombreautor"));
            leido.setFechaPublicacion(resultado.getDate("fechapublicacion"));
            leido.setPaginas(resultado.getInt("paginas"));
            leido.setIdTipoMaterial(TipoMaterial.TEXTO.getid());
            return leido;
        }
        return null;
    }

    public void save(Texto nuevo) throws SQLException {
        if (this.cmdInsertar != null) {
            this.cmdInsertar.setDate(1, convertToSqlDate(nuevo.getFechaCompra()));
            this.cmdInsertar.setBoolean(2, nuevo.isRestringido());
            this.cmdInsertar.setString(3, nuevo.getTema());
            this.cmdInsertar.setString(4, nuevo.getIdioma());
            this.cmdInsertar.setString(5, nuevo.getNombreMaterial());
            this.cmdInsertar.setString(6, nuevo.getNombreAutor());
            this.cmdInsertar.setDate(7, convertToSqlDate(nuevo.getFechaPublicacion()));
            this.cmdInsertar.setInt(8, nuevo.getPaginas());
            this.cmdInsertar.setString(9, nuevo.getNombreMaterial());
            this.cmdInsertar.execute();
        }
    }

    private java.sql.Date convertToSqlDate(java.util.Date fechaAConvertir) {
        return new java.sql.Date(fechaAConvertir.getTime());
    }

    public List<Texto> obtenerTodosLosTextos() throws SQLException {
        ArrayList<Texto> listaTextos = new ArrayList<>();
        ResultSet resultado = this.queryTodosLosTextos.executeQuery();
        while (resultado.next()) {
            Texto leido = new Texto();
            
            
            leido.setNombreMaterial(resultado.getString("nombrematerial"));
            leido.setFechaCompra(resultado.getDate("fechacompra"));
            leido.setRestringido(resultado.getBoolean("restringido"));
            leido.setTema(resultado.getString("tema"));
            leido.setIdioma(resultado.getString("idioma"));
            leido.setSignatura(resultado.getInt("idtexto"));
          //  leido.(resultado.getString("titulo"));
            leido.setNombreAutor(resultado.getString("nombreautor"));
            leido.setFechaPublicacion(resultado.getDate("fechapublicacion"));
            leido.setPaginas(resultado.getInt("paginas"));
            leido.setIdTipoMaterial(TipoMaterial.TEXTO.getid());
            listaTextos.add(leido);
        }

        return listaTextos;

    }
}
