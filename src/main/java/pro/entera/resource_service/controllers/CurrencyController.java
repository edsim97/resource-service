package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.CurrencyDto;
import pro.entera.resource_service.services.CurrencyService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Контроллер с API методами для работы с валютами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/currency")
public class CurrencyController {
    //region Fields

    private final CurrencyService currencyService;

    //endregion
    //region Public

    /**
     * Возвращает валюту по её трёхзначному буквенному коду.
     *
     * @param alpha3Code Трёхзначный буквенный код валюты.
     *
     * @return Валюта.
     */
    @GetMapping("/{alpha3code}")
    public Mono<CurrencyDto> get(@PathVariable("alpha3code") String alpha3Code) {

        return this.currencyService.findByAlpha3Code(alpha3Code);
    }

    /**
     * Возвращает все валюты.
     *
     * @return Список всех валют.
     */
    @GetMapping
    public Flux<CurrencyDto> getAll() {

        return this.currencyService.findAll();
    }

    //endregion
}
