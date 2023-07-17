/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
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
        
    ArrayList<Vendedor> users = Vendedor.readFile(nomfileVendedor);
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
        vehiculo.ofertas = Vehiculo.rellenarOfertasVehiculo(nomfileOferta, vehiculo);
        System.out.println(vehiculo.marca + vehiculo.modelo + "Precio: " + vehiculo.precio);
        System.out.println("Se han realizado" + vehiculo.ofertas.size() + "ofertas");
        Vendedor.recorrerOfertas(vehiculo.ofertas, sc, nomfileVehiculo, vehiculo, vehiculos);    
    }
    else
            System.out.println("Clave incorrecta");
        
    }
//        Oferta oferta = Oferta.buscarPorIdVehiculo(ofertas, vehiculo.id);
//        int t_oferta = Oferta.totalOfertasPorVehiculo(ofertas, vehiculo.id);
//        System.out.println(vehiculo.marca + vehiculo.modelo + "Precio: " + vehiculo.precio);
  
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
                    Utilitaria.enviarConGMail(ofertas.get(ind).getComprador().correoElectronico, "Oferta aceptada", "Su oferta para el vehiculo" + vehiculo.marca + vehiculo.modelo + "ha sido aceptada");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
            }
            
            System.out.println();
        } while (!opcion.equals("3") || !opcion.equals("4"));
    }
    
    public void ingresarSistema(String nomfileVehiculo, String nomfileVendedor) {
        ArrayList<Vendedor> users = Vendedor.readFile(nomfileVendedor);
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Ingrese su correo");
        String correo = sc.nextLine();
        System.out.println("Ingrese su clave");
        String clave = sc.nextLine();
        Vendedor v = Vendedor.buscarPorCorreo(users, correo);
        if (v.validarClave(clave)) {
            System.out.println("Ingrese el tipo de vehiculo");
            String tipo_V = sc.nextLine();
            boolean ing_v_validate = ingresarVehiculo(tipo_V, nomfileVehiculo);
            while(!ing_v_validate){
                System.out.println("La placa ingresada ya existe en los registros.");
            }
            if(ing_v_validate){
                System.out.println("El vehiculo se ingreso exitosamente!");
            }
        } else {
            System.out.println("La contraseña es incorrecta");
        }
    }

    public boolean ingresarVehiculo(String tipo_vehiculo, String nomfile) {
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Ingrese la placa: ");
        String placa = sc2.nextLine();
        sc2.nextLine();
        for(Vehiculo v: vehiculos){
            if(v.getPlaca().equals(placa)){
                return false;
            }
        }
        System.out.println("Ingrese la marca del vehículo: ");
        String marca = sc2.nextLine();
        sc2.nextLine();
        System.out.println("Ingrese el modelo del vehículo: ");
        String modelo = sc2.nextLine();
        sc2.nextLine();
        System.out.println("Ingrese el tipo de motor del vehículo: ");
        String tipoMotor = sc2.nextLine();
        sc2.nextLine();
        System.out.println("Ingrese el año del vehículo: ");
        int anioVehiculo = sc2.nextInt();
        sc2.nextLine();
        System.out.println("Ingrese el recorrido del vehículo: ");
        int  recorrido = sc2.nextInt();
        sc2.nextLine();
        System.out.println("Ingrese el color del vehículo: ");
        String color = sc2.nextLine();
        sc2.nextLine();
        System.out.println("Ingrese el tipo de combustible del vehículo: ");
        String tipoCombustible = sc2.nextLine();
        sc2.nextLine();
        double precio_V;
        int id = Utilitaria.generarID(nomfile);
        if (tipo_vehiculo.equalsIgnoreCase("Auto") || tipo_vehiculo.equalsIgnoreCase("Camioneta")) {
            System.out.println("Ingrese vidrio del vehiculo: ");
            String vidrio = sc2.nextLine();
            sc2.nextLine();
            System.out.println("Ingrese transmision del vehiculo: ");
            String transmision = sc2.nextLine();
            sc2.nextLine();
            System.out.println("Ingrese tracción  del vehiculo: ");
            int traccion = sc2.nextInt();
            sc2.nextLine();
            System.out.println("Ingrese el precio del Vehiculo: ");
            precio_V = sc2.nextDouble();
            
            if (tipo_vehiculo.equalsIgnoreCase("Auto")) {
                Auto auto_add = new Auto(id, TipoVehiculo.AUTO, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision);
                vehiculos.add(auto_add);
            }else if(tipo_vehiculo.equalsIgnoreCase("Camioneta")){
                Camionetas cam_add = new Camionetas(id, TipoVehiculo.CAMIONETA, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V, vidrio, transmision, traccion);
                //int id, TipoVehiculo tipo,String placa, String marca, String modelo, String tipoMotor, int anio, int recorrido, String color, String tipoCombustible, double precio,String vidrios, String transmision, int traccion)
                vehiculos.add(cam_add);
            }
        }else {
            System.out.println("Ingrese el precio del Vehiculo: ");
            precio_V = sc2.nextDouble();
            vehiculos.add(new Vehiculo(id, TipoVehiculo.CAMIONETA, placa, marca, modelo, tipoMotor, anioVehiculo, recorrido, color, tipoCombustible, precio_V));
        }
        return true;
    }

    public boolean validarClave(String clave) {
        String clave_h = Utilitaria.claveHash(clave);
        return this.clave.equals(clave_h);
    }

    public static Vendedor buscarPorCorreo(ArrayList<Vendedor> users, String correo) {
        for (Vendedor u : users) {
            if (u.correoElectronico.equals(correo)) {
                return u;
            }
        }
        return null;
    }
    public static void saveFile(ArrayList<Vendedor> usuarios, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))){
            for(Vendedor u: usuarios){
                pw.println(u.id+"|"+u.nombre+"|"+u.apellidos+"|"+u.organizacion+"|"+u.correoElectronico+"|"+u.clave);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    
     public static ArrayList<Vendedor> readFile(String nomfile){
        ArrayList<Vendedor> vendedor = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
           while(sc.hasNextLine()){
               String linea = sc.nextLine();
               String[] s = linea.split("\\|");
               Vendedor v = new Vendedor(Integer.parseInt(s[0]), s[1], s[2], s[3], s[4], s[5]);
               vendedor.add(v);
           }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return vendedor;
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
        if(Comprador.validarCorreoUnico(correo, nomfile)){
            System.out.println("Correo valido");
            System.out.println("Organización: ");
            String organizacion=sc.nextLine();
            System.out.println("Clave: ");
            String clave= sc.nextLine();
            int id = Utilitaria.generarID(nomfile);
            Comprador u = new Comprador(id, nombre,apellido,organizacion,correo,clave);
            u.saveFile(nomfile);
        }
        else{
            System.out.println("Este correo ya se encuentra registrado");
        }
    }
//       public static boolean validarCorreoUnico(String correo, String nomfile){
//        ArrayList<Vendedor> vendedor = Vendedor.readFile(nomfile);
//        for(Usuario u: comprador){
//            if(u.correoElectronico.equalsIgnoreCase( correo))
//            {
//                return false;
//                //retorna false cuando encuentre un correo igual al ingresado por el usuario
//            }
//        }
//        return true;
//        //retorna true si al terminar de recorrer el archivo no encuentra un correo igual
//    }
}
     