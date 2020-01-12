package boardgamestore.exception;

public class AlreadyExist extends Exception{

    public AlreadyExist() {
        super();
    }

    public AlreadyExist(String message) {
        super(message);
    }
}
