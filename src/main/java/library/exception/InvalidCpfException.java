package library.exception;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException() {
        super("Invalid Cpf.");
    }

    public InvalidCpfException(String msg) {
        super(msg);
    }
}
