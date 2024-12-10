package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.dtos.CountryDto;
import pro.entera.resource_service.repositories.CountryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {
    //region Fields

    private final CountryRepository countryRepository;

    //endregion
    //region Public

    @Override
    public Mono<CountryDto> findByAlpha3Code(String alpha3Code) {

        return this.countryRepository.findById(alpha3Code).map(CountryDto::from);
    }

    @Override
    public Mono<CountryDto> findByOksmCode(String code) {

        return this.countryRepository.findByCode(code).map(CountryDto::from);
    }

    @Override
    public Flux<CountryDto> findAll() {

        return this.countryRepository.findAll().map(CountryDto::from);
    }

    //endregion
}
