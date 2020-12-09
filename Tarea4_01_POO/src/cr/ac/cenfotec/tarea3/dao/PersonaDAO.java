/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import cr.ac.cenfotec.tarea3.bl.entidades.interfaces.SerializacionCSV;
import java.util.List;

public abstract class PersonaDAO{

    public abstract boolean save(Persona newPersona);

}
