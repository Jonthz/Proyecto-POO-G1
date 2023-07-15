/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculos_Package;

import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Vendedor extends Usuario {
    
public Vendedor(int id, String n,String ap, String org, String correo, String clave){
        super(id,n,ap,org,correo,Utilitaria.claveHash(clave));
    }


}