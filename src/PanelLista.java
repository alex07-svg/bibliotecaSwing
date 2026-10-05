import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelLista extends JPanel {
    private Bibliotecario bibliotecario;
    private DefaultTableModel modelo;

    public PanelLista(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "Título", "Autor", "Año"};
        modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        tabla.setEnabled(false);                // solo lectura

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    // Se llama cada vez que se muestra este panel
    public void refrescar() {
        modelo.setRowCount(0);                  // borra las filas anteriores
        Libro[] libros = bibliotecario.obtenerLibros();
        for (int i = 0; i < libros.length; i++) {
            modelo.addRow(new Object[]{
                    libros[i].getId(),
                    libros[i].getTitulo(),
                    libros[i].getAutor(),
                    libros[i].getAnio()
            });
        }
    }
}