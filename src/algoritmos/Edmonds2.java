package algoritmos;

import grafos.ArbolAlternado;
import grafos.Arista;
import grafos.Floracion;
import grafos.GrafoEdmonds;
import grafos.Nodo;
import grafos.NodoEdmonds;
import grafos.SubGrafoIgualdad;
import interfaz.PanelGrafos;
import java.awt.Point;
import java.util.Vector;
import javax.swing.JTextArea;

public class Edmonds2 extends Algoritmo {
   private GrafoEdmonds grafoTrabajo;
   private SubGrafoIgualdad sGI;
   private ArbolAlternado arbol;
   private boolean minimizar;
   private boolean ajustarEtiquetado;
   private Vector<Integer> indexAdyacentes = new Vector();
   private int indexNodo = -1;
   private int contadorFloracion = 0;
   private double delta = 0.0D;
   private double delta3 = 0.0D;

   public Edmonds2(PanelGrafos p, JTextArea area) {
      this.panel = p;
      this.textoInformacion = area;
      this.minimizar = false;
      this.ajustarEtiquetado = false;
      this.grafoTrabajo = this.panel.getGrafo().convertirAEdmonds();
      this.grafoTrabajo.setNumNodosReales();
      this.grafoTrabajo.setNumAristasReales();
   }

