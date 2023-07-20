/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Vendedor extends Usuario {

    private ArrayList<Vehiculo> vehiculos;

    public Vendedor(int id, String n, String ap, String org, String correo, String clave) {
        super(id, n, ap, org, correo, Utilitaria.claveHash(clave));
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

    @Override
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
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    public static void accederAceptarOferta(String nomfileVehiculo, String nomfileVendedor, String nomfileOferta){
        
    ArrayList<Usuario> users = Usuario.readFile(nomfileVendedor);
    Scanner sc = new Scanner(System.in);
    sc.useDelimiter("\n");
    System.out.println("Ingrese sus credenciales para acceder a esta funcion: ");
    boolean metodoV= false;
        do{
            System.out.println("Ingrese su correo: ");
            String correo= sc.nextLine();
            System.out.println("Ingrese su clave: ");
            String clave= sc.nextLine();
            String claveHash= Utilitaria.claveHash(clave);
            boolean acceso= validarCredenciales(correo,claveHash);
            if(!acceso){
                System.out.println("Credenciales no validas");
                System.out.println("Ingrese I si quiere intentar de nuevo, sino ingrese R para registrarse");
                String opcion= sc.nextLine();
                opcion=opcion.toUpperCase();
                if(opcion.equals("R")){
                    Usuario.registrarUsuario("compradores.txt");
                    metodoV=true;
                }
            }
            else{
                Vendedor vendedor = Vendedor.buscarVendedor(correo,claveHash,nomfileVendedor);
                ArrayList<Vehiculo> vehiculos = Vehiculo.leerVehiculos(nomfileVehiculo,vendedor);
                Vehiculo v= Vendedor.validarPlaca(nomfileVehiculo, vendedor, sc,vehiculos);
                if(v==null){
                    metodoV=true;
                }
                else{
                    aceptarOferta(nomfileVehiculo,nomfileVendedor,nomfileOferta, vendedor,sc,v,vehiculos);
                    metodoV=true;
                }
            }
        }
        while(!metodoV);
    }
    public static Vehiculo validarPlaca(String nomfileVehiculo, Vendedor v,Scanner sc,ArrayList<Vehiculo> vehiculos){
                String placa;
                Vehiculo vehiculo;
                boolean vehiculoP= false;
                do{
                    System.out.print("Ingrese la placa: ");
                    placa = sc.nextLine();
                    vehiculo = Vehiculo.buscarPorPlaca(vehiculos, placa);
                    if(vehiculo==null){
                        System.out.println("La placa ingresada no existe");
                        System.out.println("Ingrese I si quiere intentar de nuevo, sino inrese R para registrar un vehiculo");
                        String opcion= sc.nextLine();
                        opcion=opcion.toUpperCase();
                        if(opcion.equals("R")){
                            Vendedor.ingresarSistema("vehiculos.txt", "vendedores.txt");
                            vehiculoP=true;
                            return null;
                        }
                    }
                    else{
                        vehiculoP=true;
                    }
                }
                while(!vehiculoP);
                return vehiculo;
            }
public static void aceptarOferta(String nomfileVehiculo, String nomfileVendedor, String nomfileOferta, Vendedor v, Scanner sc, Vehiculo vehiculo, ArrayList<Vehiculo> vehiculos) {
        vehiculo.ofertas = Vehiculo.rellenarOfertasVehiculo(nomfileOferta, vehiculo);
        if (vehiculo.ofertas.isEmpty()) {
            System.out.println("No se han encontrado ofertas para este vehiculo");
            return;
        } 
        int ofertas = vehiculo.ofertas.size();
            if (ofertas == 1) {
                System.out.println("Se ha realizado 1 oferta");
            } else {
                System.out.println("Se han realizado " + ofertas + " ofertas");
            }
        Vendedor.recorrerOfertas(vehiculo.ofertas, sc, nomfileVehiculo, vehiculo, vehiculos);
}


    public static void recorrerOfertas(ArrayList<Oferta> ofertas, Scanner sc, String nomfileVehiculo, Vehiculo vehiculo, ArrayList<Vehiculo> vehiculos) {
    int ind = 0;
    String opcion;
    do {
        System.out.println("Marca: " + vehiculo.marca);
        System.out.println("Modelo: " + vehiculo.modelo);
        System.out.println("Precio: " + vehiculo.precio);
        
        Oferta.revisarOfertaActual(ind, ofertas);

        System.out.println("Para avanzar a la siguiente oferta escriba 1");
        System.out.println("Para retroceder a la anterior oferta escriba 2");
        System.out.println("Para aceptar la oferta escriba 3");
        System.out.println("Para cerrar las ofertas escriba 4");
        System.out.print("Elige una opción: ");
        opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                ind++;
                if (ind < ofertas.size()) {
                    Oferta.revisarOfertaActual(ind, ofertas);
                } else {
                    System.out.println("Ya has revisado todas las ofertas.");
                }
                break;
            case "2":
                ind--;
                if (ind >= 0) {
                    Oferta.revisarOfertaActual(ind, ofertas);
                } else {
                    System.out.println("No puedes retroceder más, es la primera oferta.");
                }
                break;
            case "3":
                Oferta ofertaActual = ofertas.get(ind);
                System.out.println("Oferta aceptada");
                Vehiculo.eliminarVehiculo(nomfileVehiculo, vehiculo, vehiculos);
                Utilitaria.enviarConGMail(ofertaActual.getCorreoElectronico(), "Oferta aceptada", "Su oferta para el vehiculo " + vehiculo.marca + " " + vehiculo.modelo + " ha sido aceptada");
                break;
            default:
                System.out.println("Opción inválida. Inténtalo de nuevo.");
        }

        System.out.println();
    } while (!opcion.equals("3") && !opcion.equals("4"));
}

    

    public static void ingresarSistema(String nomfileVehiculo, String nomfileVendedor) {
        ArrayList<Usuario> users = Usuario.readFile(nomfileVendedor);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese sus credenciales para acceder a esta funcion: ");
        boolean acceso= false;
        boolean metodoV=false;
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
                    Usuario.registrarUsuario("vendedores.txt");
                    metodoV=true;
                }
            }
            else{
                String tipo_vehiculo=null;
                boolean tipoV=false;
                while(!tipoV){
                    System.out.println("Ingrese el tipo de vehiculo que va a vender (Auto,Camioneta,Moto): ");
                    tipo_vehiculo=sc.nextLine();
                    if (tipo_vehiculo.equalsIgnoreCase("Auto") || tipo_vehiculo.equalsIgnoreCase("Camioneta") || tipo_vehiculo.equalsIgnoreCase("Moto")) {
                        System.out.println("Tipo de vehiculo valido");
                        tipoV=true;
                    } 
                    else {
                        System.out.println("Tipo de vehiculo no valido. Por favor, ingrese un Auto, Camioneta o Moto.");
                    }
                }
                Vendedor v= buscarVendedor(correo,claveHash,"vendedores.txt");
                ingresarVehiculo(tipo_vehiculo,nomfileVehiculo,v);
                metodoV=true;
            }
        }
        while(!metodoV);
    }
    public static boolean validarCredenciales(String correo, String clave){
        Vendedor v = buscarVendedor(correo, clave, "vendedores.txt");
        if(v==null){
            return false;
        }
        else{
            return true;
        }
    }
    public static Vendedor buscarVendedor(String correo, String clave,String nomFile){
        Vendedor vEncontrado= null;
        try(Scanner sc= new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                String[] tokens=linea.split("\\|");
                String correoC= tokens[4];
                String claveC=tokens[5];
                if(correoC.equals(correo)&& claveC.equals(clave)){
                    vEncontrado= new Vendedor(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]); 
                    break;
                }
            }
        }
        catch(Exception e){
                System.out.println(e.getMessage());
        }
        return vEncontrado;
    }

    public static void ingresarVehiculo(String tipo_vehiculo,String nomfileVehiculo,Vendedor v) {
        Scanner sc2 = new Scanner(System.in);
        sc2.useDelimiter("\n");
        sc2.useLocale(Locale.US);
        boolean placaE;
        String placa;
        do{
        System.out.print("Ingrese la placa: ");
        placa = sc2.nextLine();
        placaE= Vehiculo.buscarPlaca(nomfileVehiculo, placa);
        if(!placaE){
            System.out.println("La placa ingresada ya existe en los registros");
            }
        }
        while(!placaE);
        System.out.println("Ingrese la marca del vehículo: ");
        String marca = sc2.nextLine();
        System.out.println("Ingrese el modelo del vehículo: ");
        String modelo = sc2.nextLine();
        System.out.println("Ingrese el tipo de motor del vehículo: ");
        String tipoMotor = sc2.nextLine();
        boolean año=false;
        int anioVehiculo = Utilitaria.validarIntPos(sc2, "año");
        sc2.nextLine();
        int recorrido=Utilitaria.validarIntPos(sc2, "recorrido");
        sc2.nextLine();
        System.out.println("Ingrese el color del vehículo: ");
        String color = sc2.nextLine();
        System.out.println("Ingrese el tipo de combustible del vehículo: ");
        String tipoCombustible = sc2.nextLine();
        double precio_V;
        boolean prec=false;
        int id = Utilitaria.generarID(nomfileVehiculo);
        
        if(tipo_vehiculo.equals("Auto")){
            System.out.println("Ingrese vidrio del vehiculo: ");
            String vidrio = sc2.nextLine();
            System.out.println("Ingrese transmision del vehiculo: ");
            String transmision = sc2.nextLine();
            precio_V=Utilitaria.validarDoublePos(sc2, "precio");
            Auto auto_add = new Auto(id, TipoVehiculo.AUTO, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision);
            auto_add.saveFile(nomfileVehiculo,v);
        }
        else if(tipo_vehiculo.equals("Camioneta")){
            System.out.println("Ingrese vidrio del vehiculo: ");
            String vidrio = sc2.nextLine();
            System.out.println("Ingrese transmision del vehiculo: ");
            String transmision = sc2.nextLine();
            System.out.println("Ingrese tracción  del vehiculo: ");
            int traccion = sc2.nextInt();
            sc2.nextLine();
            precio_V=Utilitaria.validarDoublePos(sc2, "precio");

            Camionetas cam_add = new Camionetas(id, TipoVehiculo.CAMIONETA, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision, traccion);
            
                //int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio,String vidrios, String transmision, int traccion)
            cam_add.saveFile(nomfileVehiculo,v);
        }
        else {
            precio_V=Utilitaria.validarDoublePos(sc2, "precio");
            Vehiculo vh = new Vehiculo(id, TipoVehiculo.MOTO, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V);
            vh.saveFile(nomfileVehiculo,v);
        }
        System.out.println("El vehiculo se ingreso exitosamente");
    }
        public static ArrayList<Usuario> readFile(String nomfile){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] s = linea.split("\\|");
               Vendedor u = new Vendedor(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
               usuarios.add(u);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return usuarios;
    }

}
     