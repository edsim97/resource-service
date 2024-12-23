package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.banks.BankDto;
import pro.entera.resource_service.services.BankService;
import reactor.core.publisher.Flux;

/**
 * Контроллер с API методами для работы с банками.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    //region Fields

    private final BankService bankService;

    //endregion
    //region Public

    /**
     * Выполняет поиск банка.
     *
     * @param search Строка поиска.
     * @param countryCode Код страны банка.
     *
     * @return Список найденных банков.
     */
    @GetMapping
    public Flux<BankDto> find(
        @RequestParam("search") String search,
        @RequestParam(name = "countryCode", required = false) String countryCode
    ) {
        return this.bankService.find(search, countryCode);
    }

    //endregion
}
