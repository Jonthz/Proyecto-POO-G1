/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Vendedor extends Usuario {

    private ArrayList<Vehiculo> vehiculos;
    
public Vendedor(int id, String n,String ap, String org, String correo, String clave){
        super(id,n,ap,org,correo,Utilitaria.claveHash(clave));
        this.vehiculos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }



public static void ingresarSistema(String nomfileVehiculo, String nomfileVendedor, String nomfileOferta){
    ArrayList<Usuario> users = Usuario.readFile(nomfileVendedor);
    ArrayList<Vehiculo> vehiculos = Vehiculo.readFile(nomfileVehiculo);
    ArrayList<Oferta> ofertas = Oferta.readFile(nomfileOferta);
    Scanner sc = new Scanner(System.in);
    sc.useDelimiter("\n");
    System.out.println("Ingrese su correo");
    String correo = sc.nextLine();
    System.out.println("Ingrese su clave");
    String clave = sc.nextLine();
    Vendedor vendedor = Vendedor.buscarPorCorreo(users, correo);
    if(vendedor.validarClave(clave)){
        System.out.println("Ingrese la placa: ");
        String placa = sc.nextLine();
        Vehiculo vehiculo = Vehiculo.buscarPorPlaca(vehiculos, placa);
        Oferta oferta = Oferta.buscarPorIdVehiculo(ofertas, vehiculo.id);
        
    }
    else{
        
    }
}
public boolean validarClave(String clave){
    String clave_h = Utilitaria.claveHash(clave);
    return this.clave.equals(clave_h);
}

public static Vendedor buscarPorCorreo(ArrayList<Usuario> users, String correo){
    for(Usuario u: users){
        if(u.correoElectronico.equals(correo)){
            return (Vendedor)u;      
        }
      return null;  
    }
        
} 

}