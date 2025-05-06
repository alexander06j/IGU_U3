package modelo;

public class Persona {
// Declaración de variables privadas de la clase "Persona".

    private String nombre;
    private String telefono;
    private String correo;
    private String categoria;
    private boolean favorito;
//Constructor vacío.

    public Persona() {
    }
//constructor de la clase Persona.

    public Persona(String nombre, String telefono, String correo, String categoria, boolean favorito) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.categoria = categoria;
        this.favorito = favorito;
    }

    //Getters y Setters para cada atributo.
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    @Override
    public String toString() {
        return nombre + "," + telefono + "," + correo + "," + categoria + "," + favorito;
    }
}
