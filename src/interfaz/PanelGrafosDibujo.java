package interfaz;

import grafos.Arista;
import grafos.Grafo;
import grafos.Nodo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.event.MouseMotionListener;
import java.util.Stack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.Toolkit;

public class PanelGrafosDibujo extends PanelGrafos
      implements MouseListener, ActionListener, MouseMotionListener, KeyListener {
   private static final long serialVersionUID = 1L;
   private int operacion = 0;
   private Point punto;
   private int nodoSeleccionado = -1;
   private int aristaSeleccionada = -1;
   private final double umbralDistancia = 20.0D;
   private Point lastDragPoint;
   private boolean mPressed = false;

   public PanelGrafosDibujo() {
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.addKeyListener(this);
      this.setFocusable(true);
      this.setupShortcuts();
   }

   public PanelGrafosDibujo(Grafo arg0) {
      super(arg0);
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      this.addKeyListener(this);
      this.setFocusable(true);
      this.setupShortcuts();
   }

   private void setupShortcuts() {
      InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);
      ActionMap actionMap = this.getActionMap();

      KeyStroke undoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Z,
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
      inputMap.put(undoKeyStroke, "undo");
      actionMap.put("undo", new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
            deshacer();
         }
      });

      KeyStroke redoKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Y,
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
      inputMap.put(redoKeyStroke, "redo");
      actionMap.put("redo", new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
            rehacer();
         }
      });
   }

   public void setOperacion(int op) {
      this.operacion = op;
   }

   public int getOperacion() {
      return this.operacion;
   }

   public void mouseClicked(MouseEvent arg0) {
      if (this.operacion == 0) {
         this.nodoSeleccionado = this.obtenerNodoMasCercano(arg0.getPoint(), 20.0D);
         // Node creation moved to mouseReleased for better responsiveness
         // if (arg0.getButton() == 1) {
         // this.crearNuevoNodo(arg0.getPoint());
         // }

         if (arg0.getButton() == 3 && this.nodoSeleccionado > -1) {
            this.emergerMenuNodos(arg0.getPoint());
         }
      } else if (arg0.getButton() == 3) {
         this.aristaSeleccionada = this.obtenerAristaMasCercana(arg0.getPoint(), 20.0D);
         if (this.aristaSeleccionada > -1) {
            if (this.operacion == 1) {
               this.emergerMenuAristas(arg0.getPoint());
            } else {
               this.emergerMenuArcos(arg0.getPoint());
            }
         }
      }

   }

   public void mouseEntered(MouseEvent arg0) {
      this.requestFocusInWindow();
   }

   public void mouseExited(MouseEvent arg0) {
   }

   private Stack<Grafo> undoStack = new Stack<>();
   private Stack<Grafo> redoStack = new Stack<>();

   public void guardarEstado() {
      try {
         System.out.println("Guardando estado... Stack size: " + undoStack.size());
         this.undoStack.push(this.getGrafo().clonar());
         this.redoStack.clear(); // New action clears redo history
      } catch (Exception e) {
         System.err.println("Error saving state: " + e.getMessage());
         e.printStackTrace();
      }
   }

   public void deshacer() {
      System.out.println("Deshaciendo... Stack size: " + undoStack.size());
      if (!this.undoStack.isEmpty()) {
         // Save current state to redo stack before undoing
         this.redoStack.push(this.getGrafo().clonar());

         this.setGrafo(this.undoStack.pop());
         // Reset selection to avoid stale state issues
         this.nodoSeleccionado = -1;
         this.aristaSeleccionada = -1;
         this.punto = null;
         this.repaint();
      } else {
         System.out.println("Nothing to undo.");
      }
   }

   public void rehacer() {
      System.out.println("Rehaciendo... Redo Stack size: " + redoStack.size());
      if (!this.redoStack.isEmpty()) {
         // Save current state to undo stack before redoing
         this.undoStack.push(this.getGrafo().clonar());

         this.setGrafo(this.redoStack.pop());
         // Reset selection
         this.nodoSeleccionado = -1;
         this.aristaSeleccionada = -1;
         this.punto = null;
         this.repaint();
      } else {
         System.out.println("Nothing to redo.");
      }
   }

   private Point mousePressedPoint;

   public void mousePressed(MouseEvent e) {
      System.out.println("MousePressed: " + e.getPoint());
      this.requestFocusInWindow();
      this.mousePressedPoint = e.getPoint();
      if (SwingUtilities.isMiddleMouseButton(e) || (this.mPressed && SwingUtilities.isLeftMouseButton(e))) {
         this.lastDragPoint = e.getPoint();
         this.nodoSeleccionado = -1; // Deselect node when panning to prevent accidental drags
      } else if (e.getButton() == 1) {
         this.nodoSeleccionado = this.obtenerNodoMasCercano(e.getPoint(), 20.0D);
         if (this.nodoSeleccionado != -1) {
            // Save state before potential drag
            this.guardarEstado();
            Nodo n = this.getGrafo().getNodoByIndex(this.nodoSeleccionado);
            this.punto = n.getPos();
         }
      } else {
         this.nodoSeleccionado = -1;
      }

   }

   public void mouseReleased(MouseEvent e) {
      this.lastDragPoint = null;
      switch (this.operacion) {
         case 0:
            if (this.nodoSeleccionado != -1 && this.punto != null && e.getPoint().distance(this.punto) > 15.0D) {
               int x = Math.max(10, Math.min(e.getX(), this.getWidth() - 10));
               int y = Math.max(10, Math.min(e.getY(), this.getHeight() - 10));
               // Apply zoom and translation correction
               x = (int) ((x - this.tx) / this.zoom);
               y = (int) ((y - this.ty) / this.zoom);
               this.getGrafo().cambiarPosicionIndex(this.nodoSeleccionado, new Point(x, y));
               this.repaint();
            } else if (this.nodoSeleccionado != -1) {
               // If it wasn't a drag (just a click), pop the state we saved in mousePressed
               // to avoid accumulating useless states
               if (!this.undoStack.isEmpty()) {
                  this.undoStack.pop();
               }
            } else if (SwingUtilities.isLeftMouseButton(e) && !this.mPressed && this.mousePressedPoint != null
                  && e.getPoint().distance(this.mousePressedPoint) < 5.0D) {
               // Click on empty space (not a drag, not a pan) -> Create Node
               this.crearNuevoNodo(e.getPoint());
            }
            break;
         case 1:
            if (this.nodoSeleccionado != -1 && this.obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1) {
               this.guardarEstado();
               if (this.getGrafo().insertarArista(this.obtenerNodoMasCercano(e.getPoint(), 20.0D),
                     this.nodoSeleccionado)) {
                  this.repaint();
               } else {
                  this.undoStack.pop(); // Revert save if insertion failed
               }
            }
            break;
         case 2:
            if (this.nodoSeleccionado != -1 && this.obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1) {
               this.guardarEstado();
               if (this.getGrafo().insertarArco(this.nodoSeleccionado,
                     this.obtenerNodoMasCercano(e.getPoint(), 20.0D))) {
                  this.repaint();
               } else {
                  this.undoStack.pop(); // Revert save if insertion failed
               }
            }
      }

   }

   public void actionPerformed(ActionEvent arg0) {
      JMenuItem source = (JMenuItem) arg0.getSource();
      switch (this.operacion) {
         case 0:
            if (this.nodoSeleccionado != -1 && source.getText() == "Borrar el nodo") {
               this.guardarEstado();
               this.eliminarNodoSeleccionado();
            } else {
               this.cambiarNombre();
            }
            break;
         case 1:
            if (this.aristaSeleccionada != -1 && source.getText() == "Borrar la arista") {
               this.guardarEstado();
               this.eliminarAristaSeleccionada();
            } else {
               this.cambiarPeso();
            }
            break;
         case 2:
            if (this.aristaSeleccionada != -1 && source.getText() == "Borrar el arco") {
               this.guardarEstado();
               this.eliminarAristaSeleccionada();
            } else {
               this.cambiarPeso();
            }
      }

   }

   private void eliminarAristaSeleccionada() {
      this.getGrafo().borrarAristaIndex(this.aristaSeleccionada);
      this.repaint();
   }

   private void eliminarNodoSeleccionado() {
      this.getGrafo().borrarNodoIndex(this.nodoSeleccionado);
      this.repaint();
   }

   private void cambiarNombre() {
      String texto = JOptionPane.showInputDialog(this, "Introduzca el nuevo nombre del nodo", "Cambiar Nombre", 3);

      try {
         if (texto.length() == 0) {
            JOptionPane.showMessageDialog(this, "No se permiten nombres vacios", "ERROR", 0);
         } else {
            this.guardarEstado();
            if (this.getGrafo().cambiarNombreIndex(this.nodoSeleccionado, texto)) {
               this.repaint();
            } else {
               JOptionPane.showMessageDialog(this, "El nombre ya existe", "ERROR", 0);
               this.undoStack.pop(); // Revert save
            }
         }
      } catch (NullPointerException var3) {
      }

   }

   private void cambiarPeso() {
      String texto = JOptionPane.showInputDialog(this, "Introduzca el nuevo peso de la arista", "Cambio peso", 3);

      try {
         double nuevoPeso = Double.parseDouble(texto);
         this.guardarEstado();
         Arista arista = this.getGrafo().getAristaByIndex(this.aristaSeleccionada);
         arista.setPeso(nuevoPeso);
         this.getGrafo().getAristas().set(this.aristaSeleccionada, arista);
         this.repaint();
      } catch (NumberFormatException var5) {
         JOptionPane.showMessageDialog(this, "El peso " + texto + " no es valido.\nDebe ser un nmero", "ERROR", 0);
      } catch (NullPointerException var6) {
      }

   }

   private void crearNuevoNodo(Point point) {
      if (this.nodoSeleccionado == -1) {
         this.guardarEstado();
         // Apply zoom and translation correction for new nodes
         int x = (int) ((point.x - this.tx) / this.zoom);
         int y = (int) ((point.y - this.ty) / this.zoom);
         this.getGrafo().insertarNodo(new Point(x, y));
         this.repaint();
      }

   }

   private void emergerMenuAristas(Point p) {
      JPopupMenu popup = new JPopupMenu();
      JMenuItem menuItem = new JMenuItem("Borrar la arista");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.addSeparator();
      menuItem = new JMenuItem("Cambiar peso de la arista");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.show(this, p.x, p.y);
   }

   private void emergerMenuArcos(Point p) {
      JPopupMenu popup = new JPopupMenu();
      JMenuItem menuItem = new JMenuItem("Borrar el arco");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.addSeparator();
      menuItem = new JMenuItem("Cambiar peso del arco");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.show(this, p.x, p.y);
   }

   private void emergerMenuNodos(Point p) {
      JPopupMenu popup = new JPopupMenu();
      JMenuItem menuItem = new JMenuItem("Borrar el nodo");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.addSeparator();
      menuItem = new JMenuItem("Cambiar el nombre");
      menuItem.addActionListener(this);
      popup.add(menuItem);
      popup.show(this, p.x, p.y);
   }

   public void mouseMoved(MouseEvent e) {
   }

   public void mouseDragged(MouseEvent e) {
      if ((SwingUtilities.isMiddleMouseButton(e) || (this.mPressed && SwingUtilities.isLeftMouseButton(e)))
            && this.lastDragPoint != null) {
         int dx = e.getX() - this.lastDragPoint.x;
         int dy = e.getY() - this.lastDragPoint.y;
         this.tx += dx;
         this.ty += dy;
         this.lastDragPoint = e.getPoint();
         this.repaint();
      } else if (SwingUtilities.isLeftMouseButton(e) && this.operacion == 0 && this.nodoSeleccionado != -1
            && !this.mPressed) {
         int x = Math.max(10, Math.min(e.getX(), this.getWidth() - 10));
         int y = Math.max(10, Math.min(e.getY(), this.getHeight() - 10));
         // Apply zoom and translation correction
         x = (int) ((x - this.tx) / this.zoom);
         y = (int) ((y - this.ty) / this.zoom);
         this.getGrafo().cambiarPosicionIndex(this.nodoSeleccionado, new Point(x, y));
         this.repaint();
      }
   }

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_M) {
         this.mPressed = true;
      }
   }

   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_M) {
         this.mPressed = false;
         this.lastDragPoint = null;
      }
   }
}
