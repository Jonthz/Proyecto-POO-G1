/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.ventadevehiculos;

import Vehiculos_Package.Comprador;
import Vehiculos_Package.Utilitaria;
import ec.edu.espol.ventadevehiculos.clases.Usuario;

/**
 *
 * @author Jonathan
 */
public class VentaDeVehiculos {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //Utilitaria.vaciarArchivo("compradores.txt");
        Usuario.registrarUsuario("compradores.txt");
    }
    
}
