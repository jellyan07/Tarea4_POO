package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Cuenta;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaAhorro;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaAhorroProgramado;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CuentaAhorroProgramadoDAO{

    Connection cnx;
    CuentaCorrienteDAO cuentaCorrienteDAO;

    public CuentaAhorroProgramadoDAO(Connection cnx){
        this.cnx = cnx;
        this.cuentaCorrienteDAO = new CuentaCorrienteDAO(this.cnx);
    }

    public void save(CuentaAhorroProgramado material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into cuentaAhorroProgramado (id_cliente, saldo, cuenta_asociada, fecha_creacion)");
        buildSentence.append(" values (");
        buildSentence.append(material.getID_cliente());
        buildSentence.append(",");
        buildSentence.append(material.getSaldo());
        buildSentence.append(",");
        buildSentence.append(material.getCuentaAsociada().getNumeroCuenta());
        buildSentence.append(",'");
        buildSentence.append(material.getFecha_creacion());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public void deposito(Cuenta material, double deposito) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("update cuentaahorroprogramado set saldo = ");
        buildSentence.append(material.getSaldo() + deposito);
        buildSentence.append(" where idcuentaahorroprogramado = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public void retiro(Cuenta material, double deposito) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("update cuentaahorroprogramado set saldo = ");
        buildSentence.append(material.getSaldo() - deposito);
        buildSentence.append(" where idcuenta = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<CuentaAhorroProgramado> findAll() throws SQLException {
        ArrayList<CuentaAhorroProgramado> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from cuentaahorroprogramado");
        while(result.next()){
            CuentaAhorroProgramado uno = new CuentaAhorroProgramado();
            uno.setID_cliente(result.getInt("id_cliente"));
            uno.setNumeroCuenta(result.getInt("idcuentaahorroprogramado"));
            uno.setSaldo(result.getInt("saldo"));
            uno.setCuentaAsociada(cuentaCorrienteDAO.findCuentaCorrienteByID(result.getInt("cuenta_asociada")));
            uno.setFecha_creacion(result.getDate("fecha_creacion").toLocalDate());
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public CuentaAhorroProgramado findCuentaAhorroProgramadoByID(int id) throws SQLException {
        List<CuentaAhorroProgramado> cuentas = findAll();
        for (CuentaAhorroProgramado cuenta: cuentas) {
            if(cuenta.getNumeroCuenta() == id) {
                return cuenta;
            }
        }
        return null;
    }

}
