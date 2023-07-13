/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

/**
 *
 * @author Scarlet Cevallos
 */
public class Auto extends Vehiculo{
    private String vidrios;
    private String transmision;

    public Auto(String vidrios, String transmision, String placa, String marca, String modelo, String tipoMotor, String anio, String recorrido, String color, String tipoCombustible, double precio) {
        super(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
    } 
}