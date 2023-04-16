/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.FiltroReporteTramites;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Vehiculo;
import com.mycompany.utils.ConfiguracionDePaginado;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPlacaDAO {
    
    List<Placa> consultarPlacasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado);
    Placa generarPlacaVehiculoNuevo(Persona persona, Vehiculo vehiculo);
    Placa generarPlacaVehiculoUsado(Persona persona,Vehiculo vehiculo);
    Placa consultarPlacaActiva(String serie);
    String generaNumeroDePlaca();
    List<Placa> consultaReporteLicencia(FiltroReporteTramites filtro);
    
}
