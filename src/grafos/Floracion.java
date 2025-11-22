package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class Floracion extends NodoEdmonds {
   private Vector<Integer> cicloContraido = null;

   public Floracion(String arg0, Point arg1, Vector<Integer> arg2) {
      super(arg0, arg1);
      this.cicloContraido = arg2;
      this.setEtiqueta(0.0D);
   }

   public Vector<Integer> getCicloContraido() {
      return this.cicloContraido;
   }

   public void setCicloContraido(Vector<Integer> ciclo) {
      this.cicloContraido = ciclo;
   }

   public void pintarFloracion(Graphics g) {
      if (this.getFloracion() == -1) {
         Point p = this.getPos();
         g.setColor(Color.BLACK);
         if (this.getEtiquetaArborea() == "E") {
            g.setColor(Color.RED);
         }

         if (this.getEtiquetaArborea() == "I") {
            g.setColor(Color.BLUE);
         }

         g.drawRect(p.x, p.y, 25, 15);
         g.drawString(this.getNombre(), p.x + 10, p.y + 10);
         g.drawString(String.valueOf(this.getEtiqueta()), p.x - 5, p.y - 5);
      }

   }

   public void pintarFloracion(Graphics g, Color c) {
      if (this.getFloracion() == -1) {
         Point p = this.getPos();
         g.setColor(c);
         g.drawRect(p.x, p.y, 25, 15);
         g.drawString(this.getNombre(), p.x + 10, p.y + 10);
         g.drawString(String.valueOf(this.getEtiqueta()), p.x - 5, p.y - 5);
      }

   }
}
