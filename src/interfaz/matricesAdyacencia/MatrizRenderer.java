package interfaz.matricesAdyacencia;

import java.awt.Component;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MatrizRenderer extends JTextField implements TableCellRenderer {
   private static final long serialVersionUID = 1L;

   public MatrizRenderer() {
      this.setOpaque(true);
   }

   public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
      this.setText((String)arg1);
      JTableHeader columnHeader = arg0.getTableHeader();
      this.setForeground(columnHeader.getForeground());
      this.setBackground(columnHeader.getBackground());
      this.setFont(columnHeader.getFont());
      this.setAlignmentX(0.5F);
      this.setBorder(BorderFactory.createRaisedBevelBorder());
      this.setMargin(new Insets(10, 30, 0, 30));
      return this;
   }
}
