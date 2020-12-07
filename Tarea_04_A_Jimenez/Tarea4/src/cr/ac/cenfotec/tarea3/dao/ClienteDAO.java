package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Cliente;
import cr.ac.cenfotec.tarea3.bl.entidades.Cuenta;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaAhorro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {


    Connection cnx;

    public ClienteDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(Cliente material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into cliente (idcliente, nombre, direccion)");
        buildSentence.append(" values (");
        buildSentence.append(material.getIdentificacion());
        buildSentence.append(",'");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getDireccion());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Cliente> findAll() throws SQLException {
        ArrayList<Cliente> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from cliente");
        while(result.next()){
            Cliente uno = new Cliente();
            uno.setIdentificacion(result.getInt("idcliente"));
            uno.setNombre(result.getString("nombre"));
            uno.setDireccion(result.getString("apellido"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Cliente findClienteByID(int id) throws SQLException {
        List<Cliente> clientes = findAll();
        for (Cliente cliente: clientes) {
            if(cliente.getIdentificacion() == id) {
                return cliente;
            }
        }
        return null;
    }
}
