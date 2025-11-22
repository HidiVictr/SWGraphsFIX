package interfaz;

import javax.swing.Icon;
import javax.swing.JButton;

public class BotonImagen extends JButton {
   private static final long serialVersionUID = 1L;

   public BotonImagen(Icon arg0, String help, String text) {
      super(arg0);
      this.setToolTipText(help);
      this.setText(text);
      this.setVisible(true);
      this.setBorderPainted(false);
      this.setVerticalTextPosition(3);
      this.setHorizontalTextPosition(0);
   }
}
