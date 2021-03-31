package br.com.roger.study.casadocodigo.exception;

/**
 * 0
 */
public class EmailNotUniqueException extends RuntimeException {

    private String email;

    public EmailNotUniqueException(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
