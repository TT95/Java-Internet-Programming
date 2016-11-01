package start.ex;

/**
 * Created by teo on 10/26/16.
 */
public class IncorrectInput extends Exception {

    private String message;
    private String input;
    private final static String idMessage = "Input error!";

    public IncorrectInput(String input, String message) {
        this.input = input;
        this.message = message;
    }

    public IncorrectInput(String input) {
        this.input = input;
    }

    public IncorrectInput() {
    }

    @Override
    public String toString() {
        String string = idMessage;
        if (input != null) {
            string+= " input:\"" + input + "\"";
        }
        if (message != null) {
            string += " message:\"" + message + "\"";
        }
        return string;
    }
}
