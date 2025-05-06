package controlador;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.Persona;
import modelo.PersonaDAO;
import vista.Ventana;

public class LogicaPrograma {

    private Ventana vista;
    private PersonaDAO personaDAO = new PersonaDAO("c:/gestionContactos/datosContactos.csv");
    private ArrayList<Persona> contactos;
    private TableRowSorter<DefaultTableModel> sorter;
    private ExecutorService executor;
    private ReentrantLock lock;

    public LogicaPrograma(Ventana vista, PersonaDAO personaDAO, ArrayList<Persona> contactos) {
        this.vista = vista;
        this.personaDAO = personaDAO;
        this.contactos = contactos;
        this.executor = Executors.newFixedThreadPool(2);
        this.lock = new ReentrantLock();

        // Inicializar la barra de progreso y cargar contactos automáticamente
        new CargarContactosWorker(vista, personaDAO, contactos).execute();

        initController();//Inicializar la logica del controlador.

        try {
            contactos = personaDAO.cargarContactos();
            cargarContactosEnTabla(contactos);// Llenar la tabla con los contactos cargados

        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista, "Error al cargar contactos");
        }

    }

    //guardar contacto en segundo plano con validacion
     public void guardarPersona(Persona persona) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                synchronized (personaDAO) {
                    if (!personaDAO.existePersona(persona)) {
                        personaDAO.agregarPersona(persona);
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            @Override
            protected void done() {
                try {
                    boolean guardado = get();
                    if (guardado) {
                        vista.mostrarNotificacion("Contacto guardado con éxito.");
                        contactos.add(persona);//Actualiza la lista interna
                        cargarContactosEnTabla(contactos);//Refresca la tabla
                        actualizarLista();
                    } else {
                        vista.mostrarNotificacion("El contacto ya existe.");
                    }
                } catch (Exception e) {
                    vista.mostrarNotificacion("Error al guardar: " + e.getMessage());
                }
            }
        };
        worker.execute();
    }
    
     //Buscar contactos en segundo plano
