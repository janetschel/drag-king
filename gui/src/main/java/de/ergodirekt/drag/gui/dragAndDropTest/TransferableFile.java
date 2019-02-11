package de.ergodirekt.drag.gui.dragAndDropTest;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.List;

public class TransferableFile implements Transferable
{
    private List fileList ;

    public TransferableFile(List files)
    {
        fileList = files;
    }

    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException
    {
        if( flavor.equals(DataFlavor.javaFileListFlavor) )
            return fileList ;

        throw new UnsupportedFlavorException(flavor);
    }

    public DataFlavor[] getTransferDataFlavors()
    {
        return new DataFlavor[] {DataFlavor.javaFileListFlavor} ;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(DataFlavor.javaFileListFlavor) ;
    }
}
