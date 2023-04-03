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
    private Calendar fechaRecepcion;

    @Column(name = "estado", nullable = false)
    private Estado estado;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;

    public Placa() {
    }

    public Placa(Long id, Calendar fechaEmision, Calendar fechaRecepcion, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(id, fechaEmision, costo, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    public Placa(Calendar fechaEmision, Calendar fechaRecepcion, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(fechaEmision, costo, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    public Placa(Long id, Calendar fechaEmision, Float costo, Persona persona, List<Pago> pagos, String numero, Estado estado, Vehiculo vehiculo) {
        super(id, fechaEmision, costo, persona, pagos);
        this.numero = numero;
        this.estado = estado;
        this.vehiculo = vehiculo;
    }

    public Placa(Calendar fechaEmision, Float costo, Persona persona, String numero, Estado estado, Vehiculo vehiculo) {
        super(fechaEmision, costo, persona);
        this.numero = numero;
        this.estado = estado;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
