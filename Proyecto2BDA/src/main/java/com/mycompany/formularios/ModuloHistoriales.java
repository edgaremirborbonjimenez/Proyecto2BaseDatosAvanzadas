/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.formularios;

import com.mycompany.daos.PersonaDAO;
import com.mycompany.dominio.Discapacitado;
import com.mycompany.dominio.FiltroHistorial;
import com.mycompany.dominio.Persona;
import com.mycompany.dominio.Sexo;
import com.mycompany.excepciones.PersistenciaException;
import com.mycompany.interfaces.IPersonaDAO;
import com.mycompany.utils.ConfiguracionDePaginado;
import com.mycompany.utils.ValidacionDatos;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edemb
 */
public class ModuloHistoriales extends javax.swing.JFrame {

    private static final Logger LOG = Logger.getLogger(ModuloHistoriales.class.getName());
    private ConfiguracionDePaginado configPaginado;
    private FiltroHistorial parametros;
    private final IPersonaDAO personasDAO = null;
    
    /**
     * Creates new form ModuloHistoriales
     */
    public ModuloHistoriales() {
        this.configPaginado = new ConfiguracionDePaginado(0, 10);
        initComponents();
        this.deshabilitarBotonesTramites();     
    }

    private void cerrarVentanaActual() {
        this.dispose();
    }

    private void irMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    private void irHistorialLicencia() {

        HistorialLicencias hisLic = new HistorialLicencias();
        hisLic.setVisible(true);

    }

    private void irHistorialPlaca() {

        HistorialPlacas hisPla = new HistorialPlacas();
        hisPla.setVisible(true);
    }
    
    private String extraerDatosFormulario(){
        String rfc = this.txtRFC.getText();
        String nombre = this.txtNombre.getText();
        return rfc;
    }
    
    private FiltroHistorial filtroRFC(){
        FiltroHistorial filtro = new FiltroHistorial();
        filtro.setRFC(this.extraerDatosFormulario());
        return filtro;
    }

