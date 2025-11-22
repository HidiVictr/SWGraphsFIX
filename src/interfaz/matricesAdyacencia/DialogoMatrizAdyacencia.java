package interfaz.matricesAdyacencia;

import grafos.Arista;
import grafos.Grafo;
import interfaz.BotonImagen;
import interfaz.DialogoAyuda;
import interfaz.MenuPrincipal;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DialogoMatrizAdyacencia extends JDialog {
   private static final long serialVersionUID = 1L;
   private JLabel tipoMatrizLabel;
   private Grafo grafoResultado;
   private MatrizTabla matrizTable;
   private MenuPrincipal menuPpal;
   private JLabel tipoGrafoLabel;

   public DialogoMatrizAdyacencia(MenuPrincipal arg0, Grafo arg1, int algoritmo) throws HeadlessException {
      this.menuPpal = arg0;
      this.grafoResultado = arg1;
      this.setModal(true);
      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension ventana = this.getSize();
      this.setLocation((pantalla.width - ventana.width) / 2 - 200, (pantalla.height - ventana.height) / 2 - 315);
      this.matrizTable = new MatrizTabla(this.grafoResultado);
      JScrollPane scrollPane = new JScrollPane(this.matrizTable);
      ColumnaPrincipal rowHeader = new ColumnaPrincipal(this.grafoResultado);
      scrollPane.setRowHeaderView(rowHeader);
      JPanel panelMatriz = new JPanel();
      BorderLayout panelMatrizLayout = new BorderLayout();
      panelMatriz.setLayout(panelMatrizLayout);
      panelMatriz.setOpaque(true);
      panelMatriz.add(scrollPane, "Center");
      this.matrizTable.revalidate();
      this.setContentPane(panelMatriz);
      JPanel superiorPanel = new JPanel();
      panelMatriz.add(superiorPanel, "South");
      BotonImagen botonsalir = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/ayuda.png")), "Ayuda sobre el uso de la aplicacion", "");
      superiorPanel.add(botonsalir);
      botonsalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            DialogoMatrizAdyacencia.this.ayudaActionPerformed(evt);
         }
      });
      botonsalir.setPreferredSize(new Dimension(125, 80));
      botonsalir = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/home.png")), "Cierra el dialogo y vuelve a la ventana anterior", "");
      superiorPanel.add(botonsalir);
      botonsalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            DialogoMatrizAdyacencia.this.botonsalirActionPerformed(evt);
         }
      });
      botonsalir.setPreferredSize(new Dimension(125, 80));
      superiorPanel = new JPanel();
      BorderLayout superiorPanelLayout = new BorderLayout();
      superiorPanel.setLayout(superiorPanelLayout);
      panelMatriz.add(superiorPanel, "North");
      superiorPanel.setPreferredSize(new Dimension(584, 70));
      this.tipoMatrizLabel = new JLabel();
      FlowLayout tipoGrafoLabelLayout = new FlowLayout();
      superiorPanel.add(this.tipoMatrizLabel, "South");
      this.tipoMatrizLabel.setLayout(tipoGrafoLabelLayout);
      this.tipoMatrizLabel.setPreferredSize(new Dimension(387, 35));
      this.tipoMatrizLabel.setFont(new Font("Segoe UI", 0, 18));
      this.tipoGrafoLabel = new JLabel();
      tipoGrafoLabelLayout = new FlowLayout();
      this.tipoGrafoLabel.setLayout(tipoGrafoLabelLayout);
      superiorPanel.add(this.tipoGrafoLabel, "North");
      this.tipoGrafoLabel.setPreferredSize(new Dimension(387, 35));
      this.tipoGrafoLabel.setFont(new Font("Segoe UI", 0, 18));
      if (this.grafoResultado.getTipo() == 0) {
         this.tipoGrafoLabel.setText("   Grafo No Dirigido");
      } else {
         this.tipoGrafoLabel.setText("   Grafo Dirigido");
      }

      this.setIconImage((new ImageIcon(this.getClass().getResource("/interfaz/img/k.png"))).getImage());
      switch(algoritmo) {
      case 1:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 2:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 3:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 4:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 5:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 6:
         this.tipoMatrizLabel.setText("   Matriz de multiplicidades");
         break;
      case 7:
         this.tipoMatrizLabel.setText("   Matriz de capacidades");
         break;
      case 8:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
      case 9:
      case 11:
      case 12:
      case 13:
      default:
         break;
      case 10:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
         break;
      case 14:
         this.tipoMatrizLabel.setText("   Matriz de pesos");
      }

      this.setSize(600, 400);
      this.setMinimumSize(new Dimension(600, 400));
      this.setSize(scrollPane.getSize());
      panelMatriz.setSize(scrollPane.getSize());
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            DialogoMatrizAdyacencia.this.windowClosingPerformed(e);
         }
      });
      this.pack();
      this.setVisible(true);
   }

   private void windowClosingPerformed(WindowEvent e) {
      this.devolverGrafo();
   }

   private void botonsalirActionPerformed(ActionEvent evt) {
      this.devolverGrafo();
      this.dispose();
   }

   private void ayudaActionPerformed(ActionEvent evt) {
      new DialogoAyuda(12);
   }

   private void devolverGrafo() {
      this.grafoResultado.resetAristas();
      int i;
      int j;
      Double peso;
      Arista a;
      if (this.grafoResultado.getTipo() == 0) {
         for(i = 0; i < this.grafoResultado.getNodos().size(); ++i) {
            for(j = i; j < this.grafoResultado.getNodos().size(); ++j) {
               peso = (Double)this.matrizTable.getValueAt(i, j);
               if (peso != 0.0D) {
                  a = new Arista(i, j, 0);
                  a.setPeso(peso);
                  this.grafoResultado.insertarArista(a);
               }
            }
         }
      } else {
         for(i = 0; i < this.grafoResultado.getNodos().size(); ++i) {
            for(j = 0; j < this.grafoResultado.getNodos().size(); ++j) {
               peso = (Double)this.matrizTable.getValueAt(i, j);
               if (peso != 0.0D) {
                  a = new Arista(i, j, 1);
                  a.setPeso(peso);
                  this.grafoResultado.insertarArco(a);
               }
            }
         }
      }

      if (this.grafoResultado.getTipo() == 0) {
         this.grafoResultado.setTipo(0);
         this.menuPpal.tipoGrafoComboBox.setSelectedIndex(0);
         if (this.menuPpal.arcoRadioButton.isSelected()) {
            this.menuPpal.arcoRadioButton.setSelected(false);
            this.menuPpal.aristasRadioButton.setSelected(true);
            this.menuPpal.panel.setOperacion(1);
         }
      } else {
         this.grafoResultado.setTipo(1);
         this.menuPpal.tipoGrafoComboBox.setSelectedIndex(1);
         if (this.menuPpal.aristasRadioButton.isSelected()) {
            this.menuPpal.aristasRadioButton.setSelected(false);
            this.menuPpal.arcoRadioButton.setSelected(true);
            this.menuPpal.panel.setOperacion(2);
         }
      }

      this.menuPpal.setGrafo(this.grafoResultado);
      this.menuPpal.redibujarGrafo();
   }
}
