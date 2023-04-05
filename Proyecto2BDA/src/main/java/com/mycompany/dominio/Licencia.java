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
 *
 * @author edemb
 */
@Entity
@Table(name = "licencias")
public class Licencia extends Tramite implements Serializable {

    @Column(name = "fechaVigencia", nullable = false)
    @Temporal(TemporalType.DATE)
    Date fechaVigencia;

    public Licencia() {
    }

//    public Licencia(Long id, Calendar fechaEmision, Calendar fechaVigencia, Float costo, Persona persona, List<Pago> pagos) {
//        super(id, fechaEmision, costo, persona, pagos);
//        this.fechaVigencia = fechaVigencia;
//    }
//
//        public Licencia(Long id, Calendar fechaEmision, Calendar fechaVigencia, Float costo, Persona persona) {
//        super(id, fechaEmision, costo, persona);
//        this.fechaVigencia = fechaVigencia;
//    }
//    
//    public Licencia(Calendar fechaEmision, Calendar fechaVigencia, Float costo, Persona persona, List<Pago> pagos) {
//        super(fechaEmision, costo, persona, pagos);
//        this.fechaVigencia = fechaVigencia;
//    }
//
//    public Licencia(Calendar fechaEmision, Calendar fechaVigencia, Float costo, Persona persona) {
//        super(fechaEmision, costo, persona);
//        this.fechaVigencia = fechaVigencia;
//    }
        public Licencia(Long id, Date fechaEmision, Date fechaVigencia, Float costo,Estado estado, Persona persona, List<Pago> pagos) {
        super(id, fechaEmision, costo,estado, persona, pagos);
        this.fechaVigencia = fechaVigencia;
    }

        public Licencia(Long id, Date fechaEmision, Date fechaVigencia, Float costo,Estado estado, Persona persona) {
        super(id, fechaEmision, costo,estado, persona);
        this.fechaVigencia = fechaVigencia;
    }
    
    public Licencia(Date fechaEmision, Date fechaVigencia, Float costo,Estado estado, Persona persona, List<Pago> pagos) {
        super(fechaEmision, costo,estado, persona, pagos);
        this.fechaVigencia = fechaVigencia;
    }

    public Licencia(Date fechaEmision, Date fechaVigencia, Float costo,Estado estado, Persona persona) {
        super(fechaEmision, costo,estado, persona);
        this.fechaVigencia = fechaVigencia;
    }
//   public Calendar getFechaVigencia() {
//        return fechaVigencia;
//    }
//
//    public void setFechaVigencia(Calendar fechaVigencia) {
//        this.fechaVigencia = fechaVigencia;
//    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaVigencia=" + fechaVigencia + '}';
    }

}
