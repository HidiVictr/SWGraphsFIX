package algoritmos;

import grafos.Arista;
import grafos.DijkstraObject;
import grafos.Grafo;
import grafos.GrafoEdmonds;
import grafos.Nodo;
import interfaz.PanelGrafos;
import java.awt.Color;
import java.util.Vector;
import javax.swing.JTextArea;

public class CarteroChino extends Algoritmo {
   private Grafo grafoTrabajo;
   private Edmonds2 algEdmonds = null;
   private Fleury algFleury = null;
   private PanelGrafos resultado;
   private Vector<Integer> v;
   private GrafoEdmonds gEdmonds;

   public CarteroChino(PanelGrafos panelGE, PanelGrafos panelG, JTextArea text) {
      this.resultado = panelG;
      this.resultado.setGrafo(new Grafo());
      this.panel = panelGE;
      this.textoInformacion = text;
      this.grafoTrabajo = this.panel.getGrafo();
   }

   public void run() {
      while(!this.terminado() && !this.enSuspension()) {
         Arista a;
         switch(this.estado) {
         case 0:
            this.iniciar();
            if (!this.grafoTrabajo.esConexo()) {
               this.terminar();
               this.textoInformacion.setText("El grafo debe obligatoriamente ser conexo, por tanto no se puede ejecutar el algoritmo sobre este grafo");
               break;
            }

            this.estado = 1;
            break;
         case 1:
            this.v = this.nodosGradoImpar();
            switch(this.v.size()) {
            case 0:
               if (this.modo == Algoritmo.porPasos) {
                  this.textoInformacion.setText("No hay nodos de grado impar, el grafo es Euleriano y se le puede aplicar el algoritmo de Fleury");
                  this.algFleury = new Fleury(this.panel, this.resultado, this.textoInformacion);
                  this.algFleury.setModo(this.modo);
                  this.suspender();
               }

               this.estado = 9;
               continue;
            case 1:
            default:
               if (this.modo == Algoritmo.porPasos) {
                  this.grafoTrabajo.pintarNodos(this.panel.getGraphics(), this.v, Color.BLUE);
                  this.textoInformacion.setText("Se han encontrado varios nodos de grado impar. Se va a construir un grafo completo con los mismos, y las aristas se etiquetarán con los valores de los caminos más cortos entre dichos vértices");
                  this.suspender();
               }

               this.estado = 2;
               continue;
            case 2:
               if (this.modo == Algoritmo.porPasos) {
                  this.grafoTrabajo.pintarNodos(this.panel.getGraphics(), this.v, Color.BLUE);
                  this.textoInformacion.setText("Se han encontrado únicamente un par de nodos de grado impar. Se trata de los nodos marcados en color azul, antes de ejecutar Fleury debemos hacer que el grafo sea euleriano, para ello se busca el camino de mínimo peso entre los nodos marcados y se replican las aristas que forman parte del mismo.");
                  this.suspender();
               }

               this.estado = 8;
               continue;
            }
         case 2:
            Grafo aux = new GrafoEdmonds(this.v.size());

            for(int i = this.v.size() - 1; i >= 0; --i) {
               Nodo n = this.grafoTrabajo.getNodoByIndex((Integer)this.v.get(i));
               aux.cambiarNombreIndex(i, n.getNombre());
            }

            this.gEdmonds = aux.convertirAEdmonds();
            if (this.modo == Algoritmo.porPasos) {
               this.panel.setGrafo(this.gEdmonds);
               this.panel.repaint();
               this.textoInformacion.setText("Aquí representamos el grafo completo K(" + this.gEdmonds.getNodos().size() + "), donde los nodos son aquellos que tenían grado impar en el grafo original. Las aristas se etiquetan a continuación utilizando el algoritmo de Dijkstra sobre el grafo original para hallar los caminos más cortos (no se muestra este subproceso).");
               this.suspender();
            }

            this.estado = 3;
            break;
         case 3:
            this.etiquetarAristasDijkstra(this.v, this.gEdmonds);
            this.panel.setGrafo(this.gEdmonds);
            this.algEdmonds = new Edmonds2(this.panel, this.textoInformacion);
            this.algEdmonds.setMinimizar(true);
            this.algEdmonds.setModo(Algoritmo.oculto);
            if (this.modo == Algoritmo.porPasos) {
               this.panel.repaint();
               this.textoInformacion.setText("Una vez que ya se han etiquetado las aristas vamos a obtener un acoplamiento de mínimo peso en este nuevo grafo. ");
               this.suspender();
            }

            this.estado = 4;
            break;
         case 4:
            if (this.algEdmonds.getModo() == Algoritmo.porPasos) {
               try {
                  this.algEdmonds.step();
                  this.algEdmonds.join();
                  if (this.algEdmonds.terminado()) {
                     this.noAcabado();
                     this.estado = 5;
                  }

                  this.suspender();
               } catch (InterruptedException var6) {
                  var6.printStackTrace();
               }
            } else {
               try {
                  this.estado = 5;
                  this.algEdmonds.step();
                  this.algEdmonds.join();
                  this.noAcabado();
                  if (this.algEdmonds.getModo() == Algoritmo.continuo) {
                     this.suspender();
                  }
               } catch (InterruptedException var7) {
                  var7.printStackTrace();
               }
            }
            break;
         case 5:
            this.gEdmonds = (GrafoEdmonds)this.panel.getGrafo();
            this.panel.setGrafo(this.grafoTrabajo);
            this.estado = 6;
            if (this.modo == Algoritmo.porPasos) {
               this.panel.repaint();
               this.textoInformacion.setText("Ahora se aplica la información del acoplamiento obtenido al realizar el algoritmo de Edmonds 2 sobre el grafo anterior. El objetivo es convertir en euleriano el grafo inicial.");
               this.suspender();
            }
            break;
         case 6:
            if (this.gEdmonds.getAcoplamiento().size() == 0) {
               this.panel.setGrafo(this.grafoTrabajo);
               this.panel.repaint();
               this.algFleury = new Fleury(this.panel, this.resultado, this.textoInformacion);
               this.algFleury.setModo(this.modo);
               this.estado = 9;
               if (this.modo == Algoritmo.porPasos) {
                  this.textoInformacion.setText("Obsérvese que no quedan nodos de grado impar, por tanto ya podemos aplicar Fleury para hallar el ciclo euleriano que resuelve el problema.");
                  this.suspender();
               }
               break;
            }

            if (this.modo == Algoritmo.porPasos) {
               a = (Arista)this.gEdmonds.getAcoplamiento().get(0);
               Vector<Integer> extremos = new Vector();
               extremos.add((Integer)this.v.get(a.getCabeza()));
               extremos.add((Integer)this.v.get(a.getCola()));
               this.textoInformacion.setText("Consideramos la arista (" + this.grafoTrabajo.getNodoByIndex((Integer)this.v.get(a.getCabeza())).getNombre() + "," + this.grafoTrabajo.getNodoByIndex((Integer)this.v.get(a.getCola())).getNombre() + ") del acoplamiento obtenido en Edmonds 2. Duplicamos las aristas del camino " + "más corto entre ambos nodos. El mencionado camino se obtiene mediante el algoritmo de Dijkstra.");
               this.suspender();
               this.grafoTrabajo.pintarNodos(this.panel.getGraphics(), extremos, Color.MAGENTA);
            }

            this.estado = 7;
            break;
         case 7:
            a = (Arista)this.gEdmonds.getAcoplamiento().get(0);
            this.gEdmonds.quitarAristaDeAcoplamiento(a.getCabeza(), a.getCola());
            this.replicarCaminos(a);
            if (this.modo == Algoritmo.porPasos) {
               this.panel.setGrafo(this.grafoTrabajo);
               this.textoInformacion.setText("Se muestra el grafo tras duplicar el camino más corto entre " + this.grafoTrabajo.getNodoByIndex((Integer)this.v.get(a.getCabeza())).getNombre() + " y " + this.grafoTrabajo.getNodoByIndex((Integer)this.v.get(a.getCola())).getNombre() + ". Ahora ambos nodos son de grado par.");
               this.panel.repaint();
               this.suspender();
            }

            this.estado = 6;
            break;
         case 8:
            this.replicarCaminos();
            this.panel.setGrafo(this.grafoTrabajo);
            this.panel.repaint();
            this.algFleury = new Fleury(this.panel, this.resultado, this.textoInformacion);
            this.algFleury.setModo(this.modo);
            this.estado = 9;
            if (this.modo == Algoritmo.porPasos) {
               this.suspender();
            }
            break;
         case 9:
            if (this.algFleury.getModo() == Algoritmo.porPasos) {
               try {
                  this.algFleury.step();
                  this.algFleury.join();
                  if (this.algFleury.terminado()) {
                     this.noAcabado();
                     this.estado = 10;
                     continue;
                  }

                  this.suspender();
               } catch (InterruptedException var4) {
                  var4.printStackTrace();
               }
            } else {
               try {
                  this.algFleury.step();
                  this.algFleury.join();
                  this.noAcabado();
                  this.estado = 10;
               } catch (InterruptedException var5) {
                  var5.printStackTrace();
               }
            }
            break;
         case 10:
            this.escribirResultadoFinal();
            this.terminar();
            break;
         default:
            this.terminar();
         }
      }

      this.t = null;
   }

