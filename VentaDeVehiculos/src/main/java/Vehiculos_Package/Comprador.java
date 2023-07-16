/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Comprador extends Usuario {
    private ArrayList<Oferta> ofertas;
    public Comprador(int id,String n,String ap, String org, String correo, String clave){
        super(id,n,ap,org,correo,Utilitaria.claveHash(clave));
        this.ofertas= new ArrayList<>();
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

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public static void registrarComprador(String nomFile){
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus siguentes datos");
        System.out.println("Nombre: ");
        String nombre=sc.nextLine();
        System.out.println("Apellido:");
        String apellido=sc.nextLine();
        System.out.println("Correo: ");
        String correo= sc.nextLine();
        while(true){
        if(Usuario.validarEstructuraCorreO(correo)){
          if(Usuario.validarCorreoUnico(correo, nomFile)){
            break;
            }
          else{
            System.out.println("El correo ingresado ya se encuentra registrado. Intente de nuevo");
            correo=sc.nextLine();
            }  
           }
        else{
            System.out.println("Se ha ingresado un correo no valido");
            correo=sc.nextLine();
           }
        }
        System.out.println("Organización: ");
        String organizacion=sc.nextLine();
        System.out.println("Clave");
        String clave= sc.nextLine();
        int id= Utilitaria.generarID(nomFile);
        Comprador c= new Comprador(id,nombre,apellido,organizacion,correo,clave);
        sc.close();
        c.saveFile(nomFile);
        System.out.println("El comprador se ha registrado con exito");
    }
    public void saveFileComp(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.id+"|"+this.nombre+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correoElectronico+"|"+this.clave);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void hacerOferta(){
        ArrayList<Vehiculo> vehiculos= Utilitaria.leerVehiculos("vehiculos.txt");
        if(vehiculos.isEmpty()){
            return;
        }
        ArrayList<Vehiculo> vehiculosFiltrados= buscarVehiculos(vehiculos);
        if(vehiculosFiltrados.isEmpty()){
            return;
        }
        recorrerVehiculos(vehiculosFiltrados);
    }
    public static ArrayList<Vehiculo> buscarVehiculos(ArrayList <Vehiculo> vehiculos){
        ArrayList<Vehiculo> vehiculos_encontrados= new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese los atributos con los que quiere buscar los vehiculos, si quiere buscar todas las opciones de un atributo deje vacio");
        System.out.println("Tipo de Vehiculo: ");
        TipoVehiculo tipo= Utilitaria.obtenerTipoVehiculo(sc);
        System.out.println("Recorrido minimo: ");
        int recorr_min= Utilitaria.obtenerEntero(sc);
        System.out.println("Recorrido maximo: ");
        int recorr_max=Utilitaria.obtenerEntero(sc);
        System.out.println("Año minimo");
        int anio_min=Utilitaria.obtenerEntero(sc);
        System.out.println("Año maximo");
        int anio_max=Utilitaria.obtenerEntero(sc);
        System.out.println("Precio minimo");
        double prec_min=Utilitaria.obtenerDouble(sc);
        System.out.println("Precio maximo");
        double prec_max=Utilitaria.obtenerDouble(sc);
        sc.close();
        for(Vehiculo v: vehiculos){
            if((tipo==null||tipo==v.getTipo())&&(v.getRecorrido()>=recorr_min)&&(v.getRecorrido()<=recorr_max)&&(v.getAnio()>=anio_min)&&(v.getAnio()<=anio_max)&&(v.getPrecio()>=prec_min)&&(v.getPrecio()<=prec_max)){
                vehiculos_encontrados.add(v);
            }
        }
        if(vehiculos_encontrados.isEmpty()){
            System.out.println("No se ha encontrado ningun vehiculo que coincida con la busqueda");
        }
        return vehiculos_encontrados;
    }
    public static void recorrerVehiculos(ArrayList<Vehiculo> vehiculos){
        int indice=0;
        boolean revisarAnteriorVehiculo=false;
        while(true){
            if(!revisarAnteriorVehiculo){
                System.out.println("Informacion del Vehiculo:");
                System.out.println(vehiculos.get(indice));
            }
            System.out.println("¿Que desea hacer?");
            System.out.println("Para avanzar al siguiente vehiculo ingrese S");
            System.out.println("Para retroceder al anterior vehiculo ingrese A");
            System.out.println("Para terminar con la busqueda ingrese T");
            System.out.println("Para ofertar por un vehiculo ingrese O");
            Scanner sc= new Scanner(System.in);
            sc.useDelimiter("\n");
            String opcion = sc.nextLine();
            if(opcion.equalsIgnoreCase("S")){
                if(!revisarAnteriorVehiculo){
                    indice+=1;
                }
                revisarAnteriorVehiculo=false;
                if(indice>=vehiculos.size()){
                    System.out.println("Ha revisado todos los vehiculos");
                    revisarAnteriorVehiculo=false;
                }
            }
            else if(opcion.equalsIgnoreCase("A")){
                if(revisarAnteriorVehiculo){
                    indice-=1;
                }
                revisarAnteriorVehiculo=true;
                if(indice<0){
                    System.out.println("No existen vehiculos anteriores");
                    revisarAnteriorVehiculo=false;
                }
            }
            else if(opcion.equalsIgnoreCase("T")){
                break;
            }
            else if(opcion.equalsIgnoreCase("O")){
                Vehiculo vof= vehiculos.get(indice);
                System.out.println("Ingrese el precio ofertado: ");
                double preciof= sc.nextDouble();
                registrarOferta(vof,preciof);
            }
            else{
                System.out.println("Intente otra vez");
            }
        }
    }
    public static void registrarOferta(Vehiculo v, double precio){
        try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File("ofertas.txt"),true))){
            pw.println(v.toString()+"|"+precio);
        }
        catch(Exception e){
            System.out.println("Erro al registrar la oferta del vehiculo"+e.getMessage());
        }
    }
}
