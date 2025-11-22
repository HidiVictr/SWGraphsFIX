package algoritmos;

import grafos.Arista;
import grafos.GrafoMultiColor;
import grafos.Nodo;
import interfaz.PanelGrafos;
import interfaz.matricesAdyacencia.MatrizTabla;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class Kruskal extends Algoritmo {
   private Arista a = null;
   private Arista a2 = null;
   private ArrayList<Integer> nodosConectados = new ArrayList();

   public Kruskal(PanelGrafos p, JTextArea area) {
      this.panel = p;
      this.textoInformacion = area;
      this.grafoOriginal = this.panel.getGrafo();
   }

   public void run() {
      MatrizTabla matrizGrafoPintar = new MatrizTabla(this.grafoOriginal);
      this.grafoResultado = new GrafoMultiColor(); // Use a clean result graph
      // Copy nodes to result
      for (Nodo n : this.grafoOriginal.getNodos()) {
         this.grafoResultado.insertarNodo(n);
      }

      double pesoTotal = 0.0D;

      // 1. Get all edges and sort them by weight
      ArrayList<Arista> todasAristas = new ArrayList<>();
      for (Object obj : this.grafoOriginal.getAristas()) {
         todasAristas.add((Arista) obj);
      }

      // Reverse the list first to ensure that in case of stable sort ties, we prefer
      // the "later" edges
      // This mimics the original behavior where it iterated and updated 'pesoMin' if
      // (pesoMin >= a.getPeso())
      // which effectively picked the last edge with the minimum weight.
      java.util.Collections.reverse(todasAristas);

      // Sort edges by weight
      todasAristas.sort((a1, a2) -> Double.compare(a1.getPeso(), a2.getPeso()));

      // 2. Union-Find structure
      int numNodos = this.grafoOriginal.getNodos().size();
      int[] parent = new int[numNodos];
      for (int i = 0; i < numNodos; i++)
         parent[i] = i;

      int aristasSeleccionadas = 0;

      for (Arista arista : todasAristas) {
         int u = arista.getCabeza();
         int v = arista.getCola();

         int setU = find(parent, u);
         int setV = find(parent, v);

         if (setU != setV) {
            // Add edge
            Arista nueva = new Arista(u, v, arista.getTipo());
            nueva.setPeso(arista.getPeso());
            this.grafoResultado.insertarArista(nueva);

            pesoTotal += arista.getPeso();
            union(parent, setU, setV);
            aristasSeleccionadas++;
         }
      }

      this.textoInformacion.setText("El algoritmo de Kruskal ha finalizado.\n\nRESULTADO:\n");
      this.textoInformacion.append(
            "\n\nEl árbol de expansión mínima (bosque de expansión mínima en el caso de grafos no conexos) se muestra en el panel de grafos de color azul sobre el grafo original.\n");

      GrafoMultiColor grafoPintar = new GrafoMultiColor();

      for (int i = 0; i < this.grafoOriginal.getNodos().size(); ++i) {
         Nodo n = this.grafoOriginal.getNodoByIndex(i);
         grafoPintar.insertarNodo(n);
      }

      // Add all original edges as normal
      for (Object obj : this.grafoOriginal.getAristas()) {
         Arista a = (Arista) obj;
         grafoPintar.insertarArista(a);
      }

      // Highlight selected edges in blue
      for (Object obj : this.grafoResultado.getAristas()) {
         Arista a = (Arista) obj;
         // Find the corresponding edge in grafoPintar to mark it blue
         // Or just add it to 'deAzul' list if that's how GrafoMultiColor works
         grafoPintar.deAzul.add(a);
      }

      this.textoInformacion.append("\nEl peso del árbol generador es: " + pesoTotal);
      this.panel.setGrafo(grafoPintar);
      this.panel.repaint(); // Force repaint
      this.terminar(); // Mark as finished
   }

   private int find(int[] parent, int i) {
      if (parent[i] == i)
         return i;
      return find(parent, parent[i]);
   }

   private void union(int[] parent, int x, int y) {
      int xroot = find(parent, x);
      int yroot = find(parent, y);
      parent[xroot] = yroot;
   }
}
