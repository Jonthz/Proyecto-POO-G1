/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

/**
 *
 * @author Scarlet Cevallos
 */
public class Oferta {
    private Vehiculo vehiculo;
    private double precio;
    private Usuario usuario;
    private int id;

    public Oferta(Vehiculo vehiculo, double precio, Usuario usuario, int id) {
        this.vehiculo = vehiculo;
        this.precio = precio;
        this.usuario = usuario;
        this.id = id;
    }
}
