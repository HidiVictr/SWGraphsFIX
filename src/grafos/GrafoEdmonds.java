package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

public class GrafoEdmonds extends Grafo {
   protected Vector<Arista> acoplamiento = new Vector();
   private int numNodosReales;
   private int numAristasReales;
   private int primeraFloracion;

   public GrafoEdmonds() {
   }

   public GrafoEdmonds(int k) {
      super(k, 1);
   }

   public void cambiarEtiquetaIndex(int index, double etiq) {
      Nodo n = (Nodo)this.nodos.get(index);
      n.setEtiqueta(etiq);
      this.nodos.set(index, n);
   }

   public Vector<Integer> indexNodosAdyacentes(int index) {
      int indexReal = this.indexRealNodo(index);
      Vector<Integer> listaNodosAdyacentes = new Vector();
      int k;
      int indexNodoAdyFloracion;
      int indexRealNodoAdyFloracion;
      if (indexReal < this.getPrimeraFloracion()) {
         listaNodosAdyacentes = super.indexNodosAdyacentes(index);

         for(k = 0; k < listaNodosAdyacentes.size(); ++k) {
            indexNodoAdyFloracion = (Integer)listaNodosAdyacentes.get(k);
            indexRealNodoAdyFloracion = this.indexRealNodo(indexNodoAdyFloracion);
            if (indexRealNodoAdyFloracion != indexNodoAdyFloracion) {
               listaNodosAdyacentes.set(k, new Integer(indexRealNodoAdyFloracion));
            }
         }
      } else {
         for(k = 0; k < this.getPrimeraFloracion(); ++k) {
            if (this.indexRealNodo(k) == indexReal) {
               listaNodosAdyacentes.addAll(super.indexNodosAdyacentes(k));
            }
         }

         for(k = 0; k < listaNodosAdyacentes.size(); ++k) {
            indexNodoAdyFloracion = (Integer)listaNodosAdyacentes.get(k);
            indexRealNodoAdyFloracion = this.indexRealNodo(indexNodoAdyFloracion);
            if (indexRealNodoAdyFloracion == indexReal) {
               listaNodosAdyacentes.remove(k);
               --k;
            } else if (indexRealNodoAdyFloracion != indexNodoAdyFloracion) {
               listaNodosAdyacentes.set(k, new Integer(indexRealNodoAdyFloracion));
            }
         }
      }

      return listaNodosAdyacentes;
   }

   public int indexRealNodo(int index) {
      NodoEdmonds n = (NodoEdmonds)this.nodos.get(index);

      int indexReal;
      for(indexReal = index; n.getFloracion() != -1; n = (NodoEdmonds)this.nodos.get(n.getFloracion())) {
         if (n.getFloracion() >= this.getNodos().size()) {
            n.setFloracion(-1);
            return indexReal;
         }

         indexReal = n.getFloracion();
      }

      return indexReal;
   }

   public void pintarGrafo(Graphics g) {
      if (this != null) {
         int l;
         for(l = 0; l < this.primeraFloracion; ++l) {
            NodoEdmonds nodo = (NodoEdmonds)this.nodos.get(l);
            nodo.pintarNodo(g);
         }

         for(l = this.primeraFloracion; l < this.nodos.size(); ++l) {
            Floracion f = (Floracion)this.nodos.get(l);
            f.pintarFloracion(g);
         }

         Arista arista;
         for(l = 0; l < this.aristas.size(); ++l) {
            arista = (Arista)this.aristas.get(l);
            if (arista != null) {
               NodoEdmonds nodoCabeza = (NodoEdmonds)this.getNodoByIndex(arista.getCabeza());
               NodoEdmonds nodoCola = (NodoEdmonds)this.getNodoByIndex(arista.getCola());
               if (nodoCabeza != null && nodoCola != null) {
                  arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), false, Color.BLACK);
               }
            }
         }

