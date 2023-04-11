/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daos;

import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Reporte;
import com.mycompany.excepciones.PersistenciaException;
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
 *
 * @author edemb
 */
public class ReporteDAO {

    EntityManager entityManager;

    public ReporteDAO() {
    }

    public ReporteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

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

    public void vistaPreviaReporte(JasperPrint jasperPrint) {
//       JasperViewer.viewReport(jasperPrint);
        JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
        jasperViewer.viewReport(jasperPrint,false);
    }

    public void exportarReporte(JasperPrint jasperPrint,String path) throws PersistenciaException {
        try {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path);
        } catch (JRException e) {
            throw new PersistenciaException("No se encontro la direccion a exportar");
        }
    }
}
