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
    private int traccion;

    public Camionetas(int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio,String vidrios, String transmision, int traccion) {
        super(id,tipo,placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.traccion = traccion;
    }
}
