package gui;

import graphicsManage.Elipses;
import graphicsManage.Line;
import graphicsManage.Point;
import graphicsManage.Polygon;
import graphicsManage.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button panel for shapes that can be drawn.
 */
class VecCommandButtonPanel extends JPanel implements ActionListener{

    private VecCanvas canvas;
    private JButton rectangle;
    private JButton rectangleFill;
    private JButton elipses;
    private JButton elipsesFill;
    private JButton line;
    private JButton polygon;
    private JButton polygonFill;
    private JButton point;

    private Color fillColor = Color.BLACK;
    private Color color = Color.BLACK;

    /**
     * Constructor of panel. Create all the buttons with related icons.
     */
    VecCommandButtonPanel(){
        setLayout(new FlowLayout());
        canvas = VecCanvas.getCanvas();

        rectangle = createButton(new ImageIcon("src/gui/icons/rectangleBlank.png"));
        rectangleFill = createButton(new ImageIcon("src/gui/icons/rectangleFill.png"));
        elipses = createButton(new ImageIcon("src/gui/icons/elipsesBlank.png"));
        elipsesFill = createButton(new ImageIcon("src/gui/icons/elipsesFill.png"));
        line = createButton(new ImageIcon("src/gui/icons/line.png"));
        polygon=createButton(new ImageIcon("src/gui/icons/polygon.png"));
        polygonFill=createButton(new ImageIcon("src/gui/icons/polygonFill.png"));
        point=createButton(new ImageIcon("src/gui/icons/point.png"));


        add(rectangle);
        add(rectangleFill);
        add(elipses);
        add(elipsesFill);
        add(polygon);
        add(polygonFill);
        add(line);
        add(point);
    }

    /**
     * Creates button with icon.
     * @param icon icon for button.
     * @return button.
     */
    private JButton createButton(ImageIcon icon){
        JButton var = new JButton(icon);
        var.setBackground(Color.WHITE);
        var.addActionListener(this);
        return var;
    }

    /**
     * Sets the pen colour to be used for shape instructions.
     * @param color color for pen to use.
     */
    public void setPenColor(Color color){
        this.color=color;
    }

    /**
     * Gets the current pen colour used for shape instructions.
     * @return current pen colour.
     */
    public Color getPenColor(){
        return color;
    }

    /**
     * Gets the current fill colour used for shape instructions.
     * @return current fill colour.
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the fill colour to be used for shape instructions.
     * @param color color for fill to use.
     */
    public void setFillColor(Color color){
        this.fillColor = color;
    }


    /**
     * Action performed. Updates current instruction of canvas based on which button was pressed and colours in memory.
     * @param e event object.
     */
    public void actionPerformed(ActionEvent e) {
        Component source = (Component) e.getSource();

        if (source == rectangle){
            canvas.updateCurrentInstruction(new Rectangle(color, fillColor, false));
        }
        if (source == rectangleFill) {
            canvas.updateCurrentInstruction(new Rectangle(color, fillColor, true));
        }
        if (source == elipses){
            canvas.updateCurrentInstruction(new Elipses(color, fillColor,false));
        }
        if (source == elipsesFill){
            canvas.updateCurrentInstruction(new Elipses(color, fillColor,true));
        }
        if (source == line){
            canvas.updateCurrentInstruction(new Line(color));
        }
        if (source == point){
            canvas.updateCurrentInstruction(new Point(color));
        }
        if (source == polygon){
            canvas.updateCurrentInstruction(new Polygon(color, fillColor,false));
        }
        if (source == polygonFill){
            canvas.updateCurrentInstruction(new Polygon(color, fillColor,true));
        }
    }
}
