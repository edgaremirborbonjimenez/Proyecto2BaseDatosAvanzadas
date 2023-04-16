/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dominio;

/**
 * Clase que contiene las propiedades para el filtrado de los historiales
 *
 * @author edemb
 */
public class FiltroHistorial {

    /**
     * Nombre completo
     */
    private String nombreCompleto;
    /**
     * RFC
     */
    private String RFC;
    /**
     * Ano de Nacimiento
     */
    private String anioNacimiento;

    /**
     * Constrcutor por defecto
     */
    public FiltroHistorial() {
    }

    /**
     * Regresa el nombre completo
     *
     * @return El nombre completo
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Le asigna un valor al nombre completo
     *
     * @param nombreCompleto Nombre completo a asignar
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Regresa la RFC
     *
     * @return La RFC
     */
    public String getRFC() {
        return RFC;
    }

    /**
     * Le asgina un valor al RFC
     *
     * @param RFC RFC a asignar
     */
    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    /**
     * Regresa el ano de nacimiento
     *
     * @return Ano de Nacimiento
     */
    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    /**
     * Le asignar un ano de nacimiento
     *
     * @param anioNacimiento ano de nacimiento a asignar
     */
    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

}
