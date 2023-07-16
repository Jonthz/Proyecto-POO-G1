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
public class Oferta {
    private Vehiculo vehiculo;
    private int idVehiculo;
    private double precio;
    private Comprador comprador;
    private int idComprador;
    private int id;
    
    public Oferta(int idVehiculo, double precio, int idComprador, int id) {
        this.idVehiculo = idVehiculo;
        this.precio = precio;
        this.idComprador = idComprador;
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.idVehiculo+"|"+this.precio+"|"+this.idComprador+"|"+this.id);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void saveFile(ArrayList<Oferta> ofertas, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            for(Oferta of: ofertas){
                pw.println(of.idVehiculo+"|"+of.precio+"|"+of.idComprador+"|"+of.id);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Oferta> readFile(String nomfile){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] s = linea.split("\\|");
               Oferta of = new Oferta(Integer.parseInt(s[0]), Double.parseDouble(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[0]));
               ofertas.add(of);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ofertas;
    }
    public static Oferta buscarPorIdVehiculo(ArrayList<Oferta> ofertas, int idVehiculo){
        for(Oferta  of: ofertas){
            if(of.idVehiculo == idVehiculo){
                return of;      
            }
        }
      return null; 
    }
    
      public static void revisarOfertaActual(int ind, ArrayList<Oferta> ofertas) {
        if (ind >= 0 && ind < ofertas.size()) {
            Oferta ofertaActual = ofertas.get(ind);
            System.out.println("Oferta #" + (ind + 1) + ":");
            System.out.println("Correo: " + ofertaActual.getComprador().correoElectronico); 
            System.out.println("Precio ofertado: " + ofertaActual.precio);
        } else {
            System.out.println("No hay más ofertas disponibles.");
        }
    }
    public static void avanzarOferta(int ind, ArrayList<Oferta> ofertas) {
        if (ind < ofertas.size() - 1) {
            ind++;
            revisarOfertaActual(ind, ofertas);
        } else {
            System.out.println("Ya has revisado todas las ofertas.");
        }
    }
    public static void retrocederOferta(int ind, ArrayList<Oferta> ofertas) {
        if (ind > 0) {
            ind--;
            revisarOfertaActual(ind, ofertas);
        } else {
            System.out.println("No puedes retroceder más, es la primera oferta.");
        }
    }
    
}


