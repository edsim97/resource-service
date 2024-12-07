package pro.entera.resource_service.services;

import pro.entera.resource_service.dtos.CountryDto;

import java.util.List;

public interface CountryService {
    //region public

    CountryDto findByAlpha3Code(String alpha3Code);

    CountryDto findByOksmCode(String code);

    List<CountryDto> findAll();

    //endregion
}
