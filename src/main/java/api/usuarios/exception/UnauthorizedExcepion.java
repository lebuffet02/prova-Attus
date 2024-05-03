package api.usuarios.exception;

public class UnauthorizedExcepion extends RuntimeException {

    public UnauthorizedExcepion() {}

    public UnauthorizedExcepion(String message) {
        super(message);
    }
}
