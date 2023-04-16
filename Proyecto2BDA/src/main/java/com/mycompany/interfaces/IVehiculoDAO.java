/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Vehiculo;

/**
 *
 * @author Usuario
 */
public interface IVehiculoDAO {
    
    /**
     * Metodo para registrar un Vehiculo
     * @param vehiculo Vehiculo a registrar
     * @return Regresa un Vehiculo
     */
    Vehiculo registraVehiculo(Vehiculo vehiculo);
    
    /**
     * Metodo para consutlar un vehiculo por su serie
     * @param serie Serie a buscar
     * @return Regresa un vehiculo 
     */
    Vehiculo consultaVehiculoPorSerie(String serie);
    
    /**
     * Metodo para verificar si una serie existe en la base de datos
     * @param serie Serie a verificar si existe
     * @return Regresa true si la serie ya existe, false si no existe
     */
    Boolean existeLaSerie(String serie);
    
}
