/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

/**
 *
 * @author Scarlet Cevallos
 */
public class Camionetas extends Vehiculo{
    private String vidrios;
    private String transmision;
    private String traccion;

    public Camionetas(String vidrios, String transmision, String traccion, String placa, String marca, String modelo, String tipoMotor, String anio, String recorrido, String color, String tipoCombustible, double precio) {
        super(placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.traccion = traccion;
    }
}
