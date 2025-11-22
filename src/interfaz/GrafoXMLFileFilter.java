package interfaz;

import Util.Util;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GrafoXMLFileFilter extends FileFilter {
   public boolean accept(File arg0) {
      if (arg0.isDirectory()) {
         return true;
      } else {
         String extension = Util.getExtension(arg0);
         return extension != null && extension.equals("xml");
      }
   }

   public String getDescription() {
      return "Grafos XML";
   }
}
