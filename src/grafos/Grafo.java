package grafos;

import Util.Matriz;
import interfaz.MenuPrincipal;
import interfaz.matricesAdyacencia.MatrizTabla;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Stack;
import java.util.Vector;

public class Grafo {
   protected Vector<Nodo> nodos;
   protected Vector<Arista> aristas;
   private int tipoGrafo;
   private GrafoMultiColor grafoFlujoMax;

   public Grafo() {
      this.nodos = new Vector();
      this.aristas = new Vector();
      this.tipoGrafo = 0;
   }

   public Grafo(int K, int peso) {
      this.tipoGrafo = 0;
      int diametro = K * 20;
      if (diametro > 300) {
         diametro = 300;
      }

      this.nodos = new Vector();
      int holgura;
      if (K > 34) {
         holgura = K * 8;
      } else if (K > 26) {
         holgura = K * 12;
      } else if (K > 20) {
         holgura = K * 16;
      } else if (K > 11) {
         holgura = K * 20;
      } else if (K > 8) {
         holgura = 250;
      } else {
         holgura = 175;
      }

      int i;
      int j;
      for (i = 0; i < K; ++i) {
         j = (int) (Math.sin(6.283185307179586D * (double) i / (double) K) * (double) diametro) + holgura;
         int y = (int) (Math.cos(6.283185307179586D * (double) i / (double) K) * (double) diametro) + holgura;
         this.insertarNodo(new Point(j, y));
      }

      this.aristas = new Vector();

      for (i = 0; i < K; ++i) {
         for (j = i + 1; j < K; ++j) {
            Arista arista1 = new Arista(i, j, 0);
            arista1.setPeso((double) peso);
            this.aristas.add(arista1);
         }
      }

   }

   public void setGrafoFlujoMax(GrafoMultiColor grafoFlujoMax) {
      this.grafoFlujoMax = grafoFlujoMax;
   }

   public GrafoMultiColor getGrafoFlujoMax() {
      return this.grafoFlujoMax;
   }

   public int getTipo() {
      return this.tipoGrafo;
   }

   public void setTipo(int t) {
      this.tipoGrafo = t;
   }

   public Vector<Arista> getAristas() {
      return this.aristas;
   }

   public void setAristas(Vector<Arista> aristasP) {
      this.aristas = aristasP;
   }

   public void resetAristas() {
      this.aristas = new Vector();
   }

   public Vector<Nodo> getNodos() {
      return this.nodos;
   }

   public Grafo clonar() {
      Grafo grf_clon = new Grafo();
      grf_clon.tipoGrafo = this.tipoGrafo;
      grf_clon.nodos = (Vector) this.nodos.clone();

      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         Arista a_clonada = new Arista(a.getCabeza(), a.getCola(), a.getTipo());
         a_clonada.setPeso(a.getPeso());
         a_clonada.setMultiplicidad(a.getMultiplicidad());
         grf_clon.aristas.add(a_clonada);
      }

