/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiseno;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Menu {
    private static Menu menu;
    private ArrayList<PlatoPrincipal> platosPrincipales = new ArrayList<>();
    private ArrayList<Bebida> bebidas = new ArrayList<>();
    private ArrayList<Adicional> adicionales = new ArrayList<>();
    
    private Menu() throws FileNotFoundException{//abre los 3 archivos que tiene los platillos adicionales y bebidas y los inserta en las listas correspondientes
        Scanner platillos = new Scanner(new File("platillos.txt"));
        ArrayList<String> list = new ArrayList<>();
        while(platillos.hasNext()){
            list.add(platillos.next());
        }
        platillos.close();
        
        Scanner adicionales = new Scanner(new File("adicionales.txt"));
        ArrayList<String> listAdicionales = new ArrayList<>();
        while(adicionales.hasNext()){
            listAdicionales.add(adicionales.next());
        }
        adicionales.close();
                
        Scanner bebidas = new Scanner(new File("bebidas.txt"));
        ArrayList<String> listBebidas = new ArrayList<>();
        while(bebidas.hasNext()){
            listBebidas.add(bebidas.next());
        }
        bebidas.close();
        
        for(int i =0;i<list.size();i+=3){
            PlatoPrincipal p = new PlatoPrincipal(Integer.parseInt(list.get(i+2)),Float.parseFloat(list.get(i+1)),list.get(i));
            this.platosPrincipales.add(p);
        }
        
        for(int i =0;i<listAdicionales.size();i+=3){
            Adicional a = new Adicional(Integer.parseInt(listAdicionales.get(i+2)),Float.parseFloat(listAdicionales.get(i+1)),listAdicionales.get(i));
            this.adicionales.add(a);
        }
        
        for(int i =0;i<list.size();i+=3){
            Bebida b = new Bebida(Integer.parseInt(listBebidas.get(i+2)),Float.parseFloat(listBebidas.get(i+1)),listBebidas.get(i));
            this.bebidas.add(b);
        }
        System.out.println(this.bebidas.get(0).getNombre());
    
    }
    
    public static Menu obtenerMenu() throws FileNotFoundException{//llamada singleton
        if(menu==null){
            menu = new Menu();
            return menu;
        }
        else{
            return menu;
        }
    }
    
}
