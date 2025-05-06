package vista;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

public class Ventana extends javax.swing.JFrame {

    public DefaultTableModel modeloTabla;

    public Ventana() {
        initComponents();//Inicializar componentes generados automáticamente
        inicializarModeloTabla();
        configurarAtajosTeclado();
        configurarMenuMouse();
        

    }

    private void inicializarModeloTabla() {
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "Teléfono", "Correo", "Categoría", "Favorito"}, 0);
        tablaContactos.setModel(modeloTabla);

    }

    //Configurar Teclado
    private void configurarAtajosTeclado() {
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK), "nuevoContacto"
        );
        getRootPane().getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK), "exportarContactos"
        );

        getRootPane().getActionMap().put("nuevoContacto", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Atajo Ctrl+N detectado: Agregar nuevo contacto.");
                // Llama a la lógica correspondiente para agregar un contacto
            }
        });

        getRootPane().getActionMap().put("exportarContactos", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Atajo Ctrl+E detectado: Exportar contactos.");
                // Llama a la lógica correspondiente para exportar contactos
            }
        });
    }

    //Configuracion Mause
    private void configurarMenuMouse() {
        tablaContactos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    crearMenuContextual().show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    crearMenuContextual().show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    public JPopupMenu crearMenuContextual() {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem editar = new JMenuItem("Editar Contacto");
        JMenuItem eliminar = new JMenuItem("Eliminar Contacto");

        // Configura las acciones de las opciones del menú
        editar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Editar contacto no implementado aún"));
        eliminar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Eliminar contacto no implementado aún"));

        menu.add(editar);
        menu.add(eliminar);

        return menu;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuContextual = new javax.swing.JPopupMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        contentInicio = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbl_etiqueta1 = new javax.swing.JLabel();
        lbl_etiqueta2 = new javax.swing.JLabel();
        lbl_etiqueta3 = new javax.swing.JLabel();
        btn_eliminar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        txt_email = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_nombres = new javax.swing.JTextField();
        cmb_categoria = new javax.swing.JComboBox<>();
        chb_favorito = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        barraProgreso = new javax.swing.JProgressBar();
        scrLista = new javax.swing.JScrollPane();
        list_contactos = new javax.swing.JList<>();
        panelBuscar = new javax.swing.JPanel();
        panelBorder = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tablaContactos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbCategoriaFiltro = new javax.swing.JComboBox<>();
        btn_exportar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(140, 192, 255));
        jTabbedPane1.setForeground(new java.awt.Color(27, 63, 91));

        contentInicio.setBackground(new java.awt.Color(140, 192, 255));
        contentInicio.setForeground(new java.awt.Color(140, 192, 255));

        jPanel2.setBackground(new java.awt.Color(140, 192, 255));
        jPanel2.setForeground(new java.awt.Color(140, 192, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(140, 192, 255));

        lbl_etiqueta1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vista/Bundle"); // NOI18N
        lbl_etiqueta1.setText(bundle.getString("Ventana.lbl_etiqueta1.text")); // NOI18N

        lbl_etiqueta2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lbl_etiqueta2.setText(bundle.getString("Ventana.lbl_etiqueta2.text")); // NOI18N

        lbl_etiqueta3.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        lbl_etiqueta3.setText(bundle.getString("Ventana.lbl_etiqueta3.text")); // NOI18N

        btn_eliminar.setBackground(new java.awt.Color(46, 109, 184));
        btn_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/deleteContacto.png"))); // NOI18N
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(46, 109, 184));
        btn_modificar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editContacto.png"))); // NOI18N

        btn_add.setBackground(new java.awt.Color(46, 109, 184));
        btn_add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/addContacto.png"))); // NOI18N
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });

        txt_nombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombresActionPerformed(evt);
            }
        });

        cmb_categoria.setBackground(new java.awt.Color(46, 109, 184));
        cmb_categoria.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        cmb_categoria.setForeground(new java.awt.Color(255, 255, 255));
        cmb_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elija una categoria", "Familia", "Amigos", "Trabajo" }));
        cmb_categoria.setToolTipText(bundle.getString("Ventana.cmb_categoria.toolTipText")); // NOI18N
        cmb_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_categoriaActionPerformed(evt);
            }
        });

        chb_favorito.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        chb_favorito.setText(bundle.getString("Ventana.chb_favorito.text")); // NOI18N
        chb_favorito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/favoriteContacto.png"))); // NOI18N
        chb_favorito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chb_favoritoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_etiqueta1)
                            .addComponent(lbl_etiqueta2)
                            .addComponent(lbl_etiqueta3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chb_favorito, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_etiqueta1)
                    .addComponent(txt_nombres, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_etiqueta2)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_modificar)
                            .addComponent(btn_add)
                            .addComponent(btn_eliminar))))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_etiqueta3)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chb_favorito))
                .addGap(24, 24, 24))
        );

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(140, 192, 255));
        jPanel7.setForeground(new java.awt.Color(140, 192, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(140, 192, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(barraProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        scrLista.setViewportView(list_contactos);

        jPanel7.add(scrLista, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout contentInicioLayout = new javax.swing.GroupLayout(contentInicio);
        contentInicio.setLayout(contentInicioLayout);
        contentInicioLayout.setHorizontalGroup(
            contentInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentInicioLayout.setVerticalGroup(
            contentInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentInicioLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(bundle.getString("Ventana.contentInicio.TabConstraints.tabTitle"), contentInicio); // NOI18N

        panelBorder.setLayout(new java.awt.BorderLayout());

        scrollTabla.setPreferredSize(new java.awt.Dimension(600, 402));

        tablaContactos.setBackground(new java.awt.Color(59, 123, 181));
        tablaContactos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Teléfono", "Correo", "Categoría", "Favorito"
            }
        ));
        scrollTabla.setViewportView(tablaContactos);

        panelBorder.add(scrollTabla, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(140, 192, 255));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jLabel1.setText(bundle.getString("Ventana.jLabel1.text")); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        jLabel2.setText(bundle.getString("Ventana.jLabel2.text")); // NOI18N

        cmbCategoriaFiltro.setBackground(new java.awt.Color(46, 109, 184));
        cmbCategoriaFiltro.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        cmbCategoriaFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Familia", "Amigos", "Trabajo" }));

        btn_exportar.setBackground(new java.awt.Color(46, 109, 184));
        btn_exportar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exportContacto.png"))); // NOI18N
        btn_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCategoriaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btn_exportar)
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_exportar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(cmbCategoriaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        panelBorder.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout panelBuscarLayout = new javax.swing.GroupLayout(panelBuscar);
        panelBuscar.setLayout(panelBuscarLayout);
        panelBuscarLayout.setHorizontalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBuscarLayout.setVerticalGroup(
            panelBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("Ventana.panelBuscar.TabConstraints.tabTitle"), panelBuscar); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 798, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(bundle.getString("Ventana.jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed

    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void chb_favoritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chb_favoritoActionPerformed

    }//GEN-LAST:event_chb_favoritoActionPerformed

    private void cmb_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_categoriaActionPerformed

    }//GEN-LAST:event_cmb_categoriaActionPerformed

    private void txt_nombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombresActionPerformed

    }//GEN-LAST:event_txt_nombresActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed

    }//GEN-LAST:event_txt_emailActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed

    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btn_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarActionPerformed

    }//GEN-LAST:event_btn_exportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar barraProgreso;
    public javax.swing.JButton btn_add;
    public javax.swing.JButton btn_eliminar;
    public javax.swing.JButton btn_exportar;
    public javax.swing.JButton btn_modificar;
    public javax.swing.JCheckBox chb_favorito;
    public javax.swing.JComboBox<String> cmbCategoriaFiltro;
    public javax.swing.JComboBox<String> cmb_categoria;
    private javax.swing.JPanel contentInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lbl_etiqueta1;
    private javax.swing.JLabel lbl_etiqueta2;
    private javax.swing.JLabel lbl_etiqueta3;
    public javax.swing.JList<String> list_contactos;
    public javax.swing.JPopupMenu menuContextual;
    private javax.swing.JPanel panelBorder;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JScrollPane scrLista;
    public javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tablaContactos;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txt_email;
    public javax.swing.JTextField txt_nombres;
    public javax.swing.JTextField txt_telefono;
    // End of variables declaration//GEN-END:variables

}
