package algoritmos;

import grafos.Arista;
import grafos.Grafo;
import grafos.Nodo;
import interfaz.PanelGrafos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;
import javax.swing.JTextArea;

public class Fleury extends Algoritmo {
   private Grafo grafoTrabajo;
   private Grafo camino;
   private PanelGrafos resultado;
   private PanelGrafos panel;
   private JTextArea infoText;
   private Nodo nCamino;
   private Nodo n;
   private int indexSiguiente;
   private int indexNodo;
   private Grafo auxMantenido;
   private Grafo aux;

   public Fleury(PanelGrafos panelGrafos, PanelGrafos panelResultados, JTextArea informacion) {
      this.panel = panelGrafos;
      this.resultado = panelResultados;
      this.infoText = informacion;
      this.grafoTrabajo = this.panel.getGrafo();
   }

   public void run() {
      while(!this.terminado() && !this.enSuspension()) {
         switch(this.estado) {
         case 0:
            this.iniciado = true;
            this.aux = this.grafoTrabajo.clonar();
            this.auxMantenido = this.grafoTrabajo.clonar();
            this.camino = new Grafo();
            this.indexNodo = 0;
            this.indexSiguiente = -1;
            this.n = this.aux.getNodoByIndex(this.indexNodo);
            this.nCamino = new Nodo(this.n.getNombre(), new Point(10, 20));
            this.camino.insertarNodo(this.nCamino);
            this.estado = 1;
            break;
         case 1:
            if (this.auxMantenido.getAristas().size() > 0) {
               this.buscarSiguienteArista();
               this.panel.setGrafo(this.auxMantenido);
               this.resultado.setGrafo(this.camino);
               if (10 + 60 * (this.camino.getNodos().size() - 1) > 450) {
                  this.resultado.setPreferredSize(new Dimension(10 + 60 * (this.camino.getNodos().size() - 1) + 20, 50));
                  this.resultado.revalidate();
               }

               if (this.modo == Algoritmo.porPasos) {
                  this.resultado.repaint();
                  this.suspender();
                  this.infoText.setText("A�adiendo arista desde " + this.camino.getNodoByIndex(this.camino.getNodos().size() - 2).getNombre() + " hasta " + this.camino.getNodoByIndex(this.camino.getNodos().size() - 1).getNombre());
                  this.panel.repaint();
               }
               break;
            }

            this.estado = 2;
            break;
         case 2:
            this.panel.setGrafo(this.grafoTrabajo);
            this.panel.repaint();
            this.infoText.setText("Algoritmo de Fleury terminado");
            this.resultado.repaint();
            this.terminar();
         }
      }

      this.t = null;
   }

   private void buscarSiguienteArista() {
      Vector<Integer> verticesAdyacentes = this.auxMantenido.indexNodosAdyacentes(this.indexNodo);
      Vector<Integer> nodoInicial = new Vector();
      Vector nodoFinal = new Vector();

      while(verticesAdyacentes.size() > 0) {
         this.indexSiguiente = (Integer)verticesAdyacentes.get(0);
         Arista arista = this.aux.getAristaByNodosIndex(this.indexNodo, this.indexSiguiente);
         this.aux.borrarAristabyNodosIndex(this.indexNodo, this.indexSiguiente);
         Grafo segur1 = this.aux.clonar();
         Grafo segur2 = this.auxMantenido.clonar();
         if (segur1.cardComponentesConexas() == segur2.cardComponentesConexas() | verticesAdyacentes.size() == 1) {
            if (this.modo == Algoritmo.porPasos) {
               nodoInicial.add(new Integer(this.indexNodo));
               nodoFinal.add(new Integer(this.indexSiguiente));
            }

            this.indexNodo = this.indexSiguiente;
            this.n = this.aux.getNodoByIndex(this.indexSiguiente);
            this.nCamino = new Nodo(this.n.getNombre(), new Point(10 + 60 * this.camino.getNodos().size(), 20));
            this.camino.insertarNodo(this.nCamino);
            Arista a = new Arista(this.camino.getNodos().size() - 2, this.camino.getNodos().size() - 1, 0);
            a.setPeso(arista.getPeso());
            this.camino.insertarArista(a);
            this.auxMantenido = this.aux.clonar();
            break;
         }

         this.aux = this.auxMantenido.clonar();
         verticesAdyacentes.remove(0);
      }

      if (this.modo == Algoritmo.porPasos) {
         this.grafoTrabajo.pintarNodos(this.panel.getGraphics(), nodoInicial, Color.BLUE);
         this.grafoTrabajo.pintarNodos(this.panel.getGraphics(), nodoFinal, Color.RED);
      }

   }
}
