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
        output.println("1-Registrar Usuario");
        output.println("2-Registrar Material");
        output.println("3-Registrar prestamo");
        output.println("4-Listar prestamo");
        output.println("5-Salir");
    }

    public void mostrarMenuUsuario() {
        output.println("1-Registrar estudiante");
        output.println("2-Registrar profesor");
        output.println("3-Registrar administrativo");
        output.println("4-Salir");
    }

    public void mostrarMenuMaterial() {
        output.println("1-Registrar texto");
        output.println("2-Registrar audio");
        output.println("3-Registrar video");
        output.println("4-Registrar otro");
        output.println("5-Salir");
    }
    
       public void mostrarMenuPrestamo() {
        output.println("1-Crear prestamo");
        output.println("2-Salir");
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

    public void mostrarMensaje(String message) {
        this.output.println(message);
    }

    public String leerTexto() {
        return input.next();
    }
}
