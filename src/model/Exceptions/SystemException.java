package model.Exceptions;

public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }

    public SystemException()
    {
        this("User not registered in system !");
    }
}
