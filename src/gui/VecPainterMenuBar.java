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

/**
 * Menu bar which contains file and edit menus. Handles loading and saving commands, as well as undo and clear.
 */
public class VecPainterMenuBar extends JMenuBar {

    private JMenuItem loadFile;
    private JMenuItem saveFile;
    private JMenuItem exportFile;
    private JMenuItem clear;
    private JMenuItem undo;
    private VecCanvas canvas;
    private JMenu file;
    private JMenu edit;
    private VecFileChooser chooser;
    private MenuListener menuListener;

    /**
     * Constructs a menu bar with a file and edit menus.
     */
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
     * Creates file menu.
     * @return menu for 'file'
     */
    JMenu createFileMenu(){
        JMenu var = new JMenu("File");

        loadFile = new JMenuItem("Load");
        loadFile.addActionListener(menuListener);
        var.add(loadFile);

        saveFile = new JMenuItem("Save");
        saveFile.addActionListener(menuListener);
        var.add(saveFile);

        exportFile = new JMenuItem("Export to .png");
        exportFile.addActionListener(menuListener);
        var.add(exportFile);

        return var;
    }

    /**
     * Creates edit menu.
     * @return menu for 'edit'
     */
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

    /**
     * Private Listener class which implements ActionListener.
     */
    private class MenuListener implements ActionListener {

        /**
         * Interaction which handles file loading.
         */
        private void loadFileInteraction(){

            chooser.setExtension(".vec");

            // If the canvas is not empty, as user if they wish to save first.
            if (!canvas.getInstructions().isEmpty()){

                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save your work first?");
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

            int returnVal = chooser.showOpenDialog(null);

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
         * Interaction which handles file saving. Returns user interaction.
         * @return
         */
        private int saveFileInteraction(){

            chooser.setExtension(".vec");

            int resp = chooser.showSaveDialog(null);

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
         * Interaction to export file to png.
         */
        private void exportFileInteraction(){
            chooser.setExtension(".png");
            int resp = chooser.showSaveDialog(null);


            if (resp == chooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getPath();

                if (!path.endsWith(".png")){
                    StringBuilder sb = new StringBuilder();
                    sb.append(path);
                    sb.append(".png");
                    path = sb.toString();
                }
                try{
                    canvas.saveCanvasToPng(path);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,String.format("Cannot save file: %s.\n%s",file.getName(),e.getMessage()),"Title",1);
                }
            }
        }

        /**
         * Responds to events relating to menu selection.
         * @param e event object.
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

            if (source == exportFile){
                exportFileInteraction();
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

        private String extension = ".vec";

        void setExtension(String extension){
            this.extension=extension;
        }

        VecFileChooser(){
            setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) return true;
                    else{
                        String name = f.getName().toLowerCase();
                        return name.endsWith(extension);
                    }
                }

                @Override
                public String getDescription() {
                    return String.format("%s files",extension);
                }
            });
        }


    }
}
