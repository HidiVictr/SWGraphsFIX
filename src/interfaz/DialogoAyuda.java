package interfaz;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DialogoAyuda extends JDialog {
   private static final long serialVersionUID = 1L;
   private JTextPane textoInformacion;
   private JScrollPane areaScrollPane;

   public DialogoAyuda(int referencia) throws HeadlessException {
      this.setTitle("AYUDA");
      this.setModal(true);
      Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension ventana = this.getSize();
      this.setLocation((pantalla.width - ventana.width) / 2 - 275, (pantalla.height - ventana.height) / 2 - 315);
      this.construirIGU(referencia);
   }

   private void construirIGU(int referencia) {
      this.textoInformacion = new JTextPane();
      this.textoInformacion.setEditable(false);
      this.textoInformacion.setContentType("text/html");
      this.areaScrollPane = new JScrollPane(this.textoInformacion);
      this.areaScrollPane.setPreferredSize(new Dimension(500, 500));
      this.areaScrollPane.setVisible(true);
      this.areaScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""), BorderFactory.createEmptyBorder(5, 5, 5, 5)), this.areaScrollPane.getBorder()));
      this.mensajeAyuda(referencia);
      BotonImagen botonsalir = new BotonImagen(new ImageIcon(this.getClass().getResource("/interfaz/img/home.png")), "Cierra el dialogo y vuelve a la ventana anterior", "");
      botonsalir.setPreferredSize(new Dimension(125, 80));
      botonsalir.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            DialogoAyuda.this.botonsalirActionPerformed(evt);
         }
      });
      this.setContentPane(new JPanel());
      this.getContentPane().add(this.areaScrollPane);
      this.getContentPane().add(botonsalir);
      this.setIconImage((new ImageIcon(this.getClass().getResource("/interfaz/img/k.png"))).getImage());
      this.setSize(550, 630);
      this.setResizable(false);
      this.setVisible(true);
      this.pack();
   }

   private void mensajeAyuda(int referencia) {
      switch(referencia) {
      case 1:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/edmonds2.html"));
         } catch (IOException var14) {
            var14.printStackTrace();
         }
         break;
      case 2:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/cartero.html"));
         } catch (IOException var13) {
            var13.printStackTrace();
         }
         break;
      case 3:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/dijkstra.html"));
         } catch (IOException var12) {
            var12.printStackTrace();
         }
         break;
      case 4:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/bfs.html"));
         } catch (IOException var11) {
            var11.printStackTrace();
         }
         break;
      case 5:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/dfs.html"));
         } catch (IOException var10) {
            var10.printStackTrace();
         }
         break;
      case 6:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/hierholzer.html"));
         } catch (IOException var9) {
            var9.printStackTrace();
         }
         break;
      case 7:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/etiquetaje.html"));
         } catch (IOException var8) {
            var8.printStackTrace();
         }
         break;
      case 8:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/kruskal.html"));
         } catch (IOException var7) {
            var7.printStackTrace();
         }
         break;
      case 9:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/conectividad.html"));
         } catch (IOException var5) {
            var5.printStackTrace();
         }
         break;
      case 10:
      case 14:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/hamiltoniano.html"));
         } catch (IOException var6) {
            var6.printStackTrace();
         }
         break;
      case 11:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/manual.html"));
         } catch (IOException var17) {
            var17.printStackTrace();
         }
         break;
      case 12:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/matriz.html"));
         } catch (IOException var15) {
            var15.printStackTrace();
         }
         break;
      case 13:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/acercade.html"));
         } catch (IOException var3) {
            var3.printStackTrace();
         }
         break;
      case 15:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/orientabilidad.html"));
         } catch (IOException var4) {
            var4.printStackTrace();
         }
         break;
      case 16:
         try {
            this.textoInformacion.setPage(this.getClass().getClassLoader().getResource("ayuda/generalidades.html"));
         } catch (IOException var16) {
            var16.printStackTrace();
         }
      }

      this.textoInformacion.setCaretPosition(0);
   }

   private void botonsalirActionPerformed(ActionEvent evt) {
      this.processWindowEvent(new WindowEvent(this, 201));
   }
}
