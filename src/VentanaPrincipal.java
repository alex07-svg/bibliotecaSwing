import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private Bibliotecario bibliotecario = new Bibliotecario();
    private CardLayout cardLayout = new CardLayout();
    private JPanel contenedor = new JPanel(cardLayout);
    private PanelLista panelLista;

    public VentanaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);            // centra la ventana

        // Menú de opciones (botones)
        JPanel menu = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar libro");
        JButton btnActualizar = new JButton("Actualizar libro");
        JButton btnLista = new JButton("Ver lista");
        JButton btnSalir = new JButton("Salir");

        btnAgregar.setActionCommand("AGREGAR");
        btnActualizar.setActionCommand("ACTUALIZAR");
        btnLista.setActionCommand("LISTA");
        btnSalir.setActionCommand("SALIR");

        btnAgregar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnLista.addActionListener(this);
        btnSalir.addActionListener(this);

        menu.add(btnAgregar);
        menu.add(btnActualizar);
        menu.add(btnLista);
        menu.add(btnSalir);

        // Paneles (todos comparten el MISMO bibliotecario)
        panelLista = new PanelLista(bibliotecario);
        contenedor.add(new PanelAgregar(bibliotecario), "AGREGAR");
        contenedor.add(new PanelActualizar(bibliotecario), "ACTUALIZAR");
        contenedor.add(panelLista, "LISTA");

        add(menu, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String opcion = e.getActionCommand();

        if (opcion.equals("AGREGAR")) {
            cardLayout.show(contenedor, "AGREGAR");
        } else if (opcion.equals("ACTUALIZAR")) {
            cardLayout.show(contenedor, "ACTUALIZAR");
        } else if (opcion.equals("LISTA")) {
            panelLista.refrescar();             // primero actualiza los datos
            cardLayout.show(contenedor, "LISTA");
        } else if (opcion.equals("SALIR")) {
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres salir?", "Salir", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}