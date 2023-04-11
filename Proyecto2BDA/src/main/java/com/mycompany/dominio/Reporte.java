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

    private Long id;
    private Date fechaEmision;
    private Float costo;
    private String nombreCompleto;

    public Reporte() {
    }

    public Reporte(Long id, Date fechaEmision, Float costo, String nombreCompleto) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.nombreCompleto = nombreCompleto;
    }

    public Reporte(Date fechaEmision, Float costo, String nombreCompleto) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

}
