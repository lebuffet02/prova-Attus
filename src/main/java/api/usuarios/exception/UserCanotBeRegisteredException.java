package api.usuarios.exception;

public class UserCanotBeRegisteredException extends RuntimeException {

    public UserCanotBeRegisteredException(){}

    public UserCanotBeRegisteredException(String message) {
        super(message);
    }
}
