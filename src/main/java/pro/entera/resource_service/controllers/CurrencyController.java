package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.CurrencyDto;
import pro.entera.resource_service.services.CurrencyService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
    //region Fields

    private final CurrencyService currencyService;

    //endregion
    //region Public

    @GetMapping("/{alpha3code}")
    public CurrencyDto getCurrency(@PathVariable("alpha3code") String alpha3Code) {

        return this.currencyService.findByAlpha3Code(alpha3Code);
    }

    @GetMapping
    public List<CurrencyDto> getAllCountries() {

        return this.currencyService.findAll();
    }

    //endregion
}
