package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Nodo {
   private String nombre;
   private Point posicion;
   private double etiqueta;

   public Nodo(String arg0, Point arg1) {
      this.nombre = arg0;
      this.posicion = arg1;
      this.etiqueta = 0.0D;
   }

   public String getNombre() {
      return this.nombre;
   }

   public void setNombre(String string) {
      this.nombre = string;
   }

   public double getEtiqueta() {
      return this.etiqueta;
   }

   public void setEtiqueta(double etq) {
      this.etiqueta = etq;
      if (etq < 1.0E-7D & etq > -1.0E-7D) {
         this.etiqueta = 0.0D;
      }

   }

   public Point getPos() {
      return this.posicion;
   }

   public void setPos(Point p) {
      this.posicion = p;
   }

   public void pintarNodo(Graphics g, Color color) {
      this.pintarNodo(g, this.getPos(), color);
   }

   public void pintarNodo(Graphics g, Point p, Color color) {
      g.setColor(color);
      g.drawOval(p.x, p.y, 12, 12);
      g.setColor(Color.BLUE);
      g.drawString(this.getNombre(), p.x - 3, p.y + 23);
   }

   public void pintarNodoRelleno(Graphics g, Color color) {
      this.pintarNodoRelleno(g, this.getPos(), color);
   }

   public void pintarNodoRelleno(Graphics g, Point p, Color color) {
      g.setColor(color);
      g.fillOval(p.x, p.y, 12, 12);
   }
}
