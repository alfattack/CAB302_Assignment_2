package graphicsManage;

import gui.VecCanvas;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static graphicsManage.VectorCommand.*;

public class VecFileManager {

    /**
     * Creates a Drawable vector based on a string of commands and coloring attributes.
     * @param commandArgs
     * @param fill
     * @param color
     * @param fillColor
     * @return
     * @throws VecFileException
     */
    private static DrawableVector vecFromInstruction(String[] commandArgs, boolean fill, Color color, Color fillColor) throws VecFileException{

        DrawableVector shape = null;

        if (commandArgs.length % 2 == 0){
            throw new VecFileException();
        }

        ArrayList<Double> xcords = new ArrayList<>();
        ArrayList<Double> ycords = new ArrayList<>();

        for (int i =0; i < commandArgs.length-1;){
            try{
                xcords.add(Double.parseDouble(commandArgs[i]));
                ycords.add(Double.parseDouble(commandArgs[i+1]));
                i+=2;
            }
            catch (IndexOutOfBoundsException e){
                throw new VecFileException();
            }
            catch (NumberFormatException e){
                // continue - try parse next value
                i+=1;
            }
        }

        if (xcords.size() != ycords.size()){
            throw new VecFileException();
        }

        VectorCommand vecCommand = VectorCommand.valueOf(commandArgs[0]);

        switch (vecCommand){
            case LINE:
                shape = new Line(xcords.get(0).doubleValue(),ycords.get(0).doubleValue(),xcords.get(1).doubleValue(),ycords.get(1).doubleValue(), color);
                break;
            case RECTANGLE:
                shape = new Rectangle(xcords.get(0).doubleValue(),ycords.get(0).doubleValue(),xcords.get(1).doubleValue(),ycords.get(1).doubleValue(), fill, color, fillColor);
                break;
            case ELIPSES:
                shape = new Rectangle(xcords.get(0).doubleValue(),ycords.get(0).doubleValue(),xcords.get(1).doubleValue(),ycords.get(1).doubleValue(), fill, color, fillColor);
                break;
            case POINT:
                shape = new Point(xcords.get(0).doubleValue(), ycords.get(0), color);
                break;
            case POLYGON:
                shape = new Polygon(xcords, ycords, fill, color, fillColor);
                break;
            default:
                throw new VecFileException();
        }
        return shape;
    }

    /**
     *
     * @param color
     * @return
     */
    private static Color getColorFromHex(String color){
        return Color.BLACK;
    }

    private static String getHexFromColor(Color color){
        return "#FFFFFF";
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
        Color color = Color.BLACK;
        boolean fill = false;
        Color fillColor = null;

        String currentline = null;
        try{
            file = new BufferedReader(new FileReader(path));

            while ((currentline = file.readLine()) != null) {
                String [] commandArgs = currentline.split("\\s");

                try{
                    VectorCommand vecCommand = VectorCommand.valueOf(commandArgs[0]);

                    switch (vecCommand){
                        case PEN:
                            color = getColorFromHex(commandArgs[1]);
                            break;
                        case FILL:
                            if (commandArgs[1] == "OFF"){
                                fill=false;
                                fillColor=null;
                            }
                            else{
                                fill = true;
                                fillColor = getColorFromHex(commandArgs[1]);
                            }
                            break;
                        default:
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

    /**
     * Writes  the current state of the canvas instructions to file.
     * @param path
     */
    public static void WriteToFile(String path) throws VecFileException{
        BufferedWriter file;
        ArrayList<DrawableVector> instructions = VecCanvas.getCanvas().getInstructions();

        boolean fill;
        Color fillColor;
        Color penColor;

        try{
            file = new BufferedWriter(new FileWriter(path));

            for (DrawableVector v: instructions){

                if ((v.getCommand() != LINE) && (v.getCommand() != POINT)){

                }


                System.out.println(v.toString());
                file.write(v.toString());
                file.write("\n");
            }

            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
