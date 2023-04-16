/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Clase que contiene las propiedades para el filtrado de los Reportes
 *
 * @author edemb
 */
public class FiltroReporteTramites {

    private Date desde;
    private Date hasta;
    private String Nombre;

    /**
     * Constructor por defecto
     */
    public FiltroReporteTramites() {
    }

    /**
     * Asigna una fecha para el desde
     *
     * @param desde Fecha a asignar
     */
    public void setDesde(Date desde) {
        this.desde = desde;
    }

    /**
     * Asigna una fecha para el hasta
     *
     * @param hasta Fecha a asignar
     */
    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    /**
     * Regresa la Fecha de desde
     *
     * @return Fecha desde
     */
    public Date getDesde() {
        return desde;
    }

    /**
     * Regresa la Fecha de hasta
     *
     * @return Fecha hasta
     */
    public Date getHasta() {
        return hasta;
    }

    /**
     * Regresa el nombre
     *
     * @return Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Le asigna un valor al nombre
     *
     * @param Nombre Nombre a asignar
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
