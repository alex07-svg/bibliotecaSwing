import javax.swing.*;
import java.awt.*;

public class PanelAgregar extends JPanel {
    private Bibliotecario bibliotecario;
    private JTextField txtId = new JTextField();
    private JTextField txtTitulo = new JTextField();
    private JTextField txtAutor = new JTextField();
    private JTextField txtAnio = new JTextField();

    public PanelAgregar(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formulario.add(new JLabel("ID:"));      formulario.add(txtId);
        formulario.add(new JLabel("Título:"));  formulario.add(txtTitulo);
        formulario.add(new JLabel("Autor:"));   formulario.add(txtAutor);
        formulario.add(new JLabel("Año:"));     formulario.add(txtAnio);

        JButton btnGuardar = new JButton("Guardar libro");
        formulario.add(new JLabel());           // celda vacía para alinear
        formulario.add(btnGuardar);

        add(formulario, BorderLayout.NORTH);

        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();

        if (txtId.getText().trim().isEmpty() || titulo.isEmpty()
                || autor.isEmpty() || txtAnio.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            int anio = Integer.parseInt(txtAnio.getText().trim());

            Libro nuevo = new Libro(id, titulo, autor, anio);
            if (bibliotecario.agregarLibro(nuevo)) {
                JOptionPane.showMessageDialog(this, "Libro agregado correctamente.");
                txtId.setText(""); txtTitulo.setText("");
                txtAutor.setText(""); txtAnio.setText("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo agregar: ID repetido o biblioteca llena.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID y Año deben ser números.");
        }
    }
}