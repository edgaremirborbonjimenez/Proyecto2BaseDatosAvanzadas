/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edemb
 */
@Entity
@Table(name = "placas")
public class Placa extends Tramite implements Serializable {

    @Column(name = "numero", length = 7, nullable = false)
    private String numero;

    @Column(name = "fechaRecepcion", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcion;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;

    /**
     * Constructor por defecto
     */
    public Placa() {
    }

    /**
     * Constructor que recibe el id, fecha de emision, fecha de recepcion, costo,
     * persona a que pertenece la placa, pagos, numero de placa, estado y 
     * vehiculo al que pertenece
     * @param id id de la placa
     * @param fechaEmision fecha de emision de la placa
     * @param fechaRecepcion fecha de recepcion de la placa
     * @param costo costo de la placa
     * @param persona persona quien la tramito
     * @param pagos pago de la placa
     * @param numero numero de placa
     * @param estado estado de la placa
     * @param vehiculo vehiculo al que pertenece
     */
    public Placa(Long id, Date fechaEmision, Date fechaRecepcion, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(id, fechaEmision, costo,estado, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.vehiculo = vehiculo;
    }

    /**
     * Constructor que recibe la fecha de emision, fecha de recepcion, costo,
     * persona a la que pertenece, pagos, numero de placa, estado y vehiculo
     * al que pertenece
     * @param fechaEmision fecha de emision
     * @param fechaRecepcion fecha de recepcion
     * @param costo costo de la placa
     * @param persona persona quien la tramito
     * @param pagos pago de la placa
     * @param numero numero de placa
     * @param estado estado de la placa
     * @param vehiculo vehiculo al que pertenece
     */
    public Placa(Date fechaEmision, Date fechaRecepcion, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(fechaEmision, costo,estado, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.vehiculo = vehiculo;
    }

    /**
     * Constructor que recibe el id, fecha de emision, costo, persona que la 
     * tramito, pagos, numero de placa, estado y vehiculo al que pertenece
     * @param id id de la placa
     * @param fechaEmision fecha de emision
     * @param costo costo de la placa
     * @param persona persona que la tramito
     * @param pagos pago de la placa
     * @param numero numero de placa
     * @param estado estado de la placa
     * @param vehiculo vehiculo a la que pertenece
     */
    public Placa(Long id, Date fechaEmision, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(id, fechaEmision, costo,estado, persona, pagos);
        this.numero = numero;
        this.vehiculo = vehiculo;
    }

    /**
     * Constructor que recibe fecha de emision, costo, persona, numero, estado
     * y vehiculo al que pertenece
     * @param fechaEmision fecha de emision
     * @param costo costo de la placa
     * @param persona persona que la tramito
     * @param numero numero de placa
     * @param estado estado de la placa
     * @param vehiculo vehiculo al que pertenece
     */
    public Placa(Date fechaEmision, Float costo, Persona persona, String numero, Estado estado, Vehiculo vehiculo) {
        super(fechaEmision, costo,estado, persona);
        this.numero = numero;
        this.vehiculo = vehiculo;
    }

    /**
     * Metodo que regresa el numero de la placa
     * @return regresa el numero de la placa
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Metodo que setea el numero de la placa
     * @param numero numero de la placa
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Metodo que regresa la fecha de recepcion de la placa
     * @return regresa la fecha de recepcion de la placa
     */
    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    /**
     * Metodo que setea la fecha de recepcion de la placa
     * @param fechaRecepcion fecha de recepcion
     */
    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    /**
     * Metodo que regresa el vehiculo al que pertenece la placa
     * @return regresa el vehiculo al que pertenece la placa
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * Metodo para setear el vehiculo al que pertenece la placa
     * @param vehiculo vehiculo al que pertenece la placa
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


}
