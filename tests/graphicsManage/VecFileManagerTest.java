package graphicsManage;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class VecFileManagerTest {

    @Test
    void vecFromInstruction() throws VecFileException{
        String[] instructions = {"RECTANGLE","0.5","0.6","0.2","0.1"};
        DrawableVector vec = VecFileManager.vecFromInstruction(instructions, true, Color.red, Color.black);
        assert (vec.getCommand()==VectorCommand.RECTANGLE);
    }

    @Test
    void getColorFromHex() throws VecFileException{
        Color color = VecFileManager.getColorFromHex("#FF0000");
        assertEquals(Color.RED, color);
    }

    @Test
    void getColorFromHexIncorrect(){
        assertThrows(VecFileException.class,
                ()->{
            VecFileManager.getColorFromHex("#FF13P");
                });
    }

    @Test
    void getHexFromColor() {
        Color color = Color.cyan;
        assertEquals("#00ffff",VecFileManager.getHexFromColor(color));
    }
}