/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
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

    public Licencia() {
    }

    public Licencia(Long id, Persona persona, Calendar fechaVigencia) {
        super(id, persona);
        this.fechaVigencia = fechaVigencia;
    }

    public Licencia(Persona persona, Calendar fechaVigencia) {
        super(persona);
        this.fechaVigencia = fechaVigencia;
    }

    public Calendar getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Calendar fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaVigencia=" + fechaVigencia + '}';
    }

}
