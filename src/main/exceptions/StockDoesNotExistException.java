package exceptions;

public class StockDoesNotExistException extends Exception {
    public StockDoesNotExistException() {
        super("Stock DNE!");
    }
}
