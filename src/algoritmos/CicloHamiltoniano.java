package algoritmos;

import grafos.Arista;
import grafos.Grafo;
import grafos.GrafoMultiColor;
import grafos.Nodo;
import interfaz.PanelGrafos;
import interfaz.matricesAdyacencia.MatrizTabla;
import javax.swing.JTextArea;

public class CicloHamiltoniano extends Algoritmo {
   public CicloHamiltoniano(PanelGrafos p, JTextArea area, Grafo g) {
      this.panel = p;
      this.textoInformacion = area;
      this.grafoOriginal = g;
   }

   public void run() {
   }

   protected void crearGrafoResultadoYmostrarInformacionResultado(int tipoCiclo, boolean hayCiclo) {
      if (hayCiclo) {
         MatrizTabla matrizGrafoPintar = new MatrizTabla(this.grafoOriginal);
         GrafoMultiColor grafoPintar = new GrafoMultiColor();

         for(int i = 0; i < this.grafoOriginal.getNodos().size(); ++i) {
            Nodo n = this.grafoOriginal.getNodoByIndex(i);
            grafoPintar.insertarNodo(n);
         }

         double pesoCiclo = 0.0D;

         for(int i = 0; i < this.grafoOriginal.getNodos().size(); ++i) {
            for(int j = i; j < this.grafoOriginal.getNodos().size(); ++j) {
               Double peso = (Double)matrizGrafoPintar.getValueAt(i, j);
               if (peso != 0.0D) {
                  Arista a = new Arista(i, j, 0);
                  a.setPeso(peso);
                  grafoPintar.insertarArista(a);
                  if (this.grafoResultado.existeArista(a)) {
                     grafoPintar.deAzul.add(a);
                     pesoCiclo += peso;
                  }
               }
            }
         }

         String tipo = "";
         if (tipoCiclo == 10) {
            tipo = "'bajo'";
         } else {
            tipo = "mínimo";
         }

         this.textoInformacion.append("\nEl peso total del ciclo hamiltoniano de " + tipo + " peso es: " + pesoCiclo + "\n");
         this.panel.setGrafo(grafoPintar);
      } else {
         this.textoInformacion.setText("El algoritmo de Ciclos hamiltonianos ha finalizado.\n\nRESULTADO:\n");
         if (tipoCiclo == 10) {
            this.textoInformacion.append("\nNo se ha podido obtener ning�n ciclo hamiltoniano.\n");
         } else {
            this.textoInformacion.append("\nSe ha ejecutado el algoritmo para encontrar el ciclo hamiltoniano de m�nimo peso por fuerza bruta.\n");
            this.textoInformacion.append("\nNo existe ning�n ciclo hamiltoniano, luego el Grafo no es hamiltoniano.\n");
         }

         this.panel.setGrafo(this.grafoOriginal);
      }

   }

   protected void mostrarInformacionInicial(int tipoCiclo, int algoritmo) {
      String tipo = "";
      if (tipoCiclo == 10) {
         tipo = "'bajo'";
      } else {
         tipo = "mínimo";
      }

      this.textoInformacion.setText("El algoritmo de Ciclos hamiltonianos ha finalizado.\n\nRESULTADO:\n");
      if (algoritmo == 0) {
         this.textoInformacion.append("\nSe ha ejecutado un algoritmo basado en un �rbol generador del grafo.\n");
         this.textoInformacion.append("\nComo el grafo es completo y cumple la desigualdad triangular, se puede aplicar este algoritmo para obtener un ciclo hamiltoniano de 'bajo' peso, es decir, un ciclo hamiltoniano cuyo peso sea menor que el doble del ciclo hamiltoniano de peso m�nimo.\n");
      } else if (algoritmo == 1) {
         this.textoInformacion.append("\nSe ha ejecutado un algoritmo voraz.\n");
         this.textoInformacion.append("\nComo el grafo es completo y cumple la desigualdad triangular, se puede aplicar el algoritmo voraz para obtener ciclo hamiltoniano de 'bajo' peso, es decir, un ciclo hamiltoniano cuyo peso sea menor que el doble del ciclo hamiltoniano de peso m�nimo.\n");
      } else {
         this.textoInformacion.append("\nSe ha ejecutado el algoritmo para encontrar el ciclo hamiltoniano de m�nimo peso por fuerza bruta.\n");
      }

      this.textoInformacion.append("\nEl ciclo hamiltoniano de " + tipo + " peso se muestra en el panel de grafos dibujado en color azul sobre el grafo original.\n");
   }
}
