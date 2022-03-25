package exceptions;

public class StockDoesNotExistException extends Exception {
    public StockDoesNotExistException() {
        super("Stock Does Not Exist!");
    }
}
