/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Scarlet Cevallos
 */
public class Vehiculo {
    protected int id;
    protected TipoVehiculo tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoMotor;
    protected int anio;
    protected int recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;
    protected ArrayList<Oferta>ofertas;
    protected Vendedor vendedor;
    protected int ind;

    
    public Vehiculo(int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio) {
        this.id=id;
        this.tipo=tipo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
        this.ind = 0;
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public String getColor() {
        return color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setRecorrido(int recorrido) {
        this.recorrido = recorrido;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

    public static Vehiculo buscarPorPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo v: vehiculos){
        if(v.placa.equals(placa)){
            return v;      
        }
        }
      return null; 
    }

    public void saveFile(String nomfile){
       try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){

            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio+"|"+this.ofertas);
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
    }

    // si quiero guardar toda la info a partir de un archivo o una lista
    public static void saveFile(ArrayList<Usuario> usuarios, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            for(Usuario u: usuarios){
                pw.println(u.id+"|"+u.nombre+"|"+u.apellidos+"|"+u.organizacion+"|"+u.correoElectronico+"|"+u.clave);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
 
    public void revisarOfertaActual() {
        if (ind >= 0 && ind < ofertas.size()) {
            Oferta ofertaActual = ofertas.get(ind);
            System.out.println("Oferta #" + (ind + 1) + ":");
            System.out.println("Correo: " + ofertaActual.getComprador().correoElectronico); // Imprimir información de la oferta
        } else {
            System.out.println("No hay más ofertas disponibles.");
        }
    }
    
     public void avanzarOferta() {
        if (ind < ofertas.size() - 1) {
            ind++;
            revisarOfertaActual();
        } else {
            System.out.println("Ya has revisado todas las ofertas.");
        }
    }
    
    public void retrocederOferta() {
        if (ind > 0) {
            ind--;
            revisarOfertaActual();
        } else {
            System.out.println("No puedes retroceder más, es la primera oferta.");
        }
    }
     
    @Override
    public String toString() {
        return "placa: " + placa + "\nMarca: " + marca + "\nModelo: " + modelo + "\nTipo motor: " + tipoMotor + "\nAño: " + anio + "\nRecorrido: " + recorrido + "\nColor: " + color + "\nTipo combustible: " + tipoCombustible + "\nprecio: " + precio ;
    
    }


}
