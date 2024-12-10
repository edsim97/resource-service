package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.CountryDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CountryService {
    //region public

    Mono<CountryDto> findByAlpha3Code(String alpha3Code);

    Mono<CountryDto> findByOksmCode(String code);

    Flux<CountryDto> findAll();

    //endregion
}
