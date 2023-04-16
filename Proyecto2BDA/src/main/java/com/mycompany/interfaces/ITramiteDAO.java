/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Tramite;
import java.util.List;

/**
 *
 * @author edemb
 */
public interface ITramiteDAO {
    List<Tramite> consultarTramitesPersona(Persona persona);
}
