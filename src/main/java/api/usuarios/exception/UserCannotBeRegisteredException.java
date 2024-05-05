package api.usuarios.exception;

public class UserCannotBeRegisteredException extends RuntimeException {

    public UserCannotBeRegisteredException(){};

    public UserCannotBeRegisteredException(String message) {
        super(message);
    }
}
