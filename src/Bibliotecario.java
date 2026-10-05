import java.util.Arrays;

public class Bibliotecario {
    private static final int CAPACIDAD = 100;
    private Libro[] libros;
    private int cantidad = 0;

    // Constructor por defecto que inicializa el arreglo de libros
    public Bibliotecario() {
        this.libros = new Libro[CAPACIDAD];
        this.cantidad = 0;
    }

    // Constructor con capacidad personalizada (útil para pruebas o configuración)
    public Bibliotecario(int capacidad) {
        int cap = (capacidad > 0) ? capacidad : CAPACIDAD;
        this.libros = new Libro[cap];
        this.cantidad = 0;
    }

    // Agrega un libro a la biblioteca
    public boolean agregarLibro(Libro libro) {
        // Valida que el libro no sea nulo
        if (libro == null) {
            return false;
        }

        // Valida que no se exceda la capacidad máxima
        if (cantidad >= libros.length) {
            return false;
        }

        // Valida que no exista ya un libro con el mismo ID (evita duplicados)
        if (buscarPorId(libro.getId()) != null) {
            return false;
        }

        libros[cantidad++] = libro;
        return true;
    }

    // Busca un libro por su ID
    public Libro buscarPorId(int id) {
        for (int i = 0; i < cantidad; i++) {
            if (libros[i] != null && libros[i].getId() == id) {
                return libros[i];
            }
        }
        return null;
    }

    // Actualiza un libro existente
    public boolean actualizarLibro(int id, String titulo, String autor, int anio) {
        if (titulo == null || autor == null) {
            return false;
        }

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

    public int getCapacidad() {
        return libros.length;
    }
}
