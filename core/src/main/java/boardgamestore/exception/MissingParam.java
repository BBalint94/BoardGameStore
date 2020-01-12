package boardgamestore.exception;

public class MissingParam extends Exception{
    public MissingParam() {
        super();
    }

    public MissingParam(String message) {
        super(message);
    }
}
