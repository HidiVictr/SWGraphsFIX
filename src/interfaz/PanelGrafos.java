package interfaz;

import grafos.Arista;
import grafos.Grafo;
import grafos.Nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Line2D.Double;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class PanelGrafos extends JPanel implements MouseWheelListener {
   private static final long serialVersionUID = 1L;
   private Grafo G;
   public Image imagen = null;
   protected double zoom = 1.0;

   public PanelGrafos() {
      this.setVisible(true);
      this.G = new Grafo();
      this.addMouseWheelListener(this);
      this.repaint();
   }

   public PanelGrafos(Grafo arg0) {
      this.setGrafo(arg0);
      this.addMouseWheelListener(this);
   }

   public Grafo getGrafo() {
      return this.G;
   }

   public void setGrafo(Grafo grafo) {
      this.G = grafo;
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      if (e.getWheelRotation() < 0) {
         this.zoom *= 1.1;
      } else {
         this.zoom /= 1.1;
      }
      if (this.zoom < 0.1)
         this.zoom = 0.1;
      if (this.zoom > 5.0)
         this.zoom = 5.0;
      this.repaint();
   }

   protected int obtenerNodoMasCercano(Point p, double umbral) {
      int posicion = -1;
      double distanciaMinima = umbral;

      for (int i = 0; i < this.getGrafo().getNodos().size(); ++i) {
         Nodo nodo1 = this.getGrafo().getNodoByIndex(i);
         Point pNodo = nodo1.getPos();
         Point pNodoZoom = new Point((int) (pNodo.x * zoom), (int) (pNodo.y * zoom));
         double distancia = Math.sqrt((double) ((p.x - pNodoZoom.x) * (p.x - pNodoZoom.x)
               + (p.y - pNodoZoom.y) * (p.y - pNodoZoom.y)));
         if (distancia < distanciaMinima) {
            distanciaMinima = distancia;
            posicion = i;
         }
      }

      return posicion;
   }

   protected int obtenerAristaMasCercana(Point p, double umbral) {
      int posicion = -1;
      double distanciaMinima = umbral;

      for (int i = 0; i < this.getGrafo().getAristas().size(); ++i) {
         Arista arista = this.getGrafo().getAristaByIndex(i);
         double distancia = this.distanciaPuntoArista(arista, p);
         if (distancia < distanciaMinima) {
            distanciaMinima = distancia;
            posicion = i;
         }
      }

      return posicion;
   }

   protected double distanciaPuntoArista(Arista arista, Point pto) {
      Double linea = null;
      Point ptoCabeza = null;
      Point ptoCola = null;
      Nodo nodoCabeza = this.getGrafo().getNodoByIndex(arista.getCabeza());
      Nodo nodoCola = this.getGrafo().getNodoByIndex(arista.getCola());

      Point rawCabeza = nodoCabeza.getPos();
      Point rawCola = nodoCola.getPos();
      ptoCabeza = new Point((int) (rawCabeza.x * zoom), (int) (rawCabeza.y * zoom));
      ptoCola = new Point((int) (rawCola.x * zoom), (int) (rawCola.y * zoom));

      if (ptoCabeza == ptoCola) {
         double dist = Math
               .sqrt(Math.pow((double) (pto.x - ptoCabeza.x), 2.0D) + Math.pow((double) (pto.y - ptoCabeza.y), 2.0D));
         return dist - 10.0D;
      } else {
         linea = new Double(ptoCabeza, ptoCola);
         return linea.ptSegDist(pto);
      }
   }

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      if (this.imagen != null) {
         g.drawImage(this.imagen, 0, 0, this.getWidth(), this.getHeight(), this);
         this.setOpaque(false);
      } else {
         this.setOpaque(true);
      }

      g.setColor(Color.BLACK);
      Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      this.G.pintarGrafo(g, zoom);
   }
}
