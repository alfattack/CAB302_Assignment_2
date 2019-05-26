package graphicsManage;

/**
 * Exception relating to reading and writing to vector files and interpreting instructions from inputs.
 */
public class VecFileException extends Exception {

    /**
     * Constructs exception with message.
     * @param message message for exception.
     */
    public VecFileException(String message){
        super(message);
    }
}
