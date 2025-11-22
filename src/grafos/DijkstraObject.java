package grafos;

import java.util.Vector;

public class DijkstraObject {
   private int raiz;
   private double[] distancias;
   private int[] precedencias;

   public DijkstraObject(int nodoRaiz, int numNodosGrafo) {
      this.raiz = nodoRaiz;
      this.distancias = new double[numNodosGrafo];
      this.precedencias = new int[numNodosGrafo];

      for(int i = 0; i < numNodosGrafo; ++i) {
         this.distancias[i] = Double.POSITIVE_INFINITY;
         this.precedencias[i] = -1;
      }

      this.distancias[this.raiz] = 0.0D;
   }

   public int getPredecesor(int nodoDeInteres) {
      return this.precedencias[nodoDeInteres];
   }

   public double getDistancia(int nodoDeInteres) {
      return this.distancias[nodoDeInteres];
   }

   public void intentarMejora(double peso, int predecesor, int destino) {
      if (this.getDistancia(destino) > this.getDistancia(predecesor) + peso) {
         this.distancias[destino] = this.getDistancia(predecesor) + peso;
         this.precedencias[destino] = predecesor;
      }

   }

   public int siguienteNodo(Vector<Integer> nodosElegibles) {
      double pesoMinimo = Double.POSITIVE_INFINITY;
      int siguiente = -1;

      for(int i = 0; i < nodosElegibles.size(); ++i) {
         int indiceNodo = (Integer)nodosElegibles.get(i);
         if (pesoMinimo > this.getDistancia(indiceNodo)) {
            siguiente = indiceNodo;
            pesoMinimo = this.getDistancia(indiceNodo);
         }
      }

      return siguiente;
   }
}
