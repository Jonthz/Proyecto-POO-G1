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

    public Auto(int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio,String vidrios, String transmision) {
        super(id,tipo,placa, marca, modelo, tipoMotor, anio, recorrido, color, tipoCombustible, precio);
        this.vidrios = vidrios;
        this.transmision = transmision;
    } 
}
