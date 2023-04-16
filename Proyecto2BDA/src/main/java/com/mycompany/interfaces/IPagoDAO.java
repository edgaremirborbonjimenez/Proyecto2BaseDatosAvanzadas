/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Pago;
import com.mycompany.dominio.Tramite;

/**
 *  Clase de Implementacion con las Persistencias de Pago
 * @author Usuario
 */
public interface IPagoDAO {

    /**
     * Implementacion de generacion de Pago
     *
     * @param tramite Tramite al que se le generara el Pago
     * @return Pago generado
     */
    Pago generarPago(Tramite tramite);
}
