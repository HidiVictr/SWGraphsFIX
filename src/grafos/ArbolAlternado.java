package grafos;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class ArbolAlternado extends GrafoEdmonds {
   private int ultimoERevisado;
   private Vector<Integer> vectorE = new Vector();
   private Vector<Integer> vectorI = new Vector();

   public ArbolAlternado(SubGrafoIgualdad sgi) {
      this.nodos = sgi.nodos;
      this.ultimoERevisado = 0;
      this.setPrimeraFloracion(sgi.getPrimeraFloracion());
      this.addNodoE(sgi.getIndexNodoInsaturado());
   }

   public ArbolAlternado(SubGrafoIgualdad sgi, int index) {
      this.nodos = sgi.nodos;
      this.ultimoERevisado = 0;
      this.setPrimeraFloracion(sgi.getPrimeraFloracion());
      this.addNodoI(index);
   }

   public void addNodoE(int index) {
      if (index < this.getPrimeraFloracion()) {
         NodoEdmonds n = (NodoEdmonds)this.nodos.get(index);
         n.setEtiquetaE();
         this.nodos.set(index, n);
         if (n.getFloracion() == -1) {
            this.vectorE.add(new Integer(index));
         }
      } else {
         Floracion f = (Floracion)this.nodos.get(index);
         f.setEtiquetaE();
         this.nodos.set(index, f);

         for(int j = 0; j < f.getCicloContraido().size(); ++j) {
            int indexNodoContraido = (Integer)f.getCicloContraido().get(j);
            this.addNodoE(indexNodoContraido);
            this.sacarDeE(indexNodoContraido);
         }

         if (f.getFloracion() == -1) {
            this.vectorE.add(new Integer(index));
         }
      }

   }

   public void addNodoE(int index, int posicion) {
      if (index < this.getPrimeraFloracion()) {
         NodoEdmonds n = (NodoEdmonds)this.nodos.get(index);
         n.setEtiquetaE();
         this.nodos.set(index, n);
         if (n.getFloracion() == -1) {
            this.vectorE.add(posicion, new Integer(index));
         }
      } else {
         Floracion f = (Floracion)this.nodos.get(index);
         f.setEtiquetaE();
         this.nodos.set(index, f);

         for(int j = 0; j < f.getCicloContraido().size(); ++j) {
            int indexNodoContraido = (Integer)f.getCicloContraido().get(j);
            this.addNodoE(indexNodoContraido);
            this.sacarDeE(indexNodoContraido);
         }

         if (f.getFloracion() == -1) {
            this.vectorE.add(posicion, new Integer(index));
         }
      }

   }

   public Vector<Integer> getE() {
      return this.vectorE;
   }

   private void sacarDeE(int indexNodoContraido) {
      for(int i = 0; i < this.vectorE.size(); ++i) {
         int index = (Integer)this.vectorE.get(i);
         if (index == indexNodoContraido) {
            this.vectorE.remove(i);
            break;
         }
      }

   }

   public void addNodoI(int index) {
      if (index < this.getPrimeraFloracion()) {
         NodoEdmonds n = (NodoEdmonds)this.nodos.get(index);
         n.setEtiquetaI();
         this.nodos.set(index, n);
         if (n.getFloracion() == -1) {
            this.vectorI.add(new Integer(index));
         }
      } else {
         Floracion f = (Floracion)this.nodos.get(index);
         f.setEtiquetaI();
         this.nodos.set(index, f);

         for(int j = 0; j < f.getCicloContraido().size(); ++j) {
            int indexNodoContraido = (Integer)f.getCicloContraido().get(j);
            this.addNodoI(indexNodoContraido);
         }

         if (f.getFloracion() == -1) {
            this.vectorI.add(new Integer(index));
         }
      }

   }

   public int getIndexUltimoERevisado() {
      return (Integer)this.vectorE.get(this.ultimoERevisado);
   }

   public void setUltimoERevisado(int i) {
      this.ultimoERevisado = i;
   }

   public int getUltimoERevisado() {
      return this.ultimoERevisado;
   }

   public int getNumNodosE() {
      return this.vectorE.size();
   }

   public int retrocesoDeEHastaI(int indexNodo) {
      for(int i = 0; i < this.getAcoplamiento().size(); ++i) {
         Arista aristaAcop = (Arista)this.getAcoplamiento().get(i);
         if (this.indexRealNodo(aristaAcop.getCabeza()) != this.indexRealNodo(aristaAcop.getCola())) {
            if (this.indexRealNodo(aristaAcop.getCabeza()) == indexNodo) {
               return this.indexRealNodo(aristaAcop.getCola());
            }

            if (this.indexRealNodo(aristaAcop.getCola()) == indexNodo) {
               return this.indexRealNodo(aristaAcop.getCabeza());
            }
         }
      }

      return -1;
   }

   public int retrocesoDeIHastaE(int indexNodo) {
      for(int i = 0; i < this.aristas.size(); ++i) {
         Arista arista = (Arista)this.aristas.get(i);
         if (this.indexRealNodo(arista.getCabeza()) != this.indexRealNodo(arista.getCola())) {
            if (this.indexRealNodo(arista.getCabeza()) == indexNodo & !this.getAcoplamiento().contains(arista)) {
               return this.indexRealNodo(arista.getCola());
            }

            if (this.indexRealNodo(arista.getCola()) == indexNodo & !this.getAcoplamiento().contains(arista)) {
               return this.indexRealNodo(arista.getCabeza());
            }
         }
      }

      return -1;
   }

   public boolean esNodoE(int indexNodo) {
      NodoEdmonds n = (NodoEdmonds)this.nodos.get(indexNodo);
      return n.getEtiquetaArborea() == "E";
   }

   public boolean esNodoI(int indexNodo) {
      NodoEdmonds n = (NodoEdmonds)this.nodos.get(indexNodo);
      return n.getEtiquetaArborea() == "I";
   }

   public boolean esNodoArbol(int indexNodo) {
      return this.esNodoI(indexNodo) | this.esNodoE(indexNodo);
   }

   public void pintarGrafo(Graphics g) {
      if (this != null) {
         int raiz;
         for(raiz = 0; raiz < this.getPrimeraFloracion(); ++raiz) {
            NodoEdmonds nodo = (NodoEdmonds)this.nodos.get(raiz);
            if (nodo.getEtiquetaArborea() != null) {
               nodo.pintarNodo(g);
            }
         }

         for(raiz = this.getPrimeraFloracion(); raiz < this.nodos.size(); ++raiz) {
            Floracion f = (Floracion)this.nodos.get(raiz);
            if (f.getEtiquetaArborea() != null) {
               f.pintarFloracion(g);
            }
         }

         Arista arista;
         for(raiz = 0; raiz < this.aristas.size(); ++raiz) {
            arista = (Arista)this.aristas.get(raiz);
            if (arista != null) {
               arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), false, Color.BLACK);
            }
         }

         for(raiz = 0; raiz < this.getAcoplamiento().size(); ++raiz) {
            arista = (Arista)this.getAcoplamiento().get(raiz);
            if (arista != null) {
               arista.pintarArista(g, this.localizarPosicion(arista.getCabeza()), this.localizarPosicion(arista.getCola()), arista.getTipo(), true, Color.BLUE);
            }
         }

         if (this.getUltimoERevisado() < this.vectorE.size()) {
            Vector<Integer> revisando = new Vector();
            revisando.add(new Integer(this.getIndexUltimoERevisado()));
            this.pintarNodos(g, revisando, Color.RED);
         }

         raiz = (Integer)this.getE().get(0);
         if (raiz < this.getPrimeraFloracion()) {
            NodoEdmonds nodoRaiz = (NodoEdmonds)this.getNodos().get(raiz);
            nodoRaiz.pintarNodo(g, new Color(150, 0, 0));
         } else {
            Floracion nodoRaiz = (Floracion)this.getNodos().get(raiz);
            nodoRaiz.pintarFloracion(g, new Color(150, 0, 0));
         }
      }

   }

   public void aumentarERevisado() {
      ++this.ultimoERevisado;
   }
}
