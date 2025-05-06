package gestioncontactos;

import controlador.LogicaPrograma;
import java.io.IOException;
import java.util.ArrayList;
import modelo.PersonaDAO;
import vista.Ventana;

public class GestionContactos {

    public static void main(String[] args) {

        // Crear la vista
        Ventana vista = new Ventana();

        // Crea el DAO (responsable de manejar los datos)
        PersonaDAO personaDAO = new PersonaDAO("c:/gestionContactos/datosContactos.csv");


        //cargar contactos desde archivo CSV
        ArrayList<modelo.Persona> contactos;
        try {
            contactos = personaDAO.cargarContactos(); // Cargar contactos dinámicamente
        } catch (IOException e) {
            contactos = new ArrayList<>(); // Si hay error, inicia con lista vacía
            System.out.println("Error al cargar contactos: " + e.getMessage());
        }

//        //Lista de contactos como datos iniciales
//        ArrayList<Persona> contactos = new personaDAO.cargarContactos();
//        contactos.add(new Persona("Juan Perez", "1234567890", "juan.perez@gmail.com", "Familia", true));
//        contactos.add(new Persona("Maria Gomez", "0987654321", "maria.gomez@hotmail.com", "Amigos", false));
//        contactos.add(new Persona("Carlos Ruiz", "4567891230", "carlos.ruiz@empresa.com", "Trabajo", true));
//        
        //Crea el controlador
        new LogicaPrograma(vista, personaDAO, contactos);

        // Configurar y mostrar la ventana
        vista.setVisible(true);
        vista.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        vista.setFocusable(true); // Asegurar que la ventana pueda capturar eventos de teclado
        vista.requestFocusInWindow(); // Darle foco inicial

    }

}
