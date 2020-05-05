package ro.ubb.movie_catalog.core.domain.validators;

public class RentalException extends Exception{
    public RentalException(String message)
    {
        super(message);
    }
    public RentalException(String message, Throwable cause) {super(message, cause);}
    public RentalException(Throwable cause) {super(cause);}
}
