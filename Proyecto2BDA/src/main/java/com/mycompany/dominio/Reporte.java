/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.util.Date;

/**
 *
 * @author edemb
 */
public class Reporte {

    /**
     * ID
     */
    private Long id;
    /**
     * Fecha emision
     */
    private Date fechaEmision;
    /**
     * Costo
     */
    private Float costo;
    /**
     * Nombre completo
     */
    private String nombreCompleto;

    /**
     * Constructor por defecto
     */
    public Reporte() {
    }

    /**
     * Construcor que recibe el id, fecha de emision, costo y nombre completo
     * @param id id del tramite
     * @param fechaEmision fecha de emision del tramite
     * @param costo costo del tramite
     * @param nombreCompleto nombre completo de la persona
     */
    public Reporte(Long id, Date fechaEmision, Float costo, String nombreCompleto) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Constructor que recibe la fecha de emision, costo y nombre completo
     * @param fechaEmision fecha de emision del tramite
     * @param costo costo del tramite
     * @param nombreCompleto nombre completo de la persona
     */
    public Reporte(Date fechaEmision, Float costo, String nombreCompleto) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Metodo para regresar el id del tramite
     * @return regresa el id del tramite
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo para setear el id del trammite
     * @param id id del reporte
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo para regresar la fecha de emision del tramite
     * @return Fecha de emision
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
     * Metodo para regresa el nombre completo de la persona del tramite
     * @return regresa el nombre completo de la persona del tramtie
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Metodo para setear el nombre completo de la persona del tramite
     * @param nombreCompleto nombre completo de la persona del tramite
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}
