package exceptions;

public class CategoryExistsException extends Exception {
    public CategoryExistsException() {
        super("Portfolio already exists!");
    }
}
