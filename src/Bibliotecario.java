import java.util.Arrays;

public class Bibliotecario {
    private static final int CAPACIDAD = 100;
    private Libro[] libros;
    private int cantidad = 0;

    //agrega un libro a la biblioteca
    public boolean agregarLibro(Libro libro) {
        if (cantidad < CAPACIDAD) {
            libros[cantidad++] = libro;
            return true;
        }
        return false;
    }

    //busca un libro por su ID
    public Libro buscarPorId(int id) {
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].getId() == id) {
                return libros[i];
            }
        }
        return null;
    }
    //actualiza un libro existente
    public boolean actualizarLibro(int id, String titulo, String autor, int anio) {
        Libro libro = buscarPorId(id);
        if (libro != null) {
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setAnio(anio);
            return true;
        }
        return false;
    }

    // Devuelve solo las posiciones ocupadas (sin los null del final).
    public Libro[] obtenerLibros() {
        return Arrays.copyOf(libros, cantidad);
    }
    public int getCantidad() {
        return cantidad;
    }
}
