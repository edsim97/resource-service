package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.aop.Cacheable;
import pro.entera.resource_service.dtos.CountryDto;
import pro.entera.resource_service.repositories.CountryRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {
    //region Fields

    private final CountryRepository countryRepository;

    //endregion
    //region Public

    @Override
    @Cacheable(ttl = 24 * 60 * 60, namePrefix = "country")
    public CountryDto findByAlpha3Code(String alpha3Code) {

        return this.countryRepository.findById(alpha3Code)
            .map(CountryDto::from)
            .orElse(null);
    }

    @Override
    @Cacheable(ttl = 24 * 60 * 60, namePrefix = "country")
    public CountryDto findByOksmCode(String code) {

        return this.countryRepository.findByCode(code)
            .map(CountryDto::from)
            .orElse(null);
    }

    @Override
    @Cacheable(ttl = 24 * 60 * 60, namePrefix = "country")
    public List<CountryDto> findAll() {

        return this.countryRepository.findAll()
            .stream()
            .map(CountryDto::from)
            .toList();
    }

    //endregion
}
