/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.LicenciaDAO;
import com.mycompany.daos.PagoDAO;
import com.mycompany.daos.PlacaDAO;
import com.mycompany.daos.VehiculoDAO;
import com.mycompany.dominio.Estado;
import com.mycompany.dominio.Licencia;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Placa;
import com.mycompany.dominio.Tramite;
import com.mycompany.dominio.Vehiculo;
import com.mycompany.dominio.Vigencia;
import com.mycompany.utils.ValidacionDatos;
import java.text.SimpleDateFormat;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author edemb
 */
public class ModuloPlacaAutoUsado extends javax.swing.JFrame {

    EntityManager entityManager;
    VehiculoDAO vehiculoDAO;
    PlacaDAO placaDAO;
    Persona persona;
    LicenciaDAO licenciaDAO;
    PagoDAO pagoDAO;

    /**
     * Creates new form ModuloPlacaAutoUsado
     */
    public ModuloPlacaAutoUsado(Persona persona, EntityManager entityManager) {
        initComponents();
        this.entityManager = entityManager;
        this.persona = persona;
        setLabelPersona();
        vehiculoDAO = new VehiculoDAO(entityManager);
        placaDAO = new PlacaDAO(entityManager);
        licenciaDAO = new LicenciaDAO(entityManager);
        pagoDAO = new PagoDAO(entityManager);
    }

    private void setLabelPersona() {
        try {
            SimpleDateFormat formateado = new SimpleDateFormat("dd/MM/yyyy");
            lblNombre.setText(persona.getNombreCompleto());
            lblFechaNacimiento.setText(formateado.format(persona.getFechaNacimiento().getTime()));
            lblRFC.setText(persona.getRfc());
            lblTelefono.setText(persona.getTelefono());
            lblSexo.setText(persona.getSexo().toString());
            lblDiscapacitado.setText(persona.getDiscapasitado().toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cerrarVentana() {
        this.dispose();
    }

    private void irModuloPlaca() {
        ModuloPlaca moduloPlaca = new ModuloPlaca(this.entityManager);
        moduloPlaca.setPersona(this.persona);
        moduloPlaca.setVisible(true);
    }

    private void irMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    private Vehiculo consultarVehiculoUsado() {
        if (!this.formatoValido()) {
            return null;
        }
        Vehiculo vehiculo = vehiculoDAO.consultaVehiculoPorSerie(this.txtSerie.getText());
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Serie no encontrada, favor de crear la placa en Vehiculo Nuevo", "Alerta", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return vehiculo;
    }

    private Boolean tieneLicenciaVigente() {
        Licencia licencia = licenciaDAO.consultarLicenciaActiva(this.persona);
        if (licencia == null ||licencia.getEstado() == Estado.DESACTIVA) {
            JOptionPane.showMessageDialog(this, "La persona que quiere generar la placa no tienen vigente su Licencia", "Licencia Vencida", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private Placa generarPlacaVehiculoUsado() {
        if (!tieneLicenciaVigente()) {
            return null;
        }
        Vehiculo vehiculo = this.consultarVehiculoUsado();
        if (vehiculo == null) {
            return null;
        }
        Placa placa = this.placaDAO.generarPlacaVehiculoUsado(persona, vehiculo);
        
        if (placa==null) {
            JOptionPane.showMessageDialog(this, "No se pudo generar la Placa", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        generarPago(placa);
        
        return placa;
    }
    
    private void generarPago(Tramite tramite){
    pagoDAO.generarPago(tramite);
    }

    private void mensajePlacaGeneradaExitosamente(Placa placa) {
        JOptionPane.showMessageDialog(this, "Se genero el numero de placa: " + placa.getNumero() + " para el vehiculo con la serie: " + this.txtSerie.getText(), "Placa generada Exitosamente", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Boolean formatoValido() {
        String errores = "Formato invalido en:";
        int i = 0;
        if (!ValidacionDatos.serieEsValida(this.txtSerie.getText())) {
            errores += " Serie";
            i++;
        }
        if (i != 0) {
            JOptionPane.showMessageDialog(this, errores, "Error Formato Invalido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JToggleButton();
        btnGenerarPlaca = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblRFC = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblDiscapacitado = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generar Placa a Vehiculo Usado");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Ingresa la Serie del Vehiculo");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Serie :");

        txtSerie.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        btnGenerarPlaca.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnGenerarPlaca.setText("Generar Placa");
        btnGenerarPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPlacaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Nombre:");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombre.setText("...");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento:");

        lblFechaNacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFechaNacimiento.setText("...");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Telefono:");

        lblTelefono.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTelefono.setText("...");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Sexo:");

        lblSexo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSexo.setText("...");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("RFC:");

        lblRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRFC.setText("...");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Persona :");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("Discapacitado:");

        lblDiscapacitado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDiscapacitado.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 688, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(60, 60, 60))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblRFC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSexo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(148, 148, 148))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(lblDiscapacitado, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFechaNacimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGenerarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lblTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSexo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRFC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDiscapacitado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        irModuloPlaca();
        cerrarVentana();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGenerarPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPlacaActionPerformed
        // TODO add your handling code here:
        Placa placa = generarPlacaVehiculoUsado();
        if (placa == null) {
            return;
        } else {
            mensajePlacaGeneradaExitosamente(placa);
            irMenu();
            cerrarVentana();
        }
    }//GEN-LAST:event_btnGenerarPlacaActionPerformed

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
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoUsado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoUsado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoUsado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModuloPlacaAutoUsado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ModuloPlacaAutoUsado().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarPlaca;
    private javax.swing.JToggleButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblDiscapacitado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
