/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author edemb
 */
public class FiltroReporteTramites {

    private Calendar desde;
    private Calendar hasta;
    Boolean licencia;
    Boolean placa;
    String nombre;

    public FiltroReporteTramites() {
    }

    public void setDesde(Calendar desde) {
        this.desde = desde;
    }

    public void setHasta(Calendar hasta) {
        this.hasta = hasta;
    }

    public void setLicencia(Boolean licencia) {
        this.licencia = licencia;
    }

    public void setPlaca(Boolean placa) {
        this.placa = placa;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
