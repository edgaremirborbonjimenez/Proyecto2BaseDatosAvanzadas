/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.dominio.Reporte;
import com.mycompany.excepciones.PersistenciaException;
import java.util.List;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * I
 * Clase de Implementacion con la generacionde Reportes con JasperReport
 * @author edemb
 */
public interface IReporteDAO {

    /**
     * Implementacion de genera un reporte de Licencias
     *
     * @param lista Lista de Reportes a utilizar
     * @return un JasperPrint con el reporte generado
     * @throws PersistenciaException por si sucede algun error
     */
    JasperPrint generarReporteLicencia(List<Reporte> lista) throws PersistenciaException;

    /**
     * Implementacion de genera un reporte de Placas
     *
     * @param lista Lista de Reportes a utilizar
     * @return un JasperPrint con el reporte generado
     * @throws PersistenciaException por si sucede algun error
     */
    JasperPrint generarReportePlaca(List<Reporte> lista) throws PersistenciaException;

    /**
     * Implemetacion de generar una vista previa de un JasperPrint
     *
     * @param jasperPrint JasperPrint a usar para la vista previa
     */
    void vistaPreviaReporte(JasperPrint jasperPrint);

    /**
     * Implemntacion de exportar un reporte de un JasperPrint
     * @param jasperPrint JasperPrint a exportar
     * @param path URL a la que se exportara
     * @throws PersistenciaException or si sucede algun error
     */
    void exportarReporte(JasperPrint jasperPrint, String path) throws PersistenciaException;
}
