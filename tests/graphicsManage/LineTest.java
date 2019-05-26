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
    }

    @Test
    void returnCopy() {
    }

    @Test
    void isFilled() {
    }

    @Test
    void getColor(){

    }

    @Test
    void toString1() {
    }
}