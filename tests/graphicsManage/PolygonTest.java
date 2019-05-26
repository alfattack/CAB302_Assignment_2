package graphicsManage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {
    private Polygon polygon;

    @BeforeEach
    void setupPolygon(){
        polygon = new Polygon(Color.red, Color.black, true);
    }

    @Test
    void getCommand() {
        assertEquals(VectorCommand.POLYGON, polygon.getCommand());
    }

    @Test
    void testDefinedCoordinatesConstructor() throws VecFileException{
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        x.add(0.5);
        y.add(0.7);
        x.add(0.5672);
        y.add(0.6742);
        x.add(0.43021);
        y.add(0.45435);

        Polygon newPoly = new Polygon(x,y,true, Color.red,Color.cyan);
        assertEquals("POLYGON 0.500000 0.700000 0.567200 0.674200 0.430210 0.454350",newPoly.toString());
    }

    @Test
    void testBadConstructor(){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        x.add(0.5);
        y.add(0.7);
        x.add(0.5672);
        y.add(0.6742);
        y.add(0.45435);

        assertThrows(VecFileException.class,
                ()-> {
                    Polygon newPoly = new Polygon(x,y,true, Color.red,Color.cyan);
                }
        );
    }

    @Test
    void isValidFalse() {
        assertFalse(polygon.isValid());
    }

    @Test
    void isValidTrue(){
        polygon.addCoordinates(120,60,500,500);
        assertTrue(polygon.isValid());
    }

    @Test
    void returnCopy() {
        DrawableVector polyCopy = polygon.returnCopy();
        assert(polyCopy == null);
    }

    @Test
    void isFilled() {
        assertTrue(polygon.isFilled());
    }

    @Test
    void getColor() {
        assertEquals(Color.RED, polygon.getColor());
    }

    @Test
    void getFillColor() {
        assertEquals(Color.BLACK, polygon.getFillColor());
    }

    @Test
    void setColor() {
        polygon.setColor(Color.BLUE);
        assertEquals(Color.BLUE, polygon.getColor());
    }

    @Test
    void setFillColor() {
        polygon.setFillColor(Color.CYAN);
        assertEquals(Color.CYAN, polygon.getFillColor());
    }

    @Test
    void addCoordinates() {
        polygon.addCoordinates(60,60,120,120);
        polygon.addCoordinates(50,125,250,250);

        ArrayList<Double> expectedList = new ArrayList<>();
        expectedList.add(0.5);
        expectedList.add(0.2);
        assertEquals(polygon.x_points, expectedList);
    }

    @Test
    void getAbsoluteCoordinates() {
        polygon.addCoordinates(60,60,120,120);
        polygon.addCoordinates(50,125,250,250);
        int[] absoluteCoordinates = polygon.getAbsoluteCoordinates(polygon.y_points,300,300);
        assertEquals(absoluteCoordinates[0],150);
        assertEquals(absoluteCoordinates[1], 150);
    }

    @Test
    void finishShape() {
        polygon.addCoordinates(60,60,120,120);
        polygon.addCoordinates(50,125,250,250);
        polygon.finishShape();
        assertEquals("POLYGON 0.500000 0.500000 0.200000 0.500000", polygon.toString());
    }
}