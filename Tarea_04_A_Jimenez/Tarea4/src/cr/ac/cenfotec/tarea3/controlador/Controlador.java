/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.controlador;

import cr.ac.cenfotec.tarea3.bl.entidades.*;
import cr.ac.cenfotec.tarea3.gestor.Gestor;
import cr.ac.cenfotec.tarea3.ui.UI;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controlador {
   
    UI interfaz = new UI();
    Gestor gestor = new Gestor();
    
    public void ejecutarPrograma() throws SQLException {
        int opcion = 0;
        do{
            interfaz.mostrarMenu();
            opcion = interfaz.leerEntero();
            procesarOpcionMenu(opcion);
        }while(opcion!=5);
    }

    private void procesarOpcionMenu(int opcion) throws SQLException {
       switch(opcion){
           case 1:
               crearCliente();
               break;
           case 2:
               listarCliente();
               break;
           case 3:
               realizarDeposito();
               break;
           case 4:
               realizarRetiro();
               break;
           default:
               interfaz.mostrarMensaje("Opcion invalida");
       }
    }

    private void realizarDeposito() throws SQLException {
        Cuenta cuentaDeposito = null;
        interfaz.mostrarMensaje("Ingrese su número de identificación:");
        int id = interfaz.leerEntero();
        interfaz.mostrarMensaje("Cuentas a su nombre -----> ");
        List<Cuenta> cuentasDelCliente = gestor.encontrarCuentasPorID(id);
        for (Cuenta cuenta:
                cuentasDelCliente) {
            interfaz.mostrarMensaje(cuenta.toString());
        }
        interfaz.mostrarMensaje("Ingrese el número de la cuenta a la que hará el depósito");
        int numero_cuenta = interfaz.leerEntero();
        for (Cuenta cuenta:
                cuentasDelCliente) {
            if(cuenta.getNumeroCuenta() == numero_cuenta) {
                cuentaDeposito = cuenta;
            }
        }
        if(cuentaDeposito != null) {
            interfaz.mostrarMensaje("Ingrese el monto del depósito");
            double monto = interfaz.leerDouble();
            gestor.realizarDeposito(cuentaDeposito, monto);
            String saldoRestante = String.valueOf(cuentaDeposito.getSaldo() + monto);
            interfaz.mostrarMensaje("Saldo restante:" + saldoRestante);
        } else {
            interfaz.mostrarMensaje("Número de cuenta inválido");
        }
    }

    private void realizarRetiro() throws SQLException {
        Cuenta cuentaRetiro = null;

        interfaz.mostrarMensaje("Ingrese su número de identificación:");
        int id = interfaz.leerEntero();
        interfaz.mostrarMensaje("Cuentas a su nombre -----> ");
        List<Cuenta> cuentasDelCliente = gestor.encontrarCuentasPorID(id);
        for (Cuenta cuenta:
                cuentasDelCliente) {
            interfaz.mostrarMensaje(cuenta.toString());
        }
        interfaz.mostrarMensaje("Ingrese el número de la cuenta de la que hará el retiro");
        int numero_cuenta = interfaz.leerEntero();
        for (Cuenta cuenta:
                cuentasDelCliente) {
            if(cuenta.getNumeroCuenta() == numero_cuenta) {
                cuentaRetiro = cuenta;
            }
        }

        cuentaRetiro = gestor.getCuentaByID(numero_cuenta);

        if(cuentaRetiro != null) {
            if(cuentaRetiro instanceof CuentaCorriente) {
                interfaz.mostrarMensaje("Ingrese el monto del retiro");
                double monto = interfaz.leerDouble();
                gestor.realizarRetiro(cuentaRetiro, monto);
                String saldoRestante = String.valueOf(cuentaRetiro.getSaldo() - monto);
                interfaz.mostrarMensaje("Saldo restante:" + saldoRestante);
            }
            if(cuentaRetiro instanceof CuentaAhorro) {
                interfaz.mostrarMensaje("Ingrese el monto del retiro");
                double monto = interfaz.leerDouble();
                gestor.realizarRetiro(cuentaRetiro, monto);
                String saldoRestante = String.valueOf(cuentaRetiro.getSaldo() - monto);
                interfaz.mostrarMensaje("Saldo restante:" + saldoRestante);
            }
            if(cuentaRetiro  instanceof CuentaAhorroProgramado) {
                LocalDate currentDate = LocalDate.now();
                Period period = Period.between(currentDate, ((CuentaAhorroProgramado) cuentaRetiro).getFecha_creacion());
                System.out.println(period.getMonths());
                if (period.getMonths() <= -12) {
                    interfaz.mostrarMensaje("Ingrese el monto del retiro");
                    double monto = interfaz.leerDouble();
                    gestor.realizarRetiro(cuentaRetiro, monto);
                    String saldoRestante = String.valueOf(cuentaRetiro.getSaldo() - monto);
                    interfaz.mostrarMensaje("Saldo restante:" + saldoRestante);
                } else {
                    interfaz.mostrarMensaje("La cuenta de ahorro programado no puede realizar retiros hasta un año de haberse creado");
                }
            }
        } else {
            interfaz.mostrarMensaje("Número de cuenta inválido");
        }
    }

    private void listarCliente() {
        List<Cliente> clientes = gestor.listClientes();
        interfaz.mostrarMensaje(clientes.toString());
    }

    private void crearCliente() throws SQLException {
        interfaz.mostrarMensaje("Escriba el nombre del cliente");
        String nombre = interfaz.leerTexto();
        interfaz.mostrarMensaje("Ingrese el numero de identificacion");
        int identificacion = interfaz.leerEntero();
        interfaz.mostrarMensaje("Escriba la direccion");
        String direccion = interfaz.leerTexto();
        gestor.crearCliente(nombre,identificacion,direccion);
        crearCuenta(identificacion);
    }

    private void crearCuenta(int identificacion) throws SQLException {
        int seguir = 0;
        do {
            int ID = identificacion;
            double saldo = 0;
            interfaz.mostrarMensaje("Elija el tipo de cuenta:");
            interfaz.mostrarTiposCuenta();
            int opcion = interfaz.leerEntero();
            determinarTipoCuenta(opcion, ID, saldo);

            interfaz.mostrarMensaje("¿Desear registrar otra cuenta?");
            interfaz.mostrarMensaje("1. Sí");
            interfaz.mostrarMensaje("2. No");
            seguir = interfaz.leerEntero();
        } while(seguir != 2);

    }

    private void determinarTipoCuenta(int opcion, int id, double saldo) throws SQLException {
        switch (opcion) {
            // Cuenta Corriente
            case 1:
                do {
                    interfaz.mostrarMensaje("Digite su depósito inicial (debe ser mayor a 50 mil colones):");
                    saldo = interfaz.leerEntero();
                    if (saldo >= 50000) {
                        gestor.crearCuenta(id, saldo, opcion);
                    }
                } while (saldo < 50000);
                break;
                // Cuenta Ahorro
            case 2:
                interfaz.mostrarMensaje("Digite su depósito inicial (debe ser mayor a 50 mil colones):");
                saldo = interfaz.leerEntero();
                if (saldo >= 50000) {
                    gestor.crearCuenta(id, saldo, opcion); }
                break;
            case 3:
                // Listar Cuentas Corrientes
                List<Cuenta> cuentasCorrientes = gestor.encontrarCuentaCorrientePorID(id);
                for (Cuenta cuenta:
                     cuentasCorrientes) {
                     interfaz.mostrarMensaje(cuenta.toString());
                }
                interfaz.mostrarMensaje("Escriba el número de cuenta de la cuenta corriente asociada a su nueva cuenta de ahorro programado:");
                int numero_cuenta = interfaz.leerEntero();
                Cuenta cuentaAsociada = new CuentaCorriente();
                for (Cuenta cuenta:
                        cuentasCorrientes) {
                    if(cuenta.getNumeroCuenta() == numero_cuenta) {
                        cuentaAsociada = cuenta;
                    }
                }
                LocalDate fecha_creacion = LocalDate.now();
                saldo = 0;
                if(cuentaAsociada != null) {
                    gestor.crearCuenta(id, saldo, cuentaAsociada, fecha_creacion);
                } else {
                    interfaz.mostrarMensaje("Número de cuenta inválido");
                }
                break;

        }
    }

}
