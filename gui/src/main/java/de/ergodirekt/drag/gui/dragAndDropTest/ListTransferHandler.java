package de.ergodirekt.drag.gui.dragAndDropTest;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.Vector;

public class ListTransferHandler extends TransferHandler
{
    public Transferable createTransferable(JComponent c)
    {
        Vector files = new Vector();
        files.add(new File("C:\\Users\\Administrator\\Desktop\\Icon2.png")); //Pfad zu Datei auf Public Laufwerk
        TransferableFile  tf = new TransferableFile(files);
        return tf;
    }

   /*
   public void exportToClipboard(JComponent comp, Clipboard clip, int action)
      throws IllegalStateException
   {
      System.out.println("exportToClipboard");
      super.exportToClipboard(comp,clip,action);
   }

   // Causes the Swing drag support to be initiated.
   public void exportAsDrag(JComponent comp, java.awt.event.InputEvent e, int action)
   {
      System.out.println("exportAsDrag");
      super.exportAsDrag(comp, e, action);
   }

   //Invoked after data has been exported.
   public void exportDone(JComponent source, Transferable data, int action)
   {
      System.out.println("exportDone");
      super.exportDone(source, data, action) ;
   }
   */

} // end class ListTransferHandler extends TransferHandler
