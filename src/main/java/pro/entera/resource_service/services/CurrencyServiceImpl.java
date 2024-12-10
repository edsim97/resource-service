package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.dtos.CurrencyDto;
import pro.entera.resource_service.repositories.CurrencyRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {
    //region Fields

    private final CurrencyRepository currencyRepository;

    //endregion
    //region Public

    @Override
    public Mono<CurrencyDto> findByAlpha3Code(String alpha3Code) {

        return this.currencyRepository.findById(alpha3Code).map(CurrencyDto::from);
    }

    @Override
    public Flux<CurrencyDto> findAll() {

        return this.currencyRepository.findAll().map(CurrencyDto::from);
    }

    //endregion
}
