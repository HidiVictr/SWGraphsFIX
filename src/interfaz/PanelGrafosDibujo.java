package interfaz;

import grafos.Arista;
import grafos.Grafo;
import grafos.Nodo;
import java.awt.Color;
import java.awt.Graphics;
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
   private Point currentDragPoint = null;

   public PanelGrafosDibujo() {
      addMouseListener(this);
      addMouseMotionListener(this);
      addKeyListener(this);
      setFocusable(true);
      setupShortcuts();
   }

   public PanelGrafosDibujo(Grafo arg0) {
      super(arg0);
      addMouseListener(this);
      addMouseMotionListener(this);
      addKeyListener(this);
      setFocusable(true);
      setupShortcuts();
   }

   private void setupShortcuts() {
      InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
      ActionMap actionMap = getActionMap();

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
      operacion = op;
   }

   public int getOperacion() {
      return operacion;
   }

   public void mouseClicked(MouseEvent e) {
      if (operacion == 0) {
         nodoSeleccionado = obtenerNodoMasCercano(e.getPoint(), 20.0D);
         // Node creation moved to mouseReleased for better responsiveness
         // if (e.getButton() == 1) {
         // crearNuevoNodo(e.getPoint());
         // }

         if (e.getButton() == 3 && nodoSeleccionado > -1) {
            emergerMenuNodos(e.getPoint());
         }
      } else if (e.getButton() == 3) {
         aristaSeleccionada = obtenerAristaMasCercana(e.getPoint(), 20.0D);
         if (aristaSeleccionada > -1) {
            if (operacion == 1) {
               emergerMenuAristas(e.getPoint());
            } else {
               emergerMenuArcos(e.getPoint());
            }
         }
      }

   }

   public void mouseEntered(MouseEvent e) {
      requestFocusInWindow();
   }

   public void mouseExited(MouseEvent e) {
   }

   private Stack<Grafo> undoStack = new Stack<>();
   private Stack<Grafo> redoStack = new Stack<>();

   public void guardarEstado() {
      try {
         System.out.println("Guardando estado... Stack size: " + undoStack.size());
         undoStack.push(getGrafo().clonar());
         redoStack.clear(); // New action clears redo history
      } catch (Exception e) {
         System.err.println("Error saving state: " + e.getMessage());
         e.printStackTrace();
      }
   }

   public void deshacer() {
      System.out.println("Deshaciendo... Stack size: " + undoStack.size());
      if (!undoStack.isEmpty()) {
         // Save current state to redo stack before undoing
         redoStack.push(getGrafo().clonar());

         setGrafo(undoStack.pop());
         // Reset selection to avoid stale state issues
         nodoSeleccionado = -1;
         aristaSeleccionada = -1;
         punto = null;
         repaint();
      } else {
         System.out.println("Nothing to undo.");
      }
   }

   public void rehacer() {
      System.out.println("Rehaciendo... Redo Stack size: " + redoStack.size());
      if (!redoStack.isEmpty()) {
         // Save current state to undo stack before redoing
         undoStack.push(getGrafo().clonar());

         setGrafo(redoStack.pop());
         // Reset selection
         nodoSeleccionado = -1;
         aristaSeleccionada = -1;
         punto = null;
         repaint();
      } else {
         System.out.println("Nothing to redo.");
      }
   }

   private Point mousePressedPoint;

   public void mousePressed(MouseEvent e) {
      System.out.println("MousePressed: " + e.getPoint());
      requestFocusInWindow();
      mousePressedPoint = e.getPoint();
      if (SwingUtilities.isMiddleMouseButton(e) || (mPressed && SwingUtilities.isLeftMouseButton(e))) {
         lastDragPoint = e.getPoint();
         nodoSeleccionado = -1; // Deselect node when panning to prevent accidental drags
      } else if (e.getButton() == 1) {
         nodoSeleccionado = obtenerNodoMasCercano(e.getPoint(), 20.0D);
         if (nodoSeleccionado != -1) {
            // Save state before potential drag
            guardarEstado();
            Nodo n = getGrafo().getNodoByIndex(nodoSeleccionado);
            punto = n.getPos();
         }
      } else {
         nodoSeleccionado = -1;
      }

   }

   public void mouseReleased(MouseEvent e) {
      lastDragPoint = null;
      switch (operacion) {
         case 0:
            if (nodoSeleccionado != -1 && punto != null && e.getPoint().distance(punto) > 15.0D) {
               int x = Math.max(10, Math.min(e.getX(), getWidth() - 10));
               int y = Math.max(10, Math.min(e.getY(), getHeight() - 10));
               // Apply zoom and translation correction
               x = (int) ((x - tx) / zoom);
               y = (int) ((y - ty) / zoom);
               getGrafo().cambiarPosicionIndex(nodoSeleccionado, new Point(x, y));
               repaint();
            } else if (nodoSeleccionado != -1) {
               // If it wasn't a drag (just a click), pop the state we saved in mousePressed
               // to avoid accumulating useless states
               if (!undoStack.isEmpty()) {
                  undoStack.pop();
               }
            } else if (SwingUtilities.isLeftMouseButton(e) && !mPressed && mousePressedPoint != null
                  && e.getPoint().distance(mousePressedPoint) < 5.0D) {
               // Click on empty space (not a drag, not a pan) -> Create Node
               getGrafo().limpiarSolucion();
               crearNuevoNodo(e.getPoint());
            }
            break;
         case 1:
            if (nodoSeleccionado != -1 && obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1) {
               guardarEstado();
               if (getGrafo().insertarArista(obtenerNodoMasCercano(e.getPoint(), 20.0D),
                     nodoSeleccionado)) {
                  getGrafo().limpiarSolucion();
                  repaint();
               } else {
                  undoStack.pop(); // Revert save if insertion failed
               }
            }
            break;
         case 2:
            if (nodoSeleccionado != -1 && obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1) {
               guardarEstado();
               if (getGrafo().insertarArco(nodoSeleccionado,
                     obtenerNodoMasCercano(e.getPoint(), 20.0D))) {
                  getGrafo().limpiarSolucion();
                  repaint();
               } else {
                  undoStack.pop(); // Revert save if insertion failed
               }
            }
      }
      currentDragPoint = null;
      repaint();
   }

   public void actionPerformed(ActionEvent e) {
      JMenuItem source = (JMenuItem) e.getSource();
      switch (operacion) {
         case 0:
            if (nodoSeleccionado != -1 && source.getText() == "Borrar el nodo") {
               guardarEstado();
               eliminarNodoSeleccionado();
            } else {
               cambiarNombre();
            }
            break;
         case 1:
            if (aristaSeleccionada != -1 && source.getText() == "Borrar la arista") {
               guardarEstado();
               eliminarAristaSeleccionada();
            } else {
               cambiarPeso();
            }
            break;
         case 2:
            if (aristaSeleccionada != -1 && source.getText() == "Borrar el arco") {
               guardarEstado();
               eliminarAristaSeleccionada();
            } else {
               cambiarPeso();
            }
      }

   }

   private void eliminarAristaSeleccionada() {
      getGrafo().limpiarSolucion();
      getGrafo().borrarAristaIndex(aristaSeleccionada);
      repaint();
   }

   private void eliminarNodoSeleccionado() {
      getGrafo().limpiarSolucion();
      getGrafo().borrarNodoIndex(nodoSeleccionado);
      repaint();
   }

   private void cambiarNombre() {
      String texto = JOptionPane.showInputDialog(this, "Introduzca el nuevo nombre del nodo", "Cambiar Nombre", 3);

      try {
         if (texto.length() == 0) {
            JOptionPane.showMessageDialog(this, "No se permiten nombres vacios", "ERROR", 0);
         } else {
            guardarEstado();
            if (getGrafo().cambiarNombreIndex(nodoSeleccionado, texto)) {
               getGrafo().limpiarSolucion();
               repaint();
            } else {
               JOptionPane.showMessageDialog(this, "El nombre ya existe", "ERROR", 0);
               undoStack.pop(); // Revert save
            }
         }
      } catch (NullPointerException ex) {
      }

   }

   private void cambiarPeso() {
      String texto = JOptionPane.showInputDialog(this, "Introduzca el nuevo peso de la arista", "Cambio peso", 3);

      try {
         double nuevoPeso = Double.parseDouble(texto);
         guardarEstado();
         Arista arista = getGrafo().getAristaByIndex(aristaSeleccionada);
         arista.setPeso(nuevoPeso);
         getGrafo().getAristas().set(aristaSeleccionada, arista);
         getGrafo().limpiarSolucion();
         repaint();
      } catch (NumberFormatException ex) {
         JOptionPane.showMessageDialog(this, "El peso " + texto + " no es valido.\nDebe ser un nmero", "ERROR", 0);
      } catch (NullPointerException ex) {
      }

   }

   private void crearNuevoNodo(Point point) {
      if (nodoSeleccionado == -1) {
         guardarEstado();
         // Apply zoom and translation correction for new nodes
         int x = (int) ((point.x - tx) / zoom);
         int y = (int) ((point.y - ty) / zoom);
         getGrafo().insertarNodo(new Point(x, y));
         repaint();
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
      if ((SwingUtilities.isMiddleMouseButton(e) || (mPressed && SwingUtilities.isLeftMouseButton(e)))
            && lastDragPoint != null) {
         int dx = e.getX() - lastDragPoint.x;
         int dy = e.getY() - lastDragPoint.y;
         tx += dx;
         ty += dy;
         lastDragPoint = e.getPoint();
         repaint();
      } else if (SwingUtilities.isLeftMouseButton(e) && operacion == 0 && nodoSeleccionado != -1
            && !mPressed) {
         int x = Math.max(10, Math.min(e.getX(), getWidth() - 10));
         int y = Math.max(10, Math.min(e.getY(), getHeight() - 10));
         // Apply zoom and translation correction
         x = (int) ((x - tx) / zoom);
         y = (int) ((y - ty) / zoom);
         getGrafo().cambiarPosicionIndex(nodoSeleccionado, new Point(x, y));
         repaint();
      } else if (SwingUtilities.isLeftMouseButton(e) && (operacion == 1 || operacion == 2) && nodoSeleccionado != -1) {
         currentDragPoint = e.getPoint();
         repaint();
      }
   }

   public void keyTyped(KeyEvent e) {
   }

   public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_M) {
         mPressed = true;
      }
   }

   public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_M) {
         mPressed = false;
         lastDragPoint = null;
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      if ((operacion == 1 || operacion == 2) && nodoSeleccionado != -1 && currentDragPoint != null) {
         Nodo n = getGrafo().getNodoByIndex(nodoSeleccionado);
         Point p = n.getPos();
         int cx = (int) (p.x * zoom + tx) + 6;
         int cy = (int) (p.y * zoom + ty) + 6;
         double angle = Math.atan2(currentDragPoint.y - cy, currentDragPoint.x - cx);
         int x1 = (int) (cx + 6 * Math.cos(angle));
         int y1 = (int) (cy + 6 * Math.sin(angle));
         g.setColor(Color.BLACK);
         g.drawLine(x1, y1, currentDragPoint.x, currentDragPoint.y);
      }
   }
}
