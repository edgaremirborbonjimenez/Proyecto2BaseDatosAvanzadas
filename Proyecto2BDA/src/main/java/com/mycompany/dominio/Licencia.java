/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edemb
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {

    @Column(name = "fechaVigencia", nullable = false)
    Calendar fechaVigencia;
    
    @Column(name = "serie", nullable = false)
    private String serie;

    public Licencia() {
    }

    public Licencia(Long id, Calendar fechaEmision, Calendar fechaVigencia, String serie, Float costo, Persona persona, List<Pago> pagos) {
        super(id, fechaEmision, costo, persona, pagos);
        this.fechaVigencia = fechaVigencia;
        this.serie = serie;
    }

    public Licencia(Calendar fechaEmision, Calendar fechaVigencia, String serie, Float costo, Persona persona, List<Pago> pagos) {
        super(fechaEmision, costo, persona, pagos);
        this.fechaVigencia = fechaVigencia;
        this.serie = serie;
    }

    public Licencia(Calendar fechaEmision, Calendar fechaVigencia, Float costo, Persona persona) {
        super(fechaEmision, costo, persona);
        this.fechaVigencia = fechaVigencia;
    }

    public Calendar getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Calendar fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaVigencia=" + fechaVigencia + '}';
    }

}
