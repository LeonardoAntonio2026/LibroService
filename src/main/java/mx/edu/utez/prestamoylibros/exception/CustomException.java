package mx.edu.utez.prestamoylibros.exception;

public class CustomException extends RuntimeException {
    public CustomException(String mensaje) {
        super(mensaje);
    }
}