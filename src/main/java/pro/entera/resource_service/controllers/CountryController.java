package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.CountryDto;
import pro.entera.resource_service.services.CountryService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Контроллер с API методами для работы со странами.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    //region Fields

    private final CountryService countryService;

    //endregion
    //region Public

    /**
     * Возвращает страну по её трёхзначному буквенному коду.
     *
     * @param alpha3Code Трёхзначный буквенный код страны.
     *
     * @return Страна.
     */
    @GetMapping("/{alpha3code}")
    public Mono<CountryDto> get(@PathVariable("alpha3code") String alpha3Code) {

        return this.countryService.findByAlpha3Code(alpha3Code);
    }

    /**
     * Возвращает страну по её коду в ОКСМ.
     *
     * @param oksmCode Код страны в ОКСМ.
     *
     * @return Страна.
     */
    @GetMapping("/oksm/{oksmCode}")
    public Mono<CountryDto> findByOksm(@PathVariable("oksmCode") String oksmCode) {

        return this.countryService.findByOksmCode(oksmCode);
    }

    /**
     * Возвращает все страны.
     *
     * @return Список всех стран.
     */
    @GetMapping
    public Flux<CountryDto> getAll() {

        return this.countryService.findAll();
    }

    //endregion
}
