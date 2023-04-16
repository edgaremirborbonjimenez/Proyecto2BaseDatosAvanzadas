    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author edemb
 */
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCompleto", length = 100, nullable = false)
    private String nombreCompleto;

    @Column(name = "RFC", length = 12, nullable = false)
    private String rfc;

    @Column(name = "fechaNacimiento", nullable = false)
    private Calendar fechaNacimiento;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "sexo", nullable = false)
    private Sexo sexo;
    
    @Column(name = "es_discapacitado")
    private Discapacitado discapasitado;    
    
    @OneToMany(mappedBy = "persona", cascade = {CascadeType.REMOVE})
    private List <Tramite> tramites;
            
    /**
     * Constructor por defecto
     */
    public Persona() {
    }

    /**
     * Constructor que recibe nombre completo, rfc, fecha de nacimiento, telefono,
     * sexo y si es discapacitado
     * @param nombreCompleto Nombre completo de la persona
     * @param rfc RFC de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param telefono telefono de la persona
     * @param sexo sexo de la persona
     * @param discapasitado si es discapacitado
     */
    public Persona(String nombreCompleto, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo, Discapacitado discapasitado) {
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
        this.discapasitado = discapasitado;
    }

    /**
     * Constructor que recibe el id, nombre completo, rfc, fecha de nacimiento,
     * telefono, sexo y los tramites de la persona
     * @param id id de la persona
     * @param nombreCompleto nombre completo de la persona
     * @param rfc rfc de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param telefono telefono de la persona
     * @param sexo sexo de la persona
     * @param tramites lista de tramites de la persona
     */
    public Persona(Long id, String nombreCompleto, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo,List<Tramite> tramites) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
       // this.vehiculo = vehiculo;
        this.tramites = tramites;
    }

    /**
     * Constructor que recibe el nombre completo, rfc, fecha de nacimiento, 
     * telefono, sexo y lista de tramites de la persona
     * @param nombreCompleto nombre completo de la persona
     * @param rfc rfc de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param telefono telefono de la persona
     * @param sexo sexo de la persona
     * @param tramites lista de tramites de la persona
     */
    public Persona(String nombreCompleto, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo, List<Tramite> tramites) {
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
        //this.vehiculo = vehiculo;
        this.tramites = tramites;
    }
    
    /**
     * Constructor que recibe el id, nombre completo, rfc, fecha de nacimiento,
     * telefono y sexo de la persona
     * @param id id de la persona
     * @param nombreCompleto nombre completo de la persona
     * @param rfc rfc de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param telefono telefono de la persona
     * @param sexo sexo de la persona
     */
    public Persona(Long id, String nombreCompleto, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    /**
     * Constructor que recibe el nombre completo, rfc, fecha de nacimiento,
     * telefono y sexo de la persona
     * @param nombreCompleto nombre completo de la persona
     * @param rfc rfc de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     * @param telefono telefono de la persona
     * @param sexo sexo de la persona
     */
    public Persona(String nombreCompleto, String rfc, Calendar fechaNacimiento, String telefono, Sexo sexo) {
        this.nombreCompleto = nombreCompleto;
        this.rfc = rfc;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    /**
     * Metodo que regresa el id de la persona
     * @return regresa el id de la persona
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo para setear el id de la persona
     * @param id id de la persona
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo para regresar el nombre completo de la persona
     * @return regresa el nombre completo de la persona
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Metodo para setear el nombre completo de la persona
     * @param nombreCompleto nombre completo de la persona
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Metodo para regresar el rfc de la persona
     * @return regresa el rfc de la persona
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodo para setear el rfc de la persona
     * @param rfc rfc de la persona
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodo que regresa la fecha de nacimiento de la persona
     * @return regresa la fecha de nacimiento de la persona
     */
    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo para seteare la fecha de nacimiento de la persona
     * @param fechaNacimiento fecha de nacimiento de la persona
     */
    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * MEtodo que regresa el telefono de la persona
     * @return regresa el telefono de la persona
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo para setear el telefono de la persona
     * @param telefono telefono de la persona
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo para regresar el sexo de la persona
     * @return regresa el sexo de la persona
     */
    public Sexo getSexo() {
        return sexo;
    }

    /**
     * Metodo para setear el sexo de la persona
     * @param sexo sexo de la persona
     */
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    /**
     * Metodo para regresar la lista de tramites de la persona
     * @return regresa la lista de tramites de la persona
     */
    public List<Tramite> getTramites() {
        return tramites;
    }

    /**
     * Metodo para setear la lista de tramites de la persona
     * @param tramites lista de tramites de la persona
     */
    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    /**
     * Metodo para regresar si la persona es discapacitada o no
     * @return regresa si la persona es discapacitada o no
     */
    public Discapacitado getDiscapasitado() {
        return discapasitado;
    }

    /**
     * Metodo pare setear si la persona es discapacitada o no
     * @param discapasitado si la persona es discapacitada o no
     */
    public void setDiscapasitado(Discapacitado discapasitado) {
        this.discapasitado = discapasitado;
    }  

    @Override
    public String toString() {
        return "com.mycompany.dominio.Persona[ id=" + id + " ]";
    }

}
