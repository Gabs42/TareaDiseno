/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiseño;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Combo {
    private PlatoPrincipal platoPrincipal;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Adicional> adicionales;

    public Combo(PlatoPrincipal platoPrincipal, ArrayList<Bebida> bebidas, ArrayList<Adicional> adicionales) {
        this.platoPrincipal = platoPrincipal;
        this.bebidas = bebidas;
        this.adicionales = adicionales;
    }
    

    public PlatoPrincipal getPlatoPrincipal() {
        return platoPrincipal;
    }

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public ArrayList<Adicional> getAdicionales() {
        return adicionales;
    }
    public float precioTotal(){
        float resultado = platoPrincipal.getPrecio();
        for(int i = 0;i<bebidas.size();i++){
            resultado+=bebidas.get(i).getPrecio();
        }
        for(int i = 0;i<adicionales.size();i++){
            resultado+=adicionales.get(i).getPrecio();
        }
        return resultado;
    }
    public class ComboBuilder implements IBuilder<Combo>{
        private PlatoPrincipal platoPrincipal;
        private ArrayList<Bebida> bebidas= new ArrayList<>();
        private ArrayList<Adicional> adicionales = new ArrayList<>();
        
        public ComboBuilder(){}
        
        public ComboBuilder setPlatoPrincipal(PlatoPrincipal plato){
            this.platoPrincipal = plato;
            return this;
        }
        
        public ComboBuilder anadirBebida(Bebida bebida){
            this.bebidas.add(bebida);
            return this;
        }
        
        public ComboBuilder anadirAdicional(Adicional adicional){
            this.adicionales.add(adicional);
            return this;
        }
        
        @Override
        public Combo build(){
            return new Combo(platoPrincipal,bebidas,adicionales);
        }
    }
}
