package graphicsManage;

import java.awt.*;

public class CommandProcessor {

    private Color currentColour;
    private Color fillColour;
    private boolean fill;
    private CommandProcessor instance;

    private CommandProcessor(){

    }

    CommandProcessor getProcessor(){
        if (instance == null){
            this.instance = new CommandProcessor();
        }
        return this.instance;
    }

}
