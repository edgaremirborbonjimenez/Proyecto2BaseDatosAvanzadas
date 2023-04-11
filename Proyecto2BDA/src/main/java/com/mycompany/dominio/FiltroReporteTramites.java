/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author edemb
 */
public class FiltroReporteTramites {

    private Date desde;
    private Date hasta;
    private String Nombre;

    public FiltroReporteTramites() {
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getDesde() {
        return desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

}
