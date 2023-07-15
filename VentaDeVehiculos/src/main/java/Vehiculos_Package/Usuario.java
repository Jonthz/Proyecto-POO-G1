/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

/**
 *
 * @author HP
 */
public class Usuario {
    protected int id;
    protected String nombre;
    protected String apellidos;
    protected String organizacion;
    protected String correoElectronico;
    protected String clave;

    public Usuario(int idU,String nombre, String apellidos, String organizacio, String correoElectronico, String clave) {
        this.id=idU;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.organizacion = organizacio;
        this.correoElectronico = correoElectronico;
        this.clave = clave;
    }
    public Usuario(){
        
    }
}
