/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.ConexionDAO;
import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PlacaDAO;
import com.mycompany.daos.ReporteDAO;
import com.mycompany.dominio.FiltroReporteTramites;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Reporte;
import com.mycompany.excepciones.PersistenciaException;
import com.mycompany.utils.ValidacionDatos;
import java.io.File;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Usuario
 */
public class ModuloReporte extends javax.swing.JFrame {

    LicenciaDAO licenciaDAO;
    PlacaDAO placaDAO;
    EntityManager entityManager;
    ReporteDAO reporteDAO;

    /**
     * Creates new form ModuloReporte
     */
    public ModuloReporte(EntityManager entityManager) {
        initComponents();
        this.entityManager=entityManager;
        placaDAO = new PlacaDAO(this.entityManager);
        licenciaDAO = new LicenciaDAO(this.entityManager);
        reporteDAO = new ReporteDAO(entityManager);
    }

    private Boolean hayFechasEnPeriodo() {
        if (this.datePickerDesde.getDate() == null) {
            return false;
        }
        if (this.datePickerHasta.getDate() == null) {
            return false;
        }
        return true;
    }

    private Boolean nombreEsValido() {

        String nombre = this.txtNombre.getText();
        if (ValidacionDatos.isEmpty(nombre)) {
            return false;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(nombre)) {
            return false;
        }
        return true;
    }

    private void habilitar_DesHabilitarPeriodo() {
        if (this.chPeriodo.isSelected()) {
            this.datePickerDesde.setEnabled(true);
            this.datePickerHasta.setEnabled(true);
        } else {
            this.datePickerDesde.setDate(null);
            this.datePickerHasta.setDate(null);
            this.datePickerDesde.setEnabled(false);
            this.datePickerHasta.setEnabled(false);

        }
    }

    private void habilitarNombre() {
        if (this.chNombre.isSelected()) {
            this.txtNombre.setEnabled(true);
        } else {
            this.txtNombre.setText("");
            this.txtNombre.setEnabled(false);
        }
    }

