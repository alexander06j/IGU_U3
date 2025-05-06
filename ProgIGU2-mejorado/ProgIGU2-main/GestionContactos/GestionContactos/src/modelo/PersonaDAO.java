package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonaDAO {

    private String filePath;
    private List<Persona> personas;

    public PersonaDAO() {
        personas = new ArrayList<>();
    }

    public synchronized boolean existePersona(Persona persona) {
        return personas.contains(persona);
    }

    public synchronized void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    public synchronized List<Persona> buscarPersona(String termino) {
        return personas.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(termino.toLowerCase())).collect(Collectors.toList());
    }

    public synchronized List<Persona> getTodasPersonas() {
        return new ArrayList<>(personas);
    }

    public PersonaDAO(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); // Crea la carpeta si no existe
                file.createNewFile(); // Crea el archivo CSV si no existe
            } catch (IOException e) {
                System.err.println("No se pudo crear el archivo: " + filePath);
            }
        }
    }

    public void guardarContactos(ArrayList<Persona> contactos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Nombre,Teléfono,Correo,Categoría,Favorito"); // Encabezados
            writer.newLine();
            for (Persona persona : contactos) {
                writer.write(persona.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar contactos: " + e.getMessage());
            throw e;
        }
    }

    public ArrayList<Persona> cargarContactos() throws IOException {
        ArrayList<Persona> contactos = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length != 5) {
                        System.err.println("Formato inválido en línea: " + line);
                        continue;
                    }
                    try {
                        contactos.add(new Persona(parts[0], parts[1], parts[2], parts[3], Boolean.parseBoolean(parts[4])));
                    } catch (Exception e) {
                        System.err.println("Error al procesar línea: " + line);
                    }
                }
            }
        }
        return contactos;
    }

    public void exportarContactos(ArrayList<Persona> contactos, String rutaArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write("Nombre,Teléfono,Correo,Categoría,Favorito"); // Encabezados
            writer.newLine();
            for (Persona persona : contactos) {
                writer.write(persona.getNombre() + ","
                        + persona.getTelefono() + ","
                        + persona.getCorreo() + ","
                        + persona.getCategoria() + ","
                        + persona.isFavorito());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al exportar contactos: " + e.getMessage());
            throw e;
        }
    }

    public List<Persona> buscarPersonas(String termino) {
        return personas.stream()
                .filter(p -> p.getNombre().toLowerCase().contains(termino.toLowerCase()))
                .collect(Collectors.toList());

    }
}
