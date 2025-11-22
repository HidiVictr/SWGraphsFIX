package grafos;

import interfaz.matricesAdyacencia.MatrizTabla;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class GrafoMultiColor extends Grafo {
   public Vector<Arista> deAzul = new Vector();

   public void pintarGrafo(Graphics g) {
      if (this != null) {
         int j;
         Nodo n1;
         for(j = 0; j < this.nodos.size(); ++j) {
            n1 = (Nodo)this.nodos.get(j);
            n1.pintarNodo(g, Color.BLACK);
         }

         Arista arista;
         Nodo n2;
         for(j = 0; j < this.aristas.size(); ++j) {
            arista = (Arista)this.aristas.get(j);
            if (arista != null) {
               n1 = this.getNodoByIndex(arista.getCabeza());
               n2 = this.getNodoByIndex(arista.getCola());
               arista.pintarArista(g, n1.getPos(), n2.getPos(), arista.getTipo(), false, Color.BLACK);
            }
         }

         for(j = 0; j < this.deAzul.size(); ++j) {
            arista = (Arista)this.deAzul.get(j);
            if (arista != null) {
               n1 = this.getNodoByIndex(arista.getCabeza());
               n2 = this.getNodoByIndex(arista.getCola());
               arista.pintarArista(g, n1.getPos(), n2.getPos(), arista.getTipo(), true, Color.BLUE);
            }
         }
      }

   }

   public GrafoMultiColor convertirAGrafoMultiColor(Grafo g) {
      MatrizTabla matrizGrafoPintar = new MatrizTabla(g);
      GrafoMultiColor grafoPintar = new GrafoMultiColor();
      grafoPintar.setTipo(g.getTipo());

      int i;
      for(i = 0; i < g.getNodos().size(); ++i) {
         Nodo n = g.getNodoByIndex(i);
         grafoPintar.insertarNodo(n);
      }

      for(i = 0; i < g.getNodos().size(); ++i) {
         int fin = 0;
         if (grafoPintar.getTipo() == 0) {
            fin = i;
         }

         for(int j = fin; j < g.getNodos().size(); ++j) {
            Double peso = (Double)matrizGrafoPintar.getValueAt(i, j);
            if (peso != 0.0D) {
               Arista a;
               if (grafoPintar.getTipo() == 0) {
                  a = new Arista(i, j, 0);
                  a.setPeso(peso);
                  grafoPintar.insertarArista(a);
               } else {
                  a = new Arista(i, j, 1);
                  a.setPeso(peso);
                  grafoPintar.insertarArco(a);
               }
            }
         }
      }

      return grafoPintar;
   }

   public boolean existeAristaAzul(Arista a) {
      boolean existeArista = false;

      for(int k = 0; k < this.deAzul.size(); ++k) {
         Arista arista = (Arista)this.deAzul.get(k);
         if (arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola() || arista.getCabeza() == a.getCola() && arista.getCola() == a.getCabeza()) {
            existeArista = true;
         }
      }

      return existeArista;
   }

   public boolean existeArcoAzul(Arista a) {
      boolean existeArco = false;

      for(int k = 0; k < this.deAzul.size(); ++k) {
         Arista arista = (Arista)this.deAzul.get(k);
         if (arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola()) {
            existeArco = true;
         }
      }

      return existeArco;
   }

   public GrafoMultiColor clonar() {
      GrafoMultiColor grf_clon = new GrafoMultiColor();
      grf_clon.setTipo(this.getTipo());
      grf_clon.nodos = (Vector)this.nodos.clone();

      int j;
      Arista arista;
      for(j = 0; j < this.aristas.size(); ++j) {
         arista = (Arista)this.aristas.get(j);
         Arista a_clonada = new Arista(arista.getCabeza(), arista.getCola(), arista.getTipo());
         a_clonada.setPeso(arista.getPeso());
         a_clonada.setMultiplicidad(arista.getMultiplicidad());
         grf_clon.aristas.add(a_clonada);
      }

      for(j = 0; j < this.deAzul.size(); ++j) {
         arista = (Arista)this.deAzul.get(j);
         grf_clon.deAzul.add(arista);
      }

      return grf_clon;
   }
}
