/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.ui;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    private PrintStream output = new PrintStream(System.out);
    private Scanner input = new Scanner(System.in).useDelimiter("\n");

    public void mostrarMenu() {

        output.println("Bienvenido al menu");
        output.println("1- Registrar Usuario");
        output.println("2- Listar Usuario");
        output.println("3- Realizar Depósito");
        output.println("4- Realizar Retiro");
        output.println("5- Salir");

    }

    public int leerEntero() {
        boolean thereIsError = true;
        int i = 0;
        do {
            try {
                i = input.nextInt();
                thereIsError = false;
            } catch (InputMismatchException e) {
                output.println("Introduzca un valor numerico valido");
            }
        } while (thereIsError);
        return i;
    }

    public double leerDouble() {
        boolean thereIsError = true;
        double i = 0;
        do {
            try {
                i = input.nextDouble();
                thereIsError = false;
            } catch (InputMismatchException e) {
                output.println("Introduzca un valor numerico valido");
            }
        } while (thereIsError);
        return i;
    }

    public String leerTexto() {
        return input.next();
    }

    public void mostrarMensaje(String message) {
        this.output.println(message);
    }

    public void mostrarTiposCuenta() {
        output.println("1. Cuenta Corriente");
        output.println("2. Cuenta de Ahorro");
        output.println("3. Cuenta de Ahorro Programado");
    }
}
