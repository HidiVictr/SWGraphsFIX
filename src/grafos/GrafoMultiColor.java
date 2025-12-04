package grafos;

import interfaz.matricesAdyacencia.MatrizTabla;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class GrafoMultiColor extends Grafo {
   public Vector<Arista> deAzul = new Vector<>();

   public void pintarGrafo(Graphics g) {
      pintarGrafo(g, 1.0, 0.0, 0.0);
   }

   public void pintarGrafo(Graphics g, double zoom, double tx, double ty) {
      for (int i = 0; i < nodos.size(); ++i) {
         Nodo nodo = (Nodo) nodos.get(i);
         Point p = nodo.getPos();
         Point pZoom = new Point((int) (p.x * zoom + tx), (int) (p.y * zoom + ty));
         nodo.pintarNodo(g, pZoom, Color.BLACK);
      }

      for (int i = 0; i < aristas.size(); ++i) {
         Arista arista = (Arista) aristas.get(i);
         if (arista != null) {
            Nodo nodoCabeza = getNodoByIndex(arista.getCabeza());
            Nodo nodoCola = getNodoByIndex(arista.getCola());
            Point pCabeza = nodoCabeza.getPos();
            Point pCola = nodoCola.getPos();
            Point pCabezaZoom = new Point((int) (pCabeza.x * zoom + tx), (int) (pCabeza.y * zoom + ty));
            Point pColaZoom = new Point((int) (pCola.x * zoom + tx), (int) (pCola.y * zoom + ty));
            arista.pintarArista(g, pCabezaZoom, pColaZoom, arista.getTipo(), false, Color.BLACK);
         }
      }

      for (int i = 0; i < deAzul.size(); ++i) {
         Arista arista = (Arista) deAzul.get(i);
         if (arista != null) {
            Nodo nodoCabeza = getNodoByIndex(arista.getCabeza());
            Nodo nodoCola = getNodoByIndex(arista.getCola());
            Point pCabeza = nodoCabeza.getPos();
            Point pCola = nodoCola.getPos();
            Point pCabezaZoom = new Point((int) (pCabeza.x * zoom + tx), (int) (pCabeza.y * zoom + ty));
            Point pColaZoom = new Point((int) (pCola.x * zoom + tx), (int) (pCola.y * zoom + ty));
            arista.pintarArista(g, pCabezaZoom, pColaZoom, arista.getTipo(), true, Color.BLUE);
         }
      }
   }

   public GrafoMultiColor convertirAGrafoMultiColor(Grafo g) {
      MatrizTabla matrizGrafoPintar = new MatrizTabla(g);
      GrafoMultiColor grafoPintar = new GrafoMultiColor();
      grafoPintar.setTipo(g.getTipo());

      for (int i = 0; i < g.getNodos().size(); ++i) {
         Nodo n = g.getNodoByIndex(i);
         grafoPintar.insertarNodo(n);
      }

      for (int i = 0; i < g.getNodos().size(); ++i) {
         int fin = 0;
         if (grafoPintar.getTipo() == 0) {
            fin = i;
         }

         for (int j = fin; j < g.getNodos().size(); ++j) {
            Double peso = (Double) matrizGrafoPintar.getValueAt(i, j);
            if (peso != 0.0) {
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

      for (int i = 0; i < deAzul.size(); ++i) {
         Arista arista = (Arista) deAzul.get(i);
         if ((arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola())
               || (arista.getCabeza() == a.getCola() && arista.getCola() == a.getCabeza())) {
            existeArista = true;
         }
      }

      return existeArista;
   }

   public boolean existeArcoAzul(Arista a) {
      boolean existeArco = false;

      for (int i = 0; i < deAzul.size(); ++i) {
         Arista arista = (Arista) deAzul.get(i);
         if (arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola()) {
            existeArco = true;
         }
      }

      return existeArco;
   }

   public GrafoMultiColor clonar() {
      GrafoMultiColor grf_clon = new GrafoMultiColor();
      grf_clon.setTipo(getTipo());
      grf_clon.nodos = (Vector<Nodo>) nodos.clone();

      for (int i = 0; i < aristas.size(); ++i) {
         Arista arista = (Arista) aristas.get(i);
         Arista a_clonada = new Arista(arista.getCabeza(), arista.getCola(), arista.getTipo());
         a_clonada.setPeso(arista.getPeso());
         a_clonada.setMultiplicidad(arista.getMultiplicidad());
         grf_clon.aristas.add(a_clonada);
      }

      for (int i = 0; i < deAzul.size(); ++i) {
         Arista arista = (Arista) deAzul.get(i);
         grf_clon.deAzul.add(arista);
      }

      return grf_clon;
   }
}
