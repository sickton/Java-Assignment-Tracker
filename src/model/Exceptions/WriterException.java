package model.Exceptions;

public class WriterException extends RuntimeException {
    public WriterException(String message) {
        super(message);
    }

    public WriterException()
    {
        this("Writing to file failed due to unexpected error!");
    }
}
