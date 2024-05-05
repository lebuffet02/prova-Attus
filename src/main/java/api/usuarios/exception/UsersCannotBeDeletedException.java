package api.usuarios.exception;

public class UsersCannotBeDeletedException extends RuntimeException {

    public UsersCannotBeDeletedException(String message) {
        super(message);
    }
}
