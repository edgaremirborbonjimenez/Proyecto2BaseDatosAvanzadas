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
 * Clase de Implementacion con las Persistencias de Placa
 *
 * @author Usuario
 */
public interface IPlacaDAO {

    /**
     * Consutla las Placas de una Persona y contiene la configuracion de
     * paginado
     *
     * @param persona Persona a buscar las Placas
     * @param configuracionDePaginado Configuracion de Paginado
     * @return Lista de Placas
     */
    List<Placa> consultarPlacasPersona(Persona persona, ConfiguracionDePaginado configuracionDePaginado);

    /**
     * Implementacion de la generacion de Placa a un Vehiculo Nuevo
     *
     * @param persona Persona al que le pertenecera la Placa
     * @param vehiculo Vehiculo al que se le generara la Placa
     * @return Placa generada
     */
    Placa generarPlacaVehiculoNuevo(Persona persona, Vehiculo vehiculo);

    /**
     * Implementacion de la generacion de Placa a un Vehiculo Usado
     *
     * @param persona Persona al que le pertenecera la Placa
     * @param vehiculo Vehiculo al que se le generara la Placa
     * @return Placa generada
     */
    Placa generarPlacaVehiculoUsado(Persona persona, Vehiculo vehiculo);

    /**
     * Consulta la Placa que este Activa con el numero de serie
     *
     * @param serie Serie a utilizar para la consulta
     * @return Placa encontrada
     */
    Placa consultarPlacaActiva(String serie);

    /**
     * Genera el numero de la Placa
     *
     * @return el texto del numero de la Placa
     */
    String generaNumeroDePlaca();

    /**
     * Consulta las placas dependiendo del filtro
     *
     * @param filtro Filto a utilizar
     * @return una Lista de Placas
     */
    List<Placa> consultaReportePlaca(FiltroReporteTramites filtro);

}
