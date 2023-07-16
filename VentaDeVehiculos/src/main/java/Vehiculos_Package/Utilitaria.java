/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Utilitaria {
    /*
    public void menu_principal(){
        Scanner sc= new Scanner(System.in);
        int opcion_selec;
        do{
            System.out.println("Menu de opciones");
            System.out.println("1. Vendedor");
            System.out.println("2. Comparador");
            System.out.println("3. Salir");
            opcion_selec=sc.nextInt();
            switch(opcion_selec){
                case 1 -> menu_vendedor();
                case 2 -> menu_comprador();
                case 3 -> {
                    System.out.println("Esperamos que le haya gustado nuestro servicio.");
                    System.out.println("Deseamos que vuelva pronto.");
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
            System.out.println();
        }
        while(opcion_selec!=3);
    }
    public void menu_vendedor(){
        Vendedor v= new Vendedor();
        Scanner sc= new Scanner(System.in);
        int opcion_selec;
        do{
            System.out.println("Opciones del vendedor");
            System.out.println("1. Registar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehiculo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            opcion_selec=sc.nextInt();
            switch(opcion_selec){
                case 1 -> Vendedor.registrarVendedor();
                case 2 -> Vendedor.registrarVehiculo();
                case 3 -> Vendedor.aceptarOferta();
                case 4 -> {
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
            System.out.println();
        }
        while(opcion_selec!=4);
    }
    public void menu_comprador(){
        Comprador c= new Comprador();
        Scanner sc= new Scanner(System.in);
        int opcion_selec;
        do{
            System.out.println("Opciones del comprador");
            System.out.println("1. Registar un nuevo comprador");
            System.out.println("2. Ofertar por un vehiculo");
            System.out.println("3. Regresar");
            opcion_selec=sc.nextInt();
            switch(opcion_selec){
                case 1 -> Comprador.registrarComprador("compradores.txt");
                case 2 -> c.hacerOferta();
                case 3 ->{
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
            System.out.println();
        }
        while(opcion_selec!=3);
    }
*/
    public static int generarID(String nomFile){
        int id=0;
        try(Scanner sc= new Scanner(new File(nomFile))){
             if (!(sc.hasNextLine())) {
                id = 0; // Reiniciar el contador de IDs
                System.out.println("El archivo de vehículos está vacío. Los IDs han sido reiniciados.");
            }
           while(sc.hasNextLine()){
               String linea=sc.nextLine();
               String [] tokens = linea.split("\\|");
               id= Integer.parseInt(tokens[0]);
           } 
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return id+1;
    }
    public static String claveHash(String clave){
         try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(clave.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = String.format("%02x", b);
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
         return null;
    }
    public static ArrayList<Comprador> leerCompradores(String nomFile){
        ArrayList<Comprador> compradores= new ArrayList<>();
        try(Scanner sc= new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                String [] tokens= linea.split("\\|");
                Comprador c= new Comprador(Integer.parseInt(tokens[0]),tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                compradores.add(c);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return compradores;
    }
    public static void vaciarArchivo(String nomFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomFile, false))) {
            // Sobrescribir el archivo con una cadena vacía
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static int obtenerEntero(Scanner scanner) {
    String input = scanner.nextLine();
    if (input.isEmpty()) {
        return Integer.MIN_VALUE;
        }
    else{
        return Integer.parseInt(input);
        }
    }

    public static double obtenerDouble(Scanner scanner) {
    String input = scanner.nextLine();
    if (input.isEmpty()) {
        return Double.MIN_VALUE;
        }
    else{
        return Double.parseDouble(input);
        } 
    }
    public static TipoVehiculo obtenerTipoVehiculo(Scanner sc) {
    String tipo = sc.nextLine().toUpperCase();
    try {
        return TipoVehiculo.valueOf(tipo);
        } 
    catch (IllegalArgumentException e) {
        return null;
        }
    }
    

}
