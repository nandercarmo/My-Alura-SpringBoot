package br.com.alura.rh;

public class ExceptionValidation extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExceptionValidation(String message) {
        super(message);
    }
}