package de.ergodirekt.drag.gui.dragAndDropTest;

import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DragOutsideDemo extends JFrame implements DropTargetListener
{
    private JLabel label;

    public DragOutsideDemo()
    {
        initCenter();
        initFrame();
    }

    private void initCenter()
    {
        ImageIcon icon = new ImageIcon("C:\\Users\\Administrator\\Desktop\\Icon2.png"); //Icon der Datei auf dem Public Laufwerk
        label = new JLabel(icon);
        label.setTransferHandler(new ListTransferHandler());
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mEvt) { //Notwendig, da Drag and Drop normalerweise mit Label nich möglich
                JComponent component = (JComponent) mEvt.getSource();
                TransferHandler tHandler = component.getTransferHandler();
                tHandler.exportAsDrag(component, mEvt, TransferHandler.COPY);
            }
        });
        add(label);
    }

    private void initFrame()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
        setTitle("JListDragDemo");
        setSize(300,400);
        setLocation(47,47);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new DragOutsideDemo();
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {

    }

    @Override
    public void dragExit(DropTargetEvent dte) {

    }

    @Override
    public void drop(DropTargetDropEvent dtde) {

    }
} // end class
