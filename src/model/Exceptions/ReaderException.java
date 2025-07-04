package model.Exceptions;

public class ReaderException extends RuntimeException {
    public ReaderException(String message) {
        super(message);
    }

    public ReaderException()
    {
      this("Could not read from the given file !");
    }
}
