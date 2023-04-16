/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que contiene todas las propiedades de la Licencia
 *
 * @author edemb
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {

    /**
     * Fecha de Vigencia
     */
    @Column(name = "fechaVigencia", nullable = false)
    @Temporal(TemporalType.DATE)
    Date fechaVigencia;

    /**
     * Constructor por defecto
     */
    public Licencia() {
    }

    /**
     * Constructor que asignar todas las propiedades
     *
     * @param id ID a asignar
     * @param fechaEmision Fecha asignar a fecha emision
     * @param fechaVigencia Fecha a asignar fecha vigencia
     * @param costo Costo a asignar
     * @param estado Estado a asignar
     * @param persona Persona a asignar
     * @param pagos Lista de Pagos que le pertenecen
     */
    public Licencia(Long id, Date fechaEmision, Date fechaVigencia, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        super(id, fechaEmision, costo, estado, persona, pagos);
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Constructor que asignar todas las propiedades
     *
     * @param id ID a asignar
     * @param fechaEmision Fecha asignar a fecha emision
     * @param fechaVigencia Fecha a asignar fecha vigencia
     * @param costo Costo a asignar
     * @param estado Estado a asignar
     * @param persona Persona a asignar
     */
    public Licencia(Long id, Date fechaEmision, Date fechaVigencia, Float costo, Estado estado, Persona persona) {
        super(id, fechaEmision, costo, estado, persona);
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Constructor que asignar todas las propiedades
     *
     * @param fechaEmision Fecha asignar a fecha emision
     * @param fechaVigencia Fecha a asignar fecha vigencia
     * @param costo Costo a asignar
     * @param estado Estado a asignar
     * @param persona Persona a asignar
     * @param pagos Lista de Pagos que le pertenecen
     */
    public Licencia(Date fechaEmision, Date fechaVigencia, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        super(fechaEmision, costo, estado, persona, pagos);
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Constructor que asignar todas las propiedades
     *
     * @param fechaEmision Fecha asignar a fecha emision
     * @param fechaVigencia Fecha a asignar fecha vigencia
     * @param costo Costo a asignar
     * @param estado Estado a asignar
     * @param persona Persona a asignar
     */
    public Licencia(Date fechaEmision, Date fechaVigencia, Float costo, Estado estado, Persona persona) {
        super(fechaEmision, costo, estado, persona);
        this.fechaVigencia = fechaVigencia;
    }

    /**
     * Regresa la fecha de vigencia
     *
     * @return Fecha de vigencia
     */
    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    /**
     * Le asigna una Fecha a la fecha de vigencia
     *
     * @param fechaVigencia Fecha a asignar
     */
    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaVigencia=" + fechaVigencia + '}';
    }

}
