/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
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
@Table(name = "efectivo")
public class Efectivo extends Pago implements Serializable {

    public Efectivo() {
    }

    public Efectivo(Long id, Float cantidadPagar, Tramite tramite) {
        super(id, cantidadPagar, tramite);
    }

    public Efectivo(Float cantidadPagar, Tramite tramite) {
        super(cantidadPagar, tramite);
    }

   
    
}
