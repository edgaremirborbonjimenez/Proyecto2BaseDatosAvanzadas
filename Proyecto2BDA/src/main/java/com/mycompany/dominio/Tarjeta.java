/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edemb
 */
@Entity
@Table(name = "tarjetas")
public class Tarjeta extends Pago implements Serializable {

    @Column(name = "numTarjeta",length = 10,nullable = false)
    private String numTarjeta;

    public Tarjeta() {
    }

    public Tarjeta(Long id, Float cantidadPagar, Tramite tramite,String numTarjeta) {
        super(id, cantidadPagar, tramite);
        this.numTarjeta = numTarjeta;
    }

    public Tarjeta(Float cantidadPagar, Tramite tramite,String numTarjeta) {
        super(cantidadPagar, tramite);
        this.numTarjeta = numTarjeta;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "numTarjeta=" + numTarjeta + '}';
    }
    
        
    
}
