/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.tipos;


public enum TipoUsuario 
{ 
    // This will call enum constructor with one 
    // String argument 
    ESTUDIANTE(1), PROFESOR(2), ADMINISTRATIVO(3); 
  
    // declaring private variable for getting values 
    private int id; 
  
    // getter method 
    public int getid() 
    { 
        return this.id; 
    } 
  
    // enum constructor - cannot be public or protected 
    private TipoUsuario(int action) 
    { 
        this.id = action; 
    } 
    
   
} 