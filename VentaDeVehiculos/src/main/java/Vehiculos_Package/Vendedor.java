/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;
import java.io.File;
import java.util.ArrayList;
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
    public static void aceptarOferta(String nomfileVehiculo, String nomfileVendedor, String nomfileOferta){
        //para que funcione este codigo necesito ya tener actualizado de la lista ofertas en el atributo de vehiculo
        
    ArrayList<Usuario> users = Usuario.readFile(nomfileVendedor);
    ArrayList<Vehiculo> vehiculos = Vehiculo.leerVehiculos(nomfileVehiculo);
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
        vehiculo.ofertas = Oferta.readFile(nomfileOferta);
        System.out.println(vehiculo.marca + vehiculo.modelo + "Precio: " + vehiculo.precio);
        System.out.println("Se han realizado" + vehiculo.ofertas.size() + "ofertas");
        Vendedor.recorrerOfertas(vehiculo.ofertas, sc, nomfileVehiculo, vehiculo, vehiculos);
        
    }
        
    }
  
    public static void recorrerOfertas(ArrayList<Oferta> ofertas, Scanner sc, String nomfileVehiculo, Vehiculo vehiculo, ArrayList<Vehiculo> vehiculos){
        int ind=0;
        String opcion;
        do {
            Oferta.revisarOfertaActual(ind, ofertas);
            System.out.println("Para avanzar a la siguiente oferta escriba 1");
            System.out.println("Para retroceder a la anterior oferta escriba 2");
            System.out.println("Para aceptar la oferta escriba 3");
            System.out.println("Para cerrar las ofertas escriba 4");
            System.out.print("Elige una opción: ");
            opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    Oferta.avanzarOferta(ind, ofertas);
                    break;
                case "2":
                    Oferta.retrocederOferta(ind, ofertas);
                    break;
                case "3":
                    System.out.println("Oferta aceptada");
                    Vehiculo.eliminarVehiculo(nomfileVehiculo, vehiculo, vehiculos);
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
            
            System.out.println();
        } while (!opcion.equals("3") || !opcion.equals("4"));
    }
    
    public static void ingresarSistema(String nomfileVehiculo, String nomfileVendedor) {
        ArrayList<Usuario> users = Usuario.readFile(nomfileVendedor);
        Scanner sc = new Scanner(System.in);
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
                    Usuario.registrarUsuario("vendedores.txt");
                    break;
                }
            }
            else{
                String tipo_vehiculo;
                while(true){
                    System.out.println("Ingrese el tipo de vehiculo que va a vender (Auto,Camioneta,Moto): ");
                    tipo_vehiculo=sc.nextLine();
                    if (tipo_vehiculo.equalsIgnoreCase("Auto") || tipo_vehiculo.equalsIgnoreCase("Camioneta") || tipo_vehiculo.equalsIgnoreCase("Moto")) {
                        break;
                    } 
                    else {
                        System.out.println("Tipo de vehiculo no valido. Por favor, ingrese un Auto, Camioneta o Moto.");
                    }
                }
                Vendedor v= buscarVendedor(correo,claveHash,"vendedores.txt");
                ingresarVehiculo(tipo_vehiculo,nomfileVehiculo,v);
                break;
            }
        }
        while(true);
        sc.close();
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

    public static void ingresarVehiculo(String tipo_vehiculo,String nomfile,Vendedor v) {
        Scanner sc2 = new Scanner(System.in);
        sc2.useDelimiter("\n");
        System.out.print("Ingrese la placa: ");
        String placa = sc2.nextLine();
        boolean placaE= Vehiculo.buscarPlaca(nomfile, placa);
        if(!placaE){
            System.out.println("La placa ingresada ya existe en los registros");
        }
        System.out.println("Ingrese la marca del vehículo: ");
        String marca = sc2.nextLine();
        System.out.println("Ingrese el modelo del vehículo: ");
        String modelo = sc2.nextLine();
        System.out.println("Ingrese el tipo de motor del vehículo: ");
        String tipoMotor = sc2.nextLine();
        System.out.println("Ingrese el año del vehículo: ");
        int anioVehiculo = sc2.nextInt();
        sc2.nextLine();
        System.out.println("Ingrese el recorrido del vehículo: ");
        int  recorrido = sc2.nextInt();
        sc2.nextLine();
        System.out.println("Ingrese el color del vehículo: ");
        String color = sc2.nextLine();
        System.out.println("Ingrese el tipo de combustible del vehículo: ");
        String tipoCombustible = sc2.nextLine();
        double precio_V;
        int id = Utilitaria.generarID(nomfile);
        if(tipo_vehiculo.equals("Auto")){
            System.out.println("Ingrese vidrio del vehiculo: ");
            String vidrio = sc2.nextLine();
            System.out.println("Ingrese transmision del vehiculo: ");
            String transmision = sc2.nextLine();
            System.out.println("Ingrese el precio del Vehiculo: ");
            precio_V = sc2.nextDouble();
            Auto auto_add = new Auto(id, TipoVehiculo.AUTO, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision);
            auto_add.saveFile(nomfile,v);
        }
        else if(tipo_vehiculo.equals("Camioneta")){
            System.out.println("Ingrese vidrio del vehiculo: ");
            String vidrio = sc2.nextLine();
            System.out.println("Ingrese transmision del vehiculo: ");
            String transmision = sc2.nextLine();
            System.out.println("Ingrese tracción  del vehiculo: ");
            int traccion = sc2.nextInt();
            sc2.nextLine();
            System.out.println("Ingrese el precio del Vehiculo: ");
            precio_V = sc2.nextDouble();
            Camionetas cam_add = new Camionetas(id, TipoVehiculo.CAMIONETA, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision, traccion);
                //int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio,String vidrios, String transmision, int traccion)
            cam_add.saveFile(nomfile,v);
        }
        else {
            System.out.println("Ingrese el precio del Vehiculo: ");
            precio_V = sc2.nextDouble();
            Vehiculo vh = new Vehiculo(id, TipoVehiculo.MOTO, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V);
            vh.saveFile(nomfile,v);
        }
        System.out.println("El vehiculo se ingreso exitosamente");
    }

    public boolean validarClave(String clave) {
        String clave_h = Utilitaria.claveHash(clave);
        return this.clave.equals(clave_h);
    }

    public static Vendedor buscarPorCorreo(ArrayList<Usuario> users, String correo) {
        for (Usuario u : users) {
            if (u.correoElectronico.equals(correo)) {
                return (Vendedor) u;
            }
        }
        return null;
    }
}