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

public class PanelGrafos extends JPanel {
   private static final long serialVersionUID = 1L;
   private Grafo G;
   public Image imagen = null;

   public PanelGrafos() {
      this.setVisible(true);
      this.G = new Grafo();
      this.repaint();
   }

   public PanelGrafos(Grafo arg0) {
      this.setGrafo(arg0);
   }

   public Grafo getGrafo() {
      return this.G;
   }

   public void setGrafo(Grafo grafo) {
      this.G = grafo;
   }

   protected int obtenerNodoMasCercano(Point p, double umbral) {
      int posicion = -1;
      double distanciaMinima = umbral;

      for (int i = 0; i < this.getGrafo().getNodos().size(); ++i) {
         Nodo nodo1 = this.getGrafo().getNodoByIndex(i);
         double distancia = Math.sqrt((double) ((p.x - nodo1.getPos().x) * (p.x - nodo1.getPos().x)
               + (p.y - nodo1.getPos().y) * (p.y - nodo1.getPos().y)));
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
      ptoCabeza = nodoCabeza.getPos();
      ptoCola = nodoCola.getPos();
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
      this.G.pintarGrafo(g);
   }
}
