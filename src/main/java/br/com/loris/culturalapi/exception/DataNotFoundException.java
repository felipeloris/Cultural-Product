package br.com.loris.culturalapi.exception;

public class DataNotFoundException extends Exception {

    public DataNotFoundException(Long id) {
        super("Data not found with ID " + id);
    }
}
