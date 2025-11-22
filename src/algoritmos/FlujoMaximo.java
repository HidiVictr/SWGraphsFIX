package algoritmos;

import Util.Matriz;
import grafos.Arista;
import grafos.Grafo;
import grafos.GrafoMultiColor;
import interfaz.MenuPrincipal;
import interfaz.PanelGrafos;
import javax.swing.JTextArea;

public class FlujoMaximo extends Algoritmo {
   private Grafo grafoOriginal;
   private Grafo grafoResultado;
   private GrafoMultiColor grafoPintar;
   private int fuente;
   private int sumidero;
   private double flujoMax = 0.0D;
   private double delta = 0.0D;
   private int numVertices;
   private Matriz C = null;
   private Matriz Aux = null;
   private Matriz B = null;
   private Matriz M = null;
   private PanelGrafos panel;
   private JTextArea textoInformacion;

   public FlujoMaximo(PanelGrafos p, JTextArea area, int f, int s) {
      this.panel = p;
      this.textoInformacion = area;
      this.fuente = f;
      this.sumidero = s;
      this.grafoOriginal = this.panel.getGrafo();
      this.grafoResultado = this.grafoOriginal.clonar();
      this.numVertices = this.grafoOriginal.getNodos().size();
      this.C = new Matriz(this.grafoOriginal);
      this.Aux = new Matriz(this.numVertices, this.numVertices, 0.0D);
      this.Aux = this.C.adyacente();
      this.B = new Matriz(this.numVertices, this.numVertices, 0.0D);
      this.B = this.Aux.marcarAristasCapacidadesResiduos();
      this.B = this.B.suma(this.Aux);
   }

   public void run() {
      this.textoInformacion.setText("El algoritmo de Ford-Fulkerson ha finalizado.\n\nRESULTADO:\n");

      while(this.obtenerCaminoIncrementable()) {
         double capacidadOld = 0.0D;
         double residuoOld = 0.0D;
         double capacidadAct = 0.0D;
         double residuoAct = 0.0D;
         this.flujoMax += this.delta;

         for(int i = 0; i < this.numVertices; ++i) {
            for(int j = 0; j < this.numVertices; ++j) {
               if (this.M.A[i][j] == 3.0D) {
                  capacidadOld = this.C.A[i][j];
                  residuoOld = this.C.A[j][i];
                  this.C.A[i][j] -= this.delta;
                  this.C.A[j][i] += this.delta;
                  capacidadAct = this.C.A[i][j];
                  residuoAct = this.C.A[j][i];
                  Arista a;
                  if (residuoOld == 0.0D && residuoAct != 0.0D) {
                     a = new Arista(j, i, 1);
                     a.setPeso(residuoAct);
                     this.grafoOriginal.insertarArco(a);
                  }

                  if (residuoOld != 0.0D && residuoAct == 0.0D) {
                     this.grafoOriginal.borrarArcobyNodosIndex(j, i);
                  }

                  if (residuoOld != 0.0D && residuoAct != 0.0D) {
                     this.grafoOriginal.getArcoByNodosIndex(j, i).setPeso(residuoAct);
                  }

                  if (capacidadOld == 0.0D && capacidadAct != 0.0D) {
                     a = new Arista(i, j, 1);
                     a.setPeso(capacidadAct);
                     this.grafoOriginal.insertarArco(a);
                  }

                  if (capacidadOld != 0.0D && capacidadAct == 0.0D) {
                     this.grafoOriginal.borrarArcobyNodosIndex(i, j);
                  }

                  if (capacidadOld != 0.0D && capacidadAct != 0.0D) {
                     this.grafoOriginal.getArcoByNodosIndex(i, j).setPeso(capacidadAct);
                  }
               }
            }
         }
      }

      this.obtenerGrafoResultado(this.C);
      this.textoInformacion.append("\nRed N(G, s, t, c), donde:\n\n");
      this.textoInformacion.append("G --> Grafo dirigido débil conexo\n");
      this.textoInformacion.append(this.grafoResultado.getNodoByIndex(this.fuente).getNombre() + " --> Vértice fuente\n");
      this.textoInformacion.append(this.grafoResultado.getNodoByIndex(this.sumidero).getNombre() + " --> Vértice sumidero\n");
      this.textoInformacion.append("c --> función c: E(G) -> N U {0} (función capacidad)\n");
      this.textoInformacion.append("\nEl flujo máximo de la red es: " + (int)this.flujoMax + "\n");
      this.textoInformacion.append("\nEn el grafo se muestra la capacidad (en color azul) y el flujo máximo (en color verde) para cada uno de los arcos de la red.");
   }

   private boolean obtenerCaminoIncrementable() {
      this.delta = 9999.0D;
      this.M = new Matriz(this.numVertices, this.numVertices, 0.0D);
      this.grafoOriginal.BFS((MenuPrincipal)null, this.grafoOriginal.getNodoByIndex(this.fuente), true);
      this.grafoPintar = this.grafoOriginal.getGrafoFlujoMax().clonar();
      return this.recuperarCaminoBFS();
   }

   private boolean recuperarCaminoBFS() {
      boolean existeCamino = false;
      double peso = 0.0D;
      int cola = this.sumidero;

      for(int i = 0; i < this.grafoPintar.deAzul.size(); ++i) {
         Arista a = (Arista)this.grafoPintar.deAzul.get(i);
         if (a.getCola() == cola) {
            this.M.A[a.getCabeza()][cola] = 3.0D;
            peso = a.getPeso();
            if (peso < this.delta) {
               this.delta = peso;
            }

            cola = a.getCabeza();
            i = -1;
            existeCamino = true;
         }
      }

      return existeCamino;
   }

   private void obtenerGrafoResultado(Matriz X) {
      for(int i = 0; i < this.numVertices; ++i) {
         for(int j = 0; j < this.numVertices; ++j) {
            if (this.B.A[i][j] == 2.0D) {
               Arista pepa = this.grafoResultado.getArcoByNodosIndex(j, i);
               pepa.setFlujo((int)X.A[i][j]);
            }
         }
      }

      this.panel.setGrafo(this.grafoResultado);
   }
}
