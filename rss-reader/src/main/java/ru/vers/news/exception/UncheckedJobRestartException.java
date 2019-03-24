package ru.vers.news.exception;

import java.util.Objects;
import org.springframework.batch.core.repository.JobRestartException;

public class UncheckedJobRestartException extends RuntimeException {

  private static final long serialVersionUID = -281759217835320731L;

  public UncheckedJobRestartException(String message,
      JobRestartException cause) {
    super(message, Objects.requireNonNull(cause));
  }
}
