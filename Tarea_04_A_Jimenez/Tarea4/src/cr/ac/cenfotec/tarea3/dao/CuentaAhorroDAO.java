package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Cuenta;
import cr.ac.cenfotec.tarea3.bl.entidades.CuentaAhorro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CuentaAhorroDAO{


    Connection cnx;

    public CuentaAhorroDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(CuentaAhorro material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into cuentaahorro (id_cliente, saldo)");
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
        StringBuilder buildSentence = new StringBuilder("update cuentaahorro set saldo = ");
        buildSentence.append(material.getSaldo() + deposito);
        buildSentence.append(" where idcuentaahorro = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public void retiro(Cuenta material, double deposito) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("update cuentaahorro set saldo = ");
        buildSentence.append(material.getSaldo() - deposito);
        buildSentence.append(" where idcuentaahorro = ");
        buildSentence.append(material.getNumeroCuenta());
        buildSentence.append(";");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }


    public List<CuentaAhorro> findAll() throws SQLException {
        ArrayList<CuentaAhorro> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from cuentaahorro");
        while(result.next()){
            CuentaAhorro uno = new CuentaAhorro();
            uno.setID_cliente(result.getInt("id_cliente"));
            uno.setNumeroCuenta(result.getInt("idcuentaahorro"));
            uno.setSaldo(result.getInt("saldo"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public CuentaAhorro findCuentaAhorroByID(int id) throws SQLException {
        List<CuentaAhorro> cuentas = findAll();
        for (CuentaAhorro cuenta: cuentas) {
            if(cuenta.getNumeroCuenta() == id) {
                return cuenta;
            }
        }
        return null;
    }

}
