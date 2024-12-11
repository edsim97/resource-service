package pro.entera.resource_service.configurations;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableR2dbcRepositories(basePackages = "pro.entera.resource_service.repositories")
@EntityScan(basePackages = "pro.entera.resource_service.models")
public class EmbeddedPostgresConfiguration {

    private static EmbeddedPostgres embeddedPostgres;

    @Bean
    public DataSource dataSource() throws IOException {

        embeddedPostgres = EmbeddedPostgres.builder()
            .setImage(DockerImageName.parse("postgres:14.1"))
            .start();

        return embeddedPostgres.getPostgresDatabase();
    }

    public static class EmbeddedPostgresExtension implements AfterAllCallback {

        @Override
        public void afterAll(ExtensionContext context) throws Exception {

            if (embeddedPostgres == null) {
                return;
            }
            embeddedPostgres.close();
        }
    }
}
