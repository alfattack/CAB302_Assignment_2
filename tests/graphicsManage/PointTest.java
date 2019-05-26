package graphicsManage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    Point point;

    @BeforeEach
    void setupPoint(){
        point = new Point(0.5,0.5,Color.RED);

    }

    @Test
    void getCommand() {
        assertEquals(VectorCommand.PLOT,point.getCommand());
    }

    @Test
    void returnCopy() {
        DrawableVector p2 = point.returnCopy();
        assertEquals(VectorCommand.PLOT,p2.getCommand());
    }

    @Test
    void isFilled() {
        assertFalse(point.isFilled());
    }

    @Test
    void getFillColor() {
        assertNull(point.getFillColor());
    }

    @Test
    void toString1() {
        assertEquals("PLOT 0.500000 0.500000",point.toString());
    }

    @Test
    void setCoordinates(){
        point.setCoordinates(100,200,100,200,300,300);
        assertEquals("PLOT 0.333333 0.666667",point.toString());
    }

    @Test
    void setColor(){
        point.setColor(Color.PINK);
        assertEquals(Color.PINK,point.getColor());
    }
}