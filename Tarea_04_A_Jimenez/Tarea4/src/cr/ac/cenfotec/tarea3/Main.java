package cr.ac.cenfotec.tarea3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cr.ac.cenfotec.tarea3.controlador.Controlador;

import java.sql.SQLException;

/**
 *
 * @author K_DaS
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Controlador controller = new Controlador();
        controller.ejecutarPrograma();
    }
    
}