      return grf_clon;
   }

   public int getPosicionNodoByNombre(String name) {
      for (int i = 0; i < this.nodos.size(); ++i) {
         Nodo n = (Nodo) this.nodos.get(i);
         if (n.getNombre().matches(name)) {
            return i;
         }
      }

      return -1;
   }

   public Nodo getNodoByNombre(String name) {
      for (int i = 0; i < this.nodos.size(); ++i) {
         Nodo n = (Nodo) this.nodos.get(i);
         if (n.getNombre().matches(name)) {
            return n;
         }
      }

      return null;
   }

   public Nodo getNodoByIndex(int index) {
      if (index >= 0 && index < this.nodos.size()) {
         Nodo n = (Nodo) this.nodos.get(index);
         return n;
      } else {
         return null;
      }
   }

   public Arista getAristaByIndex(int index) {
      if (index >= 0 && index < this.aristas.size()) {
         Arista n = (Arista) this.aristas.get(index);
         return n;
      } else {
         return null;
      }
   }

   public Arista getAristaByNodosIndex(int indexN1, int indexN2) {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getCabeza() == indexN1 && a.getCola() == indexN2 || a.getCabeza() == indexN2 && a.getCola() == indexN1) {
            return a;
         }
      }

      return null;
   }

   public Arista getArcoByNodosIndex(int indexN1, int indexN2) {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getCabeza() == indexN1 && a.getCola() == indexN2) {
            return a;
         }
      }

      return null;
   }

   public boolean cambiarPesoArista(int cabeza, int cola, double peso) {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getCabeza() == cabeza & a.getCola() == cola | a.getCabeza() == cola & a.getCola() == cabeza) {
            a.setPeso(peso);
            this.aristas.set(i, a);
            return true;
         }
      }

      return false;
   }

   public void pintarGrafo(Graphics g) {
      this.pintarGrafo(g, 1.0);
   }

   public void pintarGrafo(Graphics g, double zoom) {
      Arista arista = null;
      if (this != null) {
         int j;
         for (j = 0; j < this.nodos.size(); ++j) {
            Nodo nodo = (Nodo) this.nodos.get(j);
            Point p = nodo.getPos();
            Point pZoom = new Point((int) (p.x * zoom), (int) (p.y * zoom));
            nodo.pintarNodo(g, pZoom, Color.BLACK);
         }

         for (j = 0; j < this.aristas.size(); ++j) {
            arista = (Arista) this.aristas.get(j);
            if (arista != null) {
               Nodo nodoCabeza = (Nodo) this.nodos.get(arista.getCabeza());
               Nodo nodoCola = (Nodo) this.nodos.get(arista.getCola());
               Point pCabeza = nodoCabeza.getPos();
               Point pCola = nodoCola.getPos();
               Point pCabezaZoom = new Point((int) (pCabeza.x * zoom), (int) (pCabeza.y * zoom));
               Point pColaZoom = new Point((int) (pCola.x * zoom), (int) (pCola.y * zoom));
               arista.pintarArista(g, pCabezaZoom, pColaZoom, arista.getTipo(), false, Color.BLACK);
            }
         }
      }

   }

   public void pintarNodos(Graphics g, Vector<Integer> indices, Color color) {
      for (int i = 0; i < indices.size(); ++i) {
         int indice = (Integer) indices.get(i);
         Nodo nodo = (Nodo) this.nodos.get(indice);
         nodo.pintarNodoRelleno(g, color);
      }

   }

   public void insertarNodo(Point v_punto) {
      int tamanyo = this.nodos.size() + 1;
      String nombre = "v" + tamanyo;
      Nodo n = this.getNodoByNombre(nombre);

      for (int i = this.nodos.size() - 1; n != null; --i) {
         nombre = "v" + i;
         n = this.getNodoByNombre(nombre);
      }

      this.nodos.add(new Nodo(nombre, v_punto));
   }

   public void insertarNodo(Nodo n) {
      this.nodos.add(n);
   }

   public void insertarNodo(Nodo n, int indice) {
      this.nodos.add(indice, n);
   }

   public void borrarNodoNombre(String nom) {
      for (int i = 0; i < this.nodos.size(); ++i) {
         if (this.getNodoByIndex(i).getNombre().equalsIgnoreCase(nom)) {
            this.borrarNodoIndex(i);
            break;
         }
      }

   }

   public void borrarNodoIndex(int index) {
      if (index > -1 && index < this.nodos.size()) {
         this.nodos.remove(index);

         for (int j = 0; j < this.aristas.size(); ++j) {
            Arista arista1 = (Arista) this.aristas.get(j);
            if (arista1.getCabeza() != index && arista1.getCola() != index) {
               if (arista1.getCabeza() > index) {
                  arista1.setCabeza(arista1.getCabeza() - 1);
               }

               if (arista1.getCola() > index) {
                  arista1.setCola(arista1.getCola() - 1);
               }

               this.aristas.set(j, arista1);
            } else {
               this.aristas.remove(j);
               --j;
            }
         }
      }

   }

   public boolean insertarArista(int nodo1, int nodo2) {
      if (!this.existeArista(nodo1, nodo2)) {
         this.aristas.add(new Arista(nodo1, nodo2, 0));
         return true;
      } else {
         return false;
      }
   }

   public void insertarArista(Arista a) {
      if (!this.existeArista(a)) {
         this.aristas.add(a);
      }

   }

   public boolean insertarArco(int nodo1, int nodo2) {
      if (!this.existeArco(nodo1, nodo2)) {
         this.aristas.add(new Arista(nodo1, nodo2, 1));
         return true;
      } else {
         return false;
      }
   }

   public void insertarArco(Arista a) {
      if (!this.existeArco(a)) {
         this.aristas.add(a);
      }

   }

   public boolean existeArista(int cabeza, int cola) {
      boolean existeArista = false;

      for (int k = 0; k < this.aristas.size(); ++k) {
         Arista arista = (Arista) this.aristas.get(k);
         if (arista.getCabeza() == cabeza && arista.getCola() == cola
               || arista.getCabeza() == cola && arista.getCola() == cabeza) {
            existeArista = true;
         }
      }

      return existeArista;
   }

   public boolean existeArista(Arista a) {
      boolean existeArista = false;

      for (int k = 0; k < this.aristas.size(); ++k) {
         Arista arista = (Arista) this.aristas.get(k);
         if (arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola()
               || arista.getCabeza() == a.getCola() && arista.getCola() == a.getCabeza()) {
            existeArista = true;
         }
      }

      return existeArista;
   }

   public boolean existeArco(int cabeza, int cola) {
      boolean existeArco = false;

      for (int k = 0; k < this.aristas.size(); ++k) {
         Arista arista = (Arista) this.aristas.get(k);
         if (arista.getCabeza() == cabeza && arista.getCola() == cola) {
            existeArco = true;
         }
      }

      return existeArco;
   }

   public boolean existeArco(Arista a) {
      boolean existeArco = false;

      for (int k = 0; k < this.aristas.size(); ++k) {
         Arista arista = (Arista) this.aristas.get(k);
         if (arista.getCabeza() == a.getCabeza() && arista.getCola() == a.getCola()) {
            existeArco = true;
         }
      }

      return existeArco;
   }

   public boolean aumentarArista(int indexN1, int indexN2) {
      Arista a = null;

      int index;
      for (index = 0; index < this.aristas.size(); ++index) {
         a = (Arista) this.aristas.get(index);
         if (a.getCabeza() == indexN1 & a.getCola() == indexN2 | a.getCabeza() == indexN2 & a.getCola() == indexN1) {
            break;
         }
      }

      if (index < this.aristas.size()) {
         a.incrementarMultiplicidad();
         this.aristas.set(index, a);
         return true;
      } else {
         return false;
      }
   }

   public void borrarArcobyNodosIndex(int indexN1, int indexN2) {
      Arista a = null;

      int index;
      for (index = 0; index < this.aristas.size(); ++index) {
         a = (Arista) this.aristas.get(index);
         if (a.getCabeza() == indexN1 & a.getCola() == indexN2) {
            break;
         }
      }

      if (index < this.aristas.size()) {
         if (a.getMultiplicidad() == 1) {
            this.aristas.remove(index);
         } else {
            a.decrementarMultiplicidad();
            this.aristas.set(index, a);
         }
      }

   }

   public void borrarAristabyNodosIndex(int indexN1, int indexN2) {
      Arista a = null;

      int index;
      for (index = 0; index < this.aristas.size(); ++index) {
         a = (Arista) this.aristas.get(index);
         if (a.getCabeza() == indexN1 & a.getCola() == indexN2 | a.getCabeza() == indexN2 & a.getCola() == indexN1) {
            break;
         }
      }

      if (index < this.aristas.size()) {
         if (a.getMultiplicidad() == 1) {
            this.aristas.remove(index);
         } else {
            a.decrementarMultiplicidad();
            this.aristas.set(index, a);
         }
      }

   }

   public void borrarAristaIndex(int index) {
      if (index != -1 && index < this.aristas.size()) {
         Arista arista = (Arista) this.aristas.get(index);
         if (arista.getMultiplicidad() == 1) {
            this.aristas.remove(index);
         } else {
            arista.decrementarMultiplicidad();
            this.aristas.set(index, arista);
         }
      }

   }

   private Vector<Nodo> nodosAdyacentes(Nodo n) {
      Vector<Nodo> ListaNodosAdyacentes = new Vector();
      Arista a = null;
      Nodo n1 = null;
      Nodo n2 = null;

      for (int i = 0; i < this.aristas.size(); ++i) {
         a = (Arista) this.aristas.get(i);
         n1 = (Nodo) this.nodos.get(a.getCabeza());
         n2 = (Nodo) this.nodos.get(a.getCola());
         if (a.getTipo() == 0) {
            if (n1 == n) {
               ListaNodosAdyacentes.add(n2);
            } else if (n2 == n) {
               ListaNodosAdyacentes.add(n1);
            }
         } else if (n1 == n) {
            ListaNodosAdyacentes.add(n2);
         }
      }

      return ListaNodosAdyacentes;
   }

   public Vector<Nodo> BFS(MenuPrincipal menuppal, Nodo n, boolean flujoMaximo) {
      Vector<Nodo> nodos_alcanzados = new Vector();
      Vector<Nodo> adyacentes = null;
      Nodo nodo = null;
      GrafoMultiColor grafoPintar = new GrafoMultiColor();
      grafoPintar = grafoPintar.convertirAGrafoMultiColor(this);
      nodos_alcanzados.add(n);

      for (int i = 0; i < nodos_alcanzados.size(); ++i) {
         adyacentes = this.nodosAdyacentes((Nodo) nodos_alcanzados.get(i));

         for (int j = 0; j < adyacentes.size(); ++j) {
            nodo = (Nodo) adyacentes.get(j);
            if (!nodos_alcanzados.contains(nodo)) {
               nodos_alcanzados.add(nodo);
               if (menuppal != null || flujoMaximo) {
                  Nodo n1 = (Nodo) nodos_alcanzados.get(i);
                  Arista a0 = null;
                  Arista a1 = null;
                  if (grafoPintar.getTipo() == 0) {
                     a0 = this.getAristaByNodosIndex(this.getPosicionNodoByNombre(n1.getNombre()),
                           this.getPosicionNodoByNombre(nodo.getNombre()));
                     a1 = new Arista(this.getPosicionNodoByNombre(n1.getNombre()),
                           this.getPosicionNodoByNombre(nodo.getNombre()), 0);
                     if (a0 != null) {
                        a1.setPeso(a0.getPeso());
                     }

                     if (grafoPintar.existeArista(a1)) {
                        grafoPintar.deAzul.add(a1);
                     }
                  } else {
                     a0 = this.getArcoByNodosIndex(this.getPosicionNodoByNombre(n1.getNombre()),
                           this.getPosicionNodoByNombre(nodo.getNombre()));
                     a1 = new Arista(this.getPosicionNodoByNombre(n1.getNombre()),
                           this.getPosicionNodoByNombre(nodo.getNombre()), 1);
                     if (a0 != null) {
                        a1.setPeso(a0.getPeso());
                     }

                     if (grafoPintar.existeArco(a1)) {
                        grafoPintar.deAzul.add(a1);
                     }
                  }
               }
            }
         }
      }

      if (menuppal != null) {
         menuppal.panel.setGrafo(grafoPintar);
         menuppal.redibujarGrafo();
      }

      if (flujoMaximo) {
         this.grafoFlujoMax = grafoPintar.clonar();
      }

      return nodos_alcanzados;
   }

   public Grafo DFS(MenuPrincipal menuppal, Nodo n, boolean orientar, Vector<Nodo> ListaNodosAdyacentes) {
      int cima = 0;
      boolean existeW2 = false;
      GrafoMultiColor grafoPintar = new GrafoMultiColor();
      grafoPintar = grafoPintar.convertirAGrafoMultiColor(this);
      Vector<Nodo> L = new Vector();
      Vector<Arista> A = new Vector();
      Stack<Nodo> P = new Stack();
      L.add(n);
      P.push(n);

      int j;
      while (!P.isEmpty()) {
         Nodo w1 = (Nodo) P.peek();

         for (j = 0; j < this.getNodos().size(); ++j) {
            if (w1 == this.getNodoByIndex(j)) {
               cima = j;
            }
         }

         existeW2 = false;

         for (j = 0; j < this.getAristas().size(); ++j) {
            Nodo w2 = null;
            boolean existeCima = false;
            boolean contenidoL = false;
            int cola;
            if (this.getTipo() != 0) {
               existeCima = cima == this.getAristaByIndex(j).getCabeza();
               cola = this.getAristaByIndex(j).getCola();
               w2 = this.getNodoByIndex(cola);
               contenidoL = !L.contains(w2);
            } else {
               existeCima = cima == this.getAristaByIndex(j).getCabeza() || cima == this.getAristaByIndex(j).getCola();
               cola = this.getAristaByIndex(j).getCola();
               w2 = this.getNodoByIndex(cola);
               contenidoL = !L.contains(w2);
               if (!contenidoL) {
                  cola = this.getAristaByIndex(j).getCabeza();
                  w2 = this.getNodoByIndex(cola);
                  contenidoL = !L.contains(w2);
               }
            }

            if (!existeW2 && existeCima && contenidoL) {
               P.push(w2);
               L.add(w2);
               Arista a = new Arista(cima, cola, this.getAristaByIndex(j).getTipo());
               A.add(a);
               if (this.getTipo() == 0) {
                  if (grafoPintar.existeArista(this.getAristaByIndex(j))) {
                     grafoPintar.deAzul.add(this.getAristaByIndex(j));
                  }
               } else if (grafoPintar.existeArco(this.getAristaByIndex(j))) {
                  grafoPintar.deAzul.add(this.getAristaByIndex(j));
               }

               existeW2 = true;
            }
         }

         if (!existeW2 && !P.isEmpty()) {
            P.pop();
         }
      }

      if (menuppal != null) {
         menuppal.panel.setGrafo(grafoPintar);
         menuppal.redibujarGrafo();
      }

      Grafo G = new Grafo();
      G.nodos = L;
      if (!orientar) {
         return G;
      } else {
         for (j = 0; j < G.getNodos().size(); ++j) {
            Nodo n13 = G.getNodoByIndex(j);
            ListaNodosAdyacentes.add(n13);
         }

         return grafoPintar;
      }
   }

   public boolean esConexo() {
      Nodo n = null;
      if (this.nodos.size() > 0) {
         n = (Nodo) this.nodos.get(0);
         if (this.BFS((MenuPrincipal) null, n, false).size() == this.nodos.size()) {
            return true;
         }
      }

      return false;
   }

   public boolean esFuertementeConexo() {
      for (int i = 0; i < this.nodos.size(); ++i) {
         if (this.BFS((MenuPrincipal) null, (Nodo) this.nodos.get(i), false).size() != this.nodos.size()) {
            return false;
         }
      }

      return true;
   }

   public boolean esDebilmenteConexo() {
      Grafo g = this.obtenerGrafoSubyacente(this);
      return g.esConexo();
   }

   public Grafo obtenerGrafoSubyacente(Grafo g) {
      Grafo resultado = this.clonar();
      resultado.resetAristas();
      resultado.setTipo(0);
      MatrizTabla matriz = new MatrizTabla(this);

      for (int i = 0; i < this.getNodos().size(); ++i) {
         for (int j = 0; j < this.getNodos().size(); ++j) {
            Double peso = (Double) matriz.getValueAt(i, j);
            if (peso != 0.0D) {
               Arista a = new Arista(i, j, 0);
               a.setPeso(peso);
               resultado.insertarArista(a);
            }
         }
      }

      resultado.convertirAGrafoNOdirigido();
      return resultado;
   }

   public int cardComponentesConexas() {
      int componentes = 0;
      Grafo aux = this.clonar();

      while (this.getNodos().size() != 0) {
         ++componentes;
         Vector<Nodo> nodos_componente = this.BFS((MenuPrincipal) null, (Nodo) this.nodos.get(0), false);

         for (int i = 0; i < nodos_componente.size(); ++i) {
            Nodo n = (Nodo) nodos_componente.get(i);
            this.borrarNodoNombre(n.getNombre());
         }
      }

      this.aristas = aux.aristas;
      this.nodos = aux.nodos;
      return componentes;
   }

   public int cardComponentesFuerteConexas() {
      int n = this.getNodos().size();
      Matriz m = new Matriz(n, n);
      Vector<Nodo> v = null;
      Nodo n1 = null;
      Nodo n2 = null;
      int indexNodo = 0;

      int i;
      int j;
      for (i = 0; i < n; ++i) {
         for (j = 0; j < n; ++j) {
            if (i == j) {
               m.A[i][j] = 1.0D;
            } else {
               m.A[i][j] = 0.0D;
            }
         }
      }

      for (i = 0; i < n; ++i) {
         n1 = (Nodo) this.nodos.get(i);
         v = this.BFS((MenuPrincipal) null, n1, false);

         for (j = 0; j < v.size(); ++j) {
            n2 = (Nodo) v.get(j);
            indexNodo = this.getPosicionNodoByNombre(n2.getNombre());
            m.A[i][indexNodo] = 1.0D;
         }
      }

      Matriz t = m.traspuesta();
      Matriz p = m.productoElementoAElemento(t);
      Vector<Double> aux1 = new Vector(n);
      Vector<Double> aux2 = new Vector(n);
      int componentes = 0;

      for (i = 0; i < n - 1; ++i) {
         aux1.clear();

         for (j = 0; j < this.getNodos().size(); ++j) {
            aux1.add(p.A[i][j]);
         }

         for (j = i + 1; j < n; ++j) {
            aux2.clear();

            for (int k = 0; k < this.getNodos().size(); ++k) {
               aux2.add(p.A[j][k]);
            }

            if (aux2.equals(aux1)) {
               p = p.eliminarFila(j);
               --n;
               i = -1;
            }
         }
      }

      return n;
   }

   public Vector<Integer> indexNodosAdyacentes(int index) {
      Vector<Integer> listaNodosAdyacentes = new Vector();
      Arista a = null;

      for (int i = 0; i < this.aristas.size(); ++i) {
         a = (Arista) this.aristas.get(i);
         if (this.getTipo() == 0) {
            if (a.getCabeza() == index) {
               listaNodosAdyacentes.add(a.getCola());
            } else if (a.getCola() == index) {
               listaNodosAdyacentes.add(a.getCabeza());
            }
         } else if (a.getCabeza() == index) {
            listaNodosAdyacentes.add(a.getCola());
         }
      }

      return listaNodosAdyacentes;
   }

   public DijkstraObject dijkstra(int raiz) {
      DijkstraObject djkObjeto = new DijkstraObject(raiz, this.getNodos().size());
      Vector<Integer> nodosPendientes = new Vector(this.nodos.size());

      int indiceATratar;
      for (indiceATratar = 0; indiceATratar < this.nodos.size(); ++indiceATratar) {
         nodosPendientes.add(new Integer(indiceATratar));
      }

      while (!nodosPendientes.isEmpty()) {
         indiceATratar = djkObjeto.siguienteNodo(nodosPendientes);
         if (indiceATratar < 0) {
            break;
         }

         nodosPendientes.remove(new Integer(indiceATratar));
         Vector<Integer> adyacentes = this.indexNodosAdyacentes(indiceATratar);

         for (int i = 0; i < adyacentes.size(); ++i) {
            if (nodosPendientes.contains((Integer) adyacentes.get(i))) {
               int adyacente = (Integer) adyacentes.get(i);
               Arista a = null;
               if (this.getTipo() == 0) {
                  a = this.getAristaByNodosIndex(indiceATratar, adyacente);
               } else {
                  a = this.getArcoByNodosIndex(indiceATratar, adyacente);
               }

               djkObjeto.intentarMejora(a.getPeso(), indiceATratar, adyacente);
            }
         }
      }

      return djkObjeto;
   }

   public DijkstraObject dijkstra(int raiz, int destino) {
      DijkstraObject djkObjeto = new DijkstraObject(raiz, this.getNodos().size());
      Vector<Integer> nodosPendientes = new Vector(this.nodos.size());

      int indiceATratar;
      for (indiceATratar = 0; indiceATratar < this.nodos.size(); ++indiceATratar) {
         nodosPendientes.add(new Integer(indiceATratar));
      }

      while (nodosPendientes.contains(new Integer(destino))) {
         indiceATratar = djkObjeto.siguienteNodo(nodosPendientes);
         if (indiceATratar < 0) {
            break;
         }

         nodosPendientes.remove(new Integer(indiceATratar));
         Vector<Integer> adyacentes = this.indexNodosAdyacentes(indiceATratar);

         for (int i = 0; i < adyacentes.size(); ++i) {
            if (nodosPendientes.contains((Integer) adyacentes.get(i))) {
               int adyacente = (Integer) adyacentes.get(i);
               Arista a = this.getAristaByNodosIndex(indiceATratar, adyacente);
               djkObjeto.intentarMejora(a.getPeso(), indiceATratar, adyacente);
            }
         }
      }

      return djkObjeto;
   }

   public boolean cambiarNombreIndex(int index, String NewName) {
      if (index >= this.nodos.size()) {
         return false;
      } else {
         for (int i = 0; i < this.nodos.size(); ++i) {
            if (i != index) {
               Nodo n = (Nodo) this.nodos.get(i);
               if (n.getNombre().matches(NewName)) {
                  return false;
               }
            }
         }

         Nodo n1 = (Nodo) this.nodos.get(index);
         n1.setNombre(NewName);
         this.nodos.set(index, n1);
         return true;
      }
   }

   public boolean cambiarPosicionIndex(int index, Point NewPos) {
      if (index >= this.nodos.size()) {
         return false;
      } else {
         for (int i = 0; i < this.nodos.size(); ++i) {
            if (i != index) {
               Nodo n = this.getNodoByIndex(i);
               if (n.getPos().distance(NewPos) < 15.0D) {
                  return false;
               }
            }
         }

         Nodo n1 = (Nodo) this.nodos.get(index);
         n1.setPos(NewPos);
         this.nodos.set(index, n1);
         return true;
      }
   }

   public GrafoEdmonds convertirAEdmonds() {
      GrafoEdmonds G = new GrafoEdmonds();
      G.aristas = this.aristas;

      for (int i = 0; i < this.nodos.size(); ++i) {
         Nodo n = this.getNodoByIndex(i);
         NodoEdmonds nEdmonds = new NodoEdmonds(n.getNombre(), n.getPos());
         G.nodos.add(nEdmonds);
      }

      G.setPrimeraFloracion(G.nodos.size());
      return G;
   }

   public int getGradoIndex(int index) {
      int grado = 0;

      for (int j = 0; j < this.aristas.size(); ++j) {
         Arista arista = (Arista) this.aristas.get(j);
         if (arista.getCabeza() == index | arista.getCola() == index) {
            grado += arista.getMultiplicidad();
            if (arista.getCabeza() == index && arista.getCola() == index) {
               grado += arista.getMultiplicidad();
            }
         }
      }

      return grado;
   }

   public int getGradoEntradaIndex(int index) {
      int grado = 0;

      for (int j = 0; j < this.aristas.size(); ++j) {
         Arista arista = (Arista) this.aristas.get(j);
         if (arista.getCola() == index) {
            grado += arista.getMultiplicidad();
         }
      }

      return grado;
   }

   public int getGradoSalidaIndex(int index) {
      int grado = 0;

      for (int j = 0; j < this.aristas.size(); ++j) {
         Arista arista = (Arista) this.aristas.get(j);
         if (arista.getCabeza() == index) {
            grado += arista.getMultiplicidad();
         }
      }

      return grado;
   }

   public void invertirPesosAristas() {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         a.setPeso(-a.getPeso());
         this.aristas.set(i, a);
      }

   }

   public String toMathematica() {
      if (this.nodos.size() == 0) {
         return "";
      } else {
         String str = "Grafo[{";

         int j;
         Arista a;
         for (j = 0; j < this.nodos.size() - 1; ++j) {
            str = str + "{";

            for (int k = 0; k < this.nodos.size() - 1; ++k) {
               a = this.getAristaByNodosIndex(j, k);
               if (a == null) {
                  str = str + "0, ";
               } else {
                  str = str + a.getPeso() + ", ";
               }
            }

            a = this.getAristaByNodosIndex(j, this.nodos.size() - 1);
            if (a == null) {
               str = str + "0";
            } else {
               str = str + a.getPeso();
            }

            str = str + "}, ";
         }

         str = str + "{";

         for (j = 0; j < this.nodos.size() - 1; ++j) {
            a = this.getAristaByNodosIndex(this.nodos.size() - 1, j);
            if (a == null) {
               str = str + "0, ";
            } else {
               str = str + a.getPeso() + ", ";
            }
         }

         a = this.getAristaByNodosIndex(this.nodos.size() - 1, this.nodos.size() - 1);
         if (a == null) {
            str = str + "0";
         } else {
            str = str + a.getPeso();
         }

         str = str + "} ";
         str = str + "}, VerticesDefecto[" + this.nodos.size() + "]]";
         return str;
      }
   }

   public String toXMLString() {
      if (this.tipoGrafo == 0) {
         this.convertirAGrafoNOdirigido();
      }

      String str = "<grafo>\n<tipoGrafo> \"" + this.getTipo() + "\" </tipoGrafo>\n<nodos>";

      int i;
      for (i = 0; i < this.getNodos().size(); ++i) {
         str = str + "<nodo>\n<nombre> \"" + this.getNodoByIndex(i).getNombre() + "\" </nombre>\n<posicion> ("
               + this.getNodoByIndex(i).getPos().x + "," + this.getNodoByIndex(i).getPos().y + ") </posicion>"
               + "\n</nodo>\n";
      }

      str = str + "</nodos>\n<aristas>";

      for (i = 0; i < this.getAristas().size(); ++i) {
         str = str + "<arista>\n<nodo1> \"" + this.getAristaByIndex(i).getCabeza() + "\" </nodo1>\n<nodo2> \""
               + this.getAristaByIndex(i).getCola() + "\" </nodo2>\n<peso> \"" + this.getAristaByIndex(i).getPeso()
               + "\" </peso>\n<tipo> \"" + this.getAristaByIndex(i).getTipo() + "\" </tipo>\n</arista>\n";
      }

      str = str + "</aristas> \n </grafo>";
      return str;
   }

   public boolean hayBucles() {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getCabeza() == a.getCola()) {
            return true;
         }
      }

      return false;
   }

   public boolean hayArcos() {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getTipo() == 1) {
            return true;
         }
      }

      return false;
   }

   public boolean hayAristas() {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getTipo() == 0) {
            return true;
         }
      }

      return false;
   }

   public boolean hayAristasDeCorte() {
      Grafo aux = this.clonar();
      int comp = this.cardComponentesConexas();

      for (int i = 0; i < this.aristas.size(); ++i) {
         aux.borrarAristaIndex(i);
         if (comp < aux.cardComponentesConexas()) {
            return true;
         }

         aux = this.clonar();
      }

      return false;
   }

   public boolean hayVerticesDeCorte() {
      Grafo aux = this.clonar();
      int comp = this.cardComponentesConexas();

      for (int i = 0; i < this.nodos.size(); ++i) {
         aux.borrarNodoIndex(i);
         if (comp < aux.cardComponentesConexas()) {
            return true;
         }

         aux = this.clonar();
      }

      return false;
   }

   public boolean existenPesosNegativos() {
      for (int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista) this.aristas.get(i);
         if (a.getPeso() < 0.0D) {
            return true;
         }
      }

      return false;
   }

   private boolean todosLosNodosGradoPar() {
      Vector<Integer> nGradoPar = new Vector();

      for (int i = 0; i < this.getNodos().size(); ++i) {
         int grado = this.getGradoIndex(i);
         if ((float) grado / 2.0F == (float) Math.round((float) (grado / 2))) {
            nGradoPar.add(new Integer(i));
         }
      }

      if (nGradoPar.size() == this.getNodos().size()) {
         return true;
      } else {
         return false;
      }
   }

   private boolean todosLosNodosMismoGradoEntradaSalida() {
      for (int j = 0; j < this.nodos.size(); ++j) {
         if (this.getGradoEntradaIndex(j) != this.getGradoSalidaIndex(j)) {
            return false;
         }
      }

      return true;
   }

   public boolean esEuleriano() {
      if (this.getTipo() == 0 && this.esConexo() && this.todosLosNodosGradoPar()) {
         return true;
      } else {
         return this.getTipo() == 1 && this.esDebilmenteConexo() && this.todosLosNodosMismoGradoEntradaSalida();
      }
   }

   public void pasarDePesosAMultiplicidades() {
      for (int i = 0; i < this.getAristas().size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         int pesoArista = (int) a.getPeso();
         if (pesoArista > 1) {
            a.setMultiplicidad(pesoArista);
         }
      }

   }

   public void pasarDeMultiplicidadesAPesos() {
      for (int i = 0; i < this.getAristas().size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         int multiplicidadArista = a.getMultiplicidad();
         if (multiplicidadArista > 1) {
            a.setPeso((double) multiplicidadArista);
            a.setMultiplicidad(1);
         }
      }

   }

   public boolean existenPesosConDecimales() {
      double peso = 0.0D;

      for (int i = 0; i < this.getAristas().size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         peso = a.getPeso();
         if (peso - (double) ((int) peso) != 0.0D) {
            return true;
         }
      }

      return false;
   }

   public boolean verificaDesigualdadTriangular() {
      double pesoA1 = 0.0D;
      double pesoA2 = 0.0D;
      double pesoA3 = 0.0D;
      Arista a1 = null;
      Arista a2 = null;
      Arista a3 = null;

      for (int i = 0; i < this.aristas.size(); ++i) {
         a1 = this.getAristaByIndex(i);
         pesoA1 = a1.getPeso();

         for (int j = 0; j < this.nodos.size(); ++j) {
            a2 = this.getAristaByNodosIndex(a1.getCabeza(), j);
            a3 = this.getAristaByNodosIndex(a1.getCola(), j);
            if (a2 != null && a3 != null) {
               pesoA2 = a2.getPeso();
               pesoA3 = a3.getPeso();
               if (pesoA1 > pesoA2 + pesoA3) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   public boolean existeVerticeGradoUno() {
      for (int i = 0; i < this.nodos.size(); ++i) {
         if (this.getGradoIndex(i) == 1) {
            return true;
         }
      }

      return false;
   }

   public void convertirAGrafoNOdirigido() {
      this.setTipo(0);
      MatrizTabla matriz = new MatrizTabla(this);
      this.resetAristas();

      for (int i = 0; i < this.getNodos().size(); ++i) {
         for (int j = i; j < this.getNodos().size(); ++j) {
            Double peso = (Double) matriz.getValueAt(i, j);
            if (peso != 0.0D) {
               Arista a = new Arista(i, j, 0);
               a.setPeso(peso);
               this.insertarArista(a);
            }
         }
      }

   }

   public boolean esMatrizSimetrica() {
      MatrizTabla matriz = new MatrizTabla(this);

      for (int i = 0; i < this.getNodos().size(); ++i) {
         for (int j = i; j < this.getNodos().size(); ++j) {
            Double peso1 = (Double) matriz.getValueAt(i, j);
            Double peso2 = (Double) matriz.getValueAt(j, i);
            if (peso1 != peso2) {
               return false;
            }
         }
      }

      return true;
   }

   public void convertirAGrafoDirigido() {
      this.setTipo(1);

      for (int i = 0; i < this.getAristas().size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         a.setTipo(1);
         Arista a2 = new Arista(a.getCola(), a.getCabeza(), 1);
         a2.setPeso(a.getPeso());
         this.insertarArco(a2);
      }

   }

   public void marcarTodosLosPesosAUno() {
      for (int i = 0; i < this.getAristas().size(); ++i) {
         Arista a = this.getAristaByIndex(i);
         a.setPeso(1.0D);
      }

   }

   public boolean esGrafoCompleto() {
      int numVertices = this.getNodos().size();
      int numAristasReales = this.getAristas().size();
      int numAristasTeoricas = numVertices * (numVertices - 1) / 2;
      return numAristasTeoricas == numAristasReales;
   }

   public double obtenerPesoGrafo() {
      double pesoTotal = 0.0D;
      Arista a = null;

      for (int i = 0; i < this.aristas.size(); ++i) {
         a = this.getAristaByIndex(i);
         pesoTotal += a.getPeso();
      }

      return pesoTotal;
   }
}
