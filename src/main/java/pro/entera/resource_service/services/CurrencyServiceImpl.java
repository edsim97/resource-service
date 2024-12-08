package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.aop.Cacheable;
import pro.entera.resource_service.dtos.CurrencyDto;
import pro.entera.resource_service.repositories.CurrencyRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {
    //region Fields

    private final CurrencyRepository currencyRepository;

    //endregion
    //region Public

    @Override
    @Cacheable(ttl = 24 * 60 * 60)
    public CurrencyDto findByAlpha3Code(String alpha3Code) {

        return this.currencyRepository.findById(alpha3Code)
            .map(CurrencyDto::from)
            .orElse(null);
    }

    @Override
    @Cacheable(ttl = 24 * 60 * 60)
    public List<CurrencyDto> findAll() {

        return this.currencyRepository.findAll()
            .stream()
            .map(CurrencyDto::from)
            .toList();
    }

    //endregion
}
