package interfaz;

import Util.Util;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GrafoIMGFileFilter extends FileFilter {
   public boolean accept(File arg0) {
      if (arg0.isDirectory()) {
         return true;
      } else {
         String extension = Util.getExtension(arg0);
         return extension != null && extension.equals("gif") | extension.equals("bmp") | extension.equals("jpg") | extension.equals("png");
      }
   }

   public String getDescription() {
      return "Im�genes: .jpg, .png, .gif, .bmp";
   }
}
