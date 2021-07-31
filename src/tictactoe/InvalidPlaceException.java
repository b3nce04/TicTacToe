package tictactoe;

public class InvalidPlaceException extends Exception {

    public InvalidPlaceException() {
        System.out.println("Invalid number! It should be between 1-9 and empty place.");
    }

    public InvalidPlaceException(String message) {
        super(message);
    }

}
