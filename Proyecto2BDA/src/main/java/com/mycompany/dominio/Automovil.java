/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.List;
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
@Table (name = "automoviles")
public class Automovil extends Vehiculo  implements Serializable {

    public Automovil() {
    }

    public Automovil(Long id, String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        super(id, serie, marca, color, linea, modelo, placa);
    }

    public Automovil(String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        super(serie, marca, color, linea, modelo, placa);
    }

    public Automovil(Long id, String serie, String marca, String color, String linea, String modelo) {
        super(id, serie, marca, color, linea, modelo);
    }

    public Automovil(String serie, String marca, String color, String linea, String modelo) {
        super(serie, marca, color, linea, modelo);
    }


    
}
