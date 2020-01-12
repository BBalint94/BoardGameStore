package boardgamestore.exception;

public class BoardGameNotExist extends Exception{
    public BoardGameNotExist() {
        super();
    }

    public BoardGameNotExist(String message) {
        super(message);
    }
}
