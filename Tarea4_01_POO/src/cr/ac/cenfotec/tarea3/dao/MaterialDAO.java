package cr.ac.cenfotec.tarea3.dao;

import cr.ac.cenfotec.tarea3.bl.entidades.Material;
import cr.ac.cenfotec.tarea3.bl.entidades.Persona;
import cr.ac.cenfotec.tarea3.bl.entidades.interfaces.SerializacionCSV;

import java.util.List;

public abstract class MaterialDAO implements SerializacionCSV {

    public abstract boolean save(Material newMaterial);
    public abstract List<Material> findAll();

}
