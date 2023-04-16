/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Table (name = "vehiculos")
public abstract class Vehiculo implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serie", nullable = false, length = 17)
    private String serie;
    
    @Column(name = "marca", nullable = false, length = 30)
    private String marca;
    
    @Column(name = "color", nullable = false, length = 30)
    private String color;
    
    @Column(name = "linea", nullable = false, length = 30)
    private String linea;
    
    @Column(name = "modelo", nullable = false, length = 30)
    private String modelo;    
    
    @OneToMany(mappedBy = "vehiculo",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List <Placa> placa;

    /**
     * Constructor por defecto
     */
    public Vehiculo() {
    }

    /**
     * Constructor que recibe id, serie, marca, color, linea, modelo y placa del
     * vehiculo
     * @param id id del vehiculo
     * @param serie serie del vehiculo
     * @param marca marca del vehiculo
     * @param color color del vehiculo
     * @param linea linea del vehiculo
     * @param modelo modelo del vehiculo
     * @param placa placa del vehiculo
     */
    public Vehiculo(Long id, String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        this.id = id;
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.placa = placa;
    }

    /**
     * Constructor que recibe la serie, marca, color, linea, modelo y placa
     * @param serie serie del vehiculo
     * @param marca marca del vehiculo
     * @param color color del vehiculo
     * @param linea linea del vehiculo
     * @param modelo modelo del vehiculo
     * @param placa placa del vehiculo
     */
    public Vehiculo(String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        //this.persona = persona;
        this.placa = placa;
    }
    
    /**
     * Constructor que recibe el id, serie, marca, color, linea, modelo del vehiculo 
     * @param id id del vehiculo
     * @param serie serie del vehiculo
     * @param marca marca del vehiculo
     * @param color color del vehiculo
     * @param linea linea del vehiculo
     * @param modelo modelo del vehiculo
     */
    public Vehiculo(Long id, String serie, String marca, String color, String linea, String modelo) {
        this.id = id;
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
    }

    /**
     * Constructor que recibe la serie, marca, color, linea, modelo del vehiculo
     * @param serie serie del vehiculo
     * @param marca marca del vehiculo
     * @param color color del vehiculo
     * @param linea linea del vehiculo
     * @param modelo modelo del vehiculo
     */
    public Vehiculo(String serie, String marca, String color, String linea, String modelo) {
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
    }
    
    /**
     * Metodo que regresa el id del vehiculo
     * @return regresa el id del vehiculo
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo para setear el id del vehiculo
     * @param id id del vehiculo
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo para regresa la serie del vehiculo
     * @return regresa la serie del vehiculo
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Metodo para setear la seire del vehiculo
     * @param serie serie del vehiculo
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * Metodo que regresa la marca del vehiculo
     * @return regresa la marca del vehiculo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo que setea la marca del vehiculo
     * @param marca marca del vehiculo
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Metodo que regresa el color del vehiculo
     * @return regresa el color del vehiculo
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo para setear el color del vehiculo
     * @param color colro del vehiculo
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Metodo que regresa la linea del vehiculo
     * @return regresa la linea del vehiculo
     */
    public String getLinea() {
        return linea;
    }

    /**
     * Metodo para setear la linea del vehiculo
     * @param linea linea del vehiculo
     */
    public void setLinea(String linea) {
        this.linea = linea;
    }

    /**
     * Metodo que regresa el modelo del vehiculo
     * @return regresa el modelo del vehiculo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Metodo para setear el modelo del vehiculo
     * @param modelo modelo del vehiculo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Metodo que regresa la lista de placas del vehiculo
     * @return regresa la lista de placas del vehiculo
     */
    public List<Placa> getPlaca() {
        return placa;
    }

    /**
     * Metodo para setear la lista de placas del vehiculo
     * @param placa lista de placas del vehiculo
     */
    public void setPlaca(List<Placa> placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Vehiculo[ id=" + id + " ]";
    }
    
}
