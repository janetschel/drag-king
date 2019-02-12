package de.ergodirekt.drag.logic;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListTransferHandler extends TransferHandler {
    private List<String> selectedItems;

    public ListTransferHandler(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public Transferable createTransferable(JComponent c) {
        List<File> files = new ArrayList<>();
        for (String item : selectedItems) {
            files.add(new File(item));
        }
        return new TransferableFile(files);
    }

    public int getSourceActions(JComponent c) {
        return COPY;
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
      super.exportDone(source, data, action);
      System.exit(0);
   }*/


}
