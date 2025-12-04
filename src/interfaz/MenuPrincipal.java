package interfaz;

import Util.Util;
import algoritmos.Algoritmo;
import algoritmos.CarteroChino;
import algoritmos.CicloHamiltonArbol;
import algoritmos.CicloHamiltonFuerzaB;
import algoritmos.CicloHamiltonVoraz;
import algoritmos.Edmonds2;
import algoritmos.FlujoMaximo;
import algoritmos.Hierholzer;
import algoritmos.Kruskal;
import grafos.Arista;
import grafos.DijkstraObject;
import grafos.Grafo;
import grafos.GrafoMultiColor;
import grafos.Nodo;
import interfaz.matricesAdyacencia.DialogoMatrizAdyacencia;
import interfaz.matricesAdyacencia.MatrizTabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.xml.parsers.FactoryConfigurationError;

public class MenuPrincipal extends JFrame {
   private static final long serialVersionUID = 1L;
   private JPanel fondo;
   private JToolBar barra;
   private BotonImagen abrir;
   private BotonImagen guardar;
   private BotonImagen pasos;
   private BotonImagen play;
   private BotonImagen kGrafo;
   private BotonImagen matriz;
   private BotonImagen limpiar;
   private BotonImagen ayuda;
   private BotonImagen guardarResultadoButton;
   private JRadioButtonMenuItem carteroChinoRadioButton;
   private JMenuItem ConexoMenuItem;
   private JMenu grafosAyudaMenu;
   private JSeparator jSeparator5;
   private JMenuItem fondoMenuItem;
   private JRadioButtonMenuItem kruskalRadioButton;
   private JSeparator jSeparator3;
   private JMenuItem fuertementeConexoMenuItem;
   private JMenuItem debilmenteConexoMenuItem;
   private JMenuItem conexoMenuItem;
   private JRadioButtonMenuItem etiquetajeRadioButton;
   private JRadioButtonMenuItem hierholzerRadioButton;
   private JRadioButtonMenuItem orientabilidadRadioButton;
   private JRadioButtonMenuItem dfsRadioButton;
   private JRadioButtonMenuItem bfsRadioButton;
   private JMenuItem matrizAdyMenuItem;
   private JMenuItem grafoCompletoMenuItem;
   private JMenu grafoMenu;
   private JMenuItem ayudaKruskalMenuItem;
   private JMenuItem deshacerMenuItem;
   private JMenuItem rehacerMenuItem;
   private JMenuItem ayudaEtiquetajeMenuItem;
   private JMenuItem ayudaHierholzerMenuItem;
   private JMenuItem ayudaDFSMenuItem;
   private JMenuItem borrarFondoMenuItem;
   private JPanel algoritmoPanel;
   private JRadioButton nodosRadioButton;
   private JPanel nodoAristaPanel;
   private JPanel hueco2Panel;
   private JScrollPane scroll;
   private JScrollPane scrollPrincipal;
   private JMenuItem ayudaEdmons2MenuItem;
   private JMenuItem ayudaCarteroChinoMenuItem;
   private JTextArea textoInformacion;
   private JPanel hueco1Panel;
   private JPanel carteroChinoPanel;
   private JSeparator jSeparator2;
   private JMenuItem porpasosMenuItem;
   private JMenuItem ejecutarMenuItem;
   private JMenuItem BFS_ayudajMenuItem8;
   private JMenuItem dijkstra_ayudaMenuItem;
   private JMenu algoritmosMenu;
   private JPanel botonesPanel;
   private JPanel infoPanel;
   private JLabel statusLabel;
   private JPanel statusPanel;
   private JSeparator jSeparator;
   private JMenuItem manualUsuarioMenuItem;
   private JMenuItem kruskal5MenuItem;
   private JMenuItem kruskal6MenuItem;
   private JMenuItem kruskal7MenuItem;
   private JMenuItem subyacenteMenuItem;
   private JMenuItem desigualdadMenuItem;
   private JMenuItem compConexasMenuItem;
   private JMenuItem compFuerteConexasMenuItem;
   private JMenuItem edmonds1MenuItem;
   private JMenuItem edmonds2MenuItem;
   private JMenuItem hierholzer1MenuItem;
   private JMenuItem hierholzer2MenuItem;
   private JMenuItem hierholzer3MenuItem;
   private JMenuItem hierholzer4MenuItem;
   private JMenuItem kruskal1MenuItem;
   private JMenuItem kruskal2MenuItem;
   private JMenuItem kruskal3MenuItem;
   private JMenuItem kruskal4MenuItem;
   private JMenuItem dijkstra4MenuItem;
   private JMenuItem dijkstra3MenuItem;
   private JMenuItem espanyaMenuItem;
   private JMenuItem dijkstra2MenuItem;
   private JMenuItem dijkstra1MenuItem;
   private JMenuItem viajante1MenuItem;
   private JMenuItem transporteMenuItem;
   private JMenuItem flujo2MenuItem;
   private JMenuItem flujo1MenuItem;
   private JMenuItem centroValenciaMenuItem;
   private JMenu hamilonianoEjemplosMenu;
   private JMenu flujoEjemplosMenu;
   private JMenu carteroChinoEjemplosMenu;
   private JMenu hierholzerEjemplosMenu;
   private JMenu Edmonds2EjemplosMenu;
   private JMenu kruskalEjemplosMenu;
   private JMenu dijkstraEjemplosMenu;
   private JMenu bfsdfsEjemplosMenu;
   private JMenuItem ejemploBFS1MenuItem;
   private JMenuItem ejemploBFS2MenuItem;
   private JMenu ejemplosMenu;
   private JRadioButtonMenuItem cicloHamoltonMinimoRadioButton;
   private JMenuItem ayudaHamiltonianoMenuItem;
   private JMenuItem ayudaOrientabilidadMenuItem;
   private JRadioButtonMenuItem cicloHamiltonianoRadioButtonMenuItem;
   private JLabel algoritmoLabel;
   private JLabel algoritmoSelectLabel;
   private JPanel algoritmoSelectPanel;
   private JMenuItem matrizAdyacenciaMenuItem;
   private JMenuItem generalidadesMenuItem;
   private JSplitPane jSplitPane1;
   private JMenuItem mathematicaMenuItem;
   private JRadioButtonMenuItem edmons2RadioButton;
   private JRadioButtonMenuItem dijkstraRadioButton;
   private JMenuItem acercaDeMenuItem;
   private JMenuItem salirMenuItem;
   private JSeparator jSeparator1;
   private JPanel grafoPanel;
   private JMenuItem guardarMenuItem;
   private JMenuItem abrirMenuItem;
   private JMenu ayudaMenu;
   private JMenu jMenu2;
   private JMenu jMenu1;
   private JMenuBar MenuBar;
   private JPanel derPanel;
   private JScrollPane areaScrollPane;
   private JMenu conexionMenu;
   private JSeparator jSeparator6;
   private JMenuItem informacionMenuItem;
   private JMenuItem gradosMenuItem;
   public JComboBox tipoGrafoComboBox;
   public JRadioButton aristasRadioButton;
   public JRadioButton arcoRadioButton;
   public PanelGrafosDibujo panel;
   private PanelGrafos panelresultados;
   private ButtonGroup groupAlgoritmos;
   private ButtonGroup groupNodoArista;
   private int AlgoritmoSeleccionado;
   private File ruta;
   private Edmonds2 algEd2;
   private CarteroChino algCh;

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            MenuPrincipal inst = new MenuPrincipal();
            inst.setLocationRelativeTo((Component) null);
            inst.setVisible(true);
         }
      });
   }

   public MenuPrincipal() throws HeadlessException {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.panel = new PanelGrafosDibujo();
      this.panelresultados = new PanelGrafos();
      this.groupAlgoritmos = new ButtonGroup();
      this.groupNodoArista = new ButtonGroup();
      this.AlgoritmoSeleccionado = 4;
      this.ruta = null;
      this.algEd2 = null;
      this.algCh = null;
      BorderLayout thisLayout = new BorderLayout();
      this.getContentPane().setLayout(thisLayout);
      this.setSize(1200, 700);
      this.construirIGU();
   }

   public void setAlgoritmoSeleccionado(int algoritmoSeleccionado) {
      this.AlgoritmoSeleccionado = algoritmoSeleccionado;
   }

   public int getAlgoritmoSeleccionado() {
      return this.AlgoritmoSeleccionado;
   }

   public void setGrafo(Grafo g) {
      this.panel.setGrafo(g);
   }

   public Grafo getGrafo() {
      Grafo G = this.panel.getGrafo();
      return G;
   }

   public void redibujarGrafo() {
      this.panel.repaint();
   }

   private void construirIGU() {
      this.fondo = new JPanel();
      BorderLayout fondoLayout = new BorderLayout();
      ImageIcon imageIcon = new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/swgraphs.png"));
      this.textoInformacion = new JTextArea() {
         private static final long serialVersionUID = 1L;
         Image image;

         {
            this.image = imageIcon.getImage();
            this.setOpaque(false);
         }

         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(this.image, 0, 0, this);
         }
      };
      this.textoInformacion.setFont(new Font("Verdana", 0, 12));
      this.textoInformacion.setEditable(false);
      this.textoInformacion.setLineWrap(true);
      this.textoInformacion.setWrapStyleWord(true);
      this.fondo.setLayout(fondoLayout);
      this.setContentPane(this.fondo);
      this.derPanel = new JPanel();
      this.fondo.add(this.derPanel, "East");
      BorderLayout derPanelLayout = new BorderLayout();
      this.derPanel.setLayout(derPanelLayout);
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      if (d.width <= 800) {
         this.setSize(800, 567);
         this.setMinimumSize(new Dimension(800, 467));
      } else if (d.width < 1200) {
         this.setSize(1000, 583);
         this.setMinimumSize(new Dimension(1000, 583));
      } else {
         this.setSize(1200, 700);
         this.setMinimumSize(new Dimension(1200, 700));
      }

      this.MenuBar = new JMenuBar();
      this.setJMenuBar(this.MenuBar);
      this.jMenu1 = new JMenu();
      this.MenuBar.add(this.jMenu1);
      this.jMenu1.setText("Archivo  ");
      this.abrirMenuItem = new JMenuItem();
      this.jMenu1.add(this.abrirMenuItem);
      this.abrirMenuItem.setText("Abrir grafo desde archivo");
      this.abrirMenuItem.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2open.png")));
      this.abrirMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.abrirMenuItemActionPerformed(evt);
         }
      });
      this.guardarMenuItem = new JMenuItem();
      this.jMenu1.add(this.guardarMenuItem);
      this.guardarMenuItem.setText("Guardar grafo");
      this.guardarMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2save.png")));
      this.guardarMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.guardarMenuItemActionPerformed(evt);
         }
      });
      this.jSeparator1 = new JSeparator();
      this.jMenu1.add(this.jSeparator1);
      this.fondoMenuItem = new JMenuItem();
      this.jMenu1.add(this.fondoMenuItem);
      this.fondoMenuItem.setText("Definir fondo");
      this.fondoMenuItem.setBounds(55, 19, 169, 21);
      this.fondoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            try {
               MenuPrincipal.this.fondoMenuItemActionPerformed(evt);
            } catch (IOException var3) {
               var3.printStackTrace();
            }

         }
      });
      this.borrarFondoMenuItem = new JMenuItem();
      this.jMenu1.add(this.borrarFondoMenuItem);
      this.borrarFondoMenuItem.setText("Borrar fondo");
      this.borrarFondoMenuItem.setBounds(55, 61, 169, 21);
      this.borrarFondoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.borrarFondoMenuItemActionPerformed(evt);
         }
      });
      this.jSeparator6 = new JSeparator();
      this.jMenu1.add(this.jSeparator6);
      this.grafoMenu = new JMenu();
      this.MenuBar.add(this.grafoMenu);
      this.grafoMenu.setText("Grafo  ");
      this.informacionMenuItem = new JMenuItem();
      this.grafoMenu.add(this.informacionMenuItem);
      this.informacionMenuItem.setText("Información del grafo");
      this.informacionMenuItem.setBounds(43, 69, 122, 21);
      this.informacionMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.informacionMenuItemActionPerformed(evt);
         }
      });
      this.gradosMenuItem = new JMenuItem();
      this.grafoMenu.add(this.gradosMenuItem);
      this.gradosMenuItem.setText("Grado de los vértices");
      this.gradosMenuItem.setBounds(43, 69, 122, 21);
      this.gradosMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.gradosMenuItemActionPerformed(evt);
         }
      });
      this.subyacenteMenuItem = new JMenuItem();
      this.subyacenteMenuItem.setText("Grafo subyacente");
      this.subyacenteMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.subyacenteMenuItemActionPerformed(evt);
         }
      });
      this.grafoMenu.add(this.subyacenteMenuItem);
      this.conexionMenu = new JMenu();
      this.grafoMenu.add(this.conexionMenu);
      this.conexionMenu.setText("Conexión del grafo");
      this.conexionMenu.setBounds(43, 69, 122, 21);
      this.conexoMenuItem = new JMenuItem();
      this.conexionMenu.add(this.conexoMenuItem);
      this.conexoMenuItem.setText("¿ Es conexo ?");
      this.conexoMenuItem.setBounds(-43, 79, 169, 21);
      this.conexoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.conexoMenuItemActionPerformed(evt);
         }
      });
      this.fuertementeConexoMenuItem = new JMenuItem();
      this.conexionMenu.add(this.fuertementeConexoMenuItem);
      this.fuertementeConexoMenuItem.setText("¿ Es fuertemente conexo ?");
      this.fuertementeConexoMenuItem.setEnabled(false);
      this.fuertementeConexoMenuItem.setBounds(-43, 79, 169, 21);
      this.fuertementeConexoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.fuertementeConexoMenuItemActionPerformed(evt);
         }
      });
      this.debilmenteConexoMenuItem = new JMenuItem();
      this.conexionMenu.add(this.debilmenteConexoMenuItem);
      this.debilmenteConexoMenuItem.setText("¿ Es débilmente conexo ?");
      this.debilmenteConexoMenuItem.setEnabled(false);
      this.debilmenteConexoMenuItem.setBounds(-43, 79, 169, 21);
      this.debilmenteConexoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.debilmenteConexoMenuItemActionPerformed(evt);
         }
      });
      this.compConexasMenuItem = new JMenuItem();
      this.compConexasMenuItem.setText("Componentes conexas");
      this.compConexasMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.compConexasMenuItemActionPerformed(evt);
         }
      });
      this.conexionMenu.add(this.compConexasMenuItem);
      this.compFuerteConexasMenuItem = new JMenuItem();
      this.compFuerteConexasMenuItem.setText("Componentes fuertemente conexas");
      this.compFuerteConexasMenuItem.setEnabled(false);
      this.compFuerteConexasMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.compFuerteConexasMenuItemActionPerformed(evt);
         }
      });
      this.conexionMenu.add(this.compFuerteConexasMenuItem);
      this.desigualdadMenuItem = new JMenuItem();
      this.desigualdadMenuItem.setText("Desigualdad triangular");
      this.desigualdadMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.desigualdadMenuItemActionPerformed(evt);
         }
      });
      this.grafoMenu.add(this.desigualdadMenuItem);
      this.jSeparator5 = new JSeparator();
      this.grafoMenu.add(this.jSeparator5);
      this.grafoCompletoMenuItem = new JMenuItem();
      this.grafoMenu.add(this.grafoCompletoMenuItem);
      this.grafoCompletoMenuItem.setText("Generar grafo completo");
      this.grafoCompletoMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2k.png")));
      this.grafoCompletoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.grafoCompletoMenuItemActionPerformed(evt);
         }
      });
      this.matrizAdyMenuItem = new JMenuItem();
      this.grafoMenu.add(this.matrizAdyMenuItem);
      this.matrizAdyMenuItem.setText("Obtener matriz del grafo");
      this.matrizAdyMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2matriz.png")));
      this.matrizAdyMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.matrizAdyMenuItemActionPerformed(evt);
         }
      });
      this.jSeparator3 = new JSeparator();
      this.grafoMenu.add(this.jSeparator3);
      this.mathematicaMenuItem = new JMenuItem();
      this.mathematicaMenuItem.setText("Mathematica 4.0");
      this.mathematicaMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2mathematica.png")));
      this.mathematicaMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.mathematicaMenuItemActionPerformed(evt);
         }
      });
      this.grafoMenu.add(this.mathematicaMenuItem);
      this.jMenu2 = new JMenu();
      this.MenuBar.add(this.jMenu2);
      this.jMenu2.setText("Algoritmos  ");
      this.ejecutarMenuItem = new JMenuItem();
      this.jMenu2.add(this.ejecutarMenuItem);
      this.ejecutarMenuItem.setText("Ejecutar");
      this.ejecutarMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2play.png")));
      this.ejecutarMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ejecutarMenuItemActionPerformed(evt);
         }
      });
      this.porpasosMenuItem = new JMenuItem();
      this.jMenu2.add(this.porpasosMenuItem);
      this.porpasosMenuItem.setEnabled(false);
      this.porpasosMenuItem.setText("...por pasos");
      this.porpasosMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2pasos.png")));
      this.porpasosMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.porpasosMenuItemActionPerformed(evt);
         }
      });
      this.jSeparator2 = new JSeparator();
      this.jMenu2.add(this.jSeparator2);
      this.bfsRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.bfsRadioButton);
      this.bfsRadioButton.setText("BFS - Búsqueda en anchura");
      this.bfsRadioButton.setSelected(true);
      this.dfsRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.dfsRadioButton);
      this.dfsRadioButton.setText("DFS - Búsqueda en profundidad");
      this.dijkstraRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.dijkstraRadioButton);
      this.dijkstraRadioButton.setText("Dijkstra - Camino más corto");
      this.kruskalRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.kruskalRadioButton);
      this.kruskalRadioButton.setText("Kruskal - Árbol de expansión mínima");
      this.edmons2RadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.edmons2RadioButton);
      this.edmons2RadioButton.setText("Edmonds 2 - Emparejamientos");
      this.hierholzerRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.hierholzerRadioButton);
      this.hierholzerRadioButton.setText("Hierholzer - Ciclo euleriano");
      this.carteroChinoRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.carteroChinoRadioButton);
      this.carteroChinoRadioButton.setText("Cartero Chino");
      this.etiquetajeRadioButton = new JRadioButtonMenuItem();
      this.jMenu2.add(this.etiquetajeRadioButton);
      this.etiquetajeRadioButton.setText("Ford-Fulkerson - Flujo máximo");
      this.cicloHamiltonianoRadioButtonMenuItem = new JRadioButtonMenuItem();
      this.cicloHamiltonianoRadioButtonMenuItem.setText("Ciclo hamiltoniano de 'bajo' peso");
      this.jMenu2.add(this.cicloHamiltonianoRadioButtonMenuItem);
      this.cicloHamoltonMinimoRadioButton = new JRadioButtonMenuItem();
      this.cicloHamoltonMinimoRadioButton.setText("Ciclo hamiltoniano de mínimo peso");
      this.jMenu2.add(this.cicloHamoltonMinimoRadioButton);
      this.orientabilidadRadioButton = new JRadioButtonMenuItem();
      this.orientabilidadRadioButton.setText("Hopcroft-Tarjan");
      this.jMenu2.add(this.orientabilidadRadioButton);
      this.groupAlgoritmos.add(this.edmons2RadioButton);
      this.edmons2RadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.edmons2RadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.carteroChinoRadioButton);
      this.carteroChinoRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.carteroChinoRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.dijkstraRadioButton);
      this.dijkstraRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstraRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.bfsRadioButton);
      this.bfsRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.bfsRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.dfsRadioButton);
      this.dfsRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dfsRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.hierholzerRadioButton);
      this.hierholzerRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.hierholzerRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.etiquetajeRadioButton);
      this.etiquetajeRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.etiquetajeRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.kruskalRadioButton);
      this.kruskalRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskalRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.cicloHamiltonianoRadioButtonMenuItem);
      this.cicloHamiltonianoRadioButtonMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.cicloHamiltonianoRadioButtonMenuItemActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.cicloHamoltonMinimoRadioButton);
      this.cicloHamoltonMinimoRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.cicloHamoltonMinimoRadioButtonActionPerformed(evt);
         }
      });
      this.groupAlgoritmos.add(this.orientabilidadRadioButton);
      this.orientabilidadRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.orientabilidadRadioButtonActionPerformed(evt);
         }
      });
      this.salirMenuItem = new JMenuItem();
      this.jMenu1.add(this.salirMenuItem);
      this.salirMenuItem.setText("Salir");
      this.salirMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.salirMenuItemActionPerformed(evt);
         }
      });

      this.jMenu1.add(new JSeparator());

      this.deshacerMenuItem = new JMenuItem();
      this.jMenu1.add(this.deshacerMenuItem);
      this.deshacerMenuItem.setText("Deshacer");
      this.deshacerMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.panel.deshacer();
         }
      });

      this.rehacerMenuItem = new JMenuItem();
      this.jMenu1.add(this.rehacerMenuItem);
      this.rehacerMenuItem.setText("Rehacer");
      this.rehacerMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.panel.rehacer();
         }
      });
      this.ejemplosMenu = new JMenu();
      this.MenuBar.add(this.ejemplosMenu);
      this.ejemplosMenu.setText("Ejemplos   ");
      this.bfsdfsEjemplosMenu = new JMenu();
      this.bfsdfsEjemplosMenu.setText("BFS y DFS");
      this.ejemplosMenu.add(this.bfsdfsEjemplosMenu);
      this.ejemploBFS1MenuItem = new JMenuItem();
      this.ejemploBFS1MenuItem.setText("Árbol dirigido");
      this.ejemploBFS1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ejemploBFS1MenuItemActionPerformed(evt);
         }
      });
      this.bfsdfsEjemplosMenu.add(this.ejemploBFS1MenuItem);
      this.ejemploBFS2MenuItem = new JMenuItem();
      this.ejemploBFS2MenuItem.setText("Árbol no dirigido");
      this.ejemploBFS2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ejemploBFS2MenuItemActionPerformed(evt);
         }
      });
      this.bfsdfsEjemplosMenu.add(this.ejemploBFS2MenuItem);
      this.dijkstraEjemplosMenu = new JMenu();
      this.dijkstraEjemplosMenu.setText("Dijkstra");
      this.ejemplosMenu.add(this.dijkstraEjemplosMenu);
      this.dijkstra1MenuItem = new JMenuItem();
      this.dijkstra1MenuItem.setText("Ejemplo 1 (GND)");
      this.dijkstra1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstra1MenuItemActionPerformed(evt);
         }
      });
      this.dijkstraEjemplosMenu.add(this.dijkstra1MenuItem);
      this.dijkstra2MenuItem = new JMenuItem();
      this.dijkstra2MenuItem.setText("Ejemplo 2 (GND)");
      this.dijkstra2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstra2MenuItemActionPerformed(evt);
         }
      });
      this.dijkstraEjemplosMenu.add(this.dijkstra2MenuItem);
      this.dijkstra3MenuItem = new JMenuItem();
      this.dijkstra3MenuItem.setText("Ejemplo 3 (GD)");
      this.dijkstra3MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstra3MenuItemActionPerformed(evt);
         }
      });
      this.dijkstraEjemplosMenu.add(this.dijkstra3MenuItem);
      this.dijkstra4MenuItem = new JMenuItem();
      this.dijkstra4MenuItem.setText("Ejemplo 4 (GD)");
      this.dijkstra4MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstra4MenuItemActionPerformed(evt);
         }
      });
      this.dijkstraEjemplosMenu.add(this.dijkstra4MenuItem);
      this.espanyaMenuItem = new JMenuItem();
      this.espanyaMenuItem.setText("Mapa de España");
      this.espanyaMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.espanyaMenuItemActionPerformed(evt);
         }
      });
      this.dijkstraEjemplosMenu.add(this.espanyaMenuItem);
      this.kruskalEjemplosMenu = new JMenu();
      this.kruskalEjemplosMenu.setText("Kruskal");
      this.ejemplosMenu.add(this.kruskalEjemplosMenu);
      this.kruskal1MenuItem = new JMenuItem();
      this.kruskal1MenuItem.setText("Ejemplo 1 (Conexo)");
      this.kruskal1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal1MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal1MenuItem);
      this.kruskal2MenuItem = new JMenuItem();
      this.kruskal2MenuItem.setText("Ejemplo 2 (Conexo)");
      this.kruskal2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal2MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal2MenuItem);
      this.kruskal3MenuItem = new JMenuItem();
      this.kruskal3MenuItem.setText("Ejemplo 3 (No conexo)");
      this.kruskal3MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal3MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal3MenuItem);
      this.kruskal4MenuItem = new JMenuItem();
      this.kruskal4MenuItem.setText("Ejemplo 4 (No conexo)");
      this.kruskal4MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal4MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal4MenuItem);
      this.kruskal5MenuItem = new JMenuItem();
      this.kruskal5MenuItem.setText("Pesos distintos");
      this.kruskal5MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal5MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal5MenuItem);
      this.kruskal6MenuItem = new JMenuItem();
      this.kruskal6MenuItem.setText("Pesos repetidos, árbol no único");
      this.kruskal6MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal6MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal6MenuItem);
      this.kruskal7MenuItem = new JMenuItem();
      this.kruskal7MenuItem.setText("Pesos repetidos, árbol único");
      this.kruskal7MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kruskal7MenuItemActionPerformed(evt);
         }
      });
      this.kruskalEjemplosMenu.add(this.kruskal7MenuItem);
      this.Edmonds2EjemplosMenu = new JMenu();
      this.Edmonds2EjemplosMenu.setText("Edmonds 2");
      this.ejemplosMenu.add(this.Edmonds2EjemplosMenu);
      this.edmonds1MenuItem = new JMenuItem();
      this.edmonds1MenuItem.setText("Ejemplo 1");
      this.edmonds1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.edmonds1MenuItemActionPerformed(evt);
         }
      });
      this.Edmonds2EjemplosMenu.add(this.edmonds1MenuItem);
      this.edmonds2MenuItem = new JMenuItem();
      this.edmonds2MenuItem.setText("Futbol Sala FCB");
      this.edmonds2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.edmonds2MenuItemActionPerformed(evt);
         }
      });
      this.Edmonds2EjemplosMenu.add(this.edmonds2MenuItem);
      this.hierholzerEjemplosMenu = new JMenu();
      this.hierholzerEjemplosMenu.setText("Hierholzer");
      this.ejemplosMenu.add(this.hierholzerEjemplosMenu);
      this.hierholzer1MenuItem = new JMenuItem();
      this.hierholzer1MenuItem.setText("Ejemplo 1 (GND)");
      this.hierholzer1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.hierholzer1MenuItemActionPerformed(evt);
         }
      });
      this.hierholzerEjemplosMenu.add(this.hierholzer1MenuItem);
      this.hierholzer2MenuItem = new JMenuItem();
      this.hierholzer2MenuItem.setText("Ejemplo 2 (GND)");
      this.hierholzer2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.hierholzer2MenuItemActionPerformed(evt);
         }
      });
      this.hierholzerEjemplosMenu.add(this.hierholzer2MenuItem);
      this.hierholzer3MenuItem = new JMenuItem();
      this.hierholzer3MenuItem.setText("Ejemplo 3 (GD)");
      this.hierholzer3MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.hierholzer3MenuItemActionPerformed(evt);
         }
      });
      this.hierholzerEjemplosMenu.add(this.hierholzer3MenuItem);
      this.hierholzer4MenuItem = new JMenuItem();
      this.hierholzer4MenuItem.setText("Ejemplo 4 (GD)");
      this.hierholzer4MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.hierholzer4MenuItemActionPerformed(evt);
         }
      });
      this.hierholzerEjemplosMenu.add(this.hierholzer4MenuItem);
      this.carteroChinoEjemplosMenu = new JMenu();
      this.carteroChinoEjemplosMenu.setText("Cartero Chino");
      this.ejemplosMenu.add(this.carteroChinoEjemplosMenu);
      this.centroValenciaMenuItem = new JMenuItem();
      this.centroValenciaMenuItem.setText("Centro de Valencia");
      this.centroValenciaMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.centroValenciaMenuItemActionPerformed(evt);
         }
      });
      this.carteroChinoEjemplosMenu.add(this.centroValenciaMenuItem);
      this.flujoEjemplosMenu = new JMenu();
      this.flujoEjemplosMenu.setText("Ford-Fulkerson");
      this.ejemplosMenu.add(this.flujoEjemplosMenu);
      this.flujo1MenuItem = new JMenuItem();
      this.flujo1MenuItem.setText("Ejemplo 1");
      this.flujo1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.flujo1MenuItemActionPerformed(evt);
         }
      });
      this.flujoEjemplosMenu.add(this.flujo1MenuItem);
      this.flujo2MenuItem = new JMenuItem();
      this.flujo2MenuItem.setText("Ejemplo 2");
      this.flujo2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.flujo2MenuItemActionPerformed(evt);
         }
      });
      this.flujoEjemplosMenu.add(this.flujo2MenuItem);
      this.transporteMenuItem = new JMenuItem();
      this.transporteMenuItem.setText("Transporte");
      this.transporteMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.transporteMenuItemActionPerformed(evt);
         }
      });
      this.flujoEjemplosMenu.add(this.transporteMenuItem);
      this.hamilonianoEjemplosMenu = new JMenu();
      this.hamilonianoEjemplosMenu.setText("Hamiltonianos");
      this.ejemplosMenu.add(this.hamilonianoEjemplosMenu);
      this.viajante1MenuItem = new JMenuItem();
      this.viajante1MenuItem.setText("Viajante en Córdoba");
      this.viajante1MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.viajante1MenuItemActionPerformed(evt);
         }
      });
      this.hamilonianoEjemplosMenu.add(this.viajante1MenuItem);
      this.ayudaMenu = new JMenu();
      this.MenuBar.add(this.ayudaMenu);
      this.ayudaMenu.setText("Ayuda  ");
      this.manualUsuarioMenuItem = new JMenuItem();
      this.ayudaMenu.add(this.manualUsuarioMenuItem);
      this.manualUsuarioMenuItem.setText("Manual de usuario");
      this.manualUsuarioMenuItem
            .setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/2ayuda.png")));
      this.manualUsuarioMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.manualUsuarioMenuItemActionPerformed(evt);
         }
      });
      this.grafosAyudaMenu = new JMenu();
      this.ayudaMenu.add(this.grafosAyudaMenu);
      this.grafosAyudaMenu.setText("Grafos");
      this.generalidadesMenuItem = new JMenuItem();
      this.grafosAyudaMenu.add(this.generalidadesMenuItem);
      this.generalidadesMenuItem.setText("Generalidades");
      this.generalidadesMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.generalidadesMenuItemActionPerformed(evt);
         }
      });
      this.matrizAdyacenciaMenuItem = new JMenuItem();
      this.grafosAyudaMenu.add(this.matrizAdyacenciaMenuItem);
      this.matrizAdyacenciaMenuItem.setText("Matriz del grafo");
      this.matrizAdyacenciaMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.matrizAdyacenciaMenuItemActionPerformed(evt);
         }
      });
      this.ConexoMenuItem = new JMenuItem();
      this.grafosAyudaMenu.add(this.ConexoMenuItem);
      this.ConexoMenuItem.setText("Conectividad de grafos");
      this.ConexoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ConexoMenuItemActionPerformed(evt);
         }
      });
      this.algoritmosMenu = new JMenu();
      this.ayudaMenu.add(this.algoritmosMenu);
      this.algoritmosMenu.setText("Algoritmos");
      this.ayudaEdmons2MenuItem = new JMenuItem();
      this.ayudaEdmons2MenuItem.setText("Edmonds 2 - Emparejamientos");
      this.ayudaEdmons2MenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaEdmons2MenuItemActionPerformed(evt);
         }
      });
      this.ayudaCarteroChinoMenuItem = new JMenuItem();
      this.ayudaCarteroChinoMenuItem.setText("Cartero Chino");
      this.ayudaCarteroChinoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaCarteroChinoMenuItemActionPerformed(evt);
         }
      });
      this.dijkstra_ayudaMenuItem = new JMenuItem();
      this.dijkstra_ayudaMenuItem.setText("Dijkstra - Camino más corto");
      this.dijkstra_ayudaMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.dijkstra_ayudaMenuItemActionPerformed(evt);
         }
      });
      this.BFS_ayudajMenuItem8 = new JMenuItem();
      this.algoritmosMenu.add(this.BFS_ayudajMenuItem8);
      this.BFS_ayudajMenuItem8.setText("BFS - Búsqueda en anchura");
      this.BFS_ayudajMenuItem8.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.BFS_ayudajMenuItem8ActionPerformed(evt);
         }
      });
      this.ayudaDFSMenuItem = new JMenuItem();
      this.algoritmosMenu.add(this.ayudaDFSMenuItem);
      this.algoritmosMenu.add(this.dijkstra_ayudaMenuItem);
      this.ayudaDFSMenuItem.setText("DFS - Búsqueda en profundidad");
      this.ayudaDFSMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaDFSMenuItemActionPerformed(evt);
         }
      });
      this.ayudaHierholzerMenuItem = new JMenuItem();
      this.ayudaHierholzerMenuItem.setText("Hierholzer - Ciclo euleriano");
      this.ayudaHierholzerMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaHierholzerMenuItemActionPerformed(evt);
         }
      });
      this.ayudaEtiquetajeMenuItem = new JMenuItem();
      this.ayudaEtiquetajeMenuItem.setText("Ford-Fulkerson - Flujo máximo");
      this.ayudaEtiquetajeMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaEtiquetajeMenuItemActionPerformed(evt);
         }
      });
      this.ayudaHamiltonianoMenuItem = new JMenuItem();
      this.ayudaHamiltonianoMenuItem.setText("Ciclos hamiltonianos");
      this.ayudaHamiltonianoMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaHamiltonianoMenuItemActionPerformed(evt);
         }
      });
      this.ayudaOrientabilidadMenuItem = new JMenuItem();
      this.ayudaOrientabilidadMenuItem.setText("Hopcroft-Tarjan");
      this.ayudaOrientabilidadMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaOrientabilidadMenuItemActionPerformed(evt);
         }
      });
      this.ayudaKruskalMenuItem = new JMenuItem();
      this.algoritmosMenu.add(this.ayudaKruskalMenuItem);
      this.algoritmosMenu.add(this.ayudaEdmons2MenuItem);
      this.algoritmosMenu.add(this.ayudaHierholzerMenuItem);
      this.algoritmosMenu.add(this.ayudaCarteroChinoMenuItem);
      this.algoritmosMenu.add(this.ayudaEtiquetajeMenuItem);
      this.algoritmosMenu.add(this.ayudaHamiltonianoMenuItem);
      this.algoritmosMenu.add(this.ayudaOrientabilidadMenuItem);
      this.ayudaKruskalMenuItem.setText("Kruskal - Árbol de expansión mínima");
      this.ayudaKruskalMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaKruskalMenuItemActionPerformed(evt);
         }
      });
      this.jSeparator = new JSeparator();
      this.ayudaMenu.add(this.jSeparator);
      this.acercaDeMenuItem = new JMenuItem();
      this.ayudaMenu.add(this.acercaDeMenuItem);
      this.acercaDeMenuItem.setText("Acerca de...");
      this.acercaDeMenuItem.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("interfaz/img/3k.png")));
      this.acercaDeMenuItem.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.acercaDeMenuItemActionPerformed(evt);
         }
      });
      this.statusPanel = new JPanel();
      BorderLayout botonesPanelLayout = new BorderLayout();
      this.statusPanel.setLayout(botonesPanelLayout);
      this.getContentPane().add(this.statusPanel, "South");
      this.statusLabel = new JLabel();
      this.statusPanel.add(this.statusLabel, "South");
      this.statusLabel.setSize(1000, 25);
      this.statusLabel.setPreferredSize(new Dimension(1000, 25));
      this.modificarStatusBar("Inicio");
      this.carteroChinoPanel = new JPanel();
      BorderLayout izqPanelLayout = new BorderLayout();
      this.statusPanel.add(this.carteroChinoPanel, "North");
      this.carteroChinoPanel.setLayout(izqPanelLayout);
      this.carteroChinoPanel.setPreferredSize(new Dimension(1184, 110));
      this.carteroChinoPanel.setSize(1000, 120);
      this.carteroChinoPanel.setVisible(false);
      this.scroll = new JScrollPane();
      this.carteroChinoPanel.add(this.scroll, "Center");
      this.scroll.setPreferredSize(new Dimension(1184, 100));
      this.scroll.setVisible(true);
      this.scroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Camino del Cartero chino / Ciclo euleriano de Hierlholzer"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)), this.scroll.getBorder()));
      this.scroll.setSize(400, 100);
      this.scroll.setLocation(15, 570);
      this.scroll.setViewportView(this.panelresultados);
      this.panelresultados.setPreferredSize(new Dimension(1145, 45));
      this.panelresultados.setBackground(Color.WHITE);
      this.panelresultados.setSize(450, 75);
      this.botonesPanel = new JPanel();
      botonesPanelLayout = new BorderLayout();
      this.fondo.add(this.botonesPanel, "North");
      this.jSplitPane1 = new JSplitPane();
      this.infoPanel = new JPanel();
      this.jSplitPane1.add(this.infoPanel, "left");
      izqPanelLayout = new BorderLayout();
      this.infoPanel.setLayout(izqPanelLayout);
      this.infoPanel.setSize(500, 443);
      this.infoPanel.setPreferredSize(new Dimension(428, 298));
      this.hueco2Panel = new JPanel();
      this.infoPanel.add(this.hueco2Panel, "North");
      this.areaScrollPane = new JScrollPane(this.textoInformacion);
      this.infoPanel.add(this.areaScrollPane, "Center");
      this.areaScrollPane.setVisible(true);
      this.areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Panel información"),
                  BorderFactory.createEmptyBorder(5, 5, 5, 5)),
            this.areaScrollPane.getBorder()));
      this.areaScrollPane.getVerticalScrollBar().setSize(17, 61);
      this.areaScrollPane.setPreferredSize(new Dimension(522, 433));
      this.areaScrollPane.setMinimumSize(new Dimension(300, 300));
      this.grafoPanel = new JPanel();
      this.jSplitPane1.add(this.grafoPanel, "right");
      izqPanelLayout = new BorderLayout();
      this.grafoPanel.setLayout(izqPanelLayout);
      this.grafoPanel.setPreferredSize(new Dimension(500, 463));
      this.grafoPanel.setSize(500, 463);
      this.scrollPrincipal = new JScrollPane();
      this.grafoPanel.add(this.scrollPrincipal, "Center");
      this.scrollPrincipal.setPreferredSize(new Dimension(4000, 4000));
      this.scrollPrincipal.setMinimumSize(new Dimension(4000, 4000));
      this.scrollPrincipal.setSize(500, 400);
      this.scrollPrincipal.setVisible(true);
      this.scrollPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Vista del Grafo"),
                  BorderFactory.createEmptyBorder(5, 5, 5, 5)),
            this.scrollPrincipal.getBorder()));
      this.scrollPrincipal.setLocation(new Point(15, 400));
      this.scrollPrincipal.getVerticalScrollBar().setSize(17, 61);
      this.scrollPrincipal.getHorizontalScrollBar().setSize(new Dimension(17, 61));
      this.scrollPrincipal.setViewportView(this.panel);
      this.panel.setPreferredSize(new Dimension(4000, 4000));
      this.panel.setSize(4000, 4000);
      this.panel.setBackground(Color.WHITE);
      this.panel.setVisible(true);
      this.panel.revalidate();
      this.hueco1Panel = new JPanel();
      this.grafoPanel.add(this.hueco1Panel, "North");
      this.nodoAristaPanel = new JPanel();
      this.nodosRadioButton = new JRadioButton();
      this.nodosRadioButton.setText("Dibujar NODO");
      this.nodosRadioButton.setSelected(true);
      this.groupNodoArista.add(this.nodosRadioButton);
      this.nodosRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.nodosRadioButtonActionPerformed(evt);
         }
      });
      this.aristasRadioButton = new JRadioButton();
      this.aristasRadioButton.setText("Dibujar ARISTA");
      this.groupNodoArista.add(this.aristasRadioButton);
      this.aristasRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.aristasRadioButtonActionPerformed(evt);
         }
      });
      this.arcoRadioButton = new JRadioButton();
      this.arcoRadioButton.setText("Dibujar ARCO");
      this.groupNodoArista.add(this.arcoRadioButton);
      this.arcoRadioButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.arcoRadioButtonActionPerformed(evt);
         }
      });
      this.arcoRadioButton.setEnabled(false);
      ComboBoxModel tipoGrafoComboBoxModel = new DefaultComboBoxModel(
            new String[] { "Grafo No Dirigido", "Grafo Dirigido" });
      this.tipoGrafoComboBox = new JComboBox();
      this.tipoGrafoComboBox.setModel(tipoGrafoComboBoxModel);
      this.tipoGrafoComboBox.setPreferredSize(new Dimension(162, 20));
      this.nodoAristaPanel.add(this.tipoGrafoComboBox);
      this.tipoGrafoComboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.tipoGrafoComboBoxActionPerformed(evt);
         }
      });
      this.nodoAristaPanel.add(this.nodosRadioButton);
      this.nodoAristaPanel.add(this.aristasRadioButton);
      this.nodoAristaPanel.add(this.arcoRadioButton);
      this.guardarResultadoButton = new BotonImagen(
            new ImageIcon(this.getClass().getResource("/interfaz/img/2save.png")),
            "Guardar grafo resultado en archivo XML", "");
      this.guardarResultadoButton.setPreferredSize(new Dimension(36, 27));
      this.nodoAristaPanel.add(this.guardarResultadoButton);
      this.guardarResultadoButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.guardarResultadoButtonActionPerformed(evt);
         }
      });
      this.setEnabledGuardarResultadoButton(false);
      this.grafoPanel.add(this.nodoAristaPanel, "South");
      this.nodoAristaPanel.setSize(500, 33);
      this.algoritmoSelectPanel = new JPanel();
      this.algoritmoSelectLabel = new JLabel();
      this.algoritmoSelectLabel.setText("Algoritmo seleccionado: ");
      this.algoritmoSelectLabel.setPreferredSize(new Dimension(140, 20));
      this.algoritmoLabel = new JLabel();
      this.algoritmoLabel.setFont(new Font("Tahoma", 1, 16));
      this.algoritmoSelectPanel.add(this.algoritmoSelectLabel);
      this.algoritmoSelectPanel.add(this.algoritmoLabel);
      this.algoritmoLabel.setText("BFS - Búsqueda en anchura");
      this.algoritmoLabel.setPreferredSize(new Dimension(300, 20));
      this.grafoPanel.add(this.algoritmoSelectPanel, "North");
      this.fondo.add(this.jSplitPane1, "Center");
      this.jSplitPane1.setBorder(BorderFactory.createTitledBorder(""));
      this.botonesPanel.setLayout(botonesPanelLayout);
      this.barra = new JToolBar();
      this.botonesPanel.add(this.barra, "Center");
      this.abrir = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/open.png")),
            "Abre un archivo con un grafo guardado", " Abrir grafo   ");
      this.barra.add(this.abrir);
      this.abrir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.abrirActionPerformed(evt);
         }
      });
      this.guardar = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/save.png")),
            "Guardar grafo en archivo XML", "    Guardar grafo  ");
      this.barra.add(this.guardar);
      this.guardar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.guardarActionPerformed(evt);
         }
      });
      this.play = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/play.png")),
            "Ejecutar algoritmo", "     Ejecutar    ");
      this.barra.add(this.play);
      this.play.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.playActionPerformed(evt);
         }
      });
      this.pasos = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/pasos.png")),
            "Ejecutar por pasos", "   ...por pasos  ");
      this.barra.add(this.pasos);
      this.pasos.setEnabled(false);
      this.pasos.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.pasosActionPerformed(evt);
         }
      });
      this.kGrafo = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/k.png")),
            "Genera un grafo completo", "  Grafo completo ");
      this.barra.add(this.kGrafo);
      this.kGrafo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.kGrafoActionPerformed(evt);
         }
      });
      this.matriz = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/matriz.png")),
            "Abre la matriz del grafo", " Matriz del grafo");
      this.barra.add(this.matriz);
      this.matriz.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.matrizActionPerformed(evt);
         }
      });
      this.limpiar = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/limpia.png")),
            "Limpia totalmente los paneles de la aplicación", "  Borrar paneles ");
      this.barra.add(this.limpiar);
      this.limpiar.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.limpiarActionPerformed(evt);
         }
      });
      this.ayuda = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/ayuda.png")),
            "Manual de usuario de la aplicación", "       Ayuda     ");
      this.barra.add(this.ayuda);
      this.ayuda.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            MenuPrincipal.this.ayudaActionPerformed(evt);
         }
      });
      this.algoritmoPanel = new JPanel();
      FlowLayout algoritmoPanelLayout = new FlowLayout();
      this.algoritmoPanel.setPreferredSize(new Dimension(203, 74));
      this.algoritmoPanel.setLayout(algoritmoPanelLayout);
      this.barra.add(this.algoritmoPanel);
      this.barra.setFloatable(false);
      this.setDefaultCloseOperation(0);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            MenuPrincipal.this.windowClosingPerformed(e);
         }
      });
      this.setIconImage((new ImageIcon(this.getClass().getResource("/interfaz/img/k.png"))).getImage());
      this.setTitle("SWGraphs 2.1.9");
      this.setVisible(true);
      this.textoInformacion.requestFocus();
   }

   private void abrirMenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("");
   }

   private void guardarMenuItemActionPerformed(ActionEvent evt) {
      this.guardarGrafo(this.panel.getGrafo());
   }

   private void fondoMenuItemActionPerformed(ActionEvent evt) throws IOException {
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(0);
      GrafoIMGFileFilter filtro = new GrafoIMGFileFilter();
      fc.setFileFilter(filtro);
      if (this.ruta != null) {
         fc.setCurrentDirectory(this.ruta);
      }

      int returnVal = fc.showOpenDialog(this);
      if (returnVal == 0) {
         this.modificarStatusBar("La imagen de fondo ha sido cargada correctamente");
         this.panel.imagen = ImageIO.read(new File(fc.getSelectedFile().getPath()));
         this.ruta = fc.getCurrentDirectory();
         this.panel.repaint();
      }

   }

   private void borrarFondoMenuItemActionPerformed(ActionEvent evt) {
      this.panel.imagen = null;
      this.redibujarGrafo();
      this.modificarStatusBar("La imagen de fondo ha sido borrada correctamente");
   }

   private void salirMenuItemActionPerformed(ActionEvent evt) {
      this.SalirAplicacion();
   }

   private void conexoMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (this.panel.getGrafo().esConexo()) {
            JOptionPane.showMessageDialog(this, "El grafo introducido SI es conexo", "¿Grafo conexo?", 1);
         } else {
            JOptionPane.showMessageDialog(this, "El grafo introducido NO es conexo", "¿Grafo conexo?", 1);
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void fuertementeConexoMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (this.panel.getGrafo().esFuertementeConexo()) {
            JOptionPane.showMessageDialog(this, "El grafo introducido SI es fuertemente conexo",
                  "¿Grafo fuertemente conexo?", 1);
         } else {
            JOptionPane.showMessageDialog(this, "El grafo introducido NO es fuertemente conexo",
                  "¿Grafo fuertemente conexo?", 1);
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void debilmenteConexoMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (this.panel.getGrafo().esDebilmenteConexo()) {
            JOptionPane.showMessageDialog(this, "El grafo introducido SI es débilmente conexo",
                  "¿Grafo débilmente conexo?", 1);
         } else {
            JOptionPane.showMessageDialog(this, "El grafo introducido NO es débilmente conexo",
                  "¿Grafo débilmente conexo?", 1);
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void subyacenteMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         int seleccion = JOptionPane.showOptionDialog(this, "¿Desea convertir el grafo en su grafo subyacente?",
               "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
         if (seleccion != -1 && seleccion + 1 == 1) {
            if (this.panel.getGrafo().getTipo() == 0) {
               this.getGrafo().marcarTodosLosPesosAUno();
            } else {
               this.getGrafo().convertirAGrafoNOdirigido();
               this.getGrafo().marcarTodosLosPesosAUno();
               this.tipoGrafoComboBox.setSelectedIndex(0);
            }
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void informacionMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         this.textoInformacion.setText("INFORMACIÓN DEL GRAFO:\n\n");
         if (this.panel.getGrafo().getTipo() == 0) {
            this.textoInformacion.append("Grafo no dirigido");
            if (this.panel.getGrafo().esConexo()) {
               this.textoInformacion.append(", conexo\n\n");
            } else {
               this.textoInformacion.append(", no conexo\n\n");
            }
         } else {
            this.textoInformacion.append("Grafo dirigido");
            if (this.panel.getGrafo().esFuertementeConexo()) {
               this.textoInformacion.append(", fuertemente conexo\n\n");
            } else if (this.panel.getGrafo().esDebilmenteConexo()) {
               this.textoInformacion.append(", debilmente conexo\n\n");
            }
         }

         this.textoInformacion.append("Número de vértices: " + this.panel.getGrafo().getNodos().size() + "\n");
         this.textoInformacion.append("Número de aristas: " + this.panel.getGrafo().getAristas().size() + "\n\n");
         this.textoInformacion.append("Peso del grafo: " + this.panel.getGrafo().obtenerPesoGrafo() + "\n\n");
         if (this.panel.getGrafo().getTipo() == 0) {
            if (this.panel.getGrafo().hayAristasDeCorte()) {
               this.textoInformacion.append("SI hay aristas de corte.\n");
            } else {
               this.textoInformacion.append("NO hay aristas de corte.\n");
            }

            if (this.panel.getGrafo().hayVerticesDeCorte()) {
               this.textoInformacion.append("SI hay vértices de corte.\n");
            } else {
               this.textoInformacion.append("NO hay vértices de corte.\n");
            }
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void gradosMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         this.textoInformacion.setText("Grado de los vértices:\n");
         if (this.AlgoritmoSeleccionado == 6) {
            this.panel.getGrafo().pasarDePesosAMultiplicidades();
         }

         int i;
         if (this.panel.getGrafo().getTipo() == 0) {
            this.textoInformacion.append("\nGrafo No Dirigido\n");

            for (i = 0; i < this.panel.getGrafo().getNodos().size(); ++i) {
               this.textoInformacion.append("\nNodo " + this.panel.getGrafo().getNodoByIndex(i).getNombre() + ": Grado "
                     + this.panel.getGrafo().getGradoIndex(i));
            }
         } else {
            this.textoInformacion.append("\nGrafo Dirigido\n");

            for (i = 0; i < this.panel.getGrafo().getNodos().size(); ++i) {
               this.textoInformacion.append("\nNodo " + this.panel.getGrafo().getNodoByIndex(i).getNombre()
                     + ":\n       Grado de entrada " + this.panel.getGrafo().getGradoEntradaIndex(i)
                     + "\n       Grado   de  salida " + this.panel.getGrafo().getGradoSalidaIndex(i));
            }
         }

         if (this.AlgoritmoSeleccionado == 6) {
            this.panel.getGrafo().pasarDeMultiplicidadesAPesos();
         }

         this.modificarStatusBar("Se ha listado el grafo de los vértices correctamente");
      } else {
         this.errorNoGrafo();
      }

   }

   private void desigualdadMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (this.panel.getGrafo().verificaDesigualdadTriangular()) {
            JOptionPane.showMessageDialog(this, "El grafo introducido SI cumple la desigualdad triangular",
                  "Desigualdad triangular", 1);
         } else {
            JOptionPane.showMessageDialog(this, "El grafo introducido NO cumple la desigualdad triangular",
                  "Desigualdad triangular", 1);
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void compConexasMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         JOptionPane.showMessageDialog(this,
               "El número de componentes conexas son:  " + this.panel.getGrafo().cardComponentesConexas(),
               "Componentes conexas", 1);
      } else {
         this.errorNoGrafo();
      }

   }

   private void compFuerteConexasMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         JOptionPane.showMessageDialog(this, "El número de componentes fuertemente conexas son:  "
               + this.panel.getGrafo().cardComponentesFuerteConexas(), "Componentes fuertemente conexas", 1);
      } else {
         this.errorNoGrafo();
      }

   }

   private void grafoCompletoMenuItemActionPerformed(ActionEvent evt) {
      this.dibujarKGrafo(1);
   }

   private void matrizAdyMenuItemActionPerformed(ActionEvent evt) {
      this.abrirMatrizAdyacencia();
   }

   private void mathematicaMenuItemActionPerformed(ActionEvent evt) {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         this.textoInformacion.setText("El código Mathematica del grafo es el siguiente:\n \n");
         this.textoInformacion.append(this.panel.getGrafo().toMathematica());
         this.textoInformacion.append(
               "\n \nNOTA: Este código sólo es válido para la versión 4.0 de Mathematica, con el módulo 'grafos.m' de la UPV");
         this.modificarStatusBar("El código para Mathematica 4.0 ha sido generado correctamente");
      } else {
         this.errorNoGrafo();
      }

   }

   private void ejecutarMenuItemActionPerformed(ActionEvent evt) {
      this.ejecutarAlgoritmoContinuo();
   }

   private void porpasosMenuItemActionPerformed(ActionEvent evt) {
      this.ejecutarPaso();
   }

   private void escogerAlgoritmo(int algoritmo, boolean carteroChino, String textoLabel, boolean porPasos) {
      this.setAlgoritmoSeleccionado(algoritmo);
      this.carteroChinoPanel.setVisible(carteroChino);
      this.pasos.setEnabled(porPasos);
      this.porpasosMenuItem.setEnabled(porPasos);
      this.algoritmoLabel.setText(textoLabel);
   }

   private void edmons2RadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(1, false, "Edmonds 2 - Emparejamientos", true);
      if (this.panel.getGrafo().getNodos().size() > 0) {
         this.panel.getGrafo().convertirAEdmonds();
         this.algEd2 = new Edmonds2(this.panel, this.textoInformacion);
      }

   }

   private void carteroChinoRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(2, true, "Cartero Chino", true);
      this.algCh = new CarteroChino(this.panel, this.panelresultados, this.textoInformacion);
   }

   private void dijkstraRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(3, false, "Dijkstra - Camino más corto", false);
   }

   private void bfsRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(4, false, "BFS - Búsqueda en anchura", false);
   }

   private void dfsRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(5, false, "DFS - Búsqueda en profundidad", false);
   }

   private void hierholzerRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(6, true, "Hierholzer - Ciclo euleriano", false);
   }

   private void etiquetajeRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(7, false, "Ford-Fulkerson - Flujo máximo", false);
   }

   private void kruskalRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(8, false, "Kruskal - Árbol de expansión mínima", false);
   }

   private void cicloHamiltonianoRadioButtonMenuItemActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(10, false, "Ciclo hamiltoniano de 'bajo' peso", false);
   }

   private void cicloHamoltonMinimoRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(14, false, "Ciclo hamiltoniano de mínimo peso", false);
   }

   private void orientabilidadRadioButtonActionPerformed(ActionEvent evt) {
      this.escogerAlgoritmo(15, false, "Hopcroft-Tarjan", false);
   }

   private void ejemploBFS1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/arbolDirigido.xml");
   }

   private void ejemploBFS2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/arbolNoDirigido.xml");
   }

   private void kruskal1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal1.xml");
   }

   private void kruskal2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal2.xml");
   }

   private void kruskal3MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal3.xml");
   }

   private void kruskal4MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal4.xml");
   }

   private void kruskal5MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal5.xml");
   }

   private void kruskal6MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal6.xml");
   }

   private void kruskal7MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/kruskal7.xml");
   }

   private void dijkstra1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/dijkstra1.xml");
   }

   private void dijkstra2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/dijkstra2.xml");
   }

   private void dijkstra3MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/dijkstra3.xml");
   }

   private void dijkstra4MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/dijkstra4.xml");
   }

   private void espanyaMenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/spainkm.xml");
   }

   private void edmonds1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/edmonds1.xml");
   }

   private void edmonds2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/futbolSalaFCB.xml");
   }

   private void centroValenciaMenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/centroValencia.xml");
   }

   private void hierholzer1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/hierholzer1.xml");
   }

   private void hierholzer2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/hierholzer2.xml");
   }

   private void hierholzer3MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/hierholzer3.xml");
   }

   private void hierholzer4MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/hierholzer4.xml");
   }

   private void flujo1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/flujo1.xml");
   }

   private void flujo2MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/flujo2.xml");
   }

   private void transporteMenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/flujo4-Transporte.xml");
   }

   private void viajante1MenuItemActionPerformed(ActionEvent evt) {
      this.abrirGrafo("ejemplos/cordoba.xml");
   }

   private void manualUsuarioMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(11);
   }

   private void acercaDeMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(13);
   }

   private void generalidadesMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(16);
   }

   private void matrizAdyacenciaMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(12);
   }

   private void ConexoMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(9);
   }

   private void ayudaEdmons2MenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(1);
   }

   private void ayudaCarteroChinoMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(2);
   }

   private void dijkstra_ayudaMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(3);
   }

   private void BFS_ayudajMenuItem8ActionPerformed(ActionEvent evt) {
      new DialogoAyuda(4);
   }

   private void ayudaDFSMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(5);
   }

   private void ayudaHierholzerMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(6);
   }

   private void ayudaEtiquetajeMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(7);
   }

   private void ayudaKruskalMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(8);
   }

   private void ayudaHamiltonianoMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(10);
   }

   private void ayudaOrientabilidadMenuItemActionPerformed(ActionEvent evt) {
      new DialogoAyuda(15);
   }

   private void abrirActionPerformed(ActionEvent evt) {
      this.abrirGrafo("");
   }

   private void guardarActionPerformed(ActionEvent evt) {
      this.guardarGrafo(this.panel.getGrafo());
   }

   private void playActionPerformed(ActionEvent evt) {
      this.ejecutarAlgoritmoContinuo();
   }

   private void pasosActionPerformed(ActionEvent evt) {
      this.ejecutarPaso();
   }

   private void kGrafoActionPerformed(ActionEvent evt) {
      this.dibujarKGrafo(1);
   }

   private void matrizActionPerformed(ActionEvent evt) {
      this.abrirMatrizAdyacencia();
      this.redibujarGrafo();
   }

   private void limpiarActionPerformed(ActionEvent evt) {
      this.limpiarPanelGrafos();
   }

   private void ayudaActionPerformed(ActionEvent evt) {
      new DialogoAyuda(11);
   }

   private void abrirGrafo(String grafo) {
      boolean abrirXML = true;
      boolean abrirOtroXML = true;
      boolean alg_edmonds2 = false;
      boolean alg_cartero = false;
      boolean abrirGrafoOK = false;
      Grafo G = null;
      switch (this.getAlgoritmoSeleccionado()) {
         case 1:
            abrirXML = this.algEd2 == null || !this.algEd2.enProceso();
            alg_edmonds2 = true;
            break;
         case 2:
            abrirXML = this.algCh == null || !this.algCh.enProceso();
            alg_cartero = true;
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
      }

      if (abrirXML) {
         if (this.panel.getGrafo().getNodos().size() > 0) {
            int seleccion = JOptionPane.showOptionDialog(this,
                  "¿Desea abrir otro grafo?\n\nNOTA:\nEl grafo actual no se podrá recuperar, guarde el grafo si lo quiere utilizar más tarde",
                  "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
            if (seleccion != -1) {
               if (seleccion + 1 == 1) {
                  abrirOtroXML = true;
               } else {
                  abrirOtroXML = false;
               }
            }
         }

         if (abrirOtroXML) {
            if (grafo.equals("")) {
               try {
                  JFileChooser fc = new JFileChooser();
                  GrafoXMLFileFilter filtro = new GrafoXMLFileFilter();
                  fc.setFileFilter(filtro);
                  if (this.ruta != null) {
                     fc.setCurrentDirectory(this.ruta);
                  }

                  int returnVal = fc.showOpenDialog(this);
                  if (returnVal == 0) {
                     G = Util.leerGrafo(fc.getSelectedFile(), "");
                     this.ruta = fc.getCurrentDirectory();
                     abrirGrafoOK = true;
                  }
               } catch (FactoryConfigurationError var11) {
                  var11.printStackTrace();
               }
            } else {
               G = Util.leerGrafo((File) null, grafo);
               abrirGrafoOK = true;
            }

            if (abrirGrafoOK) {
               this.panel.setGrafo(G);
               if (alg_edmonds2) {
                  this.panel.setGrafo(G.convertirAEdmonds());
                  this.algEd2 = new Edmonds2(this.panel, this.textoInformacion);
               }

               if (alg_cartero) {
                  this.algCh = new CarteroChino(this.panel, this.panelresultados, this.textoInformacion);
               }

               this.tipoGrafoComboBox.setSelectedIndex(G.getTipo());
               this.aristasRadioButton.setEnabled(G.getTipo() == 0);
               this.arcoRadioButton.setEnabled(G.getTipo() == 1);
               this.redibujarGrafo();
               this.setEnabledGuardarResultadoButton(false);
               this.modificarStatusBar("El grafo ha sido cargado correctamente");
            }
         }
      } else {
         JOptionPane.showMessageDialog(this,
               "No se permite abrir otro grafo hasta terminar la ejecución actual del algoritmo.",
               "ALGORITMO NO TERMINADO", 2);
      }

   }

   private void guardarGrafo(Grafo grafoGuardar) {
      if (grafoGuardar.getNodos().size() > 0) {
         grafoGuardar.setTipo(this.tipoGrafoComboBox.getSelectedIndex());
         JFileChooser fc = new JFileChooser();
         GrafoXMLFileFilter filtro = new GrafoXMLFileFilter();
         fc.setFileFilter(filtro);
         if (this.ruta != null) {
            fc.setCurrentDirectory(this.ruta);
         }

         int returnVal = fc.showSaveDialog(this);
         this.ruta = fc.getCurrentDirectory();
         if (returnVal == 0) {
            File fichero = fc.getSelectedFile();
            if (!fichero.exists()) {
               if (!Util.escribirGrafo(fc.getSelectedFile(), grafoGuardar)) {
                  JOptionPane.showMessageDialog(this, "Imposible guardar los datos, fallo de Entrada/Salida", "ERROR",
                        0);
               }
            } else {
               int seleccion = JOptionPane.showOptionDialog(this, "El grafo XML ya existe,\n¿Desea reemplazarlo?",
                     "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
               if (seleccion != -1) {
                  if (seleccion + 1 == 1) {
                     if (!Util.escribirGrafo(fichero, grafoGuardar)) {
                        JOptionPane.showMessageDialog(this, "Imposible guardar los datos, fallo de Entrada/Salida",
                              "ERROR", 0);
                     }
                  } else {
                     this.guardarGrafo(grafoGuardar);
                  }
               }
            }

            this.modificarStatusBar("El grafo ha sido guardado correctamente");
         }
      } else {
         JOptionPane.showMessageDialog(this, "No se permite guardar grafos sin nodos", "ERROR", 2);
      }

   }

   private void ejecutarAlgoritmoContinuo() {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         switch (this.getAlgoritmoSeleccionado()) {
            case 1:
               if (this.panel.getGrafo().getTipo() == 0) {
                  if (!this.panel.getGrafo().hayBucles()) {
                     this.ejecutarAlgoritmoContinuoEdmonds2();
                     this.setEnabledGuardarResultadoButton(false);
                  } else {
                     JOptionPane.showMessageDialog(this,
                           "El algoritmo Edmonds 2 no permite bucles en el grafo para su ejecución",
                           "Ciclos en el grafo", 2);
                  }
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo Edmonds2 sólo está disponible para Grafos No Dirigidos", "Grafo Dirigido", 2);
               }
               break;
            case 2:
               if (this.panel.getGrafo().getTipo() == 0) {
                  if (!this.panel.getGrafo().hayBucles()) {
                     if (!this.panel.getGrafo().existenPesosNegativos()) {
                        if (!this.panel.getGrafo().esEuleriano()) {
                           this.ejecutarAlgoritmoContinuoCarteroChino();
                           this.setEnabledGuardarResultadoButton(false);
                        } else {
                           JOptionPane.showMessageDialog(this,
                                 "El grafo es euleriano. Por favor, aplique Hierholzer.\n\nEl recorrido buscado es cualquier ciclo euleriano y\nsu peso la suma de todas las aristas del grafo",
                                 "Grafo euleriano", 2);
                        }
                     } else {
                        JOptionPane.showMessageDialog(this,
                              "El algoritmo del Cartero Chino no permite pesos negativos en el grafo.\nPor favor, revise los pesos del grafo",
                              "Grafo con pesos negativos", 2);
                     }
                  } else {
                     JOptionPane.showMessageDialog(this,
                           "El algoritmo del Cartero Chino no permite bucles en el grafo para su ejecución",
                           "Ciclos en el grafo", 2);
                  }
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo del Cartero Chino sólo está disponible para Grafos No Dirigidos",
                        "Grafo Dirigido", 2);
               }
               break;
            case 3:
               if (!this.panel.getGrafo().existenPesosNegativos()) {
                  this.ejecutarAlgoritmoDijkstra();
                  this.setEnabledGuardarResultadoButton(true);
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo de Dijkstra no permite pesos negativos en el grafo.\nPor favor, revise los pesos del grafo",
                        "Grafo con pesos negativos", 2);
               }
               break;
            case 4:
               this.ejecutarAlgoritmoBFS();
               this.setEnabledGuardarResultadoButton(true);
               break;
            case 5:
               this.ejecutarAlgoritmoDFS();
               this.setEnabledGuardarResultadoButton(true);
               break;
            case 6:
               boolean ejecutado = false;
               Grafo aux = this.panel.getGrafo().clonar();
               aux.pasarDePesosAMultiplicidades();
               if (!aux.esEuleriano()) {
                  JOptionPane.showMessageDialog(this,
                        "No se puede ejecutar el algoritmo porque el grafo no es euleriano.\nPor favor, revise el grafo",
                        "Revisar grafo", 2);
               } else {
                  this.panel.getGrafo().pasarDePesosAMultiplicidades();
                  this.ejecutarAlgoritmoHierholzer();
                  ejecutado = true;
               }

               if (ejecutado) {
                  this.panel.getGrafo().pasarDeMultiplicidadesAPesos();
               }

               this.setEnabledGuardarResultadoButton(false);
               break;
            case 7:
               if (this.panel.getGrafo().getTipo() == 1) {
                  if (this.panel.getGrafo().esDebilmenteConexo()) {
                     if (!this.panel.getGrafo().existenPesosConDecimales()) {
                        this.ejecutarAlgoritmoEtiquetaje();
                        this.setEnabledGuardarResultadoButton(false);
                     } else {
                        JOptionPane.showMessageDialog(this,
                              "No se puede ejecutar el algoritmo porque existen capacidades con la parte decimal distinta de 0.\nPor favor, revise el grafo",
                              "Revisar grafo", 2);
                     }
                  } else {
                     JOptionPane.showMessageDialog(this,
                           "No se puede ejecutar el algoritmo porque el grafo no es débilmente conexo.\nPor favor, revise el grafo",
                           "Revisar grafo", 2);
                  }
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo para obtener el flujo máximo de la red sólo está disponible para Grafos Dirigidos.",
                        "Grafo No Dirigido", 2);
               }
               break;
            case 8:
               if (this.panel.getGrafo().getTipo() == 0) {
                  this.ejecutarAlgoritmoKruskal();
                  this.setEnabledGuardarResultadoButton(true);
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo de Kruskal sólo está disponible para Grafos No Dirigidos", "Grafo Dirigido", 2);
               }
               break;
            case 9:
            case 11:
            case 12:
            case 13:
            default:
               JOptionPane.showMessageDialog(this, "No se ha escogido ningún algoritmo", "Algoritmo no seleccionado",
                     0);
               break;
            case 10:
            case 14:
               if (this.panel.getGrafo().getTipo() == 0) {
                  if (!this.panel.getGrafo().existenPesosNegativos()) {
                     if (this.panel.getGrafo().esConexo()) {
                        if (this.panel.getGrafo().getNodos().size() >= 3) {
                           if (!this.panel.getGrafo().existeVerticeGradoUno()) {
                              if (!this.panel.getGrafo().hayBucles()) {
                                 this.ejecutarAlgoritmoHamiltoniano(this.getAlgoritmoSeleccionado());
                                 this.setEnabledGuardarResultadoButton(true);
                              } else {
                                 JOptionPane.showMessageDialog(this,
                                       "El grafo no puede tener bucles para ser hamiltoniano.\nPor favor, revise el grafo",
                                       "Revise el grafo", 2);
                              }
                           } else {
                              JOptionPane.showMessageDialog(this,
                                    "El grafo contiene vértices de grado 1, por lo que no es hamiltoniano.\nPor favor, revise el grafo",
                                    "Grafo no hamiltoniano", 2);
                           }
                        } else {
                           JOptionPane.showMessageDialog(this,
                                 "El grafo debe tener almenos 3 nodos para tener un ciclo hamiltoniano.\nPor favor, revise el grafo",
                                 "Revise el grafo", 2);
                        }
                     } else {
                        JOptionPane.showMessageDialog(this, "El grafo no es conexo.\nPor favor, revise el grafo",
                              "Revise el grafo", 2);
                     }
                  } else {
                     JOptionPane.showMessageDialog(this,
                           "El algoritmo del Ciclo Hamiltoniano no permite pesos negativos en el grafo.\nPor favor, revise los pesos del grafo",
                           "Grafo con pesos negativos", 2);
                  }
               } else {
                  JOptionPane.showMessageDialog(this,
                        "El algoritmo del Ciclo Hamiltoniano sólo está disponible para Grafos No Dirigidos",
                        "Grafo Dirigido", 2);
               }
               break;
            case 15:
               this.orientarGrafo();
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void ejecutarAlgoritmoContinuoEdmonds2() {
      int seleccion = JOptionPane.showOptionDialog(this, "¿Desea maximizar o minimizar los pesos?",
            "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Maximizar", "Minimizar" }, "Si");
      if (seleccion != -1) {
         if (seleccion + 1 == 1) {
            this.algEd2.setMinimizar(false);
         } else {
            this.algEd2.setMinimizar(true);
         }

         this.algEd2.setModo(Algoritmo.continuo);
         if (this.algEd2.enSuspension()) {
            this.algEd2.reactivar();
         }

         try {
            this.algEd2.step();
            this.algEd2.join();
            this.textoInformacion.append(
                  "\n\nNOTA: Para volver a ejecutar este algoritmo, por favor haga clic en borrar paneles y/o vuelva a abrir o dibujar el grafo");
            this.modificarStatusBarAlgoritmoFin(1);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      } else {
         JOptionPane.showMessageDialog(this, "Debe escojer una opción para poder ejecutar el algormitmo.",
               "OPCIÓN NO VÁLIDA", 2);
      }

   }

   private void ejecutarAlgoritmoContinuoCarteroChino() {
      switch (this.algCh.getEstado()) {
         case 4:
            this.algCh.setModoEdmonds2(Algoritmo.continuo);
            break;
         case 9:
            this.algCh.setModoFleury(Algoritmo.continuo);
         default:
            this.algCh.setModo(Algoritmo.continuo);
      }

      if (this.algCh.enSuspension()) {
         this.algCh.reactivar();
      }

      try {
         this.algCh.step();
         this.algCh.join();
         this.modificarStatusBarAlgoritmoFin(2);
      } catch (InterruptedException var2) {
         var2.printStackTrace();
      }

   }

   private void ejecutarAlgoritmoDijkstra() {
      Stack<String> pila = new Stack();
      int predecesor = -1;
      GrafoMultiColor grafoPintar = new GrafoMultiColor();
      grafoPintar = grafoPintar.convertirAGrafoMultiColor(this.panel.getGrafo());
      int raiz = this.obtenerNodo("origen", "inicial");
      if (raiz != -1) {
         new DijkstraObject(raiz, this.panel.getGrafo().getNodos().size());
         int destino = this.obtenerNodo("destino", "final");
         int ini = 0;
         int fin = 0;
         DijkstraObject djkObjeto;
         if (destino == -1) {
            djkObjeto = this.panel.getGrafo().dijkstra(raiz);
            ini = 0;
            fin = this.panel.getGrafo().getNodos().size();
         } else {
            djkObjeto = this.panel.getGrafo().dijkstra(raiz, destino);
            ini = destino;
            fin = destino + 1;
         }

         this.textoInformacion.setText("El algoritmo de Dijkstra ha finalizado.\n\nRESULTADO:\n");

         for (int i = ini; i < fin; ++i) {
            this.textoInformacion.append("\nDistancia de " + this.panel.getGrafo().getNodoByIndex(raiz).getNombre()
                  + " a " + this.panel.getGrafo().getNodoByIndex(i).getNombre() + " = "
                  + Double.toString(djkObjeto.getDistancia(i)));
            this.textoInformacion.append("\nCamino recorrido: ");
            predecesor = djkObjeto.getPredecesor(i);

            int ultimo;
            Arista a2;
            while (predecesor != -1) {
               ultimo = predecesor;
               pila.push(this.panel.getGrafo().getNodoByIndex(predecesor).getNombre());
               predecesor = djkObjeto.getPredecesor(predecesor);
               if (this.panel.getGrafo().getTipo() == 0) {
                  if (this.panel.getGrafo().existeArista(predecesor, ultimo) && destino != -1) {
                     a2 = new Arista(predecesor, ultimo, 0);
                     a2.setPeso(this.panel.getGrafo().getAristaByNodosIndex(predecesor, ultimo).getPeso());
                     grafoPintar.deAzul.add(a2);
                  }
               } else if (this.panel.getGrafo().existeArco(predecesor, ultimo) && destino != -1) {
                  a2 = new Arista(predecesor, ultimo, 1);
                  a2.setPeso(this.panel.getGrafo().getArcoByNodosIndex(predecesor, ultimo).getPeso());
                  grafoPintar.deAzul.add(a2);
               }
            }

            String nombreNodo;
            for (ultimo = -1; !pila.empty(); ultimo = this.panel.getGrafo().getPosicionNodoByNombre(nombreNodo)) {
               nombreNodo = (String) pila.pop();
               this.textoInformacion.append(nombreNodo + " --> ");
            }

            if (this.panel.getGrafo().getTipo() == 0) {
               if (this.panel.getGrafo().existeArista(ultimo, i)) {
                  a2 = new Arista(i, ultimo, 0);
                  a2.setPeso(this.panel.getGrafo().getAristaByNodosIndex(ultimo, i).getPeso());
                  grafoPintar.deAzul.add(a2);
               }
            } else if (this.panel.getGrafo().existeArco(ultimo, i)) {
               a2 = new Arista(ultimo, i, 1);
               a2.setPeso(this.panel.getGrafo().getArcoByNodosIndex(ultimo, i).getPeso());
               grafoPintar.deAzul.add(a2);
            }

            this.textoInformacion.append(this.panel.getGrafo().getNodoByIndex(i).getNombre() + "\n");
         }

         this.panel.setGrafo(grafoPintar);
         this.modificarStatusBarAlgoritmoFin(3);
      } else {
         this.textoInformacion
               .setText("Error:\nNo se ha podido ejecutar el algoritmo...\n\nDijkstra - Camino más corto");
      }

   }

   private void ejecutarAlgoritmoBFS() {
      int raiz = this.obtenerNodo("origen", "inicial");
      if (raiz != -1) {
         Vector<Nodo> v = this.panel.getGrafo().BFS(this, this.panel.getGrafo().getNodoByIndex(raiz), false);
         Nodo ini = (Nodo) v.get(0);
         this.textoInformacion.setText("El algoritmo de BFS (Breadth First Search) ha finalizado.\n\nRESULTADO:\n");
         this.textoInformacion
               .append("\nLos nodos alcanzados en la búsqueda en anchura, mostrados en orden, desde el nodo inicial \""
                     + ini.getNombre() + "\" son: \n");

         for (int i = 0; i < v.size(); ++i) {
            Nodo n = (Nodo) v.get(i);
            this.textoInformacion.append("\n" + n.getNombre());
         }

         this.textoInformacion.append(
               "\n\nEl árbol de búsqueda se muestra en el panel de grafos de color azul sobre el grafo original");
         this.modificarStatusBarAlgoritmoFin(4);
      } else {
         this.textoInformacion.setText("Error:\nNo se ha podido ejecutar el algoritmo...\n\nBFS - Búsqueda en anchura");
      }

   }

   private void ejecutarAlgoritmoDFS() {
      int raiz = this.obtenerNodo("origen", "inicial");
      if (raiz != -1) {
         Grafo G = this.panel.getGrafo().DFS(this, this.panel.getGrafo().getNodoByIndex(raiz), false, (Vector) null);
         Nodo ini = G.getNodoByIndex(0);
         this.textoInformacion.setText("El algoritmo de DFS (Depth First Search) ha finalizado.\n\nRESULTADO:\n");
         this.textoInformacion.append(
               "\nLos nodos alcanzados en la búsqueda en profundidad, mostrados en orden, desde el nodo inicial \""
                     + ini.getNombre() + "\" son: \n");

         for (int i = 0; i < G.getNodos().size(); ++i) {
            Nodo n = G.getNodoByIndex(i);
            this.textoInformacion.append("\n" + n.getNombre());
         }

         this.textoInformacion.append(
               "\n\nEl árbol de búsqueda se muestra en el panel de grafos de color azul sobre el grafo original");
         this.modificarStatusBarAlgoritmoFin(5);
      } else {
         this.textoInformacion
               .setText("Error:\nNo se ha podido ejecutar el algoritmo...\n\nDFS - Búsqueda en profundidad");
      }

   }

   private void ejecutarAlgoritmoKruskal() {
      Kruskal algKruskal = new Kruskal(this.panel, this.textoInformacion);
      algKruskal.run();
      this.modificarStatusBarAlgoritmoFin(8);
   }

   private void ejecutarAlgoritmoHierholzer() {
      Hierholzer algHierholzer = new Hierholzer(this.panel, this.panelresultados, this.textoInformacion);
      algHierholzer.run();
      this.modificarStatusBarAlgoritmoFin(6);
   }

   private void ejecutarAlgoritmoHamiltoniano(int algoritmo) {
      if (algoritmo == 10) {
         if (this.panel.getGrafo().esGrafoCompleto()) {
            if (this.panel.getGrafo().verificaDesigualdadTriangular()) {
               ArrayList<String> listaNodos = new ArrayList();
               listaNodos.add("Basado en el árbol generador");
               listaNodos.add("Voraz");
               Object[] nodos = listaNodos.toArray();
               String nodoSelec = (String) JOptionPane.showInputDialog(this, "Algoritmo:",
                     "Indique el algoritmo a aplicar", 3, (Icon) null, nodos, nodos[0]);
               if (nodoSelec != null && nodoSelec.length() > 0) {
                  if (nodoSelec.equals("Basado en el árbol generador")) {
                     Grafo GrafoOriginal = this.panel.getGrafo().clonar();
                     Kruskal algKruskal = new Kruskal(this.panel, this.textoInformacion);
                     algKruskal.run();
                     Grafo g = this.guardarGrafoResultado();
                     this.panel.setGrafo(g);
                     g.convertirAGrafoDirigido();
                     Hierholzer algHierholzer = new Hierholzer(this.panel, this.panelresultados, this.textoInformacion);
                     algHierholzer.run();
                     this.panel.setGrafo(this.panelresultados.getGrafo());
                     this.panelresultados.setGrafo((Grafo) null);
                     CicloHamiltonArbol algHamiltonArbol = new CicloHamiltonArbol(this.panel, this.textoInformacion,
                           GrafoOriginal);
                     algHamiltonArbol.run();
                  } else {
                     CicloHamiltonVoraz algHamiltonVoraz = new CicloHamiltonVoraz(this.panel, this.textoInformacion);
                     algHamiltonVoraz.run();
                  }
               }

               this.modificarStatusBarAlgoritmoFin(10);
            } else {
               JOptionPane.showMessageDialog(this,
                     "El grafo no verifica la desigualdad triangular, no se puede asegurar la cota de error para el ciclo hamitloniano de bajo peso.\nPor favor, revise el grafo",
                     "Revise el grafo", 2);
            }
         } else {
            JOptionPane.showMessageDialog(this,
                  "El grafo no es completo, no se puede asegurar la cota de error para el ciclo hamitloniano de bajo peso.\nPor favor, revise el grafo",
                  "Revise el grafo", 2);
         }
      } else if (algoritmo == 14) {
         if (this.panel.getGrafo().getNodos().size() < 10) {
            if (this.panel.getGrafo().getNodos().size() == 9) {
               JOptionPane.showMessageDialog(this,
                     "El algoritmo por fuerza bruta para un grafo de 9 nodos, puede tardar en ejecutarse casi un minuto",
                     "Ejecutar algoritmo", 2);
            }

            CicloHamiltonFuerzaB algHamiltonFuerzaB = new CicloHamiltonFuerzaB(this.panel, this.textoInformacion);
            algHamiltonFuerzaB.run();
            this.modificarStatusBarAlgoritmoFin(14);
         } else {
            JOptionPane.showMessageDialog(this,
                  "El algoritmo por fuerza bruta no se puede aplicar a grafos con más de 9 nodos.\nPor favor, revise el grafo",
                  "Revise el grafo", 2);
         }
      }

   }

   private void orientarGrafo() {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         int seleccion = JOptionPane.showOptionDialog(this,
               "¿Desea orientar el grafo actual?\n\nNOTA:\nEl grafo actual no se podrá recuperar, guarde el grafo si lo quiere utilizar más tarde",
               "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
         if (seleccion != -1 && seleccion + 1 == 1) {
            if (this.panel.getGrafo().getTipo() == 0) {
               if (!this.panel.getGrafo().hayAristasDeCorte()) {
                  Vector<Nodo> ListaNodosAdyacentes = new Vector();
                  Nodo n1 = null;
                  Nodo n2 = null;
                  GrafoMultiColor resultado = (GrafoMultiColor) this.panel.getGrafo().DFS((MenuPrincipal) null,
                        this.panel.getGrafo().getNodoByIndex(0), true, ListaNodosAdyacentes);
                  resultado.resetAristas();

                  int i;
                  Arista a;
                  for (i = 0; i < resultado.deAzul.size(); ++i) {
                     a = (Arista) resultado.deAzul.get(i);
                     n1 = resultado.getNodoByIndex(a.getCabeza());
                     n2 = resultado.getNodoByIndex(a.getCola());
                     if (ListaNodosAdyacentes.indexOf(n1) < ListaNodosAdyacentes.indexOf(n2)) {
                        resultado.insertarArco(a.getCabeza(), a.getCola());
                     } else {
                        resultado.insertarArco(a.getCola(), a.getCabeza());
                     }
                  }

                  resultado.setTipo(1);

                  for (i = 0; i < resultado.getAristas().size(); ++i) {
                     a = resultado.getAristaByIndex(i);
                     if (resultado.existeAristaAzul(a)) {
                        this.panel.getGrafo().borrarAristabyNodosIndex(a.getCabeza(), a.getCola());
                     }
                  }

                  for (i = 0; i < this.panel.getGrafo().getAristas().size(); ++i) {
                     a = this.panel.getGrafo().getAristaByIndex(i);
                     n1 = this.panel.getGrafo().getNodoByIndex(a.getCabeza());
                     n2 = this.panel.getGrafo().getNodoByIndex(a.getCola());
                     if (ListaNodosAdyacentes.indexOf(n1) > ListaNodosAdyacentes.indexOf(n2)) {
                        resultado.insertarArco(a.getCabeza(), a.getCola());
                     } else {
                        resultado.insertarArco(a.getCola(), a.getCabeza());
                     }
                  }

                  resultado.deAzul.clear();
                  this.setGrafo(resultado);
                  this.tipoGrafoComboBox.setSelectedIndex(1);
                  this.textoInformacion.setText("El algoritmo de Hopcroft-Tarjan ha finalizado.\n\nRESULTADO:\n");
                  this.textoInformacion.append(
                        "\nEn el panel de grafos se puede observar el grafo dirigido fuertemente conexo resultado.");
                  this.modificarStatusBarAlgoritmoFin(15);
               } else {
                  JOptionPane.showMessageDialog(this, "No se puede orientar el grafo porque contiene aristas de corte",
                        "Aristas de corte", 2);
               }
            } else {
               JOptionPane.showMessageDialog(this, "Orientar el grafo sólo está disponible para Grafos No Dirigidos",
                     "Grafo Dirigido", 2);
            }
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void ejecutarAlgoritmoEtiquetaje() {
      int fuente = this.obtenerNodo("fuente", "fuente");
      int sumidero = this.obtenerNodo("sumidero", "sumidero");
      boolean ejecutado = false;
      if (fuente != -1 && sumidero != -1) {
         if (this.panel.getGrafo().getGradoSalidaIndex(fuente) <= 0) {
            JOptionPane.showMessageDialog(this, "El nodo fuente, debe tener grado de salida mayor que cero.",
                  "Revise el grafo", 2);
         } else if (this.panel.getGrafo().getGradoEntradaIndex(sumidero) <= 0) {
            JOptionPane.showMessageDialog(this, "El nodo sumidero, debe tener grado de entrada mayor que cero.",
                  "Revise el grafo", 2);
         } else {
            FlujoMaximo algEtiquetaje = new FlujoMaximo(this.panel, this.textoInformacion, fuente, sumidero);
            algEtiquetaje.run();
            this.modificarStatusBarAlgoritmoFin(7);
            ejecutado = true;
         }
      }

      if (!ejecutado) {
         this.textoInformacion
               .setText("Error:\nNo se ha podido ejecutar el algoritmo...\n\nFord-Fulkerson - Flujo máximo");
      }

   }

   private void ejecutarPaso() {
      switch (this.getAlgoritmoSeleccionado()) {
         case 1:
            this.ejecutarPasoEdmonds2();
            break;
         case 2:
            this.ejecutarPasoCarteroChino();
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
      }

   }

   private void ejecutarPasoEdmonds2() {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (!this.algEd2.enProceso()) {
            this.algEd2.setModo(Algoritmo.porPasos);
            this.algEd2.setMinimizar(true);
            int seleccion = JOptionPane.showOptionDialog(this, "¿Desea maximizar o minimizar los pesos?",
                  "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Maximizar", "Minimizar" }, "Si");
            if (seleccion != -1) {
               if (seleccion + 1 == 1) {
                  this.algEd2.setMinimizar(false);
               } else {
                  this.algEd2.setMinimizar(true);
               }
            }
         }

         if (this.algEd2.getEstado() == 2) {
            Object[] posibilidades = new Object[] { "Sencillo", "Ajustado" };
            String s = (String) JOptionPane.showInputDialog((Component) null, "¿Que etiquetado desea aplicar?",
                  "Seleccion etiquetado", -1, (Icon) null, posibilidades, "Sencillo");
            if (s == "Ajustado") {
               this.algEd2.setEtiquetadoAjustado();
            }
         }

         if (this.algEd2.enSuspension()) {
            this.algEd2.reactivar();
         }

         try {
            this.algEd2.step();
            this.algEd2.join();
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private void ejecutarPasoCarteroChino() {
      if (this.panel.getGrafo().getNodos().size() > 0) {
         if (!this.algCh.enProceso()) {
            this.algCh.setModo(Algoritmo.porPasos);
         }

         if (this.algCh.getEstado() == 4 && !this.algCh.getEdmonds2().enProceso()) {
            Object[] posibilidades = new Object[] { "Deseo ver únicamente el resultado final",
                  "Deseo ver el avance paso a paso", "No quiero ver la ejecución del algoritmo" };
            String s = (String) JOptionPane.showInputDialog((Component) null,
                  "¿Como desea ver la ejecución del algoritmo de Edmonds?", "Ejecución Algoritmo de Edmonds", -1,
                  (Icon) null, posibilidades, "Deseo ver únicamente el resultado final");
            if (s == "Deseo ver únicamente el resultado final") {
               this.algCh.setModoEdmonds2(Algoritmo.continuo);
            }

            if (s == "Deseo ver el avance paso a paso") {
               this.algCh.setModoEdmonds2(Algoritmo.porPasos);
            }

            if (s == "No quiero ver la ejecución del algoritmo") {
               this.algCh.setModoEdmonds2(Algoritmo.oculto);
            }
         }

         if (this.algCh.enSuspension()) {
            this.algCh.reactivar();
         }

         try {
            this.algCh.step();
            this.algCh.join();
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      } else {
         this.errorNoGrafo();
      }

   }

   private boolean dibujarKGrafo(int peso) {
      boolean dibujado = false;
      boolean abrirOtroXML = true;
      if (this.panel.getGrafo().getNodos().size() > 0) {
         int seleccion = JOptionPane.showOptionDialog(this,
               "¿Desea generar otro grafo?\n\nNOTA:\nEl grafo actual no se podrá recuperar, guarde el grafo si lo quiere utilizar más tarde",
               "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
         if (seleccion != -1) {
            if (seleccion + 1 == 1) {
               abrirOtroXML = true;
            } else {
               abrirOtroXML = false;
            }
         }
      }

      if (abrirOtroXML) {
         String texto = JOptionPane.showInputDialog(this, "Indique el número de nodos", "Crear K-Grafo", 3);

         try {
            int k = Integer.parseInt(texto);
            if (k > 0) {
               Grafo kGND = new Grafo(k, peso);
               this.panel.setGrafo(kGND);
               this.redibujarGrafo();
               dibujado = true;
               if (peso == 0) {
                  this.panel.getGrafo().setTipo(this.tipoGrafoComboBox.getSelectedIndex());
               } else {
                  this.tipoGrafoComboBox.setSelectedIndex(0);
               }

               this.modificarStatusBar("Se ha generado el k-grafo correctamenete");
            } else {
               JOptionPane.showMessageDialog(this, "El valor indicado no es un número de nodos válido", "ERROR", 0);
            }
         } catch (NumberFormatException var7) {
            JOptionPane.showMessageDialog(this, "El valor indicado no es un número de nodos válido", "ERROR", 0);
         }
      }

      return dibujado;
   }

   private void abrirMatrizAdyacencia() {
      if (this.panel.getGrafo().getNodos().size() > 1) {
         new DialogoMatrizAdyacencia(this, this.panel.getGrafo(), this.AlgoritmoSeleccionado);
      } else if (this.dibujarKGrafo(0)) {
         new DialogoMatrizAdyacencia(this, this.panel.getGrafo(), this.AlgoritmoSeleccionado);
      }

   }

   private void limpiarPanelGrafos() {
      int seleccion = JOptionPane.showOptionDialog(this,
            "¿Desea eliminar el grafo y limpiar el panel de información?\n\nNOTA:\nEl grafo actual no se podrá recuperar, guarde el grafo si lo quiere utilizar más tarde",
            "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
      if (seleccion != -1 && seleccion + 1 == 1) {
         this.panel.setGrafo(new Grafo());
         this.redibujarGrafo();
         this.panel.getGrafo().setTipo(this.tipoGrafoComboBox.getSelectedIndex());
         this.textoInformacion.setText("");
         this.panelresultados.setGrafo(new Grafo());
         this.panelresultados.repaint();
         this.nodosRadioButton.setSelected(true);
         ActionEvent evt = null;
         this.nodosRadioButtonActionPerformed((ActionEvent) evt);
         this.setEnabledGuardarResultadoButton(false);
         this.algCh = new CarteroChino(this.panel, this.panelresultados, this.textoInformacion);
         this.modificarStatusBar("Se ha borrado el grafo y el texto del panel de información correctamente");
      }

   }

   private void windowClosingPerformed(WindowEvent e) {
      this.SalirAplicacion();
   }

   private void SalirAplicacion() {
      int resp = JOptionPane.showConfirmDialog((Component) null, "¿Seguro que desea salir?", "Aviso", 2);
      if (resp == 0) {
         System.exit(0);
      }

   }

   private void tipoGrafoComboBoxActionPerformed(ActionEvent evt) {
      boolean error = false;
      int seleccion;
      switch (this.tipoGrafoComboBox.getSelectedIndex()) {
         case 0:
            if (this.panel.getGrafo().hayArcos()) {
               if (this.getGrafo().esMatrizSimetrica()) {
                  seleccion = JOptionPane.showOptionDialog(this,
                        "Se va a convertir el 'Grafo Dirigido' a 'Grafo No Dirigido'.\n¿Que desea realizar?\n",
                        "Seleccione una opción", 1, 3, (Icon) null,
                        new Object[] { "Mantener pesos", "Matriz de adyacencia", "Cancelar" }, "Mantener pesos");
                  if (seleccion != -1) {
                     if (seleccion == 0) {
                        this.getGrafo().convertirAGrafoNOdirigido();
                     } else if (seleccion == 1) {
                        this.getGrafo().convertirAGrafoNOdirigido();
                        this.getGrafo().marcarTodosLosPesosAUno();
                     } else {
                        error = true;
                        this.tipoGrafoComboBox.setSelectedIndex(1);
                     }
                  }
               } else {
                  seleccion = JOptionPane.showOptionDialog(this,
                        "Se va a convertir el 'Grafo Dirigido' a 'Grafo No Dirigido' con todos los pesos a 1.\n¿Desea continuar?",
                        "Seleccione una opción", 1, 3, (Icon) null, new Object[] { "Si", "No" }, "Si");
                  if (seleccion != -1) {
                     if (seleccion + 1 == 1) {
                        this.getGrafo().convertirAGrafoNOdirigido();
                        this.getGrafo().marcarTodosLosPesosAUno();
                     } else {
                        error = true;
                        this.tipoGrafoComboBox.setSelectedIndex(1);
                     }
                  }
               }
            }

            if (this.arcoRadioButton.isSelected() && !error) {
               this.arcoRadioButton.setSelected(false);
               this.aristasRadioButton.setSelected(true);
               this.panel.setOperacion(1);
            }
            break;
         case 1:
            if (this.getGrafo().hayAristas()) {
               seleccion = JOptionPane.showOptionDialog(this,
                     "Se va a convertir el 'Grafo No Dirigido' a 'Grafo Dirigido'.\n¿Que desea realizar?\n",
                     "Seleccione una opción", 1, 3, (Icon) null,
                     new Object[] { "Mantener pesos", "Matriz de adyacencia", "Cancelar" }, "Mantener pesos");
               if (seleccion != -1) {
                  if (seleccion == 0) {
                     this.getGrafo().convertirAGrafoDirigido();
                  } else if (seleccion == 1) {
                     this.getGrafo().convertirAGrafoDirigido();
                     this.getGrafo().marcarTodosLosPesosAUno();
                  } else {
                     error = true;
                     this.tipoGrafoComboBox.setSelectedIndex(0);
                  }
               }
            }

            if (this.aristasRadioButton.isSelected() && !error) {
               this.aristasRadioButton.setSelected(false);
               this.arcoRadioButton.setSelected(true);
               this.panel.setOperacion(2);
            }
      }

      this.panel.getGrafo().setTipo(this.tipoGrafoComboBox.getSelectedIndex());
      if (!error) {
         this.aristasRadioButton.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 0);
         this.arcoRadioButton.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 1);
         this.conexoMenuItem.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 0);
         this.fuertementeConexoMenuItem.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 1);
         this.debilmenteConexoMenuItem.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 1);
         this.compConexasMenuItem.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 0);
         this.compFuerteConexasMenuItem.setEnabled(this.tipoGrafoComboBox.getSelectedIndex() == 1);
      }

   }

   private void nodosRadioButtonActionPerformed(ActionEvent evt) {
      this.panel.setOperacion(0);
      FlowLayout panelLayout = new FlowLayout();
      this.panel.setLayout(panelLayout);
   }

   private void aristasRadioButtonActionPerformed(ActionEvent evt) {
      this.panel.setOperacion(1);
   }

   private void arcoRadioButtonActionPerformed(ActionEvent evt) {
      this.panel.setOperacion(2);
   }

   private void guardarResultadoButtonActionPerformed(ActionEvent evt) {
      this.guardarGrafo(this.guardarGrafoResultado());
   }

   private Grafo guardarGrafoResultado() {
      ArrayList<String> listaNodos = new ArrayList();
      Grafo grafoResultado = new Grafo();
      GrafoMultiColor grafoPanel = (GrafoMultiColor) this.panel.getGrafo();
      MatrizTabla matrizGrafoPintar = new MatrizTabla(grafoPanel);
      grafoResultado.setTipo(grafoPanel.getTipo());

      int i;
      for (i = 0; i < grafoPanel.getNodos().size(); ++i) {
         Nodo n = grafoPanel.getNodoByIndex(i);
         grafoResultado.insertarNodo(n);
      }

      for (i = 0; i < grafoPanel.getNodos().size(); ++i) {
         for (int j = 0; j < grafoPanel.getNodos().size(); ++j) {
            Double peso = (Double) matrizGrafoPintar.getValueAt(i, j);
            if (peso != 0.0D) {
               int tipo = 1;
               if (grafoPanel.getTipo() == 0) {
                  tipo = 0;
               } else {
                  tipo = 1;
               }

               Arista a = new Arista(i, j, tipo);
               a.setPeso(peso);
               if (grafoPanel.getTipo() == 0) {
                  if (grafoPanel.existeAristaAzul(a)) {
                     grafoResultado.insertarArista(a);
                     listaNodos.add(grafoPanel.getNodoByIndex(i).getNombre());
                     listaNodos.add(grafoPanel.getNodoByIndex(j).getNombre());
                  }
               } else if (grafoPanel.existeArcoAzul(a)) {
                  grafoResultado.insertarArco(a);
                  listaNodos.add(grafoPanel.getNodoByIndex(i).getNombre());
                  listaNodos.add(grafoPanel.getNodoByIndex(j).getNombre());
               }
            }
         }
      }

      for (i = 0; i < grafoResultado.getNodos().size(); ++i) {
         String nom = grafoResultado.getNodoByIndex(i).getNombre();
         if (!listaNodos.contains(nom)) {
            grafoResultado.borrarNodoNombre(nom);
            i = -1;
         }
      }

      return grafoResultado;
   }

   private void setEnabledGuardarResultadoButton(boolean enabled) {
      this.guardarResultadoButton.setEnabled(enabled);
   }

   private int obtenerNodo(String OrigenDestino, String InicialFinal) {
      ArrayList<String> listaNodos = new ArrayList();
      if (InicialFinal.equals("final")) {
         listaNodos.add("A TODOS");
      }

      for (int i = 0; i < this.panel.getGrafo().getNodos().size(); ++i) {
         listaNodos.add(this.panel.getGrafo().getNodoByIndex(i).getNombre());
      }

      Object[] nodos = listaNodos.toArray();
      String nodoSelec = (String) JOptionPane.showInputDialog(this, "Indique el nodo " + OrigenDestino + ":",
            "Nodo " + InicialFinal, 3, (Icon) null, nodos, nodos[0]);
      return nodoSelec != null && nodoSelec.length() > 0 ? this.panel.getGrafo().getPosicionNodoByNombre(nodoSelec)
            : -1;
   }

   private void errorNoGrafo() {
      JOptionPane.showMessageDialog(this,
            "No se puede ejecutar el algoritmo porque no existe un grafo sobre el que hacerlo.\nPor favor, dibuje o abra un grafo",
            "GRAFO NO INTRODUCIDO", 2);
   }

   public void modificarStatusBarAlgoritmoFin(int algoritmo) {
      String alg = "";
      switch (algoritmo) {
         case 1:
            alg = "Edmonds 2 - Emparejamientos";
            break;
         case 2:
            alg = "Cartero Chino";
            break;
         case 3:
            alg = "Dijkstra - Camino más corto";
            break;
         case 4:
            alg = "BFS - Búsqueda en anchura";
            break;
         case 5:
            alg = "DFS - Búsqueda en profundidad";
            break;
         case 6:
            alg = "Hierholzer - Ciclo euleriano";
            break;
         case 7:
            alg = "Ford-Fulkerson - Flujo máximo";
            break;
         case 8:
            alg = "Kruskal - Árbol de expansión mínima";
         case 9:
         case 11:
         case 12:
         case 13:
         default:
            break;
         case 10:
            alg = "Ciclo hamiltoniano de 'bajo' peso";
            break;
         case 14:
            alg = "Ciclo hamiltoniano de mínimo peso";
            break;
         case 15:
            alg = "Hopcroft-Tarjan";
      }

      this.modificarStatusBar("El algoritmo de " + alg + " ha finalizado correctamente");
      this.panel.repaint();
   }

   public void modificarStatusBar(String mensaje) {
      this.statusLabel.setText("    " + this.getFechaActual() + " >>> " + mensaje + " a las " + getHoraActual());
   }

   private String getFechaActual() {
      Date ahora = new Date();
      SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
      return formateador.format(ahora);
   }

   private static String getHoraActual() {
      Date ahora = new Date();
      SimpleDateFormat formateador = new SimpleDateFormat("HH:mm'h.'");
      return formateador.format(ahora);
   }
}
