/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Reporte;
import com.mycompany.excepciones.PersistenciaException;
import com.mycompany.interfaces.IReporteDAO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *Clase que contiene el manjero de los Reportes de Tramitess
 * @author edemb
 */
public class ReporteDAO implements IReporteDAO{

    EntityManager entityManager;

    /**
     * Constructor por defecto
     */
    public ReporteDAO() {
    }

    /**
     * Constructor
     * @param entityManager EntityManager a utilizar 
     */
    public ReporteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Genera el Reporte de Licencia
     * @param lista lista de Reporte a utilzar
     * @return JasperPrint
     * @throws PersistenciaException Si sucede algun error 
     */
    public JasperPrint generarReporteLicencia(List<Reporte> lista) throws PersistenciaException {
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);

        InputStream input;
        try {
            input = new FileInputStream("..\\jasper\\ReporteLicencia.jrxml");
        } catch (FileNotFoundException e) {
            throw new PersistenciaException("Hubo un error al querer cargar el formato del archivo de reporte");
        }
        JasperPrint jasperPrint;
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            throw new PersistenciaException("Hubo un error al cargar los datos del reporte");
        }
        return jasperPrint;
    }
    
    /**
     * Genera el Reporte de Placa
     * @param lista lista de Reporte a utilzar
     * @return JasperPrint
     * @throws PersistenciaException Si sucede algun error 
     */
        public JasperPrint generarReportePlaca(List<Reporte> lista) throws PersistenciaException {
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);

        InputStream input;
        try {
            input = new FileInputStream("..\\jasper\\ReportePlaca.jrxml");
        } catch (FileNotFoundException e) {
            throw new PersistenciaException("Hubo un error al querer cargar el formato del archivo de reporte");
        }
        JasperPrint jasperPrint;
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(input);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (JRException e) {
            throw new PersistenciaException("Hubo un error al cargar los datos del reporte");
        }
        return jasperPrint;
    }

        /**
         * Ejecuta una vista previa del Reporte
         * @param jasperPrint Reporte ya generado a mostrar
         */
    public void vistaPreviaReporte(JasperPrint jasperPrint) {
//       JasperViewer.viewReport(jasperPrint);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
        jasperViewer.viewReport(jasperPrint,false);
    }

    /**
     * Exporta el reporte
     * @param jasperPrint Reporte generado a Exportar
     * @param path URL a la que se guardara
     * @throws PersistenciaException Por si sucede algun error;
     */
    public void exportarReporte(JasperPrint jasperPrint,String path) throws PersistenciaException {
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        } catch (JRException e) {
            throw new PersistenciaException("No se encontro la direccion a exportar");
        }
    }
}
