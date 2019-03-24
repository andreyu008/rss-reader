package ru.vers.news.support;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class FileUtils {
  
  public static Path createDirectory(Path path) {
    Path location = path;
    if (!path.toFile().exists()) {
      try {
        location = Files.createDirectory(path);
      } catch (IOException e) {
        throw new UncheckedIOException(e);
      }
    }
    return location;
  }
  
  public static void moveTo(Path source, Path path) throws IOException {
    log.info("Start file {} move to {}", source.getFileName(), path);
    Path target = path.resolve(source.getFileName());
    Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    log.info("File {} move to {}", source.getFileName(), path);
  }
  
  public static void moveToUnchecked(Path source, Path path) {
    log.info("Start file {} move to {}", source.getFileName(), path);
    Path target = path.resolve(source.getFileName());
    try {
      Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
    log.info("File {} move to {}", source.getFileName(), path);
  }
}
