package graphicsManage;

import gui.VecCanvas;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static graphicsManage.VectorCommand.*;

/**
 * Handles reading and writing for .vec files
 */
public class VecFileManager {

    /**
     * Creates a Drawable vector based on a string of commands and coloring attributes.
     * @param commandArgs String of commands in form SHAPE COORDINATES
     * @param fill whether the fill setting is set
     * @param color the current pen color
     * @param fillColor the current fill color
     * @return a Drawable vector object
     * @throws VecFileException if the instructions are incorrect.
     */
    public static DrawableVector vecFromInstruction(String[] commandArgs, boolean fill, Color color, Color fillColor) throws VecFileException{

        DrawableVector shape = null;

        if (commandArgs.length % 2 == 0){
            throw new VecFileException("not enough arguments in one or more lines.");
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
                throw new VecFileException("X and Y values don't match for one or more instructions.");
            }
            catch (NumberFormatException e){
                // continue - try parse next value
                i+=1;
            }
        }

        if (xcords.size() != ycords.size()){
            throw new VecFileException("X and Y values don't match for one or more instructions.");
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
                shape = new Elipses(xcords.get(0).doubleValue(),ycords.get(0).doubleValue(),xcords.get(1).doubleValue(),ycords.get(1).doubleValue(), fill, color, fillColor);
                break;
            case PLOT:
                shape = new Point(xcords.get(0).doubleValue(), ycords.get(0), color);
                break;
            case POLYGON:
                shape = new Polygon(xcords, ycords, fill, color, fillColor);
                break;
            default:
                throw new VecFileException("Instruction not recognised.");
        }
        return shape;
    }

    /**
     * Returns a a hex string representation of a color object in form #RRGGBB
     * @param color string which represents hex values of RGB
     * @return Color color object derived from hex.
     * @throws VecFileException colour could not be parsed.
     */
    public static Color getColorFromHex(String color) throws VecFileException{
        try{
            int r = Integer.parseInt(color.substring(1,3),16);
            int g = Integer.parseInt(color.substring(3,5),16);
            int b = Integer.parseInt(color.substring(5),16);
            return new Color(r,g,b);
        }
        catch (Exception e){
            throw new VecFileException("Could not parse colour.");
        }
    }

    public static String getHexFromColor(Color color)
    {
        return String.format("#%02x%02x%02x",color.getRed(),color.getGreen(),color.getBlue());
    }

    /**
     * Reads a .vec file and returns an ArrayList of DrawableVectors based on instructions.
     * @param path the path of the file to read from.
     * @return a list of DrawableVector objects.
     * @throws VecFileException if something went wrong with parsing.
     */
    public static ArrayList<DrawableVector> readFromFile(String path) throws VecFileException {

        ArrayList<DrawableVector> instructions = new ArrayList<>();
        BufferedReader file;
        Color color = Color.BLACK;
        boolean fill = false;
        Color fillColor = Color.black;
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
                            if (currentline.equals("FILL OFF")){
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
                    throw new VecFileException("Illegal argument.");
                }
                catch (Exception e){
                    throw new VecFileException(e.getMessage());
                }
            }
            file.close();
        }
        catch (FileNotFoundException e){
            throw new VecFileException("File not found");
        }
        catch (IOException e){
            throw new VecFileException("IO Exception");
        }
        return instructions;
    }

    /**
     * Writes the current state of the canvas instructions to file.
     * @param path output path.
     * @throws VecFileException something went wrong with writing to file.
     */
    public static void writeToFile(String path) throws VecFileException{
        BufferedWriter file;
        ArrayList<DrawableVector> instructions = VecCanvas.getCanvas().getInstructions();

        boolean fill = false;
        Color fillColor= Color.BLACK;
        Color penColor = Color.BLACK;

        try{
            file = new BufferedWriter(new FileWriter(path));

            for (DrawableVector v: instructions){

                if (!(v.getColor().equals(penColor))){
                    file.write(String.format("PEN %S\n",getHexFromColor(v.getColor())));
                    penColor=v.getColor();
                }

                if ((v.getCommand() != LINE) && (v.getCommand() != PLOT)){


                    if ((v.isFilled()) && (fill) && (!v.getFillColor().equals(fillColor))){
                        file.write(String.format("FILL %S\n",getHexFromColor(v.getFillColor())));
                        fillColor=v.getFillColor();
                    }

                    if ((v.isFilled() != fill)){

                        fill = v.isFilled();

                        if (fill){
                            file.write(String.format("FILL %S\n",getHexFromColor(v.getFillColor())));
                            fillColor=v.getFillColor();
                        }
                        else{
                            file.write("FILL OFF\n");
                        }
                    }
                }

                file.write(v.toString());
                file.write("\n");
            }

            file.close();

        } catch (IOException e) {
            throw new VecFileException("IO Exception.");
        }

        catch (NullPointerException e){
            throw new VecFileException("Is the file open somewhere else?");
        }
    }
}
