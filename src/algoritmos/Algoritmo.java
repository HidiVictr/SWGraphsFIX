package algoritmos;

import grafos.Grafo;
import interfaz.PanelGrafos;
import javax.swing.JTextArea;

public abstract class Algoritmo implements Runnable {
   protected Grafo grafoOriginal;
   protected Grafo grafoResultado;
   protected PanelGrafos panel;
   protected JTextArea textoInformacion;
   protected Thread t = null;
   protected int estado;
   protected static boolean terminado;
   protected static boolean suspendido;
   protected boolean iniciado;
   protected int modo;
   public static int porPasos = 0;
   public static int continuo = 1;
   public static int oculto = 2;

   public Algoritmo() {
      this.t = null;
      terminado = false;
      suspendido = false;
      this.iniciado = false;
      this.estado = 0;
   }

   public void step() {
      if (this.t == null) {
         this.t = new Thread(this);
         this.t.start();
      }

   }

   public void join() throws InterruptedException {
      if (this.t != null) {
         this.t.join();
      }

   }

   public boolean enSuspension() {
      return suspendido;
   }

   public boolean terminado() {
      return terminado;
   }

   public boolean enProceso() {
      return this.iniciado;
   }

   public void terminar() {
      terminado = true;
      this.iniciado = false;
   }

   public void suspender() {
      suspendido = true;
   }

   public void reactivar() {
      suspendido = false;
   }

   public void iniciar() {
      if (!this.terminado()) {
         this.iniciado = true;
      }

   }

   public void setModo(int modoEjecucion) {
      this.modo = modoEjecucion;
   }

   public int getEstado() {
      return this.estado;
   }

   public int getModo() {
      return this.modo;
   }

   public void noAcabado() {
      terminado = false;
   }
}
