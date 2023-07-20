
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
    public static void accederHacerOferta(){
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus credenciales para acceder a esta funcion: ");
        boolean acceso= false;
        boolean metodoC= false;
        do{
            System.out.println("Ingrese su correo: ");
            String correo= sc.nextLine();
            System.out.println("Ingrese su clave: ");
            String clave= sc.nextLine();
            String claveHash= Utilitaria.claveHash(clave);
            acceso= validarCredenciales(correo,claveHash);
            if(!acceso){
                System.out.println("Credenciales no validas");
                System.out.println("Ingrese I si quiere intentar de nuevo, sino ingrese R para registrarse");
                String opcion= sc.nextLine();
                opcion=opcion.toUpperCase();
                if(opcion.equals("R")){
                    Usuario.registrarUsuario("compradores.txt");
                    metodoC=true;
                }
            }
            else{
                Comprador c= buscarComprador(correo,claveHash,"compradores.txt");
                hacerOferta(c);
                metodoC=true;
            }
        }
        while(!metodoC);
    }
    public static boolean validarCredenciales(String correo, String clave){
        Comprador c = buscarComprador(correo, clave, "compradores.txt");
        if(c==null){
            return false;
        }
        else{
            return true;
        }
    }
    public static Comprador buscarComprador(String correo, String clave,String nomFile){
        Comprador compradorEncontrado= null;
        try(Scanner sc= new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                String[] tokens=linea.split("\\|");
                String correoC= tokens[4];
                String claveC=tokens[5];
                if(correoC.equals(correo)&& claveC.equals(clave)){
                    compradorEncontrado= new Comprador(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]); 
                    break;
                }
            }
        }
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return compradorEncontrado;
    }
    public static void hacerOferta(Comprador c){
        ArrayList<Vehiculo> vehiculos= Vehiculo.leerVehiculos("vehiculos.txt");
        if(vehiculos.isEmpty()){
            return;
        }
        ArrayList<Vehiculo> vehiculosFiltrados= buscarVehiculos(vehiculos);
        if(vehiculosFiltrados.isEmpty()){
            return;
        }
        recorrerVehiculos(vehiculosFiltrados,c);
    }
    public static ArrayList<Vehiculo> buscarVehiculos(ArrayList <Vehiculo> vehiculos){
        ArrayList<Vehiculo> vehiculos_encontrados= new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese los atributos con los que quiere buscar los vehiculos, si quiere buscar todas las opciones de un atributo deje vacio");
        System.out.println("Tipo de Vehiculo:");
        TipoVehiculo tipo= Utilitaria.obtenerTipoVehiculo(sc);
        System.out.println("Recorrido minimo:");
        int recorr_min= Utilitaria.obtenerEntero(sc,"recorrido minimo");
        System.out.println("Recorrido maximo:");
        int recorr_max=Utilitaria.obtenerEntero(sc,"recorrido maximo");
        System.out.println("Año minimo:");
        int anio_min=Utilitaria.obtenerEntero(sc,"año minimo");
        System.out.println("Año maximo:");
        int anio_max=Utilitaria.obtenerEntero(sc,"año maximo");
        System.out.println("Precio minimo:");
        double prec_min=Utilitaria.obtenerDouble(sc,"precio minimo");
        System.out.println("Precio maximo:");
        double prec_max=Utilitaria.obtenerDouble(sc,"precio maximo");
        for(Vehiculo v: vehiculos){
            if((tipo==null||tipo==v.getTipo())
                    &&(recorr_min<=0||v.getRecorrido()>=recorr_min)
                    &&(recorr_max<=0||v.getRecorrido()<=recorr_max)
                    &&(anio_min<=0||v.getAnio()>=anio_min)
                    &&(anio_max<=0||v.getAnio()<=anio_max)
                    &&(prec_min<=0||v.getPrecio()>=prec_min)
                    &&(prec_max<=0||v.getPrecio()<=prec_max)){
                vehiculos_encontrados.add(v);
            }
        }
        if(vehiculos_encontrados.isEmpty()){
            System.out.println("No se ha encontrado ningun vehiculo que coincida con la busqueda");
        }
        return vehiculos_encontrados;
    }
    public static void recorrerVehiculos(ArrayList<Vehiculo> vehiculos,Comprador c){
        int indice=0;
        boolean recorrer=true;
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        while(recorrer){
            System.out.println("Informacion del Vehiculo:");
            Vehiculo v = vehiculos.get(indice);
            if(v instanceof Auto){
                Auto a=(Auto)v;
                System.out.println(a.toString());
               }
            else if(v instanceof Camionetas){
                Camionetas cam=(Camionetas)v;
                System.out.println(cam.toString());
                 }
            else{
                System.out.println(v.toString());
                 }
            System.out.println("¿Que desea hacer?");
            System.out.println("Para avanzar al siguiente vehiculo ingrese S");
            System.out.println("Para retroceder al anterior vehiculo ingrese A");
            System.out.println("Para terminar con la busqueda ingrese T");
            System.out.println("Para ofertar por un vehiculo ingrese O");
            String opcion = sc.nextLine().toUpperCase();
            if(opcion.equalsIgnoreCase("S")){
                indice+=1;
                if(indice>=vehiculos.size()){
                    System.out.println("Ha revisado todos los vehiculos");
                    indice-=1;
                }
            }
            else if(opcion.equalsIgnoreCase("A")){
                indice-=1;
                if(indice<0){
                    System.out.println("No existen vehiculos anteriores");
                    indice+=1;
                }
            }
            else if(opcion.equalsIgnoreCase("T")){
                recorrer=false;
            }
            else if(opcion.equalsIgnoreCase("O")){
                Vehiculo vof= vehiculos.get(indice);
                System.out.println("Ingrese el precio que quiere ofertar: ");
                double preciof= Utilitaria.obtenerDouble(sc,"precio");
                registrarOferta("ofertas.txt",vof,preciof,c);
                sc.nextLine();
            }
            else{
                System.out.println("Intente otra vez");
            }
        }
    }
    public static void registrarOferta(String nomFile,Vehiculo v, double precio,Comprador c){
        try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File("ofertas.txt"),true))){
            int idOfe= Utilitaria.generarID(nomFile);
            pw.println(idOfe+"|"+v.getId()+"|"+c.getId()+"|"+precio); 
            System.out.println("La oferta ha sido registrada exitosamente");
        }
        catch(Exception e){
            System.out.println("Error al registrar la oferta del vehiculo"+e.getMessage());
        }
    }
        public static ArrayList<Usuario> readFile(String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] s = linea.split("\\|");
               Comprador u = new Comprador(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
               usuarios.add(u);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }
}

