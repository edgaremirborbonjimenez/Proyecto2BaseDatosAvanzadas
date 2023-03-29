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

    @Column(name = "serie", nullable = false, length = 7)
    private String serie;
    
    @Column(name = "marca", nullable = false, length = 30)
    private String marca;
    
    @Column(name = "color", nullable = false, length = 30)
    private String color;
    
    @Column(name = "linea", nullable = false, length = 30)
    private String linea;
    
    @Column(name = "modelo", nullable = false, length = 30)
    private String modelo;
    
//    @OneToOne
//    @Column(name = "persona",nullable = false)
//    private Persona persona;
    
    @OneToMany(mappedBy = "vehiculo",cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
 //   @Column(name = "placa",nullable = true)
    private List <Placa> placa;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        this.id = id;
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
       // this.persona = persona;
        this.placa = placa;
    }

    public Vehiculo(String serie, String marca, String color, String linea, String modelo, List<Placa> placa) {
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        //this.persona = persona;
        this.placa = placa;
    }
    
    

    public Vehiculo(Long id, String serie, String marca, String color, String linea, String modelo) {
        this.id = id;
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
    }

    public Vehiculo(String serie, String marca, String color, String linea, String modelo) {
        this.serie = serie;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }

    public List<Placa> getPlaca() {
        return placa;
    }

    public void setPlaca(List<Placa> placa) {
        this.placa = placa;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Vehiculo[ id=" + id + " ]";
    }
    
}