    private HashMap<String, Date> consultarPeriodos() {
        if (!this.hayFechasEnPeriodo()) {
            return null;
        }
        HashMap<String, Date> periodos = new HashMap<>();
        Date desde = Date.from(this.datePickerDesde.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date hasta = Date.from(this.datePickerHasta.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        periodos.put("desde", desde);
        periodos.put("hasta", hasta);
        return periodos;
    }

    private String consultarNombre() {
        if (txtNombre.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "El campo nombre esta vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (ValidacionDatos.exceedsLimit(txtNombre.getText(), 100)) {
            JOptionPane.showMessageDialog(this, "El nombre excede el limitede caracteres(100)", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (ValidacionDatos.contieneCaracteresEspeciales(txtNombre.getText())) {
            JOptionPane.showMessageDialog(this, "Error el nombre contiene caracteres especiales o espacio al final", "Error", HEIGHT);
            return null;
        }
        return txtNombre.getText();
    }

    private List<Reporte> consultarLicencias() {
        FiltroReporteTramites filtro = this.creaFiltroReporte();
        if (filtro == null) {
            return null;
        }
        List<Licencia> lista = licenciaDAO.consultaReporteLicencia(filtro);
        List<Reporte> reportes = new ArrayList<>();
        for (Licencia i : lista) {
            Reporte reporte = new Reporte(i.getId(), i.getFechaEmision(), i.getCosto(), i.getPersona().getNombreCompleto());
            reportes.add(reporte);
        }
        return reportes;
    }
    
        private List<Reporte> consultarPlacas() {
        FiltroReporteTramites filtro = this.creaFiltroReporte();
        if (filtro == null) {
            return null;
        }
        List<Placa> lista = placaDAO.consultaReporteLicencia(filtro);
        List<Reporte> reportes = new ArrayList<>();
        for (Placa i : lista) {
            Reporte reporte = new Reporte(i.getId(), i.getFechaEmision(), i.getCosto(), i.getPersona().getNombreCompleto());
            reportes.add(reporte);
        }
        return reportes;
    }

    private FiltroReporteTramites creaFiltroReporte() {
        FiltroReporteTramites filtro = new FiltroReporteTramites();
        HashMap<String, Date> periodo = consultarPeriodos();
        if (!chPeriodo.isSelected() && !chNombre.isSelected()) {
            JOptionPane.showMessageDialog(this, "Favor de Seleccionar algun tipo de filtro", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (chPeriodo.isSelected()) {
            if (periodo == null) {
                return null;
            }
            filtro.setDesde(periodo.get("desde"));
            filtro.setHasta(periodo.get("hasta"));
        }
        if (chNombre.isSelected()) {
            if (!this.nombreEsValido()) {
                return null;
            }
            filtro.setNombre(this.txtNombre.getText());
        }
        return filtro;
    }

    private String consultarRutaExportacion() {
        JFileChooser fc = new JFileChooser();
        int seleccion = fc.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile().getAbsolutePath();
        } else {
            return null;
        }
    }

    private void vistaPreviaReporteLicencia() {
        List<Reporte> lista = consultarLicencias();
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Datos invalidos, revisa que todo este correcto, nombre sin caracteres especiales y sin espacios al final o las dos fechas puestas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JasperPrint jasperPrint = this.reporteDAO.generarReporteLicencia(lista);
            reporteDAO.vistaPreviaReporte(jasperPrint);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
        private void vistaPreviaReportePlaca() {
        List<Reporte> lista = consultarPlacas();
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Datos invalidos, revisa que todo este correcto, nombre sin caracteres especiales y sin espacios al final o las dos fechas puestas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JasperPrint jasperPrint = this.reporteDAO.generarReportePlaca(lista);
            reporteDAO.vistaPreviaReporte(jasperPrint);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportarReporteLicencia() {
        List<Reporte> lista = consultarLicencias();
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Datos invalidos, revisa que todo este correcto, nombre sin caracteres especiales y sin espacios al final o las dos fechas puestas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            JasperPrint jasperPrint = this.reporteDAO.generarReporteLicencia(lista);
            String path = this.consultarRutaExportacion();
            if (path==null || path.isBlank()) {
                return;
            }
            path+=".pdf";
            reporteDAO.exportarReporte(jasperPrint, path);
            JOptionPane.showMessageDialog(this, "Tu reporte se exporto con exito en la siguiente ruta: "+path, "Exportacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
    
       private void exportarReportePlaca() {
        List<Reporte> lista = consultarPlacas();
        if (lista == null) {
            JOptionPane.showMessageDialog(this, "Datos invalidos, revisa que todo este correcto, nombre sin caracteres especiales y sin espacios al final o las dos fechas puestas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {

            JasperPrint jasperPrint = this.reporteDAO.generarReportePlaca(lista);
            String path = this.consultarRutaExportacion();
            if (path==null||path.isBlank()) {
                return;
            }
            path+=".pdf";
            reporteDAO.exportarReporte(jasperPrint, path);
            JOptionPane.showMessageDialog(this, "Tu reporte se exporto con exito en la siguiente ruta: "+path, "Exportacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (PersistenciaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }
       
       private void cerrarVentana(){
       this.dispose();
       }
       
       private void irMenu(){
       Menu menu = new Menu();
       menu.setVisible(true);
       }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jTextField1 = new javax.swing.JTextField();
        datePickerHasta = new com.github.lgooddatepicker.components.DatePicker();
        datePickerDesde = new com.github.lgooddatepicker.components.DatePicker();
        lblDesde = new javax.swing.JLabel();
        lblHasta1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        chPeriodo = new javax.swing.JCheckBox();
        chNombre = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Reporte de Tramites");

        datePickerHasta.setEnabled(false);

        datePickerDesde.setEnabled(false);

        lblDesde.setText("Desde :");
        lblDesde.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        lblHasta1.setText("Hasta :");
        lblHasta1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        txtNombre.setEnabled(false);
        txtNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        chPeriodo.setText("Periodo");
        chPeriodo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        chPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chPeriodoActionPerformed(evt);
            }
        });

        chNombre.setText("Nombre :");
        chNombre.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        chNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chNombreActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar Reporte de Placas");
        jButton2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Exportar Reporte de Licencias");
        jButton3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Vista Previa");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Vista Previa");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(742, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHasta1)
                            .addComponent(datePickerHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDesde)
                            .addComponent(datePickerDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chPeriodo)
                            .addComponent(chNombre))
                        .addGap(193, 193, 193)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(chPeriodo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDesde)
                        .addGap(18, 18, 18)
                        .addComponent(datePickerDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHasta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datePickerHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton4)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(chNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chPeriodoActionPerformed
        // TODO add your handling code here:
        habilitar_DesHabilitarPeriodo();
    }//GEN-LAST:event_chPeriodoActionPerformed

    private void chNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chNombreActionPerformed
        // TODO add your handling code here:
        habilitarNombre();
    }//GEN-LAST:event_chNombreActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        vistaPreviaReporteLicencia();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.exportarReporteLicencia();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        vistaPreviaReportePlaca();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.exportarReportePlaca();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        irMenu();
        cerrarVentana();
        
    }//GEN-LAST:event_jButton1ActionPerformed
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ModuloHistoriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModuloHistoriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModuloHistoriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModuloHistoriales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModuloReporte().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chNombre;
    private javax.swing.JCheckBox chPeriodo;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePickerDesde;
    private com.github.lgooddatepicker.components.DatePicker datePickerHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblDesde;
    private javax.swing.JLabel lblHasta1;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
