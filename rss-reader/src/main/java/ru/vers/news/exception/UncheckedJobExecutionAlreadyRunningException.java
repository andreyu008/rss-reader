package ru.vers.news.exception;

import java.util.Objects;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;

public class UncheckedJobExecutionAlreadyRunningException extends RuntimeException {

  private static final long serialVersionUID = -8963734682263093167L;

  public UncheckedJobExecutionAlreadyRunningException(String message,
      JobExecutionAlreadyRunningException cause) {
    super(message, Objects.requireNonNull(cause));
  }
}
