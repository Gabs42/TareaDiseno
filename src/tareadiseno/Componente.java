/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiseno;

/**
 *
 * @author Gabriel
 */
public abstract class Componente {//Clase abstracta de la cual heredaran bebida,plato y adicional
    
    private int codigo;
    private float precio;
    private String nombre;

    public Componente(int codigo, float precio, String nombre) {
        this.codigo = codigo;
        this.precio = precio;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString() {
      return nombre;
    }
    
}
