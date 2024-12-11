package pro.entera.resource_service.repositories;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import pro.entera.resource_service.configurations.EmbeddedPostgresConfiguration;
import pro.entera.resource_service.configurations.EmbeddedPostgresConfiguration.EmbeddedPostgresExtension;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DisplayName("")
@DataJpaTest
@ExtendWith(EmbeddedPostgresExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {EmbeddedPostgresConfiguration.class})
public class CountryRepositoryTest {

    private CountryRepository countryRepository;

    @DisplayName("")
    @Test
    void shouldFindAllCountries() {


    }

}
