/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import static Vehiculos_Package.TipoVehiculo.AUTO;
import static Vehiculos_Package.TipoVehiculo.CAMIONETA;
import static Vehiculos_Package.TipoVehiculo.MOTO;
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
           //(int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio)
            pw.println(this.id+"|"+this.tipo+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio);
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
    }

    // si quiero guardar toda la info a partir de un archivo o una lista
    public static void saveFile(ArrayList<Vehiculo> vehiculos, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            for(Vehiculo v: vehiculos){
                TipoVehiculo tipo = v.tipo;
                switch(tipo){
                    case AUTO:
                        Auto a = (Auto)v;
                        pw.println(a.id+"|"+a.tipo+"|"+a.placa+"|"+a.marca+"|"+a.modelo+"|"+a.tipoMotor+"|"+a.anio+"|"+a.recorrido+"|"+a.color+"|"+a.tipoCombustible+"|"+a.precio+"|"+a.getVidrios()+"|"+a.getTransmision());
                        break;
                    case CAMIONETA:
                        Camionetas c = (Camionetas)v;
                        pw.println(c.id+"|"+c.tipo+"|"+c.placa+"|"+c.marca+"|"+c.modelo+"|"+c.tipoMotor+"|"+c.anio+"|"+c.recorrido+"|"+c.color+"|"+c.tipoCombustible+"|"+c.precio+"|"+c.getVidrios()+"|"+c.getTransmision()+"|"+c.getTraccion());
                        break;
                    case MOTO:
                        pw.println(v.id+"|"+v.tipo+"|"+v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+v.anio+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio);
                        break;
                    default:
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
        public static ArrayList<Vehiculo> leerVehiculos(String nomFile){
        ArrayList<Vehiculo> vehiculos= new ArrayList<>();
        try(Scanner sc= new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                String [] tokens= linea.split("\\|");
                TipoVehiculo tipo= TipoVehiculo.valueOf(tokens[1].toUpperCase());
                Vehiculo v;
                switch(tipo){
                    case AUTO:
                        v= new Auto(Integer.parseInt(tokens[0]),tipo,tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]),tokens[11],tokens[12]);
                        break;
                    case CAMIONETA:
                        v= new Camionetas(Integer.parseInt(tokens[0]),tipo,tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]),tokens[11],tokens[12],Integer.parseInt(tokens[13]));
                        break;
                    case MOTO:
                        v= new Vehiculo(Integer.parseInt(tokens[0]),tipo,tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Integer.parseInt(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]));
                        break;
                    default:
                        continue;
                }
                vehiculos.add(v);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            vehiculos.clear();
        }
        return vehiculos;
        }
    
        public static void eliminarVehiculo(String nomfile, Vehiculo vehiculo, ArrayList<Vehiculo> vehiculos){
            vehiculos.remove(vehiculo);
            Utilitaria.vaciarArchivo(nomfile);
            Vehiculo.saveFile(vehiculos, nomfile);
            
        }
    
        public static ArrayList<Oferta> rellenarOfertasVehiculo(String nomfile, Vehiculo vehiculo){
            ArrayList<Oferta> ofertas = Oferta.readFile(nomfile);
            ArrayList<Oferta> vOf = new ArrayList<>();
            for(Oferta of: ofertas){
                if(of.getIdVehiculo() == vehiculo.id)
                    vOf.add(of);
                
            }
            return vOf;
        }
    @Override
    public String toString() {
        return "placa: " + placa + "\nMarca: " + marca + "\nModelo: " + modelo + "\nTipo motor: " + tipoMotor + "\nAño: " + anio + "\nRecorrido: " + recorrido + "\nColor: " + color + "\nTipo combustible: " + tipoCombustible + "\nprecio: " + precio ;
    
    }


}
