/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

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

    public String getVidrios() {
        return vidrios;
    }

    public String getTransmision() {
        return transmision;
    }
    @Override
      public void saveFile(String nomfile,Vendedor v){
       try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
           //(int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio)
            pw.println(v.getId()+"|"+this.id+"|"+this.tipo+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio+"|"+this.vidrios+"|"+this.transmision);
       }
       catch(Exception e){
           System.out.println(e.getMessage());
       }
    }
    public String toString() {
        return super.toString() + "\nVidrios: " + vidrios + "\nTransmisión: " + transmision;
    }
    
}
