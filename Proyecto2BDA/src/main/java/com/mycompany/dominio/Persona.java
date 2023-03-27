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
@Table(name = "Persona")
public class Persona implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "apellidoPaterno", length = 30, nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", length = 30, nullable = false)
    private String apellidoMaterno;

    @Column(name = "RFC", length = 30, nullable = false)
    private String rfc;

    @Column(name = "fechaNacimiento", nullable = false)
    Calendar fechaNacimiento;

    @Column(name = "telefono", length = 10, nullable = false)
    private String telefono;

    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

    public Persona() {
    }

    public Persona(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.dominio.Persona[ id=" + id + " ]";
    }

}
