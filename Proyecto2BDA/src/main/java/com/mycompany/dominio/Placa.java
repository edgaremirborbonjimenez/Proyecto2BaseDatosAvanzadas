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
@Table(name = "tramites")
public class Placa extends Tramite implements Serializable {

    @Column(name = "numero", length = 7, nullable = false)
    private String numero;

    @Column(name = "fechaRecepcion", nullable = false)
    private Calendar fechaRecepcion;

    @Column(name = "fechaEmision", nullable = false)
    private Calendar fechaEmision;

    public Placa() {
    }

    public Placa(Long id, Float costo, Persona persona, List<Pago> pagos,String numero, Calendar fechaRecepcion, Calendar fechaEmision) {
        super(id, costo, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEmision = fechaEmision;
    }

    public Placa(Float costo, Persona persona, List<Pago> pagos,String numero, Calendar fechaRecepcion, Calendar fechaEmision) {
        super(costo, persona, pagos);
        this.numero = numero;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEmision = fechaEmision;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Calendar getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Calendar fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Calendar getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Calendar fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public String toString() {
        return "Placa{" + "numero=" + numero + ", fechaRecepcion=" + fechaRecepcion + ", fechaEmision=" + fechaEmision + '}';
    }

}
