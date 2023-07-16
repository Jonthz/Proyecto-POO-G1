/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.util.ArrayList;

/**
 *
 * @author Scarlet Cevallos
 */
public class Auto extends Vehiculo{
    private String vidrios;
    private String transmision;

    public Auto(String vidrios, String transmision, String placa, String marca, String modelo, String tipoMotor, String anio, String recorrido, String color, String tipoCombustible, double precio, ArrayList<Oferta> ofertas) {
        super(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio, ofertas);
        this.vidrios = vidrios;
        this.transmision = transmision;
    }
   
    
    public String toString() {
        return super.toString() + "\nVidrios: " + vidrios + "\nTransmisión: " + transmision;
    }
    
}
