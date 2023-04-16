/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Tramite;
import java.util.List;

/**
 * Clase de Implementacion con las Persistencias de Tramites
 * @author edemb
 */
public interface ITramiteDAO {

    /**
     * Consulta tramites por Persona
     *
     * @param persona Persona a consultar los Tramites
     * @return Una lista de Tramites
     */
    List<Tramite> consultarTramitesPersona(Persona persona);
}
