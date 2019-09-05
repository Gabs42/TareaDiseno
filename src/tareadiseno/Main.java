package tareadiseno;

import java.io.FileNotFoundException;

import gui.Ventana;

/**
 *
 * @author Gabriel
 */
public class Main {
	
    public static void main(String[] args) throws FileNotFoundException{
    	Ventana ventana = new Ventana(Menu.obtenerMenu());
    	ventana.iniciar();
    }
    
}
