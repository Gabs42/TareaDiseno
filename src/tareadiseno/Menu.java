/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiseno;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import tareadiseno.Combo.ComboBuilder;

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
        Scanner platillos = new Scanner(new File("platillos.txt"));//scanner que abre los 4 archivos
        ArrayList<String> list = new ArrayList<>();
        while(platillos.hasNext()){
            list.add(platillos.next());//se inserta en una lista los platillos,adicionales etc.
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
        
        Scanner combos = new Scanner(new File("combos.txt"));
        ArrayList<String> listCombos = new ArrayList<>();
        while(combos.hasNext()){
            listCombos.add(combos.next());
        }
        combos.close();
        
        for(int i =0;i<list.size();i+=3){
            PlatoPrincipal p = new PlatoPrincipal(Integer.parseInt(list.get(i+2)),Float.parseFloat(list.get(i+1)),list.get(i));
            this.platosPrincipales.add(p);//se agregan del txt los platillos ya creados a la lista de platillos del menus
        }
        
        for(int i =0;i<listAdicionales.size();i+=3){
            Adicional a = new Adicional(Integer.parseInt(listAdicionales.get(i+2)),Float.parseFloat(listAdicionales.get(i+1)),listAdicionales.get(i));
            this.adicionales.add(a);
        }
        
        for(int i =0;i<list.size();i+=3){
            Bebida b = new Bebida(Integer.parseInt(listBebidas.get(i+2)),Float.parseFloat(listBebidas.get(i+1)),listBebidas.get(i));
            this.bebidas.add(b);
        }
        ComboBuilder comboAgregar = new ComboBuilder();//se crea un combo builder para poder agregar los combos predeterminados
        for(int i = 0;i<listCombos.size();i++){
            if(listCombos.get(i).compareToIgnoreCase(".")==0){//dentro del txt el . indica el final de un combo por lo que aqui se agrega al prototye para que estos puedan ser clonados
                Combo comboPred = comboAgregar.build();
                PrototypeFactory.anadirPrototipo("Combo "+comboPred.getPlatoPrincipal().getNombre(),comboPred);
                comboAgregar = new ComboBuilder();
            }
            
            for(PlatoPrincipal p:this.platosPrincipales){//Se agrega el plato/bebida/Adicional al combo
                if(p.getNombre().compareToIgnoreCase(listCombos.get(i))==0){
                    comboAgregar.setPlatoPrincipal(p).build();
                }
            }
            
            for(Bebida b:this.bebidas){
                if(b.getNombre().compareToIgnoreCase(listCombos.get(i))==0){
                    comboAgregar.anadirBebida(b).build();
                }
            }
            
            for(Adicional a:this.adicionales){
                if(a.getNombre().compareToIgnoreCase(listCombos.get(i))==0){
                    comboAgregar.anadirAdicional(a).build();
                }
            }
            
        }
    
    }
    
    public ArrayList<PlatoPrincipal> getPlatosPrincipales() {
    	return platosPrincipales;
    }
    
    public ArrayList<Bebida> getBebidas() {
    	return bebidas;
    }
    
    public ArrayList<Adicional> getAdicionales() {
    	return adicionales;
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
