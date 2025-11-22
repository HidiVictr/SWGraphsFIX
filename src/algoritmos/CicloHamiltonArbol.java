package algoritmos;

import grafos.Arista;
import grafos.Grafo;
import interfaz.PanelGrafos;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JTextArea;

public class CicloHamiltonArbol extends CicloHamiltoniano {
   public CicloHamiltonArbol(PanelGrafos p, JTextArea area, Grafo g) {
      super(p, area, g);
      this.grafoResultado = this.panel.getGrafo();
   }

   public void run() {
      this.mostrarInformacionInicial(10, 0);
      ArrayList<String> NodosConectadosRepe = new ArrayList();
      ArrayList<String> NodosConectados = new ArrayList();
      ArrayList<Integer> NodosOrden = new ArrayList();

      for(int i = 0; i < this.grafoResultado.getNodos().size(); ++i) {
         NodosConectadosRepe.add(this.grafoResultado.getNodoByIndex(i).getNombre());
      }

      HashSet<String> hashSet = new HashSet(NodosConectadosRepe);
      NodosConectados.clear();
      NodosConectados.addAll(hashSet);

      int k;
      for(k = 0; k < NodosConectados.size(); ++k) {
         NodosOrden.add(this.grafoOriginal.getPosicionNodoByNombre((String)NodosConectados.get(k)));
         this.textoInformacion.setText(this.textoInformacion.getText() + "\n" + (String)NodosConectados.get(k));
      }

      NodosOrden.add(this.grafoOriginal.getPosicionNodoByNombre((String)NodosConectados.get(0)));
      this.textoInformacion.setText(this.textoInformacion.getText() + "\n" + (String)NodosConectados.get(0) + "\n");
      this.grafoResultado.resetAristas();

      for(k = 0; k < NodosOrden.size() - 1; ++k) {
         Arista a = new Arista((Integer)NodosOrden.get(k), (Integer)NodosOrden.get(k + 1), 0);
         this.grafoResultado.insertarArista(a);
      }

      this.crearGrafoResultadoYmostrarInformacionResultado(10, true);
   }
}
