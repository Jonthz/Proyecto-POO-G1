/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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

    public int getId() {
        return id;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    
     public static boolean validarCorreoUnico(String correo, String nomfile){
        ArrayList<Usuario> usuarios = Usuario.readFile(nomfile);
        for(Usuario u: usuarios){
            if(Objects.equals(u.correoElectronico, correo))
            {
                return false;
                //retorna false cuando encuentre un correo igual al ingresado por el usuario
            }
        }
        return true;
        //retorna true si al terminar de recorrer el archivo no encuentra un correo igual
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            pw.println(this.id+"|"+this.nombre+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correoElectronico+"|"+this.clave);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    // si quiero guardar toda la info a partir de un archivo o una lista
    public static void saveFile(ArrayList<Usuario> usuarios, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            for(Usuario u: usuarios){
                pw.println(u.id+"|"+u.nombre+"|"+u.apellidos+"|"+u.organizacion+"|"+u.correoElectronico+"|"+u.clave);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Usuario> readFile(String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] s = linea.split("\\|");
               Usuario u = new Usuario(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
               usuarios.add(u);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
     public void registrarUsuario(String nomfile){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus siguentes datos");
        System.out.println("Nombre: ");
        String nombre=sc.nextLine();
        System.out.println("Apellido:");
        String apellido=sc.nextLine();
        System.out.println("Correo: ");
        String correo= sc.nextLine();
        if(Usuario.validarCorreoUnico(correo, nomfile)){
            System.out.println("Correo valido");
            System.out.println("Organización: ");
            String organizacion=sc.nextLine();
            System.out.println("Clave: ");
            String clave= sc.nextLine();
            int id = Utilitaria.generarID(nomfile);
            Usuario u = new Usuario(id, nombre,apellido,organizacion,correo,clave);
            u.saveFile(nomfile);
        }
        else{
            System.out.println("Este correo ya se encuentra registrado");
        }
    }
}
