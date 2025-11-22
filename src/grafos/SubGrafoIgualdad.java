package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class SubGrafoIgualdad extends GrafoEdmonds {
   private Vector<Arista> vectorC = new Vector();

   public SubGrafoIgualdad(GrafoEdmonds G) {
      this.nodos = G.nodos;
      this.setPrimeraFloracion(G.getPrimeraFloracion());

      for(int i = 0; i < G.getAristas().size(); ++i) {
         Arista a = G.getAristaByIndex(i);
         NodoEdmonds n1;
         NodoEdmonds n2;
         double diferencia;
         if (this.indexRealNodo(a.getCabeza()) == this.indexRealNodo(a.getCola())) {
            double etiquetaFloracion = this.floracionMasInternaEtiqueta(a.getCabeza(), a.getCola());
            n1 = (NodoEdmonds)G.getNodoByIndex(a.getCabeza());
            n2 = (NodoEdmonds)G.getNodoByIndex(a.getCola());
            diferencia = a.getPeso() - n1.getEtiqueta() - n2.getEtiqueta() - etiquetaFloracion;
         } else {
            n1 = (NodoEdmonds)G.getNodoByIndex(a.getCabeza());
            n2 = (NodoEdmonds)G.getNodoByIndex(a.getCola());
            diferencia = a.getPeso() - n1.getEtiqueta() - n2.getEtiqueta();
         }

         if (diferencia < 1.0E-7D & diferencia > -1.0E-7D) {
            this.aristas.add(a);
         } else {
            this.vectorC.add(a);
         }
      }

      this.acoplamiento = G.acoplamiento;
   }

   private double floracionMasInternaEtiqueta(int n1, int n2) {
      Floracion f1 = null;

      for(int i = this.getPrimeraFloracion(); i < this.getNodos().size(); ++i) {
         if (this.nodoContenidoEnFloracion(i, n1) && this.nodoContenidoEnFloracion(i, n1)) {
            f1 = (Floracion)this.nodos.get(i);
            return f1.getEtiqueta();
         }
      }

      return 0.0D;
   }

   public Vector<Arista> getC() {
      return this.vectorC;
   }

   public void pintarGrafo(Graphics g) {
      if (this != null) {
         int l;
         for(l = 0; l < this.getPrimeraFloracion(); ++l) {
            NodoEdmonds nodo = (NodoEdmonds)this.nodos.get(l);
            nodo.pintarNodo(g);
         }

         for(l = this.getPrimeraFloracion(); l < this.nodos.size(); ++l) {
            Floracion f = (Floracion)this.nodos.get(l);
            f.pintarFloracion(g);
         }

         Arista arista;
         for(l = 0; l < this.aristas.size(); ++l) {
            arista = (Arista)this.aristas.get(l);
            if (arista != null) {
               arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), false, Color.BLACK);
            }
         }

         for(l = 0; l < this.acoplamiento.size(); ++l) {
            arista = (Arista)this.acoplamiento.get(l);
            if (arista != null) {
               arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), true, Color.BLUE);
            }
         }
      }

   }
}
