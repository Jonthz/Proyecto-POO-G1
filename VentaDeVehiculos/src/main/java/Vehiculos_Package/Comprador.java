
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
    public static void hacerOferta(Comprador c){
        ArrayList<Vehiculo> vehiculos= Vehiculo.leerVehiculos("vehiculos.txt");
        if(vehiculos.isEmpty()){
            return;
        }
        ArrayList<Vehiculo> vehiculosFiltrados= buscarVehiculos(vehiculos);
        if(vehiculosFiltrados.isEmpty()){
            System.out.println("hola");
            return;
        }
        recorrerVehiculos(vehiculosFiltrados,c);
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
        sc.close();
        return vehiculos_encontrados;
    }
    public static void recorrerVehiculos(ArrayList<Vehiculo> vehiculos,Comprador c){
        int indice=0;
        boolean revisarAnteriorVehiculo=false;
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        while(true){
            if(!revisarAnteriorVehiculo){
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
            }
            System.out.println("¿Que desea hacer?");
            System.out.println("Para avanzar al siguiente vehiculo ingrese S");
            System.out.println("Para retroceder al anterior vehiculo ingrese A");
            System.out.println("Para terminar con la busqueda ingrese T");
            System.out.println("Para ofertar por un vehiculo ingrese O");
            sc.nextLine();
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
                registrarOferta("ofertas.txt",vof,preciof,c);
            }
            else{
                System.out.println("Intente otra vez");
            }
        }
        sc.close();
    }
    public static void registrarOferta(String nomFile,Vehiculo v, double precio,Comprador c){
        try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File("ofertas.txt"),true))){
            boolean ofertaExistente= false;
            int idOfe= Utilitaria.generarID(nomFile);
            try(Scanner sc = new Scanner(new File(nomFile))){
                while(sc.hasNextLine()){
                    String linea= sc.nextLine();
                    String[] tokens= linea.split("\\|");
                    int idVE= Integer.parseInt(tokens[1]);
                    int idCE= Integer.parseInt(tokens[2]);
                    double precE= Double.parseDouble(tokens[3]);
                    if((idVE==v.getId())&&(idCE==c.getId())){
                        ofertaExistente=true;
                        System.out.println("Ya hiciste una oferta del mismo vehiculo");
                        System.out.println("Precio por el que ofertaste:"+precE);
                        System.out.println("¿Que quiere hacer?");
                        System.out.println("1. Cambiar el precio");
                        System.out.println("2. Eliminar la oferta");
                        System.out.println("Ingrese la opcion");
                        int opcion= sc.nextInt();
                        switch(opcion){
                        case 1 -> {
                            System.out.println("Ingrese el nuevo precio:");
                            int newPrecio= sc.nextInt();
                            precE=newPrecio;
                            System.out.println("El precio de la oferta ha sido cambiado");
                            break;
                        }
                        case 2 -> System.out.println("La oferta ha sido eliminada");
                        default -> {
                            System.out.println("Ha ingresado una opcion invalida, la oferta sigue siendo la misma");
                            break;
                            }
                        }
                    }
                    pw.println(tokens[0]+"|"+idVE+"|"+idCE+"|"+precE);
                }
            }
            if(!ofertaExistente){
                pw.println(idOfe+"+"+v.getId()+"|"+c.getId()+"|"+precio); 
            }
            System.out.println("La oferta ha sido registrada exitosamente");
        }
        catch(Exception e){
            System.out.println("Error al registrar la oferta del vehiculo"+e.getMessage());
        }
    }
    public static void accederHacerOferta(){
        Scanner sc= new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus credenciales para acceder a esta funcion: ");
        boolean acceso= false;
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
                    break;
                }
            }
            else{
                Comprador c= buscarComprador(correo,claveHash,"compradores.txt");
                hacerOferta(c);
                break;
            }
        }
        while(true);
        sc.close();
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

