package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class NodoEdmonds extends Nodo {
   private String etiquetaArborea = null;
   private int floracion = -1;

   public NodoEdmonds(String arg0, Point arg1) {
      super(arg0, arg1);
   }

   public String getEtiquetaArborea() {
      return this.etiquetaArborea;
   }

   public void setEtiquetaE() {
      this.etiquetaArborea = "E";
   }

   public void setEtiquetaI() {
      this.etiquetaArborea = "I";
   }

   public void borrarEtiquetaArborea() {
      this.etiquetaArborea = null;
   }

   public int getFloracion() {
      return this.floracion;
   }

   public void setFloracion(int i) {
      this.floracion = i;
   }

   public void pintarNodo(Graphics g) {
      if (this.getFloracion() == -1) {
         Point p = this.getPos();
         g.setColor(Color.BLACK);
         if (this.getEtiquetaArborea() == "E") {
            g.setColor(Color.RED);
         }

         if (this.getEtiquetaArborea() == "I") {
            g.setColor(Color.BLUE);
         }

         g.drawOval(p.x, p.y, 12, 12);
         g.drawString(String.valueOf(this.getEtiqueta()), p.x + 3, p.y + 11);
         g.setColor(Color.BLUE);
         g.drawString(this.getNombre(), p.x - 9, p.y + 23);
      }

   }

   public void pintarNodo(Graphics g, Color c) {
      if (this.getFloracion() == -1) {
         Point p = this.getPos();
         g.setColor(c);
         g.drawOval(p.x, p.y, 12, 12);
         g.drawString(String.valueOf(this.getEtiqueta()), p.x + 3, p.y + 11);
         g.setColor(Color.BLUE);
         g.drawString(this.getNombre(), p.x - 9, p.y + 23);
      }

   }

   public Nodo convertirANodo() {
      Nodo n = new Nodo(this.getNombre(), this.getPos());
      n.setEtiqueta(this.getEtiqueta());
      return n;
   }
}
