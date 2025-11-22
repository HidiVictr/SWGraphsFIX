package Util;

import grafos.Grafo;
import interfaz.matricesAdyacencia.MatrizTabla;

public class Matriz {
   public double[][] A;
   private int m;
   private int n;

   public Matriz(int m, int n) {
      this.m = m;
      this.n = n;
      this.A = new double[m][n];
   }

   public Matriz(int m, int n, double s) {
      this.m = m;
      this.n = n;
      this.A = new double[m][n];

      for(int i = 0; i < m; ++i) {
         for(int j = 0; j < n; ++j) {
            this.A[i][j] = s;
         }
      }

   }

   public Matriz(Grafo g) {
      this.A = new double[g.getNodos().size()][g.getNodos().size()];
      this.m = g.getNodos().size();
      this.n = g.getNodos().size();
      MatrizTabla matriz = new MatrizTabla(g);

      for(int i = 0; i < g.getNodos().size(); ++i) {
         for(int j = 0; j < g.getNodos().size(); ++j) {
            this.A[i][j] = (Double)matriz.getValueAt(i, j);
         }
      }

   }

   public double[][] getArray() {
      return this.A;
   }

   public Matriz marcarAristasCapacidadesResiduos() {
      Matriz X = new Matriz(this.n, this.m);
      double[][] C = X.getArray();

      for(int i = 0; i < this.m; ++i) {
         for(int j = 0; j < this.n; ++j) {
            if (this.A[i][j] == 1.0D) {
               C[j][i] = 2.0D;
            }
         }
      }

      return X;
   }

   private void checkMatrizDimensions(Matriz B) {
      if (B.m != this.m || B.n != this.n) {
         throw new IllegalArgumentException("Matriz dimensions must agree.");
      }
   }

   public Matriz suma(Matriz B) {
      this.checkMatrizDimensions(B);
      Matriz X = new Matriz(this.m, this.n);
      double[][] C = X.getArray();

      for(int i = 0; i < this.m; ++i) {
         for(int j = 0; j < this.n; ++j) {
            C[i][j] = this.A[i][j] + B.A[i][j];
         }
      }

      return X;
   }

   public Matriz adyacente() {
      Matriz X = new Matriz(this.m, this.n);
      double[][] C = X.getArray();

      for(int i = 0; i < this.m; ++i) {
         for(int j = 0; j < this.n; ++j) {
            if (this.A[i][j] != 0.0D) {
               C[i][j] = 1.0D;
            } else {
               C[i][j] = 0.0D;
            }
         }
      }

      return X;
   }

   public Matriz productoElementoAElemento(Matriz B) {
      this.checkMatrizDimensions(B);
      Matriz X = new Matriz(this.m, this.n);
      double[][] C = X.getArray();

      for(int i = 0; i < this.m; ++i) {
         for(int j = 0; j < this.n; ++j) {
            C[i][j] = this.A[i][j] * B.A[j][i];
         }
      }

      return X;
   }

   public Matriz traspuesta() {
      Matriz X = new Matriz(this.n, this.m);
      double[][] C = X.getArray();

      for(int i = 0; i < this.m; ++i) {
         for(int j = 0; j < this.n; ++j) {
            C[j][i] = this.A[i][j];
         }
      }

      return X;
   }

   public Matriz eliminarFila(int x) {
      Matriz X = new Matriz(this.m - 1, this.n);
      double[][] C = X.getArray();
      boolean restar = false;

      for(int i = 0; i < this.m; ++i) {
         if (i != x) {
            for(int j = 0; j < this.n; ++j) {
               if (restar) {
                  C[i - 1][j] = this.A[i][j];
               } else {
                  C[i][j] = this.A[i][j];
               }
            }
         } else {
            restar = true;
         }
      }

      return X;
   }
}
