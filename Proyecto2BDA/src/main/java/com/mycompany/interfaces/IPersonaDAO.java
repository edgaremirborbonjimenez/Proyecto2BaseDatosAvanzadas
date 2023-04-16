/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.Persona;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPersonaDAO {
    
    /**
     * Metodo para registrar las personas con una insercion masiva
     */
    void registrarPersonas();
    
    /**
     * Metodo para buscar personas segun un filtro y un paginado
     * @param parametros Filtro de busqueda de personas
     * @param configPaginado Configuracion del paginado a utilizar
     * @return Regresaa una lista de personas que cumplan con los parametros 
     * del filtro de historial
     */
    List<Persona> buscarPersonas(FiltroHistorial parametros, ConfiguracionDePaginado configPaginado);
    
    /**
     * Metodo para buscar personas segun su RFC completo
     * @param rfc RFC para buscar a la persona
     * @return Regresa una persona segun su RFC
     */
    Persona buscarPersonaRFC(String rfc);
    
    /**
     * Metodo para realizar una consulta del total de personas registradas
     * con una configuracion de paginado
     * @param configPaginado Configuracion del paginado de la consulta 
     * @return Regresa una lista de personas 
     */
    List<Persona> consultaTotal(ConfiguracionDePaginado configPaginado);
    
}
