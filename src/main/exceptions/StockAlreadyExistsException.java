package exceptions;

public class StockAlreadyExistsException extends Exception {
    public StockAlreadyExistsException() {
        super("Stock already exists!");
    }
}
