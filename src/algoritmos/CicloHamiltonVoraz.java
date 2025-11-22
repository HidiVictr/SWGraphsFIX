package algoritmos;

import grafos.Arista;
import grafos.Grafo;
import interfaz.PanelGrafos;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class CicloHamiltonVoraz extends CicloHamiltoniano {
   private ArrayList<Integer> NodosConectados;
   private ArrayList<Integer> NodosNOConectados;

   public CicloHamiltonVoraz(PanelGrafos p, JTextArea area) {
      super(p, area, (Grafo) null);
      this.grafoOriginal = this.panel.getGrafo();
   }

   public void run() {
      this.mostrarInformacionInicial(10, 1);
      this.ejecutaAlgoritmoVoraz();
      this.crearGrafoResultadoYmostrarInformacionResultado(10, true);
   }

   private boolean ejecutaAlgoritmoVoraz() {
      this.NodosConectados = new ArrayList();
      this.NodosNOConectados = new ArrayList();
      double pesoMin = 9999.0D;
      Arista a = null;
      this.grafoResultado = this.grafoOriginal.clonar();
      this.grafoResultado.resetAristas();

      for (int j = 1; j < this.grafoOriginal.getNodos().size(); ++j) {
         this.NodosNOConectados.add(j);
      }

      int nodoActual = 0;
      int nodoNuevo = -1;
      int posNodoNuevo = -1;
      int posNodoAnt = 1;
      this.NodosConectados.add(Integer.valueOf(nodoActual));
      this.NodosConectados.add(Integer.valueOf(nodoActual));
      this.textoInformacion.append("\n" + this.grafoResultado.getNodoByIndex(nodoActual).getNombre());
      int k = this.NodosConectados.size();

      for (int p = this.grafoOriginal.getNodos().size(); k <= p; pesoMin = 9999.0D) {
         for (int i = 0; i < this.NodosNOConectados.size(); ++i) {
            for (int j = 1; j < this.NodosConectados.size(); ++j) {
               a = this.grafoOriginal.getAristaByNodosIndex((Integer) this.NodosNOConectados.get(i),
                     (Integer) this.NodosConectados.get(j));
               if (a != null && pesoMin >= a.getPeso()) {
                  pesoMin = a.getPeso();
                  nodoNuevo = (Integer) this.NodosNOConectados.get(i);
                  posNodoNuevo = i;
                  posNodoAnt = j;
               }

               a = null;
            }
         }

         this.NodosConectados.add(posNodoAnt, nodoNuevo);
         this.NodosNOConectados.remove(posNodoNuevo);
         this.textoInformacion.append("\n" + this.grafoResultado.getNodoByIndex(nodoNuevo).getNombre());
         ++k;
      }

      this.insertarAristasAlGrafoResultado();
      this.textoInformacion.append("\n" + this.grafoResultado.getNodoByIndex(0).getNombre() + "\n");
      return true;
   }

   private void insertarAristasAlGrafoResultado() {
      int nodo1 = (Integer) this.NodosConectados.get(0);
      int nodo2 = 0;

      for (int i = 1; i < this.NodosConectados.size(); ++i) {
         nodo2 = (Integer) this.NodosConectados.get(i);
         this.grafoResultado.insertarArista(nodo1, nodo2);
         nodo1 = nodo2;
      }

   }
}
