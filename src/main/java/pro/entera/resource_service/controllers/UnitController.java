package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.dtos.UnitDto;
import pro.entera.resource_service.services.UnitService;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/unit")
public class UnitController {
    //region Fields

    private final UnitService unitService;

    //endregion
    //region Public

    @GetMapping
    public Mono<UnitDto> find(
        @RequestParam(name = "code", required = false) String code,
        @RequestParam(name = "name", required = false) String name
    ) {
        return this.unitService.findUnit(code, name);
    }

    //endregion
}
