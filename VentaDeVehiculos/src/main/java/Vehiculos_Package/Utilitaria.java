/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Utilitaria {
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
                case 1 -> v.registrarVendedor();
                case 2 -> v.registrarVehiculo();
                case 3 -> v.aceptarOferta();
                case 4 -> {
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
            System.out.println();
        }
        while(opcion_selec!=4);
    }
    public void menu_comprador(){
        Scanner sc= new Scanner(System.in);
        int opcion_selec;
        do{
            System.out.println("Opciones del comprador");
            System.out.println("1. Registar un nuevo comprador");
            System.out.println("2. Ofertar por un vehiculo");
            System.out.println("3. Regresar");
            opcion_selec=sc.nextInt();
            switch(opcion_selec){
                case 1 -> Comprador.registrarComprador();
                case 2 -> c.hacerOferta();
                case 3 ->{
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo");
            }
            System.out.println();
        }
        while(opcion_selec!=3);
    }
    public static int generarID(String nomFile){
        int id=0;
        try(Scanner sc= new Scanner(new File(nomFile))){
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
}
