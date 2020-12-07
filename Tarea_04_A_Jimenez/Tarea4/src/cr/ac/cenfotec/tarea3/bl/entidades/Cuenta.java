/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

import cr.ac.cenfotec.tarea3.interfaces.SerializacionCSV;

public abstract class Cuenta {

    protected int ID_cliente;
    protected int numeroCuenta;
    protected double saldo;

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }

    public Cuenta() {
    }

    public Cuenta(int ID_cliente, double saldo) {
        this.ID_cliente = ID_cliente;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "Dueño de la Cuenta=" + ID_cliente + "numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + '}';
    }

}
