package br.com.etechoracio.viagem.exceptions;



import java.time.LocalDateTime;

public class ViagemInvalidaException extends RuntimeException{
    public ViagemInvalidaException(String message) {
        super(message);


    }
}
