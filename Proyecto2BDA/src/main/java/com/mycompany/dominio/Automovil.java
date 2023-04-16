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
 * Clase que contiene todas las porpiedades de un Automovil y hereda de Vehiculo
 *
 * @author edemb
 */
@Entity
@Table(name = "automoviles")
public class Automovil extends Vehiculo implements Serializable {

    /**
     * Constructor por defecto
     */
    public Automovil() {
    }

    /**
     * Constructor que especifica todas las propiedades de la clase
     *
     * @param id ID que tendra el Automovil
     * @param serie Serie que tendra el automovil
     * @param marca Marca que tendra el automovil
     * @param color Color que tendra el automovil
     * @param linea Linea que tendra el automovil
     * @param modelo Modelo que tendra el automovil
     * @param placa Lista de Placas que le pertenecen al automovil
     */
    public Automovil(Long id, String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        super(id, serie, marca, color, linea, modelo, placa);
    }

    /**
     * Constructor que especifica todas las propiedades de la clase
     *
     * @param serie Serie que tendra el automovil
     * @param marca Marca que tendra el automovil
     * @param color Color que tendra el automovil
     * @param linea Linea que tendra el automovil
     * @param modelo Modelo que tendra el automovil
     * @param placa Lista de Placas que le pertenecen al automovil
     */
    public Automovil(String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        super(serie, marca, color, linea, modelo, placa);
    }

    /**
     * Constructor que especifica todas las propiedades de la clase
     *
     * @param id ID que tendra el Automovil
     * @param serie Serie que tendra el automovil
     * @param marca Marca que tendra el automovil
     * @param color Color que tendra el automovil
     * @param linea Linea que tendra el automovil
     * @param modelo Modelo que tendra el automovil
     */
    public Automovil(Long id, String serie, String marca, String color, String linea, String modelo) {
        super(id, serie, marca, color, linea, modelo);
    }

    /**
     * Constructor que especifica todas las propiedades de la clase
     *
     * @param serie Serie que tendra el automovil
     * @param marca Marca que tendra el automovil
     * @param color Color que tendra el automovil
     * @param linea Linea que tendra el automovil
     * @param modelo Modelo que tendra el automovil
     */
    public Automovil(String serie, String marca, String color, String linea, String modelo) {
        super(serie, marca, color, linea, modelo);
    }

}