         for(l = 0; l < this.acoplamiento.size(); ++l) {
            arista = (Arista)this.acoplamiento.get(l);
            if (arista != null) {
               arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), true, Color.BLUE);
            }
         }
      }

   }

   protected Point localizarPosicion(int index) {
      Nodo n = this.getNodoByIndex(this.indexRealNodo(index));
      return n.getPos();
   }

   public void etiquetarAdmisible() {
      for(int i = 0; i < this.nodos.size(); ++i) {
         Nodo n = (Nodo)this.nodos.get(i);
         double etiqueta = -10000.0D;

         for(int j = 0; j < this.aristas.size(); ++j) {
            Arista a = (Arista)this.aristas.get(j);
            Nodo n1 = (Nodo)this.nodos.get(a.getCabeza());
            Nodo n2 = (Nodo)this.nodos.get(a.getCola());
            if (n1 == n) {
               etiqueta = Math.max(a.getPeso() - n2.getEtiqueta(), etiqueta);
            }

            if (n2 == n) {
               etiqueta = Math.max(a.getPeso() - n1.getEtiqueta(), etiqueta);
            }
         }

         n.setEtiqueta(etiqueta);
         this.nodos.remove(i);
         this.nodos.add(i, n);
      }

   }

   public boolean insertaAristaAcoplamiento(int nodo1, int nodo2, Grafo SGI) {
      if (nodo1 < this.primeraFloracion && nodo2 < this.primeraFloracion) {
         Arista a = this.getAristaByNodosIndex(nodo1, nodo2);
         if (!this.acoplamiento.contains(a)) {
            this.acoplamiento.add(a);
         }

         return true;
      } else {
         int i;
         Arista a;
         if (nodo1 < this.primeraFloracion && nodo2 >= this.primeraFloracion) {
            for(i = 0; i < SGI.aristas.size(); ++i) {
               a = SGI.getAristaByIndex(i);
               if (a.getCabeza() == nodo1 & this.nodoContenidoEnFloracion(nodo2, a.getCola()) || a.getCola() == nodo1 & this.nodoContenidoEnFloracion(nodo2, a.getCabeza())) {
                  if (!this.acoplamiento.contains(a)) {
                     this.acoplamiento.add(a);
                  }

                  return true;
               }
            }
         }

         if (nodo2 < this.primeraFloracion && nodo1 >= this.primeraFloracion) {
            for(i = 0; i < SGI.aristas.size(); ++i) {
               a = SGI.getAristaByIndex(i);
               if (a.getCabeza() == nodo2 & this.nodoContenidoEnFloracion(nodo1, a.getCola()) || a.getCola() == nodo2 & this.nodoContenidoEnFloracion(nodo1, a.getCabeza())) {
                  if (!this.acoplamiento.contains(a)) {
                     this.acoplamiento.add(a);
                  }

                  return true;
               }
            }
         }

         if (nodo1 >= this.primeraFloracion && nodo2 >= this.primeraFloracion) {
            for(i = 0; i < SGI.aristas.size(); ++i) {
               a = SGI.getAristaByIndex(i);
               if (this.nodoContenidoEnFloracion(nodo1, a.getCabeza()) & this.nodoContenidoEnFloracion(nodo2, a.getCola()) || this.nodoContenidoEnFloracion(nodo1, a.getCola()) & this.nodoContenidoEnFloracion(nodo2, a.getCabeza())) {
                  if (!this.acoplamiento.contains(a)) {
                     this.acoplamiento.add(a);
                  }

                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean quitarAristaDeAcoplamiento(int nodo1, int nodo2) {
      int i;
      Arista a;
      if (nodo1 < this.primeraFloracion && nodo2 < this.primeraFloracion) {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (a.getCabeza() == nodo1 & a.getCola() == nodo2 | a.getCabeza() == nodo2 & a.getCola() == nodo1) {
               this.acoplamiento.remove(i);
               return true;
            }
         }
      }

      if (nodo1 < this.primeraFloracion && nodo2 >= this.primeraFloracion) {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (a.getCabeza() == nodo1 & this.nodoContenidoEnFloracion(nodo2, a.getCola()) || a.getCola() == nodo1 & this.nodoContenidoEnFloracion(nodo2, a.getCabeza())) {
               this.acoplamiento.remove(i);
               return true;
            }
         }
      }

      if (nodo1 >= this.primeraFloracion && nodo2 < this.primeraFloracion) {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (a.getCabeza() == nodo2 & this.nodoContenidoEnFloracion(nodo1, a.getCola()) || a.getCola() == nodo2 & this.nodoContenidoEnFloracion(nodo1, a.getCabeza())) {
               this.acoplamiento.remove(i);
               return true;
            }
         }
      }

      if (nodo1 >= this.primeraFloracion && nodo2 >= this.primeraFloracion) {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (this.nodoContenidoEnFloracion(nodo1, a.getCabeza()) & this.nodoContenidoEnFloracion(nodo2, a.getCola()) || this.nodoContenidoEnFloracion(nodo1, a.getCola()) & this.nodoContenidoEnFloracion(nodo2, a.getCabeza())) {
               this.acoplamiento.remove(i);
               return true;
            }
         }
      }

      return false;
   }

   public Grafo convertirAGrafo() {
      Grafo G = new Grafo();

      for(int i = 0; i < this.nodos.size(); ++i) {
         NodoEdmonds nE = (NodoEdmonds)this.nodos.get(i);
         G.nodos.add(i, nE.convertirANodo());
      }

      G.aristas = this.aristas;
      return G;
   }

   public void addNodoFicticio() {
      int numeroFicticio = this.nodos.size() - this.getNumNodosReales() + 1;
      NodoEdmonds nuevoNodoFicticio = new NodoEdmonds("Ficticio " + numeroFicticio, new Point(720 * numeroFicticio, 720 * numeroFicticio));

      for(int i = 0; i < this.getNumNodosReales(); ++i) {
         Arista nuevaAristaFicticia = new Arista(i, this.nodos.size(), 0);
         nuevaAristaFicticia.setPeso(-10000.0D);
         this.aristas.add(nuevaAristaFicticia);
      }

      this.nodos.add(nuevoNodoFicticio);
      this.setPrimeraFloracion(this.primeraFloracion + 1);
   }

   public boolean esSaturadoIndex(int index) {
      int i;
      Arista a;
      if (index < this.primeraFloracion) {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (a.getCabeza() == index | a.getCola() == index) {
               return true;
            }
         }
      } else {
         for(i = 0; i < this.acoplamiento.size(); ++i) {
            a = (Arista)this.acoplamiento.get(i);
            if (this.indexRealNodo(a.getCabeza()) == index & this.indexRealNodo(a.getCabeza()) != this.indexRealNodo(a.getCola())) {
               return true;
            }

            if (this.indexRealNodo(a.getCola()) == index & this.indexRealNodo(a.getCabeza()) != this.indexRealNodo(a.getCola())) {
               return true;
            }
         }
      }

      return false;
   }

   public int getIndexNodoInsaturado() {
      for(int i = this.primeraFloracion - 1; i >= 0; --i) {
         NodoEdmonds n = (NodoEdmonds)this.nodos.get(i);
         if (n.getFloracion() == -1 & !this.esSaturadoIndex(i)) {
            return i;
         }
      }

      for(int j = this.primeraFloracion; j < this.nodos.size(); ++j) {
         NodoEdmonds n = (NodoEdmonds)this.nodos.get(j);
         if (n.getFloracion() == -1 & !this.esSaturadoIndex(j)) {
            return j;
         }
      }

      return -1;
   }

   public Arista getAristaByNodosIndex(int indexN1, int indexN2) {
      for(int i = 0; i < this.aristas.size(); ++i) {
         Arista a = (Arista)this.aristas.get(i);
         if (this.indexRealNodo(a.getCabeza()) == this.indexRealNodo(indexN1) & this.indexRealNodo(a.getCola()) == this.indexRealNodo(indexN2) | this.indexRealNodo(a.getCabeza()) == this.indexRealNodo(indexN2) & this.indexRealNodo(a.getCola()) == this.indexRealNodo(indexN1)) {
            return a;
         }
      }

      return null;
   }

   public void borrarFloracionIndex(int indexFloracion) {
      int i;
      for(i = 0; i < this.primeraFloracion; ++i) {
         NodoEdmonds n = (NodoEdmonds)this.getNodoByIndex(i);
         if (n.getFloracion() > indexFloracion) {
            n.setFloracion(n.getFloracion() - 1);
            this.nodos.set(i, n);
         }
      }

      Floracion f;
      for(i = this.primeraFloracion; i < indexFloracion; ++i) {
         f = (Floracion)this.getNodoByIndex(i);
         if (f.getFloracion() > indexFloracion) {
            f.setFloracion(f.getFloracion() - 1);
            this.nodos.set(i, f);
         }
      }

      for(i = indexFloracion; i < this.getNodos().size(); ++i) {
         f = (Floracion)this.getNodoByIndex(i);
         if (f.getFloracion() > indexFloracion) {
            f.setFloracion(f.getFloracion() - 1);
         }

         Vector<Integer> cicloContraido = f.getCicloContraido();

         for(int j = 0; j < cicloContraido.size(); ++j) {
            int nodoContraido = (Integer)cicloContraido.get(j);
            if (nodoContraido > indexFloracion) {
               cicloContraido.set(j, new Integer(nodoContraido - 1));
               f.setCicloContraido(cicloContraido);
            }
         }

         this.nodos.set(i, f);
      }

      this.getNodos().remove(indexFloracion);
   }

   public void completarConAristasFicticias() {
      for(int i = 0; i < this.primeraFloracion; ++i) {
         for(int j = i + 1; j < this.primeraFloracion; ++j) {
            if (super.getAristaByNodosIndex(i, j) == null) {
               Arista a = new Arista(i, j, 0);
               a.setPeso(-10000.0D);
               this.insertarArista(a);
            }
         }
      }

   }

   public void eliminarFiccion() {
      while(this.aristas.size() > this.numAristasReales) {
         this.aristas.remove(this.aristas.size() - 1);
      }

      for(int i = this.acoplamiento.size() - 1; i >= 0; --i) {
         Arista aristaAcopl = (Arista)this.acoplamiento.get(i);
         if (this.getAristaByNodosIndex(aristaAcopl.getCabeza(), aristaAcopl.getCola()) == null) {
            this.acoplamiento.remove(i);
         }
      }

      if (this.nodos.size() == this.numNodosReales + 1) {
         this.nodos.remove(this.numNodosReales);
         --this.primeraFloracion;
      }

   }

   public boolean nodoContenidoEnFloracion(int indexFloracion, int indexNodo) {
      NodoEdmonds n = (NodoEdmonds)this.nodos.get(indexNodo);
      if (n.getFloracion() == -1) {
         return false;
      } else {
         return n.getFloracion() == indexFloracion ? true : this.nodoContenidoEnFloracion(indexFloracion, n.getFloracion());
      }
   }

   public Vector<Integer> listaNodosContenidos(Floracion f) {
      Vector<Integer> nodosContenidos = new Vector();

      for(int i = 0; i < f.getCicloContraido().size(); ++i) {
         int index = (Integer)f.getCicloContraido().get(i);
         if (index < this.primeraFloracion) {
            nodosContenidos.add(new Integer(index));
         } else {
            Floracion floracion = (Floracion)this.getNodoByIndex(index);
            nodosContenidos.addAll(this.listaNodosContenidos(floracion));
         }
      }

      return nodosContenidos;
   }

   public Vector<Arista> getAcoplamiento() {
      return this.acoplamiento;
   }

   public int getNumNodosReales() {
      return this.numNodosReales;
   }

   public void setNumAristasReales() {
      this.numAristasReales = this.aristas.size();
   }

   public int getNumAristasReales() {
      return this.numAristasReales;
   }

   public void setNumNodosReales() {
      this.numNodosReales = this.nodos.size();
   }

   public int getPrimeraFloracion() {
      return this.primeraFloracion;
   }

   public void setPrimeraFloracion(int i) {
      this.primeraFloracion = i;
   }
}
