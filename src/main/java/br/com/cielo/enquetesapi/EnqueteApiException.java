package br.com.cielo.enquetesapi;

public class EnqueteApiException extends  RuntimeException{
    public EnqueteApiException(String message) {
        super(message);
    }
}