//     public void buscarPersonas(String termino){
//       SwingWorker<List<Persona>, Void> worker = new SwingWorker<>() {
//            @Override
//            protected List<Persona> doInBackground() {
//                return personaDAO.buscarPersonas(termino);
//            }
//
//            @Override
//            protected void done() {
//                try {
//                    List<Persona> resultados = get();
//                    vista.mostrarResultadosBusqueda(resultados);
//                } catch (Exception e) {
//                    vista.mostrarNotificacion("Error en la búsqueda: " + e.getMessage());
//                }
//            }
//        };
//        worker.execute();  
//     }
    
    //Exportar contactos en segundo plano (seguro)
    public void exportarContactos(String rutaArchivo){
        executor.submit(() -> {
            lock.lock();
            try (FileWriter writer = new FileWriter(rutaArchivo)) {
                List<Persona> lista = personaDAO.getTodasPersonas();
                for (Persona p : lista) {
                    writer.write(p.toCSV() + "\n");
                }
                SwingUtilities.invokeLater(() ->
                        vista.mostrarNotificacion("Exportación completada."));
            } catch (IOException e) {
                SwingUtilities.invokeLater(() ->
                        vista.mostrarNotificacion("Error al exportar: " + e.getMessage()));
            } finally {
                lock.unlock();
            }
        });
    }
    
     
    private void initController() {
        //Configurar eventos de botones
        vista.btn_exportar.addActionListener(e -> exportarContactos());
        vista.btn_add.addActionListener(e -> agregarContacto());
        vista.btn_eliminar.addActionListener(e -> eliminarContacto());

        //configurar eventos de tabla
        vista.tablaContactos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) { // Detecta clic derecho
                    vista.menuContextual.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    vista.menuContextual.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        //configura atajos de teclado
        agregarAtajosDeTeclado();

        //configura filtro de busqueda
        vista.txtBuscar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e
            ) {
                aplicarFiltro();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e
            ) {
                aplicarFiltro();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e
            ) {
                aplicarFiltro();
            }
        });

        // Configura filtro por categoría
        vista.cmbCategoriaFiltro.addActionListener(e -> aplicarFiltro());

        
        //Configurar el sorter (Ordenamiento y filtro de la Tabla)
        sorter = new TableRowSorter<>(vista.modeloTabla);
        vista.tablaContactos.setRowSorter(sorter);

        vista.cmb_categoria.addActionListener(e
                -> {
            String categoriaSeleccionada = vista.cmb_categoria.getSelectedItem().toString();
            System.out.println("Categoría seleccionada: " + categoriaSeleccionada);
        }
        );

    }

    private void cargarContactosEnTabla(ArrayList<Persona> contactos) {
        DefaultTableModel modelo = vista.modeloTabla;

        //Limpiar el modelo antes de llenarlo
        modelo.setRowCount(0);
        //Agregar cada contacto al modeo de la tabla
        for (Persona contacto : contactos) {
            modelo.addRow(new Object[]{
                contacto.getNombre(),
                contacto.getTelefono(),
                contacto.getCorreo(),
                contacto.getCategoria(),
                contacto.isFavorito() ? "Sí" : "No"
            });
        }
    }

    private void limpiarTablaContactos(){
        vista.modeloTabla.setRowCount(0);//elimina todas las filas de la tabla
    }
    
    
    private void aplicarFiltro() {
        String textoBusqueda = vista.txtBuscar.getText().toLowerCase();
        String categoriaFiltro = vista.cmbCategoriaFiltro.getSelectedItem().toString();

        RowFilter<DefaultTableModel, Object> filtro = new RowFilter<DefaultTableModel, Object>() {

            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                String nombre = entry.getStringValue(0).toLowerCase();
                String categoria = entry.getStringValue(3);

                boolean coincideBusqueda = textoBusqueda.isEmpty() || nombre.contains(textoBusqueda);
                boolean coincideCategoria = categoriaFiltro.equals("Todos") || categoria.equals(categoriaFiltro);

                return coincideBusqueda && coincideCategoria;
            }
        };
        sorter.setRowFilter(filtro);
    }

    private void agregarContacto() {
        try {
            String nombre = vista.txt_nombres.getText();
            String telefono = vista.txt_telefono.getText();
            String correo = vista.txt_email.getText();
            String categoria = vista.cmb_categoria.getSelectedItem().toString();
            boolean favorito = vista.chb_favorito.isSelected();

            if (!nombre.isEmpty() && !telefono.isEmpty() && !correo.isEmpty()) {
                contactos.add(new Persona(nombre, telefono, correo, categoria, favorito));
                personaDAO.guardarContactos(contactos);
                actualizarLista();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Por favor completa todos los campos.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(vista, "Error al guardar el contacto.");
        }
    }

    private void eliminarContacto() {
        int index = vista.list_contactos.getSelectedIndex();
        if (index != -1) {
            contactos.remove(index);
            try {
                personaDAO.guardarContactos(contactos);
                actualizarLista();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(vista, "Error al elminarl el contacto.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor selecciona un contacto.");
        }
    }

    private void actualizarLista() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Persona persona : contactos) {
            model.addElement(persona.getNombre() + " - " + persona.getTelefono() + " - " + persona.getCorreo() + " - " + persona.getCategoria());
        }
        vista.list_contactos.setModel(model);
    }

    private void limpiarCampos() {
        vista.txt_nombres.setText("");
        vista.txt_telefono.setText("");
        vista.txt_email.setText("");
        vista.chb_favorito.setSelected(false);
    }

    public void exportarContactos() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona dónde guardar el archivo CSV");
        int userSelection = fileChooser.showSaveDialog(vista);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath() + ".csv";
            try {
                personaDAO.exportarContactos(contactos, rutaArchivo);
                JOptionPane.showMessageDialog(vista, "Contactos exportados exitosamente a: " + rutaArchivo);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(vista, "Error al exportar los contactos.");
            }
        }
    }

    public class CargarContactosWorker extends SwingWorker<Void, Integer> {
        private Ventana vista;
        private PersonaDAO personaDAO;
        private ArrayList<Persona> contactos;

        public CargarContactosWorker(Ventana vista, PersonaDAO personaDAO, ArrayList<Persona> contactos) {
            this.vista = vista;
            this.personaDAO = personaDAO;
            this.contactos = contactos;
        }

        @Override
        protected Void doInBackground() throws Exception {
            // Simula un proceso de carga (ejemplo: lectura de contactos desde un archivo)
            int total = contactos.size();
            for (int i = 0; i < total; i++) {
                Thread.sleep(100); // Simula un tiempo de espera
                publish((i + 1) * 100 / total); // Actualiza el progreso
            }
            return null;
        }

        @Override
        protected void process(List<Integer> chunks) {
            // Actualiza la barra de progreso
            for (int progress : chunks) {
                vista.barraProgreso.setValue(progress);
            }
        }

        @Override
        protected void done() {
            JOptionPane.showMessageDialog(vista, "Carga de contactos completada.");
            vista.barraProgreso.setValue(0); // Resetea la barra de progreso
        }
    }

    public void agregarAtajosDeTeclado() {
        // Mapa de entrada para capturar combinaciones de teclas
        vista.getRootPane().getInputMap().put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK), "nuevoContacto");
        vista.getRootPane().getInputMap().put(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), "exportarContactos");

        // Mapa de acciones para ejecutar las tareas correspondientes
        vista.getRootPane().getActionMap().put("nuevoContacto", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto(); // Llama al método de agregar contacto
            }
        });

        vista.getRootPane().getActionMap().put("exportarContactos", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportarContactos(); // Llama al método de exportar contactos
            }
        });
    }
}
