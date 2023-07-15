/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Comprador extends Usuario {
    public Comprador(String n,String ap, String org, String correo, String clave){
        super(Utilitaria.generarID("compradores.txt"),n,ap,org,correo,Utilitaria.claveHash(clave));
    }
    public Comprador(){
        super();
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
    
    public static void registrarComprador(){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus siguentes datos");
        System.out.println("Nombre: ");
        String nombre=sc.nextLine();
        System.out.println("Apellido:");
        String apellido=sc.nextLine();
        System.out.println("Correo: ");
        String correo= sc.nextLine();
        System.out.println("Organización: ");
        String organizacion=sc.nextLine();
        System.out.println("Clave: ");
        String clave= sc.nextLine();
        Comprador c= new Comprador(nombre,apellido,organizacion,correo,clave);
        //acuerdate del metodo validar si lees esto Jonathan XDXD
        c.saveFileComp("compradores.txt");
        
    }
    public void saveFileComp(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.id+"|"+this.nombre+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correoElectronico+"|"+this.clave);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public 
}
