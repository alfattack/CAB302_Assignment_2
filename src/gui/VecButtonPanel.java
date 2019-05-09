package gui;

import graphicsManage.Elipses;
import graphicsManage.Line;
import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VecButtonPanel extends JPanel {

    private VecCanvas canvas;
    private JButton rectangle;
    private JButton elipses;
    private JButton line;

    VecButtonPanel(){
        setLayout(new FlowLayout());
        canvas = VecCanvas.getCanvas();
        rectangle = new JButton("Rectangle");
        rectangle.setBackground(Color.RED);
        rectangle.addActionListener(new ButtonCommandListener());
        add(rectangle);


        elipses = new JButton(new ImageIcon("src/gui/icons/elipsesBlank.png"));
        elipses.addActionListener(new ButtonCommandListener());
        add(elipses);

        line = new JButton("line");
        line.setBackground(Color.GREEN);
        line.addActionListener(new ButtonCommandListener());
        add(line);
    }


    private class ButtonCommandListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Component source = (Component) e.getSource();

            if (source == rectangle){
                canvas.updateCurrentInstruction(new Rectangle(Color.BLACK,Color.GREEN,true));
            }
            if (source == elipses){
                canvas.updateCurrentInstruction(new Elipses(Color.BLACK,Color.RED,true));
            }
            if (source == line){
                canvas.updateCurrentInstruction(new Line(Color.BLACK));
            }
        }
    }
}
