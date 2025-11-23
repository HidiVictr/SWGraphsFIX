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

public class PanelGrafosDibujo extends PanelGrafos implements MouseListener, ActionListener, MouseMotionListener {
   private static final long serialVersionUID = 1L;
   private int operacion = 0;
   private Point punto;
   private int nodoSeleccionado = -1;
   private int aristaSeleccionada = -1;
   private final double umbralDistancia = 20.0D;

   public PanelGrafosDibujo() {
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
   }

   public PanelGrafosDibujo(Grafo arg0) {
      super(arg0);
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
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
         if (arg0.getButton() == 1) {
            this.crearNuevoNodo(arg0.getPoint());
         }

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
   }

   public void mouseExited(MouseEvent arg0) {
   }

   public void mousePressed(MouseEvent e) {
      if (e.getButton() == 1) {
         this.nodoSeleccionado = this.obtenerNodoMasCercano(e.getPoint(), 20.0D);
         if (this.nodoSeleccionado != -1) {
            Nodo n = this.getGrafo().getNodoByIndex(this.nodoSeleccionado);
            this.punto = n.getPos();
         }
      } else {
         this.nodoSeleccionado = -1;
      }

   }

   public void mouseReleased(MouseEvent e) {
      switch (this.operacion) {
         case 0:
            if (this.nodoSeleccionado != -1 && e.getPoint().distance(this.punto) > 15.0D) {
               int x = Math.max(10, Math.min(e.getX(), this.getWidth() - 10));
               int y = Math.max(10, Math.min(e.getY(), this.getHeight() - 10));
               // Apply zoom correction
               x = (int) (x / this.zoom);
               y = (int) (y / this.zoom);
               this.getGrafo().cambiarPosicionIndex(this.nodoSeleccionado, new Point(x, y));
               this.repaint();
            }
            break;
         case 1:
            if (this.nodoSeleccionado != -1 && this.obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1 && this.getGrafo()
                  .insertarArista(this.obtenerNodoMasCercano(e.getPoint(), 20.0D), this.nodoSeleccionado)) {
               this.repaint();
            }
            break;
         case 2:
            if (this.nodoSeleccionado != -1 && this.obtenerNodoMasCercano(e.getPoint(), 20.0D) != -1 && this.getGrafo()
                  .insertarArco(this.nodoSeleccionado, this.obtenerNodoMasCercano(e.getPoint(), 20.0D))) {
               this.repaint();
            }
      }

   }

   public void actionPerformed(ActionEvent arg0) {
      JMenuItem source = (JMenuItem) arg0.getSource();
      switch (this.operacion) {
         case 0:
            if (this.nodoSeleccionado != -1 && source.getText() == "Borrar el nodo") {
               this.eliminarNodoSeleccionado();
            } else {
               this.cambiarNombre();
            }
            break;
         case 1:
            if (this.aristaSeleccionada != -1 && source.getText() == "Borrar la arista") {
               this.eliminarAristaSeleccionada();
            } else {
               this.cambiarPeso();
            }
            break;
         case 2:
            if (this.aristaSeleccionada != -1 && source.getText() == "Borrar el arco") {
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
         } else if (this.getGrafo().cambiarNombreIndex(this.nodoSeleccionado, texto)) {
            this.repaint();
         } else {
            JOptionPane.showMessageDialog(this, "El nombre ya existe", "ERROR", 0);
         }
      } catch (NullPointerException var3) {
      }

   }

   private void cambiarPeso() {
      String texto = JOptionPane.showInputDialog(this, "Introduzca el nuevo peso de la arista", "Cambio peso", 3);

      try {
         double nuevoPeso = Double.parseDouble(texto);
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
         // Apply zoom correction for new nodes
         int x = (int) (point.x / this.zoom);
         int y = (int) (point.y / this.zoom);
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
      if (this.operacion == 0 && this.nodoSeleccionado != -1) {
         int x = Math.max(10, Math.min(e.getX(), this.getWidth() - 10));
         int y = Math.max(10, Math.min(e.getY(), this.getHeight() - 10));
         // Apply zoom correction
         x = (int) (x / this.zoom);
         y = (int) (y / this.zoom);
         this.getGrafo().cambiarPosicionIndex(this.nodoSeleccionado, new Point(x, y));
         this.repaint();
      }
   }
}
