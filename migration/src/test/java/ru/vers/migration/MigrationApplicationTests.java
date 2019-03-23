package ru.vers.migration;

import com.opentable.db.postgres.junit.EmbeddedPostgresRules;
import com.opentable.db.postgres.junit.SingleInstancePostgresRule;
import org.flywaydb.core.Flyway;
import org.junit.Rule;
import org.junit.Test;

public class MigrationApplicationTests {

  @Rule
  public SingleInstancePostgresRule pg = EmbeddedPostgresRules.singleInstance();

  @Test
  public void whenMigrationShouldSuccess() {
    Flyway flyway = new Flyway();
    flyway.setDataSource(pg.getEmbeddedPostgres().getPostgresDatabase());
    flyway.setSchemas("news");
    flyway.migrate();
  }

}
