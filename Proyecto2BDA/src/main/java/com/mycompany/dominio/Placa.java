/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author edemb
 */
@Entity
@Table(name = "tramites")
public class Placa extends Tramite implements Serializable {

    @Column(name = "numero", length = 7, nullable = false)
    private String numero;

    @Column(name = "fechaRecepcion", nullable = false)
    private Calendar fechaRecepcion;

    @Column(name = "fechaEmision", nullable = true)
    private Calendar fechaEmision;
    
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "idVehiculo",nullable = false)
    private Vehiculo vehiculo;

    public Placa() {
    }

    public Placa(Long id, Float costo, Persona persona,String numero, Calendar fechaRecepcion, Calendar fechaEmision,Vehiculo vehiculo) {
        super(id, costo, persona);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEmision = fechaEmision;
        this.vehiculo=vehiculo;
    }

    public Placa(Float costo, Persona persona,String numero, Calendar fechaRecepcion, Calendar fechaEmision,Vehiculo vehiculo) {
        super(costo, persona);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEmision = fechaEmision;
        this.vehiculo=vehiculo;
    }

    public Placa(Float costo, Persona persona,String numero, Calendar fechaRecepcion, Vehiculo vehiculo) {
        super(costo, persona);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.vehiculo = vehiculo;
    }
    
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    

    @Override
    public String toString() {
        return "Placa{" + "numero=" + numero + ", fechaRecepcion=" + fechaRecepcion + ", fechaEmision=" + fechaEmision + '}';
    }

}
