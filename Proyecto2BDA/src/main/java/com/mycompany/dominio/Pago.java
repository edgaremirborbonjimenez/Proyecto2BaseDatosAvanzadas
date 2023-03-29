/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
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
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Inheritance (strategy = InheritanceType.JOINED)
@Table (name = "pagos")
public abstract class Pago implements Serializable {

    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "cantidadPagar", nullable = false)
    private Float cantidadPagar;

    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "idTramite",nullable = false)
    private Tramite tramite;
    
    public Pago() {
    }

    public Pago(Long id, Float cantidadPagar, Tramite tramite) {
        this.id = id;
        this.cantidadPagar = cantidadPagar;
        this.tramite = tramite;
    }

    public Pago(Float cantidadPagar, Tramite tramite) {
        this.cantidadPagar = cantidadPagar;
        this.tramite = tramite;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCantidadPagar() {
        return cantidadPagar;
    }

    public void setCantidadPagar(Float cantidadPagar) {
        this.cantidadPagar = cantidadPagar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Pago[ id=" + id + " ]";
    }
    
}
