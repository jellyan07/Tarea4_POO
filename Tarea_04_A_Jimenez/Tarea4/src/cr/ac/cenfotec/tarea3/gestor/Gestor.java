/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.gestor;

import cr.ac.cenfotec.tarea3.bl.entidades.*;
import cr.ac.cenfotec.tarea3.dao.ClienteDAO;
import cr.ac.cenfotec.tarea3.dao.CuentaAhorroDAO;
import cr.ac.cenfotec.tarea3.dao.CuentaAhorroProgramadoDAO;
import cr.ac.cenfotec.tarea3.dao.CuentaCorrienteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Gestor {

    private Connection conection;

    private ClienteDAO clienteDAO;
    private CuentaCorrienteDAO cuentaCorrienteDAO;
    private CuentaAhorroDAO cuentaAhorroDAO;
    private CuentaAhorroProgramadoDAO cuentaAhorroProgramadoDAO;

    public Gestor(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bdbanco?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "root", "root");
            this.clienteDAO = new ClienteDAO(this.conection);
            this.cuentaCorrienteDAO = new CuentaCorrienteDAO(this.conection);
            this.cuentaAhorroDAO = new CuentaAhorroDAO(this.conection);
            this.cuentaAhorroProgramadoDAO = new CuentaAhorroProgramadoDAO(this.conection);
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            e.printStackTrace();
        }
    }

    public boolean crearCliente(String nombre, int identificacion, String direccion){
        Cliente nuevoCliente = new Cliente(nombre, identificacion, direccion);
        try{
            this.clienteDAO.save(nuevoCliente);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Cliente> listClientes() {
        try{
            return this.clienteDAO.findAll();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<Cliente>();
    }

    public List<Cuenta> encontrarCuentasPorID (int id) throws SQLException {
        List<Cuenta> cuentasDeCliente = new ArrayList<>();
        for(Cuenta cuenta: this.cuentaCorrienteDAO.findAll()) {
            if(cuenta.getID_cliente() == id) {
                cuentasDeCliente.add(cuenta);
            }
        }
        for(Cuenta cuenta: this.cuentaAhorroDAO.findAll()) {
            if(cuenta.getID_cliente() == id) {
                cuentasDeCliente.add(cuenta);
            }
        }
        for(Cuenta cuenta: this.cuentaAhorroProgramadoDAO.findAll()) {
            if(cuenta.getID_cliente() == id) {
                cuentasDeCliente.add(cuenta);
            }
        }

        return cuentasDeCliente;
    }

    public List<Cuenta> encontrarCuentaCorrientePorID (int id) throws SQLException {
        ArrayList<Cuenta> cuentasDeCliente = new ArrayList<>();
        for(Cuenta cuenta: this.cuentaCorrienteDAO.findAll()) {
            if(cuenta.getID_cliente() == id) {
                cuentasDeCliente.add(cuenta);
            }
        }

        return cuentasDeCliente;
    }

    public void crearCuenta(int id, double saldo, int tipo) throws SQLException {
        if(tipo == 1) {
            Cuenta nuevaCuenta = new CuentaCorriente(id, saldo);
            cuentaCorrienteDAO.save((CuentaCorriente) nuevaCuenta);
        }
        if (tipo ==2) {
            CuentaAhorro nuevaCuenta = new CuentaAhorro(id, saldo);
            cuentaAhorroDAO.save(nuevaCuenta);
        }
        // Cuenta de ahorro Programado va aparte porque necesita un parametro extra: La cuenta asociada
    }

    public void crearCuenta(int id, double saldo, Cuenta cuentaAsociada, LocalDate fecha_creacion) throws SQLException {
        Cuenta nuevaCuenta = new CuentaAhorroProgramado(id, saldo, cuentaAsociada, fecha_creacion);
        cuentaAhorroProgramadoDAO.save((CuentaAhorroProgramado) nuevaCuenta);
    }

    public void realizarDeposito(Cuenta cuentaDeposito, double monto) throws SQLException {
        if(cuentaDeposito instanceof CuentaCorriente) {
            cuentaCorrienteDAO.deposito(cuentaDeposito, monto);
        }
        if(cuentaDeposito instanceof CuentaAhorro) {
            cuentaAhorroDAO.deposito(cuentaDeposito, monto);
        }
        if(cuentaDeposito instanceof  CuentaAhorroProgramado) {
            cuentaAhorroProgramadoDAO.deposito(cuentaDeposito, monto);
        }
    }

    public Cuenta getCuentaByID(int id) throws SQLException {
        Cuenta cuentaEncontrada = null;
        for(Cuenta cuenta: this.cuentaCorrienteDAO.findAll()) {
            if(cuenta.getNumeroCuenta() == id) {
                cuentaEncontrada = cuenta;
            }
        }
        for(Cuenta cuenta: this.cuentaAhorroDAO.findAll()) {
            if(cuenta.getNumeroCuenta() == id) {
                cuentaEncontrada = cuenta;
            }
        }
        for(Cuenta cuenta: this.cuentaAhorroProgramadoDAO.findAll()) {
            if(cuenta.getNumeroCuenta() == id) {
                cuentaEncontrada = cuenta;
            }
        }

        return cuentaEncontrada;
    }

    public void realizarRetiro(Cuenta cuentaRetiro, double monto) throws SQLException {
        if(cuentaRetiro instanceof CuentaCorriente) {
            cuentaCorrienteDAO.retiro(cuentaRetiro, monto);
        }
        if(cuentaRetiro instanceof CuentaAhorro) {
            cuentaAhorroDAO.retiro(cuentaRetiro, monto);
        }
        if(cuentaRetiro instanceof  CuentaAhorroProgramado) {
            cuentaAhorroProgramadoDAO.retiro(cuentaRetiro, monto);
        }
    }
}
