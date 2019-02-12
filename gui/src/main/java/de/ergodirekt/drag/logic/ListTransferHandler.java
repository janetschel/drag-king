package de.ergodirekt.drag.logic;

import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ListTransferHandler extends TransferHandler {
    private ArrayList<File> files;

    public ListTransferHandler(List<String> selectedItems) throws FileNotFoundException {
        StringBuilder filesNotFoundStringBuilder = new StringBuilder("<html>Folgende Dateien konnten nicht gefunden werden:<br/>");
        files = new ArrayList<>();
        File file;
        for (String item : selectedItems) {
            file = new File(item);
            if (file.exists()) {
                files.add(file);
            } else {
                filesNotFoundStringBuilder.append(item).append("<br/>");
            }
        }
        if (!filesNotFoundStringBuilder.toString().equals("<html>Folgende Dateien konnten nicht gefunden werden:\n")) {
            throw new FileNotFoundException(filesNotFoundStringBuilder.toString() + "</html>");
        }
    }

    public Transferable createTransferable(JComponent c) {
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
