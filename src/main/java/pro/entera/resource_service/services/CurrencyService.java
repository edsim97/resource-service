package pro.entera.resource_service.services;


import pro.entera.resource_service.dtos.CurrencyDto;
import pro.entera.resource_service.models.Currency;

import java.util.List;

public interface CurrencyService {
    //region public

    CurrencyDto findByAlpha3Code(String alpha3Code);

    List<CurrencyDto> findAll();

    //endregion
}
