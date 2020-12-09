/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.bl.entidades.interfaces;

import cr.ac.cenfotec.tarea3.bl.entidades.Material;
import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import java.util.List;


public interface SerializacionCSV {
  
    public String toCSVLine(Object objecto);
    public List findAll();

}

