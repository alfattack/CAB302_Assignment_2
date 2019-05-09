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
    private VecCanvas canvas;
    private JMenu menu;
    private VecFileChooser chooser;

    VecPainterMenuBar(){

        chooser = new VecFileChooser();

        menu = new JMenu("File");
        canvas = VecCanvas.getCanvas();

        loadFile = new JMenuItem("Load");
        loadFile.addActionListener(new MenuListener());
        menu.add(loadFile);

        saveFile = new JMenuItem("Save");
        menu.add(saveFile);

        clear = new JMenuItem("Clear");
        menu.add(clear);

        add(menu);
    }

    private class MenuListener implements ActionListener {


        private void loadFile(){
            if (!canvas.getInstructions().isEmpty()){
                // TO DO: Save prompt
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

        @Override
        public void actionPerformed(ActionEvent e) {

            Component source = (Component) e.getSource();
            if (source == loadFile) {
                loadFile();
            }
        }
    }

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