    private void cargarTablaPersonas(){
        validacionCamposFormulario();
        try {
            SimpleDateFormat formateado = new SimpleDateFormat("dd/MM/yyyy");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Proyecto2BDA");
            EntityManager entity = emf.createEntityManager();
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tablePersonas.getModel();    
            IPersonaDAO personaDAO = new PersonaDAO(entity);
            List<Persona> listaPersona = personaDAO.buscarPersonas(filtroRFC(), configPaginado);
            modeloTabla.setRowCount(0);
            for (Persona p : listaPersona) {
                    Object[] fila = {
                    p.getNombreCompleto(),
                    formateado.format(p.getFechaNacimiento().getTime()),                  
                    p.getTelefono(),
                    p.getSexo(),
                    p.getRfc(),
                    p.getDiscapasitado()
                };
                modeloTabla.addRow(fila);
            }
        } catch (PersistenceException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
    }
    public void avanzarPagina(){
        this.configPaginado.avanzarPagina();
        this.cargarTablaPersonas();
        this.deshabilitarBotonesTramites();
    }

    public void retrocederPagina(){
        this.configPaginado.retrocederPagina();
        this.cargarTablaPersonas();
        this.deshabilitarBotonesTramites();
    }
    
    public void deshabilitarBotonesTramites(){
        this.btnHistorialPlaca.setEnabled(false);
        this.btnHistorialLicencia.setEnabled(false);
    }
    
    public void habilitarBotonesTramites(){
        this.btnHistorialPlaca.setEnabled(true);
        this.btnHistorialLicencia.setEnabled(true);
    }
    
    public void checkRFC(){
        if (chkRFC.isSelected()) {
            txtRFC.setEditable(true);
        } else {
            txtRFC.setText("");
            txtRFC.setEditable(false);
        }
    }
    
    public void checkNombre(){
        if (chkNombre.isSelected()) {
            txtNombre.setEditable(true);
        } else {
            txtNombre.setText("");

            txtNombre.setEditable(false);
        }
    }
    
    public void checkAnioNacimiento(){
        if (chkFechaNacimiento.isSelected()) {
            cmbAnioNacimiento.setEditable(true);
        }else{
            cmbAnioNacimiento.setEditable(false);
       }
    }
    
    public void validacionCamposFormulario() {
        String nombre = txtNombre.getText();
        if (chkNombre.isSelected()) {
            if (ValidacionDatos.isEmpty(nombre)) {
                mostrarErroresValidacionNombreVacio();
            }
        }

        if (chkNombre.isSelected()) {
            if (ValidacionDatos.exceedsLimit(nombre, 60)) {
                mostrarErroresValidacionNombreExcedeLimite();
            }
        }

        String rfc = txtRFC.getText();
        if (chkRFC.isSelected()) {
            if (ValidacionDatos.isEmpty(rfc)) {
                mostrarErroresValidacionRFCVacio();
            }
        }

        if (chkRFC.isSelected()) {
            if (ValidacionDatos.exceedsLimit(rfc, 10)) {
                mostrarErroresValidacionRFCExcedeLimite();
            }
        }
    }
    
    private void mostrarErroresValidacionNombreVacio(){
        JOptionPane.showMessageDialog(null, "El Nombre esta vacio", "Campo Nombre Invalido", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarErroresValidacionNombreExcedeLimite(){
        JOptionPane.showMessageDialog(null, "El Nombre supera el limite de caracteres permitidos", "Campo Nombre Invalido", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarErroresValidacionRFCVacio(){
        JOptionPane.showMessageDialog(null, "El RFC esta vacio", "Campo RFC Invalido", JOptionPane.ERROR_MESSAGE);
    }
    
    private void mostrarErroresValidacionRFCExcedeLimite(){
        JOptionPane.showMessageDialog(null, "El RFC supera el limite de caracteres permitidos", "Campo RFC Invalido", JOptionPane.ERROR_MESSAGE);
    }
    
    public void seleccionDePersona(){
        int filaseleccionada;
        try{
            //Guardamos en un entero la fila seleccionada.
            filaseleccionada = tablePersonas.getSelectedRow();
            if (filaseleccionada == -1){
                JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.");
            } else {         
                //Habilitamos los botones de tramites
                habilitarBotonesTramites();
                
                String nombre = (String)tablePersonas.getValueAt(filaseleccionada, 0);
                String rfc = (String)tablePersonas.getValueAt(filaseleccionada, 1);
                String fechaNacimiento = (String)tablePersonas.getValueAt(filaseleccionada, 2);
                Sexo sexo = (Sexo)tablePersonas.getValueAt(filaseleccionada, 3);
                String telefono = (String)tablePersonas.getValueAt(filaseleccionada,4);
                Discapacitado discapacidad = (Discapacitado)tablePersonas.getValueAt(filaseleccionada, 5);
                                              
                lblNombre.setText(nombre);
                lblRFC.setText(rfc);
                lblFechaNacimiento.setText(fechaNacimiento);
                lblSexo.setText(sexo.toString());
                lblTelefono.setText(telefono);
                lblDiscapacitado.setText(discapacidad.toString());       

            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", "Error En la Operacion." ,JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbFiltro = new javax.swing.ButtonGroup();
        btnHistorialLicencia = new javax.swing.JButton();
        btnHistorialPlaca = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        txtRFC = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePersonas = new javax.swing.JTable();
        chkRFC = new javax.swing.JCheckBox();
        chkNombre = new javax.swing.JCheckBox();
        chkFechaNacimiento = new javax.swing.JCheckBox();
        btnBuscar = new javax.swing.JButton();
        cmbAnioNacimiento = new javax.swing.JComboBox<>();
        lblFechaNacimiento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblDiscapacitado = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblRFC = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSeleccionarPersona = new javax.swing.JButton();
        btnRetroceder = new javax.swing.JButton();
        btnAvanzar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Seleccion de Cliente");

        btnHistorialLicencia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnHistorialLicencia.setText("Historial de Licencias");
        btnHistorialLicencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorialLicenciaMouseClicked(evt);
            }
        });
        btnHistorialLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialLicenciaActionPerformed(evt);
            }
        });

        btnHistorialPlaca.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnHistorialPlaca.setText("Historial de Placas");
        btnHistorialPlaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHistorialPlacaMouseClicked(evt);
            }
        });
        btnHistorialPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialPlacaActionPerformed(evt);
            }
        });

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegresarMouseClicked(evt);
            }
        });
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        txtRFC.setEditable(false);
        txtRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        txtNombre.setEditable(false);
        txtNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablePersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Fecha Nacimiento", "Telefono", "Sexo", "RFC", "Discapacidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablePersonas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        chkRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chkRFC.setText("RFC:");
        chkRFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRFCActionPerformed(evt);
            }
        });

        chkNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chkNombre.setText("Nombre");
        chkNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNombreActionPerformed(evt);
            }
        });

        chkFechaNacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        chkFechaNacimiento.setText("Fecha de Nacimiento");
        chkFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkFechaNacimientoActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        cmbAnioNacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cmbAnioNacimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019" }));

        lblFechaNacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblFechaNacimiento.setText("...");

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("Telefono");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setText("Discapacitado");

        lblDiscapacitado.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDiscapacitado.setText("...");

        lblTelefono.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTelefono.setText("...");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setText("Sexo");

        lblSexo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblSexo.setText("...");

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("RFC");

        lblRFC.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblRFC.setText("...");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Persona :");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblNombre.setText("...");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento");

        btnSeleccionarPersona.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnSeleccionarPersona.setText("Seleccionar Persona");
        btnSeleccionarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPersonaActionPerformed(evt);
            }
        });

        btnRetroceder.setText("<- Retroceder");
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });

        btnAvanzar.setText("Avanzar ->");
        btnAvanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkFechaNacimiento)
                            .addComponent(cmbAnioNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnRetroceder)
                                .addGap(114, 114, 114)
                                .addComponent(btnAvanzar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnHistorialLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(194, 194, 194)
                                .addComponent(btnHistorialPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSeleccionarPersona)
                        .addGap(359, 359, 359)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(lblDiscapacitado, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnRetroceder)
                            .addComponent(btnAvanzar))
                        .addGap(18, 18, 18)
                        .addComponent(btnSeleccionarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHistorialPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHistorialLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(chkRFC)
                                .addGap(6, 6, 6)
                                .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(chkNombre)
                                .addGap(12, 12, 12)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(chkFechaNacimiento)
                                .addGap(12, 12, 12)
                                .addComponent(cmbAnioNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(11, 11, 11)
                                .addComponent(lblNombre)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6)
                                .addGap(12, 12, 12)
                                .addComponent(lblFechaNacimiento)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(16, 16, 16)
                                .addComponent(lblTelefono)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(lblSexo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(lblRFC)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(6, 6, 6)
                        .addComponent(lblDiscapacitado)
                        .addGap(36, 36, 36))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        irMenu();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarMouseClicked
        // TODO add your handling code here:
        cerrarVentanaActual();
    }//GEN-LAST:event_btnRegresarMouseClicked

    private void chkRFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRFCActionPerformed
        // TODO add your handling code here:
        this.checkRFC();
    }//GEN-LAST:event_chkRFCActionPerformed

    private void chkNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNombreActionPerformed
        // TODO add your handling code here:
        this.checkNombre();
    }//GEN-LAST:event_chkNombreActionPerformed
  
    private void btnHistorialLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialLicenciaActionPerformed
        // TODO add your handling code here:
        irHistorialLicencia();
    }//GEN-LAST:event_btnHistorialLicenciaActionPerformed

    private void btnHistorialLicenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialLicenciaMouseClicked
        // TODO add your handling code here:
        cerrarVentanaActual();
    }//GEN-LAST:event_btnHistorialLicenciaMouseClicked

    private void btnHistorialPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialPlacaActionPerformed
        // TODO add your handling code here:
        irHistorialPlaca();
    }//GEN-LAST:event_btnHistorialPlacaActionPerformed

    private void btnHistorialPlacaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHistorialPlacaMouseClicked
        // TODO add your handling code here:
        cerrarVentanaActual();
    }//GEN-LAST:event_btnHistorialPlacaMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.cargarTablaPersonas();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSeleccionarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarPersonaActionPerformed
        // TODO add your handling code here:
        this.seleccionDePersona(); 
    }//GEN-LAST:event_btnSeleccionarPersonaActionPerformed

    private void btnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederActionPerformed
        // TODO add your handling code here:
        this.retrocederPagina();
    }//GEN-LAST:event_btnRetrocederActionPerformed

    private void btnAvanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarActionPerformed
        // TODO add your handling code here:
        this.avanzarPagina();
    }//GEN-LAST:event_btnAvanzarActionPerformed

    private void chkFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkFechaNacimientoActionPerformed
        // TODO add your handling code here:
        this.checkAnioNacimiento();
    }//GEN-LAST:event_chkFechaNacimientoActionPerformed

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
//                new ModuloHistoriales().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAvanzar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnHistorialLicencia;
    private javax.swing.JButton btnHistorialPlaca;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JButton btnSeleccionarPersona;
    private javax.swing.JCheckBox chkFechaNacimiento;
    private javax.swing.JCheckBox chkNombre;
    private javax.swing.JCheckBox chkRFC;
    private javax.swing.JComboBox<String> cmbAnioNacimiento;
    private javax.swing.ButtonGroup cmbFiltro;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiscapacitado;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblRFC;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tablePersonas;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    // End of variables declaration//GEN-END:variables
}
