package graphicsManage;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static graphicsManage.VectorCommand.*;

public class VecFileManager {

    private static DrawableVector vecFromInstruction(String[] commandArgs, boolean fill, Color color, Color fillColor) throws VecFileException{

        DrawableVector shape = null;

        if (commandArgs.length % 2 == 0){
            throw new VecFileException();
        }

        ArrayList<Double> cords = new ArrayList<>();

        for (String arg: Arrays.copyOfRange(commandArgs,1,commandArgs.length)){
            try{
                cords.add(Double.parseDouble(arg));
            }
            catch (Exception e){
                throw new VecFileException();
            }
        }

        VectorCommand vecCommand = valueOf(commandArgs[0]);

        switch (vecCommand){
            case LINE:
                shape = new Line(cords.get(0).doubleValue(),cords.get(1).doubleValue(),cords.get(2).doubleValue(),cords.get(3).doubleValue(), color);
                break;
        }
        return shape;
    }

    /**
     * Reads a .vec file and returns an ArrayList of DrawableVectors.
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<DrawableVector> readFromFile(String path) throws VecFileException {

        ArrayList<DrawableVector> instructions = new ArrayList<>();
        BufferedReader file;
        String currentline = null;
        Color color = Color.BLACK;
        boolean fill = false;
        Color fillColor = null;

        try{
            file = new BufferedReader(new FileReader(path));

            while ((currentline = file.readLine()) != null) {
                String [] commandArgs = currentline.split("\\s");

                try{
                    VectorCommand vecCommand = valueOf(commandArgs[0]);

                    switch (vecCommand){
                        case COLOR:
                            if (commandArgs.length != 2){
                                throw new VecFileException();
                            }
                            // this needs fixing.
                            color = Color.getColor(commandArgs[1]);
                            break;
                        case FILL:
                            if (commandArgs.length != 2){
                                throw new VecFileException();
                            }

                            if (commandArgs[1] == "OFF"){
                                fill=false;
                                fillColor=null;
                            }
                            else{
                                fill = true;
                                fillColor = Color.getColor(commandArgs[1]);
                            }
                            break;
                        default:
                            //instructions.add(new Rectangle(0,0,0.5,0.5,true, Color.black));
                            instructions.add(vecFromInstruction(commandArgs, fill, color, fillColor));
                    }
                }
                catch (IllegalArgumentException e){
                    throw new VecFileException();
                }
                catch (Exception e){
                    throw new VecFileException();
                }
            }
            file.close();
        }

        catch (FileNotFoundException e){
            return null;
        }
        catch (IOException e){
            throw new VecFileException();
        }

        return instructions;
    }

    public static void WriteToFile(String path){

    }
}
