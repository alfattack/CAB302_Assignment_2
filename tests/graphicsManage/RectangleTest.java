package graphicsManage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private Rectangle shape;

    @BeforeEach
    void setupElipses(){
        shape = new Rectangle(0.5, 0.5, 0.75, 0.66, true, Color.BLUE, Color.RED);
    }

    @Test
    void setCoordinatesInt(){
        shape.setCoordinates(200,200,400,400,800,800);
        assertEquals("ELIPSES 0.250000 0.250000 0.500000 0.500000",shape.toString());
    }

    @Test
    void setCoordinatesDouble(){
        // X1 and Y1 swapped with X2 and Y2 due to X1 > X2, Y1 > Y2
        shape.setCoordinates(0.7,0.8,0.4,0.2);
        assertEquals("ELIPSES 0.400000 0.200000 0.700000 0.800000",shape.toString());
    }

    @Test
    void testConstructorX1OverX2(){
        // X1 and X2 swapped if X1 > X2
        shape = new Rectangle(0.9,0.2,0.4,0.4, true, Color.cyan, Color.red);
        assertEquals("ELIPSES 0.400000 0.200000 0.900000 0.400000", shape.toString());
    }

    @Test
    void testConstructor(){
        shape = new Rectangle(Color.BLUE, Color.RED,false);
        assertNotNull(shape);
    }

    @Test
    void getCommand() {
        assert(shape.getCommand() == VectorCommand.ELIPSES);
    }

    @Test
    void returnCopy() {
        DrawableVector elipses2 = shape.returnCopy();
        assertTrue( (shape.isFilled()==elipses2.isFilled()) &&
                (shape.getFillColor().equals(elipses2.getFillColor())) &&
                (shape.getCommand() == elipses2.getCommand()));
    }

    @Test
    void isFilled() {
        assertTrue(shape.isFilled());
    }

    @Test
    void setFillColor() {
        shape.setFillColor(Color.BLUE);
        assert(shape.getFillColor().equals(Color.BLUE));
    }

    @Test
    void setColor(){
        shape.setColor(Color.RED);
        assertEquals(Color.RED, shape.getColor());
    }

    @Test
    void toString1() {
        assertEquals("ELIPSES 0.500000 0.500000 0.750000 0.660000",shape.toString());
    }
}