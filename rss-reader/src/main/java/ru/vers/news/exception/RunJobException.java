package ru.vers.news.exception;

public class RunJobException extends RuntimeException {

  private static final long serialVersionUID = -281759217835320731L;

  public RunJobException() {
    super();
  }

  public RunJobException(String s) {
    super(s);
  }

  public RunJobException(String message, Throwable cause) {
    super(message, cause);
  }

  public RunJobException(Throwable cause) {
    super(cause);
  }
  
}
