import javax.swing.*;
import java.awt.*;

public class PanelActualizar extends JPanel {
    private Bibliotecario bibliotecario;
    private JTextField txtId = new JTextField();
    private JTextField txtTitulo = new JTextField();
    private JTextField txtAutor = new JTextField();
    private JTextField txtAnio = new JTextField();
    private JButton btnGuardar = new JButton("Guardar cambios");

    public PanelActualizar(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(6, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnBuscar = new JButton("Buscar por ID");
        formulario.add(new JLabel("ID del libro:")); formulario.add(txtId);
        formulario.add(new JLabel());                formulario.add(btnBuscar);
        formulario.add(new JLabel("Título:"));       formulario.add(txtTitulo);
        formulario.add(new JLabel("Autor:"));        formulario.add(txtAutor);
        formulario.add(new JLabel("Año:"));          formulario.add(txtAnio);
        formulario.add(new JLabel());                formulario.add(btnGuardar);

        add(formulario, BorderLayout.NORTH);

        btnGuardar.setEnabled(false);           // deshabilitado hasta encontrar un libro
        btnBuscar.addActionListener(e -> buscar());
        btnGuardar.addActionListener(e -> guardar());
    }

    private void buscar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Libro libro = bibliotecario.buscarPorId(id);

            if (libro == null) {
                JOptionPane.showMessageDialog(this, "No existe un libro con ese ID.");
                btnGuardar.setEnabled(false);
            } else {
                txtTitulo.setText(libro.getTitulo());
                txtAutor.setText(libro.getAutor());
                txtAnio.setText(String.valueOf(libro.getAnio()));
                btnGuardar.setEnabled(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número.");
        }
    }

    private void guardar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            int anio = Integer.parseInt(txtAnio.getText().trim());
            String titulo = txtTitulo.getText().trim();
            String autor = txtAutor.getText().trim();

            if (titulo.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Título y autor no pueden estar vacíos.");
                return;
            }
            if (bibliotecario.actualizarLibro(id, titulo, autor, anio)) {
                JOptionPane.showMessageDialog(this, "Libro actualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el libro.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID y Año deben ser números.");
        }
    }
}