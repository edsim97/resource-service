package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.UnitDto;
import pro.entera.resource_service.services.UnitService;
import reactor.core.publisher.Mono;

/**
 * Контроллер с API методами для работы с единицами измерения.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/unit")
public class UnitController {
    //region Fields

    private final UnitService unitService;

    //endregion
    //region Public

    /**
     * Выполняет поиск единицы измерения по её коду или краткому наименованию в ОКЕИ.
     * Если указан код, то поиск будет выполняться по нему.
     * Иначе, если указано наименование, то будет выполнен поиск по краткому наименованию и списку синонимов.
     *
     * @param code Код валюты в ОКЕИ.
     * @param name Краткое наименование валюты в ОКЕИ.
     *
     * @return Валюта.
     */
    @GetMapping
    public Mono<UnitDto> find(
        @RequestParam(name = "code", required = false) String code,
        @RequestParam(name = "name", required = false) String name
    ) {
        return this.unitService.findUnit(code, name);
    }

    //endregion
}
