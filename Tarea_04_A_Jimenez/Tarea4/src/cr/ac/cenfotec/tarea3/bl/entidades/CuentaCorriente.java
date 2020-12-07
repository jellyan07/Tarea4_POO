/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades;

public class CuentaCorriente extends Cuenta {

    public CuentaCorriente() {
    }

    public CuentaCorriente(int id_cliente, double saldo) {
        super(id_cliente, saldo);
    }

    @Override
    public String toString() {
        return "CuentaCorriente{" + super.toString() + '}';
    }

}
