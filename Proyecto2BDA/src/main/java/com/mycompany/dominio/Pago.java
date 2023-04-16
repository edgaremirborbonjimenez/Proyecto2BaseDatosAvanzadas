/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase que contiene todas las propiedades del Pago
 *
 * @author Usuario
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "pagos")
public class Pago implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidadPagar", nullable = false)
    private Float cantidadPagar;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "idTramite", nullable = false)
    private Tramite tramite;

    /**
     * Constructor por defecto
     */
    public Pago() {
    }

    /**
     * Constructor que asigna valores a todas las propiedades
     *
     * @param id ID a asignar
     * @param cantidadPagar Cantidad a asignar
     * @param tramite Tramite a asignar
     */
    public Pago(Long id, Float cantidadPagar, Tramite tramite) {
        this.id = id;
        this.cantidadPagar = cantidadPagar;
        this.tramite = tramite;
    }

    /**
     * Constructor que asigna valores a todas las propiedades
     *
     * @param cantidadPagar Cantidad a asignar
     * @param tramite Tramite a asignar
     */
    public Pago(Float cantidadPagar, Tramite tramite) {
        this.cantidadPagar = cantidadPagar;
        this.tramite = tramite;
    }

    /**
     * Regresa el ID del Pago
     *
     * @return El ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Le asignar un valor al ID
     *
     * @param id ID a asignar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Regresa la cantidad a pagar
     *
     * @return Cantidad a pagar
     */
    public Float getCantidadPagar() {
        return cantidadPagar;
    }

    /**
     * Le asigna una cantidad a pagar
     *
     * @param cantidadPagar cantidad a pagar que se asignara
     */
    public void setCantidadPagar(Float cantidadPagar) {
        this.cantidadPagar = cantidadPagar;
    }

    /**
     * Regresa el Tramite del Pago
     *
     * @return El tramite
     */
    public Tramite getTramite() {
        return tramite;
    }

    /**
     * Le asigna el Tramite al Pago
     *
     * @param tramite Tramite a asignar
     */
    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Pago[ id=" + id + " ]";
    }

}
