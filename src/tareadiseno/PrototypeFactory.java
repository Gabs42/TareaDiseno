/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiseno;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class PrototypeFactory{//factory para generar prototypes que den clones de los combos predeterminados
    private HashMap<String,IPrototype> prototipos = new HashMap<>();
    public IPrototype obtenerPrototipo(String nombre){
        
        return prototipos.get(nombre).clonar();
    }
    
    public void  anadirPrototipo(String nombre,IPrototype prototype){
        prototipos.put(nombre,prototype);
    }
}