   private void escribirResultadoFinal() {
      this.textoInformacion.setText("El algoritmo de Cartero Chino ha finalizado.\n\nRESULTADO:\n");
      Grafo caminoCarteroChino = this.resultado.getGrafo();
      if (caminoCarteroChino.getNodos().size() < 2) {
         this.textoInformacion.append("El grafo esta compuesto de un solo nodo, no hay aristas y por tanto no hay ciclo euleriano.\n");
      } else {
         this.textoInformacion.append("El recorrido a realizar es:\n");
         double peso = 0.0D;
         double pesoGrafoOriginal = 0.0D;
         Nodo nodoInicio = caminoCarteroChino.getNodoByIndex(0);

         int i;
         for(i = 1; i < caminoCarteroChino.getNodos().size(); ++i) {
            Nodo nodoSiguiente = caminoCarteroChino.getNodoByIndex(i);
            Arista a = caminoCarteroChino.getAristaByIndex(i - 1);
            this.textoInformacion.append("de " + nodoInicio.getNombre() + " a " + nodoSiguiente.getNombre() + "; ");
            peso += a.getPeso();
            nodoInicio = nodoSiguiente;
         }

         for(i = 0; i < this.grafoTrabajo.getAristas().size(); ++i) {
            Arista a = this.grafoTrabajo.getAristaByIndex(i);
            pesoGrafoOriginal += a.getPeso();
         }

         double impureza = peso - pesoGrafoOriginal;
         this.textoInformacion.append("\n\nPeso del camino = " + peso + " uds");
         this.textoInformacion.append("\nLongitud del camino = " + caminoCarteroChino.getAristas().size() + " aristas");
         this.textoInformacion.append("\nPeso de las aristas replicadas =" + impureza + " uds");
         if (pesoGrafoOriginal != 0.0D) {
            this.textoInformacion.append("  ->  " + (new Float(impureza / pesoGrafoOriginal * 100.0D)).intValue() + "% del peso del grafo inicial (" + pesoGrafoOriginal + " uds)");
         }
      }

   }

