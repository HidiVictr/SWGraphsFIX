package interfaz.matricesAdyacencia;

import grafos.Grafo;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class ColumnaPrincipal extends JTable {
   private static final long serialVersionUID = 1L;

   public ColumnaPrincipal(Grafo grafo) {
      final String[][] nombres = new String[grafo.getNodos().size()][1];

      for(int i = 0; i < grafo.getNodos().size(); ++i) {
         nombres[i][0] = grafo.getNodoByIndex(i).getNombre();
      }

      AbstractTableModel modelo = new AbstractTableModel() {
         private static final long serialVersionUID = 1L;

         public int getColumnCount() {
            return 1;
         }

         public int getRowCount() {
            return nombres.length;
         }

         public Object getValueAt(int row, int col) {
            return nombres[row][col];
         }

         public boolean isCellEditable(int row, int col) {
            return false;
         }
      };
      this.setModel(modelo);
      this.setDefaultRenderer(String.class, new MatrizRenderer());
      TableColumn column = null;

      for(int i = 0; i < this.getColumnCount(); ++i) {
         column = this.getColumnModel().getColumn(i);
         column.setPreferredWidth(80);
         column.setMinWidth(80);
      }

      JTableHeader columnHeader = this.getTableHeader();
      this.setForeground(columnHeader.getForeground());
      this.setBackground(columnHeader.getBackground());
      this.setFont(columnHeader.getFont());
      this.setAutoResizeMode(0);
      this.setPreferredScrollableViewportSize(new Dimension(80, 200));
   }
}
