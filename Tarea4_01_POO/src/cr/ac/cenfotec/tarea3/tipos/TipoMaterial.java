/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.cenfotec.tarea3.tipos;



public enum TipoMaterial 
{ 
    // This will call enum constructor with one 
    // String argument 
    TEXTO(1), OTRO(2), AUDIO(3), VIDEO(4); 
  
    // declaring private variable for getting values 
    private int id; 
  
    // getter method 
    public int getid() 
    { 
        return this.id; 
    } 
  
    // enum constructor - cannot be public or protected 
    private TipoMaterial(int action) 
    { 
        this.id = action; 
    } 
    
   
} 