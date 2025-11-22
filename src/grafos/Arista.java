package grafos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Arista {
   private int cola;
   private int cabeza;
   private int tipo;
   private double peso;
   private int multiplicidad;
   private int flujo;

   public Arista(int arg1, int arg2, int tipo) {
      this.cabeza = arg1;
      this.cola = arg2;
      this.tipo = tipo;
      this.peso = 1.0D;
      this.multiplicidad = 1;
      this.flujo = -1;
   }

   public int getCabeza() {
      return this.cabeza;
   }

   public int getCola() {
      return this.cola;
   }

   public int getTipo() {
      return this.tipo;
   }

   public void setTipo(int i) {
      this.tipo = i;
   }

   public double getPeso() {
      return this.peso;
   }

   public void setPeso(double f) {
      this.peso = f;
   }

   public void setCabeza(int i) {
      this.cabeza = i;
   }

   public void setCola(int i) {
      this.cola = i;
   }

   public int getFlujo() {
      return this.flujo;
   }

   public void setFlujo(int f) {
      this.flujo = f;
   }

   public int getMultiplicidad() {
      return this.multiplicidad;
   }

   public void setMultiplicidad(int m) {
      this.multiplicidad = m;
   }

   public void incrementarMultiplicidad() {
      ++this.multiplicidad;
   }

   public void decrementarMultiplicidad() {
      --this.multiplicidad;
   }

   public void pintarArista(Graphics g, Point posNodo1, Point posNodo2, int tipo, boolean deColor, Color color) {
      g.setColor(color);
      if (posNodo1 != posNodo2) {
         if (this.multiplicidad == 1) {
            double rumbo = this.calcularRumbo(posNodo1, posNodo2);
            int x1 = (int)((double)(posNodo1.x + 6) + 6.0D * Math.cos(rumbo - 3.141592653589793D));
            int y1 = (int)((double)(posNodo1.y + 6) + 6.0D * Math.sin(rumbo - 3.141592653589793D));
            int x2 = (int)((double)(posNodo2.x + 6) + 6.0D * Math.cos(rumbo));
            int y2 = (int)((double)(posNodo2.y + 6) + 6.0D * Math.sin(rumbo));
            int xMedio = (posNodo1.x + posNodo2.x) / 2;
            int yMedio = (posNodo1.y + posNodo2.y) / 2;
            g.drawLine(x1, y1, x2, y2);
            if (this.getFlujo() == -1) {
               g.drawString(String.valueOf(this.getPeso()), xMedio, yMedio);
            } else {
               int capacidad = (int)this.getPeso();
               int flujo = this.getFlujo();
               g.setColor(Color.BLUE);
               g.drawString(capacidad + ", ", xMedio, yMedio);
               g.setColor(new Color(51, 153, 102));
               g.drawString("     " + flujo, xMedio, yMedio);
               g.setColor(color);
            }

            if (deColor) {
               Graphics2D g2d = (Graphics2D)g;
               g2d.setStroke(new BasicStroke(3.0F));
               g2d.drawLine(x1, y1, x2, y2);
            }

            if (tipo == 1) {
               double ang = 0.0D;
               double angSep = 0.0D;
               int dist = 12;
               double ty = (double)(-(posNodo1.y - posNodo2.y)) * 1.0D;
               double tx = (double)(posNodo1.x - posNodo2.x) * 1.0D;
               ang = Math.atan(ty / tx);
               if (tx < 0.0D) {
                  ang += 3.141592653589793D;
               }

               Point p1 = new Point();
               Point p2 = new Point();
               Point punto = new Point(x2, y2);
               angSep = 25.0D;
               p1.x = (int)((double)punto.x + (double)dist * Math.cos(ang - Math.toRadians(angSep)));
               p1.y = (int)((double)punto.y - (double)dist * Math.sin(ang - Math.toRadians(angSep)));
               p2.x = (int)((double)punto.x + (double)dist * Math.cos(ang + Math.toRadians(angSep)));
               p2.y = (int)((double)punto.y - (double)dist * Math.sin(ang + Math.toRadians(angSep)));
               int[] xpoints = new int[]{p1.x, punto.x, p2.x};
               int[] ypoints = new int[]{p1.y, punto.y, p2.y};
               int npoints = 3;
               g.fillPolygon(xpoints, ypoints, npoints);
            }
         } else {
            this.pintarMultiarista(g, posNodo1, posNodo2);
         }
      } else {
         int Punto_inicio_en_x = posNodo1.x - 30;
         int Punto_inicio_en_y = posNodo1.y - 10;
         int Ancho = 30;
         int Alto = 30;
         int Angulo_de_inicio = 20;
         int Amplitud_del_angulo = 315;
         g.drawArc(Punto_inicio_en_x, Punto_inicio_en_y, Ancho, Alto, Angulo_de_inicio, Amplitud_del_angulo);
         g.drawString(String.valueOf(this.getPeso()), posNodo1.x - 25, posNodo1.y + 10);
      }

   }

   public void pintarMultiarista(Graphics g, Point posNodo1, Point posNodo2) {
      double rumbo = this.calcularRumbo(posNodo1, posNodo2);
      int x1 = (int)((double)(posNodo1.x + 6) + 6.0D * Math.cos(rumbo - 3.141592653589793D));
      int y1 = (int)((double)(posNodo1.y + 6) + 6.0D * Math.sin(rumbo - 3.141592653589793D));
      int x2 = (int)((double)(posNodo2.x + 6) + 6.0D * Math.cos(rumbo));
      int y2 = (int)((double)(posNodo2.y + 6) + 6.0D * Math.sin(rumbo));
      int xMedio = (posNodo1.x + posNodo2.x) / 2;
      int yMedio = (posNodo1.y + posNodo2.y) / 2;
      g.drawLine(x1, y1, x2, y2);
      g.drawString(String.valueOf(this.getPeso()), xMedio, yMedio);
      ++rumbo;

      for(int i = 1; i < this.multiplicidad; ++i) {
         rumbo += 3.141592653589793D;
         int xMedioDesviado = xMedio + (int)((double)((i + 1) * 10) * Math.cos(rumbo));
         int yMedioDesviado = yMedio + (int)((double)((i + 1) * 10) * Math.sin(rumbo));
         g.drawLine(x1, y1, xMedioDesviado, yMedioDesviado);
         g.drawLine(xMedioDesviado, yMedioDesviado, x2, y2);
      }

   }

   private double calcularRumbo(Point p1, Point p2) {
      int x1 = p1.x;
      int x2 = p2.x;
      int y1 = p1.y;
      int y2 = p2.y;
      int altura = y1 - y2;
      int longitud = x1 - x2;
      if (longitud == 0) {
         return altura > 0 ? 1.5707963267948966D : 4.71238898038469D;
      } else {
         double rumbo = Math.atan((double)(altura / longitud));
         if (longitud < 0) {
            rumbo += 3.141592653589793D;
         }

         return rumbo;
      }
   }
}
