package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Cuenta;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaAhorro;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaCorriente;

import java.io.*;
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

public class CuentaCorrienteDAO{


    Connection cnx;

    public CuentaCorrienteDAO(Connection connection){
        this.cnx = connection;
    }

    public void save(CuentaCorriente material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into cuentacorriente (id_cliente, saldo)");
        buildSentence.append(" values (");
        buildSentence.append(material.getID_cliente());
        buildSentence.append(",");
        buildSentence.append(material.getSaldo());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public void deposito(Cuenta material, double deposito) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("update cuentacorriente set saldo = ");
        buildSentence.append(material.getSaldo() + deposito);
        buildSentence.append(" where idcuenta = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public void retiro(Cuenta material, double deposito) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("update cuentacorriente set saldo = ");
        buildSentence.append(material.getSaldo() - deposito);
        buildSentence.append(" where idcuentaCorriente = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }


    public List<CuentaCorriente> findAll() throws SQLException {
        ArrayList<CuentaCorriente> listOfResults = new ArrayList<>();
        ResultSet result;
        try (Statement stmt = cnx.createStatement()) {
            result = stmt.executeQuery("select * from cuentacorriente");
            while(result.next()){
                CuentaCorriente uno = new CuentaCorriente();
                uno.setNumeroCuenta(result.getInt("idcuentaCorriente"));
                uno.setID_cliente(result.getInt("id_cliente"));
                uno.setSaldo(result.getInt("saldo"));
                listOfResults.add(uno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfResults;
    }

    public CuentaCorriente findCuentaCorrienteByID(int id) throws SQLException {
        List<CuentaCorriente> cuentas = findAll();
        for (CuentaCorriente cuenta: cuentas) {
            if(cuenta.getNumeroCuenta() == id) {
                return cuenta;
            }
        }
        return null;
    }

}
