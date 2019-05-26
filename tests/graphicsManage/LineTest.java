package graphicsManage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    Line shape;

    @BeforeEach
    void setupLine(){
        shape = new Line(0, 0, 1, 1, Color.RED);
    }

    @Test
    void setCoordinates() {
        shape.setCoordinates(100,100,200,200,200,200);
        assertEquals("LINE 0.500000 0.500000 1.000000 1.000000", shape.toString());
    }

    @Test
    void getCommand() {
        assertEquals(VectorCommand.LINE,shape.getCommand());
    }

    @Test
    void returnCopy() {
        DrawableVector line2 = shape.returnCopy();
        assertEquals(line2.getColor(), line2.getColor());
    }

    @Test
    void isFilled() {
        assertEquals(shape.isFilled(),false);
    }

    @Test
    void getColor(){
        assertEquals(shape.getColor(),Color.RED);
    }

    @Test
    void toString1() {
        assertEquals("LINE 0.000000 0.000000 1.000000 1.000000",shape.toString());
    }
}