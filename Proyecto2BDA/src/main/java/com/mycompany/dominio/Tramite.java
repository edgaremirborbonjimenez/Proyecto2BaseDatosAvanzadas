/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author edemb
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tramites")
public abstract class Tramite implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaEmision", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Column(name = "costo")
    private Float costo;

    @Column(name = "estado", nullable = false)
    private Estado estado;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "personaID", nullable = false)
    private Persona persona;

    @OneToMany(mappedBy = "tramite", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Pago> pagos;

    public Tramite() {
    }

//    public Tramite(Long id, Calendar fechaEmision, Float costo, Persona persona, List<Pago> pagos) {
//        this.id = id;
//        this.fechaEmision = fechaEmision;
//        this.costo = costo;
//        this.persona = persona;
//        this.pagos = pagos;
//    }
//        public Tramite(Long id, Calendar fechaEmision, Float costo, Persona persona) {
//        this.id = id;
//        this.fechaEmision = fechaEmision;
//        this.costo = costo;
//        this.persona = persona;
//    }
//
//    public Tramite(Calendar fechaEmision, Float costo, Persona persona, List<Pago> pagos) {
//        this.fechaEmision = fechaEmision;
//        this.costo = costo;
//        this.persona = persona;
//        this.pagos = pagos;
//    }
//
//    public Tramite(Calendar fechaEmision, Float costo, Persona persona) {
//        this.fechaEmision = fechaEmision;
//        this.costo = costo;
//        this.persona = persona;
//    }
    public Tramite(Long id, Date fechaEmision, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
        this.pagos = pagos;
    }

    public Tramite(Long id, Date fechaEmision, Float costo, Estado estado, Persona persona) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
    }

    public Tramite(Date fechaEmision, Float costo, Estado estado, Persona persona, List<Pago> pagos) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
        this.pagos = pagos;
    }

    public Tramite(Date fechaEmision, Float costo, Estado estado, Persona persona) {
        this.fechaEmision = fechaEmision;
        this.costo = costo;
        this.estado = estado;
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tramite)) {
            return false;
        }
        Tramite other = (Tramite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Tramite[ id=" + id + " ]";
    }

}
