/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

public class CuentaAhorro extends Cuenta {

    final double interes = 0.5;

    public double getInteres() {
        return interes;
    }

    public CuentaAhorro() {
    }

    public CuentaAhorro(int id, double saldo) {
        super(id, saldo);
    }

    @Override
    public String toString() {
        return "CuentaAhorro{" + super.toString() + '}';
    }

}
