package ru.vers.news.job.step;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.vers.news.domain.enums.Imports;
import ru.vers.news.exception.ResourceNotFoundException;
import ru.vers.news.imports.RssProperties;
import ru.vers.news.job.Constants;
import ru.vers.news.support.FileUtils;

@Component
public class DownloadFileTasklet implements Tasklet {

  @Autowired
  private IRssResourceFinder iRssResourceFinder;
  @Autowired
  private RssProperties rssProperties;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {

    URL feedSource = new URL("http://biathlonrus.com/rss/main-rus.xml");
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed feed = input.build(new XmlReader(feedSource));


    String nsi = chunkContext.getStepContext().getStepExecution().getJobParameters()
        .getString(Constants.RSS);
    Resource resource = iRssResourceFinder.findResource(Imports.valueOf(nsi));
    Path dir = FileUtils.createDirectory(rssProperties.getUploadPath().resolve(nsi));
    Path path = dir.resolve(resource.getFilename());
    Files.walk(dir).filter(e -> !e.equals(dir)).sorted(Comparator.reverseOrder()).map(Path::toFile)
        .forEach(File::delete);
    downloadFile(resource.getURL(), path);
    File zipFile = path.toFile();
//    ZipUtil.unpack(zipFile, dir.toFile());
   // Files.delete(path);
    Path file = Files.list(dir).findFirst().orElseThrow(ResourceNotFoundException::new);
    chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext()
        .put("FILE_NAME", file.toString());
    return RepeatStatus.FINISHED;
  }

  private void downloadFile(URL url, Path path) throws IOException {
    try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(path.toString());) {
      fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }
  }

}
