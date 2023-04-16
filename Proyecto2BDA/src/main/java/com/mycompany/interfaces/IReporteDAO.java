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
 *
 * @author edemb
 */
public interface IReporteDAO {
    JasperPrint generarReporteLicencia(List<Reporte> lista) throws PersistenciaException;
    JasperPrint generarReportePlaca(List<Reporte> lista) throws PersistenciaException;
    void vistaPreviaReporte(JasperPrint jasperPrint);
    void exportarReporte(JasperPrint jasperPrint,String path) throws PersistenciaException;
}
