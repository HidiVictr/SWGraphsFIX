package interfaz;

import algoritmos.layout.ForceAtlas2;
import algoritmos.layout.FruchtermanReingold;
import algoritmos.layout.KamadaKawai;
import algoritmos.layout.LayoutAlgorithm;
import algoritmos.layout.Sugiyama;
import grafos.Grafo;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DialogoOrdenarGrafo extends JDialog {

    private MenuPrincipal menuPrincipal;
    private Grafo grafo;
    private JComboBox<String> comboAlgoritmos;

    public DialogoOrdenarGrafo(MenuPrincipal menu, Grafo grafo) {
        super(menu, "Ordenar Grafo", true);
        this.menuPrincipal = menu;
        this.grafo = grafo;

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));

        panelCentral.add(new JLabel("Algoritmo:"));

        comboAlgoritmos = new JComboBox<>();
        if (grafo.getTipo() == 0) { // Grafo No Dirigido
            comboAlgoritmos.addItem("Fruchterman-Reingold");
            comboAlgoritmos.addItem("Kamada-Kawai");
            comboAlgoritmos.addItem("ForceAtlas2");
        } else { // Grafo Dirigido
            comboAlgoritmos.addItem("Sugiyama");
        }
        panelCentral.add(comboAlgoritmos);

        add(panelCentral, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAplicar = new JButton("Aplicar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarAlgoritmo();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panelBotones.add(btnAplicar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setSize(300, 150);
        setLocationRelativeTo(menuPrincipal);
    }

    private void aplicarAlgoritmo() {
        String algoritmoSeleccionado = (String) comboAlgoritmos.getSelectedItem();
        LayoutAlgorithm algoritmo = null;

        if ("Fruchterman-Reingold".equals(algoritmoSeleccionado)) {
            algoritmo = new FruchtermanReingold();
        } else if ("Kamada-Kawai".equals(algoritmoSeleccionado)) {
            algoritmo = new KamadaKawai();
        } else if ("ForceAtlas2".equals(algoritmoSeleccionado)) {
            algoritmo = new ForceAtlas2();
        } else if ("Sugiyama".equals(algoritmoSeleccionado)) {
            algoritmo = new Sugiyama();
        }

        if (algoritmo != null) {
            // Save state for Undo
            menuPrincipal.panel.guardarEstado();

            // Apply layout
            int width = menuPrincipal.panel.getWidth();
            int height = menuPrincipal.panel.getHeight();
            algoritmo.aplicar(grafo, width, height);

            // Repaint
            menuPrincipal.panel.repaint();
        }

        dispose();
    }
}
