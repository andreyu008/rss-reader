package ru.vers.news.exception;

public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 6970810004193332896L;

  public ResourceNotFoundException() {
    super();
  }

  public ResourceNotFoundException(String s) {
    super(s);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResourceNotFoundException(Throwable cause) {
    super(cause);
  }
}
