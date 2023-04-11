/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ILicenciaDAO {
    
    List<Licencia> consultarLicenciasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado);
    
}
