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

//    private Calendar desde;
//    private Calendar hasta;
        private Calendar desde;
    private Calendar hasta;
    private Boolean licencia;
    private Boolean placa;
    private Persona persona;

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

    public Calendar getDesde() {
        return desde;
    }

    public Calendar getHasta() {
        return hasta;
    }

    public Boolean getLicencia() {
        return licencia;
    }

    public Boolean getPlaca() {
        return placa;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
