/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPlacaDAO {
    
    List<Placa> consultarPlacasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado);
    
}
