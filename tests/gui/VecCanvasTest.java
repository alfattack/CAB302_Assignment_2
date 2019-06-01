package gui;

import graphicsManage.DrawableVector;
import graphicsManage.Ellipse;
import graphicsManage.Point;
import graphicsManage.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VecCanvasTest {

    private VecCanvas canvas;

    @BeforeEach
    void setupCanvas(){
        canvas = VecCanvas.getCanvas();
        canvas.clear();
    }

    @Test
    void getCanvas() {
        assert(canvas == VecCanvas.getCanvas());
    }

    @Test
    void updateCurrentInstruction() {
        DrawableVector p = new Point(0.5, 0.5, Color.RED);
        canvas.updateCurrentInstruction(p);
        assert(p==canvas.getCurrentInstruction());
    }

    @Test
    void undo() {
        DrawableVector p = new Point(0.5, 0.5, Color.RED);
        canvas.addComponent(p);
        canvas.undo();
        assertEquals(0,canvas.getInstructions().size());
    }

    @Test
    void clear() {
        canvas.addComponent(new Rectangle(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        canvas.addComponent(new Ellipse(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        canvas.addComponent(new Point(0.5, 0.5, Color.RED));
        canvas.clear();
        assertEquals(0,canvas.getInstructions().size());
    }

    @Test
    void addComponent() {
        canvas.addComponent(new Rectangle(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        canvas.addComponent(new Ellipse(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        canvas.addComponent(new Point(0.5, 0.5, Color.RED));
        assertEquals(3,canvas.getInstructions().size());
    }

    @Test
    void setInstructions() {
        ArrayList<DrawableVector> instructions = new ArrayList<>();
        instructions.add(new Rectangle(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        instructions.add(new Ellipse(0.5,0.5,0.1,0.6,true,Color.RED,Color.BLUE));
        instructions.add(new Point(0.5, 0.5, Color.RED));
        canvas.setInstructions(instructions);
        assertEquals(instructions,canvas.getInstructions());
    }
}