   public void run() {
      while (!this.terminado() && !this.enSuspension()) {
         switch (this.estado) {
            case 0:
               this.iniciar();
               if (this.minimizar) {
                  this.grafoTrabajo.invertirPesosAristas();
                  if (this.modo == Algoritmo.porPasos) {
                     this.panel.setGrafo(this.grafoTrabajo);
                     this.panel.repaint();
                     this.textoInformacion.setText(
                           "Dado que se va a minimizar se cambia el peso de todas las aristas del grafo, adjudicando como nuevo peso el opuesto del peso inicial");
                     this.suspender();
                  }
               }

               this.estado = 1;
               break;
            case 1:
               if ((float) this.grafoTrabajo.getNumNodosReales()
                     / 2.0F != (float) Math.round((float) (this.grafoTrabajo.getNumNodosReales() / 2))) {
                  this.grafoTrabajo.addNodoFicticio();
                  if (this.modo == Algoritmo.porPasos) {
                     this.panel.setGrafo(this.grafoTrabajo);
                     this.panel.repaint();
                     this.textoInformacion.setText(
                           "El grafo ten�a un n�mero impar de nodos, por este motivo y para conseguir un acoplamiento perfecto se ha a�adido un nodo ficticio. Este nodo se ha enlazado al resto de nodos con aristas de peso muy negativo");
                     this.suspender();
                  }
               } else if (this.modo == Algoritmo.porPasos) {
                  this.textoInformacion.setText(
                        "El grafo tiene un n�mero par de nodos y no requiere de nodos ficticios para alcanzar un acoplamiento perfecto, se va a aplicar un etiquetado admisible al grafo");
                  this.suspender();
               }

               this.estado = 2;
               break;
            case 2:
               this.grafoTrabajo.etiquetarAdmisible();
               if (this.modo == Algoritmo.porPasos) {
                  if (this.ajustarEtiquetado) {
                     this.grafoTrabajo.etiquetarAdmisible();
                  }

                  this.panel.setGrafo(this.grafoTrabajo);
                  this.panel.repaint();
                  this.textoInformacion.setText(
                        "Se ha generado un etiquetado admisible para el grafo. A partir de este etiquetado se calcular� el subgrafo igualdad asociado, formado por todos los v�rtices del grafo  y todas aquellas aristas cuyo peso sea igual a la suma de las etiquetas de los nodos sobre los que incide. En base a esto, �podr�as hallar el subgrafo que se va a generar?");
                  this.suspender();
               } else {
                  this.grafoTrabajo.etiquetarAdmisible();
               }

               this.estado = 3;
               break;
            case 3:
               this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.sGI);
                  this.textoInformacion.setText("Este es el subgrafo igualdad con el que empezamos a trabajar");
                  this.panel.repaint();
                  this.suspender();
               }

               this.estado = 4;
               break;
            case 4:
               if (this.sGI.getIndexNodoInsaturado() == -1) {
                  this.estado = 23;
                  break;
               }

               this.arbol = new ArbolAlternado(this.sGI);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.arbol);
                  this.panel.repaint();
                  this.textoInformacion.setText("Se ha tomado el nodo "
                        + this.grafoTrabajo.getNodoByIndex(this.sGI.getIndexNodoInsaturado()).getNombre()
                        + " del grafo."
                        + " Dicho nodo est� insaturado y ser� utilizado como ra�z del �rbol alternado que se va a construir");
                  this.suspender();
               }

               this.estado = 5;
               break;
            case 5:
               if (this.arbol.getUltimoERevisado() < this.arbol.getNumNodosE()) {
                  this.estado = 6;
                  if (this.modo == Algoritmo.porPasos) {
                     this.panel.setGrafo(this.arbol);
                     this.panel.repaint();
                     this.textoInformacion
                           .setText("Se va a revisar la posibilidad de encontrar un camino M-Aumentable desde el nodo "
                                 + this.grafoTrabajo.getNodoByIndex(this.arbol.getIndexUltimoERevisado()).getNombre()
                                 + " del grafo, que est� etiquetado E dentro del �rbol");
                     this.suspender();
                  }
               } else {
                  this.estado = 14;
                  if (this.modo == Algoritmo.porPasos) {
                     this.textoInformacion.setText(
                           "Ya no queda ningun nodo etiquetado E por examinar, hemos llegado a una situaci�n de �rbol h�ngaro y se va a realizar un cambio de etiquetado");
                     this.suspender();
                  }
               }
               break;
            case 6:
               this.indexAdyacentes = this.sGI.indexNodosAdyacentes(this.arbol.getIndexUltimoERevisado());
               this.estado = 7;
               break;
            case 7:
               if (this.indexAdyacentes.size() > 0) {
                  this.indexNodo = (Integer) this.indexAdyacentes.get(0);
                  this.indexAdyacentes.remove(0);
                  this.indexNodo = this.sGI.indexRealNodo(this.indexNodo);
                  this.estado = 8;
                  break;
               }

               if (this.modo == Algoritmo.porPasos) {
                  this.textoInformacion.setText("Desde el nodo "
                        + this.grafoTrabajo.getNodoByIndex(this.arbol.getIndexUltimoERevisado()).getNombre()
                        + " ya no es posible hacer crecer m�s el �rbol."
                        + " Tampoco hay adyacentes para perfeccionar el acoplamiento."
                        + " Se intentar� realizar alguna de estas operaciones desde otro nodo etiquetado E que no haya sido revisado a�n");
                  this.suspender();
               }

               this.arbol.aumentarERevisado();
               this.estado = 5;
               break;
            case 8:
               if (this.sGI.esSaturadoIndex(this.indexNodo)) {
                  this.estado = 11;
                  break;
               }

               this.estado = 9;
               break;
            case 9:
               this.arbol.insertarArista(
                     this.sGI.getAristaByNodosIndex(this.arbol.getIndexUltimoERevisado(), this.indexNodo));
               this.arbol.addNodoI(this.indexNodo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.arbol);
                  this.panel.repaint();
                  this.textoInformacion
                        .setText("Se ha encontrado un camino aumentable con inicio en la ra�z del arbol y final en "
                              + this.grafoTrabajo.getNodoByIndex(this.indexNodo).getNombre()
                              + ". Se va a perfeccionar el acoplamiento");
                  this.suspender();
               }

               this.estado = 10;
               break;
            case 10:
               this.perfeccionarAcoplamiento(this.indexNodo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.sGI);
                  this.panel.repaint();
                  this.textoInformacion.setText(
                        "Se ha perfeccionado el acoplamiento. Se muestra en el panel el subgrafo igualdad (que no ha variado). El �rbol alternado actual se elimina. En caso de quedar nodos insaturados se comenzar� a construir un nuevo �rbol.");
                  this.suspender();
               }

               this.eliminarArbol();
               this.estado = 4;
               break;
            case 11:
               if (!this.arbol.esNodoArbol(this.indexNodo)) {
                  this.estado = 12;
               } else {
                  if (this.arbol.esNodoE(this.indexNodo)) {
                     this.estado = 13;
                     continue;
                  }

                  this.estado = 7;
               }
               break;
            case 12:
               this.extenderArbol(this.indexNodo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.arbol);
                  this.panel.repaint();
                  this.suspender();
               }

               this.estado = 7;
               break;
            case 13:
               this.contraerFloracion(this.indexNodo, this.arbol.getIndexUltimoERevisado());
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.sGI);
                  this.panel.repaint();
                  this.suspender();
                  this.textoInformacion
                        .setText("Se ha contra�do un ciclo de longitud impar dando lugar a una floraci�n");
               }

               this.estado = 5;
               break;
            case 14:
               double delta1 = this.calcularDelta1();
               double delta2 = this.calcularDelta2();
               this.delta3 = this.calcularDelta3();
               this.delta = Math.min(Math.min(delta1, delta2), this.delta3);
               this.estado = 15;
               break;
            case 15:
               if (Double.isInfinite(this.delta)) {
                  this.estado = 16;
                  break;
               }

               this.estado = 18;
               break;
            case 16:
               if (this.sGI.getIndexNodoInsaturado() == -1) {
                  this.estado = 25;
                  break;
               }

               this.estado = 17;
               break;
            case 17:
               this.grafoTrabajo.completarConAristasFicticias();
               this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.grafoTrabajo);
                  this.panel.repaint();
                  this.suspender();
                  this.textoInformacion.setText(
                        "El par�metro delta ha tomado valor infinito, es obligado por tanto introducir aristas ficticias para conseguir un acoplamiento perfecto. Se va a recalcular el valor de delta una vez introducidas las aristas ficticias");
               }

               this.estado = 14;
               break;
            case 18:
               if (this.delta == this.delta3) {
                  this.estado = 19;
                  break;
               }

               this.estado = 21;
               break;
            case 19:
               NodoEdmonds n = null;

               for (int floracionMinimaI = this.grafoTrabajo.getPrimeraFloracion(); floracionMinimaI < this.grafoTrabajo
                     .getNodos().size(); ++floracionMinimaI) {
                  n = (NodoEdmonds) this.grafoTrabajo.getNodoByIndex(floracionMinimaI);
                  if (n.getEtiquetaArborea() == "I" & n.getFloracion() == -1 & n.getEtiqueta() == this.delta * 2.0D) {
                     this.cambiarEtiquetado(this.delta);
                     this.expandirFloracion(floracionMinimaI);
                     this.grafoTrabajo.borrarFloracionIndex(floracionMinimaI);
                     break;
                  }
               }

               if (this.modo == Algoritmo.porPasos) {
                  this.textoInformacion.setText("El �rbol era h�ngaro, se ha cambiado el etiquetado con delta = "
                        + this.delta + "." + " A continuaci�n se ha expandido la floraci�n " + n.getNombre()
                        + " que estaba etiquetada como nodo I y di� lugar a este valor");
                  this.panel.setGrafo(this.sGI);
                  this.panel.repaint();
                  this.suspender();
               }

               this.eliminarArbol();
               this.estado = 20;
               break;
            case 20:
               this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.sGI);
                  this.panel.repaint();
                  this.textoInformacion
                        .setText("Se ha obtenido este subgrafo igualdad despu�s del cambio de etiquetado");
                  this.suspender();
               }

               this.estado = 4;
               break;
            case 21:
               this.cambiarEtiquetado(this.delta);
               if (this.modo == Algoritmo.porPasos) {
                  if (this.grafoTrabajo.getAristas().size() < 300) {
                     this.panel.setGrafo(this.grafoTrabajo);
                     this.panel.repaint();
                  }

                  this.textoInformacion
                        .setText("Se realiza un cambio de etiquetado con un valor de delta = " + this.delta
                              + " y se conserva el �rbol. El siguiente paso es derivar un nuevo subgrafo igualdad");
                  this.suspender();
               }

               this.estado = 22;
               break;
            case 22:
               this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
               if (this.modo == Algoritmo.porPasos) {
                  this.panel.setGrafo(this.sGI);
                  this.panel.repaint();
                  this.textoInformacion
                        .setText("Se ha obtenido este subgrafo igualdad despu�s del cambio de etiquetado");
                  this.suspender();
               }

               this.arbol.setUltimoERevisado(0);
               this.estado = 5;
               break;
            case 23:
               if (this.grafoTrabajo.getNodos().size() - 1 >= this.grafoTrabajo.getPrimeraFloracion()) {
                  this.expandirFloracion(this.grafoTrabajo.getNodos().size() - 1);
                  String nombre = this.grafoTrabajo.getNodoByIndex(this.grafoTrabajo.getNodos().size() - 1).getNombre();
                  this.grafoTrabajo.borrarNodoIndex(this.grafoTrabajo.getNodos().size() - 1);
                  if (this.modo == Algoritmo.porPasos) {
                     this.textoInformacion.setText(
                           "Se est�n expandiendo las floraciones en orden inverso a como fueron creadas. Se muestra el estado del grafo tras expandir la floraci�n "
                                 + nombre);
                     this.panel.setGrafo(this.sGI);
                     this.panel.repaint();
                     this.suspender();
                  }

                  this.estado = 23;
                  this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
                  break;
               }

               this.estado = 24;
               break;
            case 24:
               if (this.grafoTrabajo.getAristas().size() != this.grafoTrabajo.getNumAristasReales()) {
                  this.grafoTrabajo.eliminarFiccion();
                  if (this.modo == Algoritmo.porPasos) {
                     this.textoInformacion.setText(
                           "Se han eliminado las aristas y el nodo ficticio que se introdujeron anteriormente");
                     this.panel.setGrafo(this.grafoTrabajo);
                     this.panel.repaint();
                     this.suspender();
                  }
               }

               this.estado = 25;
               break;
            case 25:
               if (this.minimizar) {
                  this.grafoTrabajo.invertirPesosAristas();
               }

               this.panel.setGrafo(this.grafoTrabajo);
               if (this.modo != Algoritmo.oculto) {
                  this.panel.repaint();
                  this.escribirResultadoFinal();
               }

               this.terminar();
               break;
            default:
               this.terminar();
         }
      }

      this.t = null;
   }

   public void setMinimizar(boolean min) {
      this.minimizar = min;
   }

   private void extenderArbol(int indexNodo) {
      Arista aristaAcopl = null;
      this.arbol.addNodoI(this.grafoTrabajo.indexRealNodo(indexNodo));
      Arista aristaInsaturada = this.sGI.getAristaByNodosIndex(this.arbol.getIndexUltimoERevisado(), indexNodo);

      for (int i = 0; i < this.grafoTrabajo.getAcoplamiento().size(); ++i) {
         aristaAcopl = (Arista) this.grafoTrabajo.getAcoplamiento().get(i);
         if (this.grafoTrabajo.indexRealNodo(aristaAcopl.getCabeza()) == indexNodo & this.grafoTrabajo
               .indexRealNodo(aristaAcopl.getCabeza()) != this.grafoTrabajo.indexRealNodo(aristaAcopl.getCola())) {
            this.arbol.addNodoE(this.grafoTrabajo.indexRealNodo(aristaAcopl.getCola()));
            this.arbol.getAcoplamiento().add(aristaAcopl);
            this.arbol.getAcoplamiento().add(aristaAcopl);
            break;
         }

         if (this.grafoTrabajo.indexRealNodo(aristaAcopl.getCola()) == indexNodo & this.grafoTrabajo
               .indexRealNodo(aristaAcopl.getCabeza()) != this.grafoTrabajo.indexRealNodo(aristaAcopl.getCola())) {
            this.arbol.addNodoE(this.grafoTrabajo.indexRealNodo(aristaAcopl.getCabeza()));
            this.arbol.getAcoplamiento().add(aristaAcopl);
            this.arbol.getAristas().add(aristaAcopl);
            break;
         }
      }

      if (this.modo == Algoritmo.porPasos) {
         this.textoInformacion.setText("Se ha extendido el arbol, a�adiendo los nodos "
               + this.grafoTrabajo.getNodoByIndex(aristaAcopl.getCabeza()).getNombre() + " y "
               + this.grafoTrabajo.getNodoByIndex(aristaAcopl.getCola()).getNombre()
               + ". Se muestra el estado actual del arbol");
      }

      this.arbol.getAristas().add(aristaInsaturada);
   }

   private void perfeccionarAcoplamiento(int indexNodo) {
      Vector<Integer> caminoMAumentable = new Vector();
      int indexNodoE = this.arbol.retrocesoDeIHastaE(indexNodo);
      caminoMAumentable.add(new Integer(indexNodo));
      caminoMAumentable.add(new Integer(indexNodoE));

      for (int indexNodoI = this.arbol.retrocesoDeEHastaI(indexNodoE); indexNodoI > -1; indexNodoI = this.arbol
            .retrocesoDeEHastaI(indexNodoE)) {
         indexNodoE = this.arbol.retrocesoDeIHastaE(indexNodoI);
         caminoMAumentable.add(new Integer(indexNodoI));
         caminoMAumentable.add(new Integer(indexNodoE));
      }

      int indexNodo1;
      int indexNodo2;
      int j;
      for (j = 1; j < caminoMAumentable.size(); j += 2) {
         indexNodo1 = (Integer) caminoMAumentable.get(j - 1);
         indexNodo2 = (Integer) caminoMAumentable.get(j);
         Arista a = this.sGI.getAristaByNodosIndex(indexNodo1, indexNodo2);
         this.sGI.getAcoplamiento().add(a);
      }

      for (j = 2; j < caminoMAumentable.size(); j += 2) {
         indexNodo1 = (Integer) caminoMAumentable.get(j - 1);
         indexNodo2 = (Integer) caminoMAumentable.get(j);
         this.sGI.quitarAristaDeAcoplamiento(indexNodo1, indexNodo2);
      }

   }

   private void eliminarArbol() {
      int j;
      for (j = 0; j < this.sGI.getPrimeraFloracion(); ++j) {
         NodoEdmonds n = (NodoEdmonds) this.sGI.getNodos().get(j);
         n.borrarEtiquetaArborea();
         this.sGI.getNodos().set(j, n);
      }

      for (j = this.sGI.getPrimeraFloracion(); j < this.sGI.getNodos().size(); ++j) {
         Floracion f = (Floracion) this.sGI.getNodos().get(j);
         f.borrarEtiquetaArborea();
         this.sGI.getNodos().set(j, f);
      }

      this.arbol = null;
   }

   private void contraerFloracion(int indexNodo, int indexNodo2) {
      Vector<Integer> cicloFloracion = new Vector();
      cicloFloracion.add(new Integer(indexNodo));

      int indexNodoI;
      int indexNodoE;
      for (indexNodoI = this.arbol.retrocesoDeEHastaI(indexNodo); indexNodoI > -1; indexNodoI = this.arbol
            .retrocesoDeEHastaI(indexNodoE)) {
         indexNodoE = this.arbol.retrocesoDeIHastaE(indexNodoI);
         cicloFloracion.add(new Integer(indexNodoI));
         cicloFloracion.add(new Integer(indexNodoE));
      }

      for (indexNodoE = indexNodo2; !cicloFloracion.contains(new Integer(indexNodoE)); indexNodoE = this.arbol
            .retrocesoDeIHastaE(indexNodoI)) {
         indexNodoI = this.arbol.retrocesoDeEHastaI(indexNodoE);
         cicloFloracion.add(0, new Integer(indexNodoE));
         cicloFloracion.add(0, new Integer(indexNodoI));
      }

      for (int i = (Integer) cicloFloracion.get(
            cicloFloracion.size() - 1); i != indexNodoE; i = (Integer) cicloFloracion.get(cicloFloracion.size() - 1)) {
         cicloFloracion.remove(cicloFloracion.size() - 1);
      }

      for (int j = 0; j < cicloFloracion.size(); ++j) {
         int indexNodoContraido = (Integer) cicloFloracion.get(j);
         if (indexNodoContraido < this.grafoTrabajo.getPrimeraFloracion()) {
            NodoEdmonds nodoContraido = (NodoEdmonds) this.grafoTrabajo.getNodoByIndex(indexNodoContraido);
            nodoContraido.setFloracion(this.grafoTrabajo.getNodos().size());
            this.grafoTrabajo.getNodos().set(indexNodoContraido, nodoContraido);
         } else {
            Floracion floracionContraida = (Floracion) this.grafoTrabajo.getNodoByIndex(indexNodoContraido);
            floracionContraida.setFloracion(this.grafoTrabajo.getNodos().size());
            this.grafoTrabajo.getNodos().set(indexNodoContraido, floracionContraida);
         }
      }

      Point posFloracion = ((NodoEdmonds) this.sGI.getNodoByIndex(indexNodoE)).getPos();
      ++this.contadorFloracion;
      Floracion f = new Floracion("F" + this.contadorFloracion, posFloracion, cicloFloracion);
      this.grafoTrabajo.getNodos().add(f);
      Vector<?> nodosE = this.arbol.getE();

      for (int j = 0; j < nodosE.size(); ++j) {
         Integer numNodo = (Integer) nodosE.get(j);
         if (indexNodoE == numNodo) {
            this.arbol.addNodoE(this.arbol.getNodos().size() - 1, j);
            this.arbol.setUltimoERevisado(j);
            break;
         }
      }

   }

   private void expandirFloracion(int indexFloracion) {
      Floracion f = (Floracion) this.grafoTrabajo.getNodoByIndex(indexFloracion);
      this.eliminarArbol();
      this.arbol = new ArbolAlternado(this.sGI, indexFloracion);
      this.cambiarEtiquetado(f.getEtiqueta() / 2.0D);
      this.eliminarArbol();
      int nodoEnlazadoAlExterior = -1;

      int posEnCiclo;
      for (posEnCiclo = 0; posEnCiclo < this.grafoTrabajo.getAcoplamiento().size(); ++posEnCiclo) {
         Arista a = (Arista) this.grafoTrabajo.getAcoplamiento().get(posEnCiclo);
         if (this.grafoTrabajo.indexRealNodo(a.getCabeza()) == indexFloracion
               & this.grafoTrabajo.indexRealNodo(a.getCabeza()) != this.grafoTrabajo.indexRealNodo(a.getCola())) {
            nodoEnlazadoAlExterior = a.getCabeza();
            break;
         }

         if (this.grafoTrabajo.indexRealNodo(a.getCola()) == indexFloracion
               & this.grafoTrabajo.indexRealNodo(a.getCabeza()) != this.grafoTrabajo.indexRealNodo(a.getCola())) {
            nodoEnlazadoAlExterior = a.getCola();
            break;
         }
      }

      int nodo1;
      for (posEnCiclo = 0; posEnCiclo < f.getCicloContraido().size(); ++posEnCiclo) {
         nodo1 = (Integer) f.getCicloContraido().get(posEnCiclo);
         if (nodo1 < this.grafoTrabajo.getPrimeraFloracion()) {
            if (nodo1 == nodoEnlazadoAlExterior) {
               break;
            }
         } else if (this.grafoTrabajo.nodoContenidoEnFloracion(nodo1, nodoEnlazadoAlExterior)) {
            break;
         }
      }

      int index;
      if (posEnCiclo > 0) {
         Vector<Integer> aux = new Vector();

         for (index = 0; index < posEnCiclo; ++index) {
            aux.add((Integer) f.getCicloContraido().get(0));
            f.getCicloContraido().remove(0);
         }

         f.getCicloContraido().addAll(aux);
      }

      for (nodo1 = 0; nodo1 < f.getCicloContraido().size(); ++nodo1) {
         index = (Integer) f.getCicloContraido().get(nodo1);
         if (index < this.grafoTrabajo.getPrimeraFloracion()) {
            NodoEdmonds n = (NodoEdmonds) this.grafoTrabajo.getNodoByIndex(index);
            if (n.getFloracion() == indexFloracion) {
               n.setFloracion(-1);
            }

            this.grafoTrabajo.getNodos().set(index, n);
         } else {
            Floracion fInterna = (Floracion) this.grafoTrabajo.getNodoByIndex(index);
            if (fInterna.getFloracion() == indexFloracion) {
               fInterna.setFloracion(-1);
            }

            this.grafoTrabajo.getNodos().set(index, fInterna);
         }
      }

      this.sGI = new SubGrafoIgualdad(this.grafoTrabajo);
      nodo1 = -1;
      int nodo2 = -1;
      int nodo3 = -1;

      for (int l = 0; l < f.getCicloContraido().size() - 1; l += 2) {
         nodo1 = (Integer) f.getCicloContraido().get(l);
         index = (Integer) f.getCicloContraido().get(l + 1);
         nodo3 = (Integer) f.getCicloContraido().get(l + 2);
         this.grafoTrabajo.quitarAristaDeAcoplamiento(nodo1, index);
         this.grafoTrabajo.insertaAristaAcoplamiento(index, nodo3, this.sGI);
      }

      nodo1 = (Integer) f.getCicloContraido().get(0);
      this.grafoTrabajo.quitarAristaDeAcoplamiento(nodo1, nodo3);
   }

   private void cambiarEtiquetado(double delta) {
      int j;
      for (j = 0; j < this.grafoTrabajo.getPrimeraFloracion(); ++j) {
         NodoEdmonds nI;
         if (this.arbol.esNodoE(j)) {
            nI = (NodoEdmonds) this.grafoTrabajo.getNodos().get(j);
            nI.setEtiqueta(nI.getEtiqueta() - delta);
            this.grafoTrabajo.getNodos().set(j, nI);
         } else if (this.arbol.esNodoI(j)) {
            nI = (NodoEdmonds) this.grafoTrabajo.getNodos().get(j);
            nI.setEtiqueta(nI.getEtiqueta() + delta);
            this.grafoTrabajo.getNodos().set(j, nI);
         }
      }

      for (j = this.grafoTrabajo.getPrimeraFloracion(); j < this.grafoTrabajo.getNodos().size(); ++j) {
         Floracion fI;
         if (this.arbol.esNodoE(j)) {
            fI = (Floracion) this.grafoTrabajo.getNodos().get(j);
            fI.setEtiqueta(fI.getEtiqueta() + 2.0D * delta);
            this.grafoTrabajo.getNodos().set(j, fI);
         }

         if (this.arbol.esNodoI(j)) {
            fI = (Floracion) this.grafoTrabajo.getNodos().get(j);
            fI.setEtiqueta(fI.getEtiqueta() - 2.0D * delta);
            this.grafoTrabajo.getNodos().set(j, fI);
         }
      }

   }

   private double calcularDelta1() {
      double delta1 = Double.POSITIVE_INFINITY;
      Vector<Arista> C1 = new Vector();

      int j;
      Arista aC1;
      for (j = 0; j < this.sGI.getC().size(); ++j) {
         aC1 = (Arista) this.sGI.getC().get(j);
         if (this.arbol.esNodoE(aC1.getCabeza()) & !this.arbol.esNodoArbol(aC1.getCola())
               | this.arbol.esNodoE(aC1.getCola()) & !this.arbol.esNodoArbol(aC1.getCabeza())) {
            C1.add(aC1);
         }
      }

      for (j = 0; j < C1.size(); ++j) {
         aC1 = (Arista) C1.get(j);
         Nodo n1 = (Nodo) this.sGI.getNodos().get(aC1.getCabeza());
         Nodo n2 = (Nodo) this.sGI.getNodos().get(aC1.getCola());
         delta1 = Math.min(delta1, n1.getEtiqueta() + n2.getEtiqueta() - aC1.getPeso());
      }

      return delta1;
   }

   private double calcularDelta2() {
      double delta2 = Double.POSITIVE_INFINITY;
      Vector<Arista> C2 = new Vector();

      int j;
      Arista aC2;
      for (j = 0; j < this.sGI.getC().size(); ++j) {
         aC2 = (Arista) this.sGI.getC().get(j);
         if (this.arbol.esNodoE(aC2.getCabeza()) & this.arbol.esNodoE(aC2.getCola())
               & this.grafoTrabajo.indexRealNodo(aC2.getCabeza()) != this.grafoTrabajo.indexRealNodo(aC2.getCola())) {
            C2.add(aC2);
         }
      }

      for (j = 0; j < C2.size(); ++j) {
         aC2 = (Arista) C2.get(j);
         Nodo n1 = (Nodo) this.sGI.getNodos().get(aC2.getCabeza());
         Nodo n2 = (Nodo) this.sGI.getNodos().get(aC2.getCola());
         delta2 = Math.min(delta2, (n1.getEtiqueta() + n2.getEtiqueta() - aC2.getPeso()) / 2.0D);
      }

      return delta2;
   }

   private double calcularDelta3() {
      double delta3 = Double.POSITIVE_INFINITY;

      for (int i = this.grafoTrabajo.getPrimeraFloracion(); i < this.grafoTrabajo.getNodos().size(); ++i) {
         NodoEdmonds n = (NodoEdmonds) this.grafoTrabajo.getNodoByIndex(i);
         if (n.getEtiquetaArborea() == "I" & n.getFloracion() == -1) {
            delta3 = Math.min(delta3, n.getEtiqueta() / 2.0D);
         }
      }

      return delta3;
   }

   private void escribirResultadoFinal() {
      this.textoInformacion.setText("El algoritmo de Edmonds 2 ha finalizado. \n\nRESULTADO:\n\n");
      this.textoInformacion.append("Cardinalidad del acoplamiento = " + this.grafoTrabajo.getAcoplamiento().size()
            + "\n\nAristas que forman parte del acoplamiento :\n");
      Arista a = null;
      double pesototal = 0.0D;

      for (int i = 0; i < this.grafoTrabajo.getAcoplamiento().size(); ++i) {
         a = (Arista) this.grafoTrabajo.getAcoplamiento().get(i);
         this.textoInformacion.append(" � (" + this.grafoTrabajo.getNodoByIndex(a.getCabeza()).getNombre() + ", "
               + this.grafoTrabajo.getNodoByIndex(a.getCola()).getNombre() + ") con peso = " + a.getPeso() + "\n");
         pesototal += a.getPeso();
      }

      this.textoInformacion.append("\nPeso del acoplamiento= " + pesototal + " uds.");
   }

   public void setEtiquetadoAjustado() {
      this.ajustarEtiquetado = true;
   }
}
