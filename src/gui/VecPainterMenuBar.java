package gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import graphicsManage.DrawableVector;
import graphicsManage.VecFileManager;
import graphicsManage.VecFileException;

public class VecPainterMenuBar extends JMenuBar {

    private JMenuItem loadFile;
    private JMenuItem saveFile;
    private JMenuItem clear;
    private JMenuItem undo;
    private VecCanvas canvas;
    private JMenu file;
    private JMenu edit;
    private VecFileChooser chooser;
    private MenuListener menuListener;

    VecPainterMenuBar(){

        chooser = new VecFileChooser();
        menuListener = new MenuListener();

        file = createFileMenu();
        edit = createEditMenu();
        canvas = VecCanvas.getCanvas();

        add(file);
        add(edit);
    }

    /**
     *
     * @return
     */
    JMenu createFileMenu(){
        JMenu var = new JMenu("File");

        loadFile = new JMenuItem("Load");
        loadFile.addActionListener(menuListener);
        var.add(loadFile);

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(menuListener);
        var.add(saveFile);

        return var;
    }

    JMenu createEditMenu(){
        JMenu var = new JMenu("Edit");
        canvas = VecCanvas.getCanvas();

        clear = new JMenuItem("Clear");
        clear.addActionListener(menuListener);
        var.add(clear);

        undo = new JMenuItem("Undo");
        undo.addActionListener(menuListener);
        var.add(undo);

        return var;
    }

    private class MenuListener implements ActionListener {

        /**
         * Interaction which handles file loading.
         */
        private void loadFileInteraction(){

            // If the canvas is not empty, as user if they wish to save first.
            if (!canvas.getInstructions().isEmpty()){

                int dialogResult = JOptionPane.showConfirmDialog (VecPainterMenuBar.this, "Would you like to save your work first?");
                if (dialogResult == JOptionPane.OK_OPTION){

                    int resp = saveFileInteraction();

                    if (resp==VecFileChooser.CANCEL_OPTION){
                        return;
                    }
                }

                if (dialogResult == JOptionPane.CANCEL_OPTION){
                    return;
                }
            }

            int returnVal = chooser.showOpenDialog(VecPainterMenuBar.this);

            if (returnVal==VecFileChooser.APPROVE_OPTION){
                File file = chooser.getSelectedFile();
                int dialogResult = JOptionPane.showConfirmDialog (chooser, String.format("Open %s?",file.getName()));

                if (dialogResult==JOptionPane.OK_OPTION){

                    try{
                        ArrayList<DrawableVector> instructions = VecFileManager.readFromFile(file.getPath());
                        canvas.setInstructions(instructions);
                        canvas.repaint();
                    }
                    catch(VecFileException ex){
                        JOptionPane.showMessageDialog(null,String.format("Cannot read file: %s.\n%s",file.getName(),ex.getMessage()),"Title",1);
                    }
                }
            }
        }


        /**
         * Interaction which handles file saving.
         * @return
         */
        private int saveFileInteraction(){
            int resp = chooser.showSaveDialog(VecPainterMenuBar.this);

            if (resp==VecFileChooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getPath();

                if (!path.endsWith(".vec")){
                    StringBuilder sb = new StringBuilder();
                    sb.append(path);
                    sb.append(".vec");
                    path = sb.toString();
                }

                try{
                    VecFileManager.writeToFile(path);

                    JOptionPane.showMessageDialog(null, String.format("File saved to: %s",path));
                    return VecFileChooser.APPROVE_OPTION;

                }
                catch(VecFileException ex){
                    JOptionPane.showMessageDialog(null,String.format("Cannot save file: %s.\n%s",file.getName(),ex.getMessage()),"Title",1);
                }
            }
            return VecFileChooser.CANCEL_OPTION;
        }

        /**
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Component source = (Component) e.getSource();
            if (source == loadFile) {
                loadFileInteraction();
            }
            if (source == clear){
                canvas.clear();
            }

            if (source == saveFile){
                saveFileInteraction();
            }

            if (source == undo ){
                canvas.undo();
                canvas.repaint();
            }
        }
    }

    /**
     * Extends JFileChooser, only shows files .vec extension by default.
     */
    private class VecFileChooser extends JFileChooser{

        VecFileChooser(){
            setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) return true;
                    else{
                        String name = f.getName().toLowerCase();
                        return name.endsWith(".vec");
                    }
                }

                @Override
                public String getDescription() {
                    return ".vec files";
                }
            });
        }


    }
}