   private void replicarCaminos() {
      int inicioCamino = (Integer)this.v.get(0);
      int finalCamino = (Integer)this.v.get(1);
      DijkstraObject resultadoDijkstra = this.grafoTrabajo.dijkstra(inicioCamino, finalCamino);

      for(int predecesor = resultadoDijkstra.getPredecesor(finalCamino); predecesor != -1; predecesor = resultadoDijkstra.getPredecesor(predecesor)) {
         this.grafoTrabajo.aumentarArista(predecesor, finalCamino);
         finalCamino = predecesor;
      }

   }

   private void replicarCaminos(Arista aristaAcoplamiento) {
      int inicioCamino = (Integer)this.v.get(aristaAcoplamiento.getCabeza());
      int finalCamino = (Integer)this.v.get(aristaAcoplamiento.getCola());
      DijkstraObject resultadoDijkstra = this.grafoTrabajo.dijkstra(inicioCamino, finalCamino);

      for(int predecesor = resultadoDijkstra.getPredecesor(finalCamino); predecesor != -1; predecesor = resultadoDijkstra.getPredecesor(predecesor)) {
         this.grafoTrabajo.aumentarArista(predecesor, finalCamino);
         finalCamino = predecesor;
      }

   }

   private void etiquetarAristasDijkstra(Vector<Integer> v, Grafo aux) {
      for(int i = 0; i < v.size() - 1; ++i) {
         int numNodo = (Integer)v.get(i);
         DijkstraObject resultadoDijkstra = this.grafoTrabajo.dijkstra(numNodo);

         for(int j = i + 1; j < v.size(); ++j) {
            int numNodo2 = (Integer)v.get(j);
            double pesoArista = resultadoDijkstra.getDistancia(numNodo2);
            aux.cambiarPesoArista(i, j, pesoArista);
         }
      }

   }

   private Vector<Integer> nodosGradoImpar() {
      Vector<Integer> nGradoImpar = new Vector();

      for(int i = 0; i < this.grafoTrabajo.getNodos().size(); ++i) {
         int grado = this.grafoTrabajo.getGradoIndex(i);
         if ((float)grado / 2.0F != (float)Math.round((float)(grado / 2))) {
            nGradoImpar.add(new Integer(i));
         }
      }

      return nGradoImpar;
   }

   public void setModoEdmonds2(int i) {
      if (this.algEdmonds != null) {
         this.algEdmonds.setModo(i);
      }

   }

   public void setModoFleury(int i) {
      if (this.algFleury != null) {
         this.algFleury.setModo(i);
      }

   }

   public Edmonds2 getEdmonds2() {
      return this.algEdmonds;
   }
}
