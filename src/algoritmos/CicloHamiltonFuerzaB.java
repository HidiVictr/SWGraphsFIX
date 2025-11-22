package algoritmos;

import grafos.Arista;
import grafos.Grafo;
import interfaz.PanelGrafos;
import interfaz.matricesAdyacencia.MatrizTabla;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class CicloHamiltonFuerzaB extends CicloHamiltoniano {
   private Grafo aux = null;
   private ArrayList<String> ciclos = new ArrayList();

   public CicloHamiltonFuerzaB(PanelGrafos p, JTextArea area) {
      super(p, area, (Grafo) null);
      this.grafoOriginal = this.panel.getGrafo();
      this.grafoResultado = this.grafoOriginal.clonar();
   }

   public void run() {
      boolean encuentraCiclo = false;
      this.mostrarInformacionInicial(14, 2);
      encuentraCiclo = this.ejecutaAlgoritmoFuerzaBruta();
      this.crearGrafoResultadoYmostrarInformacionResultado(14, encuentraCiclo);
   }

   private boolean ejecutaAlgoritmoFuerzaBruta() {
      MatrizTabla matrizGrafo = new MatrizTabla(this.grafoOriginal);
      this.aux = this.grafoOriginal.clonar();
      ArrayList<Integer> lista = null;
      Arista a = null;
      String ciclo = "";
      String cicloOK = "";
      int ant = 0;
      double menorPeso = 99999.0D;
      Double peso = 0.0D;
      double pesoCiclo = 0.0D;
      boolean eliminado = false;
      boolean existeCiclo = false;
      ArrayList<Integer> Nodos = new ArrayList();

      int n;
      for (n = 0; n < this.grafoResultado.getNodos().size(); ++n) {
         Nodos.add(n);
      }

      n = Nodos.size();
      int r = Nodos.size();
      this.Permutaciones(Nodos, "", n, r);

      int i;
      int var19;
      int k;
      for (i = 0; i < this.ciclos.size(); ++i) {
         ciclo = (String) this.ciclos.get(i);
         lista = new ArrayList();
         String[] var21;
         int var20 = (var21 = ciclo.split(",")).length;

         for (var19 = 0; var19 < var20; ++var19) {
            String numero = var21[var19];
            lista.add(Integer.valueOf(numero));
         }

         lista.add((Integer) lista.get(0));
         ant = (Integer) lista.get(0);
         pesoCiclo = 0.0D;
         eliminado = false;
         this.aux.resetAristas();

         for (k = 1; k < lista.size(); ++k) {
            peso = (Double) matrizGrafo.getValueAt(ant, (Integer) lista.get(k));
            if (peso == 0.0D) {
               eliminado = true;
               break;
            }

            pesoCiclo += peso;
            a = new Arista(ant, (Integer) lista.get(k), 0);
            a.setPeso(peso);
            this.aux.insertarArista(a);
            ant = (Integer) lista.get(k);
         }

         if (!eliminado) {
            if (pesoCiclo < menorPeso) {
               menorPeso = pesoCiclo;
               cicloOK = ciclo;
               this.grafoResultado = this.aux.clonar();
            }

            existeCiclo = true;
         }
      }

      if (existeCiclo) {
         lista = new ArrayList();
         String[] var25;
         var19 = (var25 = cicloOK.split(",")).length;

         for (k = 0; k < var19; ++k) {
            String numero = var25[k];
            lista.add(Integer.valueOf(numero));
         }

         lista.add((Integer) lista.get(0));

         for (i = 0; i < lista.size(); ++i) {
            this.textoInformacion.append("\n" + this.grafoResultado.getNodoByIndex((Integer) lista.get(i)).getNombre());
         }

         this.textoInformacion.append("\n");
      }

      return existeCiclo;
   }

   private void Permutaciones(ArrayList<Integer> elem, String act, int n, int r) {
      if (n == 0) {
         this.ciclos.add(act);
      } else {
         for (int i = 0; i < r; ++i) {
            if (!act.contains(((Integer) elem.get(i)).toString())) {
               this.Permutaciones(elem, act + elem.get(i) + ",", n - 1, r);
            }
         }
      }

   }
}
