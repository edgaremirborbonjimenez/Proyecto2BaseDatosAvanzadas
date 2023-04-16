/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.FiltroReporteTramites;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Vigencia;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.List;

/**
 *  Clase de Implementacion con las Persistencias de Licencia
 * @author Usuario
 */
public interface ILicenciaDAO {
    
    /**
     * Implementacion de consultar Licencias de persona con configuracion de paginado
     * @param persona Persona a buscar Licencias
     * @param configuracionDePaginado configuracion de paginado
     * @return Una lista de Licencias
     */
    List<Licencia> consultarLicenciasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado);
    /**
     * Implementacion de generacion de Licencias
     * @param vigencia Vigencia que tendra la Licencia
     * @param persona Persona a la que le pertenecera la Liciencia
     * @return Licencia generada
     */
    Licencia generarLicencia(Vigencia vigencia, Persona persona);
    /**
     * Implementacion de consulta de Licencia Activa
     * @param persona Pesona a buscar Licencia Activa
     * @return la Licencia Activa
     */
    Licencia consultarLicenciaActiva(Persona persona);
    /**
     * Consulta Licencias dependiendo de filtro
     * @param filtro Filtro a utilizar
     * @return Lista de Licencias
     */
    List<Licencia> consultaReporteLicencia(FiltroReporteTramites filtro);
}
