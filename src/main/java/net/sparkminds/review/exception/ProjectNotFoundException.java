package net.sparkminds.review.exception;

public class ProjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
