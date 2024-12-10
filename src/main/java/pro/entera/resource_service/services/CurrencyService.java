package pro.entera.resource_service.services;


import pro.entera.resource_service.dtos.CurrencyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CurrencyService {
    //region public

    Mono<CurrencyDto> findByAlpha3Code(String alpha3Code);

    Flux<CurrencyDto> findAll();

    //endregion
}
