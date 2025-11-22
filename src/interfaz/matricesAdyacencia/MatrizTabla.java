package interfaz.matricesAdyacencia;

import grafos.Arista;
import grafos.Grafo;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class MatrizTabla extends JTable {
   private static final long serialVersionUID = 1L;
   private Object[][] cuerpoMatriz;
   private String[] listaNodos;

   public MatrizTabla(Grafo G) {
      this.cuerpoMatriz = new Double[G.getNodos().size()][G.getNodos().size()];
      this.listaNodos = new String[G.getNodos().size()];

      int i;
      int j;
      for(i = 0; i < G.getNodos().size(); ++i) {
         this.listaNodos[i] = G.getNodoByIndex(i).getNombre();

         for(j = 0; j < G.getNodos().size(); ++j) {
            Arista a = G.getArcoByNodosIndex(i, j);
            if (a == null) {
               this.cuerpoMatriz[i][j] = new Double(0.0D);
            } else {
               this.cuerpoMatriz[i][j] = new Double(a.getPeso());
            }
         }
      }

      if (G.getTipo() == 0) {
         for(i = 0; i < G.getNodos().size(); ++i) {
            for(j = 0; j < G.getNodos().size(); ++j) {
               Double valor1 = (Double)this.cuerpoMatriz[i][j];
               Double valor2 = (Double)this.cuerpoMatriz[j][i];
               if (Double.compare(valor1, 0.0D) != 0) {
                  this.cuerpoMatriz[j][i] = valor1;
               }

               if (Double.compare(valor2, 0.0D) != 0) {
                  this.cuerpoMatriz[i][j] = valor2;
               }
            }
         }
      }

      this.setModel(new MatrizTablaModel(this.listaNodos, this.cuerpoMatriz, G.getTipo()));
      this.setDefaultRenderer(String.class, new MatrizRenderer());
      this.getTableHeader().setVisible(true);
      this.getTableHeader().setReorderingAllowed(false);
      TableColumn column = null;

      for(j = 0; j < this.getColumnCount(); ++j) {
         column = this.getColumnModel().getColumn(j);
         column.setWidth(65);
         column.setMinWidth(65);
         column.setResizable(false);
      }

      this.setAutoResizeMode(0);
   }
}
