package treky.exception;

/**
 * Represents a fatal exception in Treky.
 */
public class TrekyFatalException extends Exception {
    /**
     * Constructs a TrekyFatalException.
     *
     * @param message Error message.
     */
    public TrekyFatalException(String message) {
        super(message);
    }
}
