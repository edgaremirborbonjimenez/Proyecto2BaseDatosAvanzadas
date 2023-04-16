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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edemb
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tramites")
public abstract class Tramite implements Serializable {

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Fecha emision
     */
    @Column(name = "fechaEmision", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    /**
     * Costo
     */
    @Column(name = "costo")
    private Float costo;

    /**
     * Estado
     */
    @Column(name = "estado", nullable = false)
    private Estado estado;

    /**
     * Persona
     */
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "personaID", nullable = false)
    private Persona persona;

    /**
     * Pagos
     */
    @OneToMany(mappedBy = "tramite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Pago> pagos;

    /**
     * Constructor por defecto
     */
    public Tramite() {
    }

    /**
     * Constructor que recibe el id, fecha de emision, costo, estado, persona
     * y pagos de un tramite
     * @param id id del tramite
     * @param fechaEmision fecha de emision del tramite
     * @param costo costo del tramite
     * @param estado estado del tramite
     * @param persona persona a la que le pertenece el tramite
     * @param pagos pagos del tramite
     */
    public Tramite(Long id, Date fechaEmision, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
        this.pagos = pagos;
    }

    /**
     * Constructor que recibe id, fecha de emision, costo, estado y persona
     * @param id id del tramite
     * @param fechaEmision fecha de emision del tramite
     * @param costo costo del tramite
     * @param estado estado del tramite
     * @param persona persona a la que le pertenece el tramite
     */
    public Tramite(Long id, Date fechaEmision, Float costo, Estado estado, Persona persona) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
    }

    /**
     * Constructor que recibe la fecha de emision, costo, estado, persona y
     * pagos del tramite
     * @param fechaEmision fecha de emision del tramite
     * @param costo costo del tramite
     * @param estado estado del tramite
     * @param persona persona a la que le pertenece el tramite
     * @param pagos pagos del tramite
     */
    public Tramite(Date fechaEmision, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
        this.pagos = pagos;
    }

    /**
     * Constructor que recibe la fecha de emision, costo, estado y persona
     * @param fechaEmision fecha emision del tramite
     * @param costo costo del tramite
     * @param estado estado del tramite
     * @param persona persona a la que le pertenece el trammite
     */
    public Tramite(Date fechaEmision, Float costo, Estado estado, Persona persona) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
    }

    /**
     * Metodo que regresa el id del tramite
     * @return regresa el id del tramite
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo para setear el id del tramite
     * @param id id del tramite
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que regresa la persona a la que le pertenece el tramite
     * @return regresa la persona a la que le pertenece el tramite
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Metodo para setear la persona a la que le pertenece el tramite
     * @param persona persona a la que le pertenece el tramite
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * Metodo que regresa la lista de pagos del tramite
     * @return regresa la lista de pagos del tramite
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * Metodo para setear la lista de pagos del tramite
     * @param pagos lista de pagos del tramite
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    /**
     * Metodo para regresar el costo del tramite
     * @return regresa el costo del tramite
     */
    public Float getCosto() {
        return costo;
    }

    /**
     * Metodo para setear el costo del tramite
     * @param costo costo del tramite
     */
    public void setCosto(Float costo) {
        this.costo = costo;
    }

    /**
     * Metodo para regresar el estado del tramite
     * @return regresa el estado del tramite
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Metodo para setear el estado del tramite
     * @param estado estado del tramite
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Metodo que regresa la fecha de emision del tramite
     * @return regresa la fecha de emision del tramite
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Metodo para setear la fecha de emision del tramite
     * @param fechaEmision fecha de emision del tramite
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Tramite[ id=" + id + " ]";
    }

}
