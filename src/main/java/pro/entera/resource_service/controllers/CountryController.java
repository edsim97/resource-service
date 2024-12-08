package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.aop.CacheAspect;
import pro.entera.resource_service.dtos.CountryDto;
import pro.entera.resource_service.services.CountryService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    //region Fields

    private final CountryService countryService;

    private final CacheAspect cacheAspect;

    //endregion
    //region Public

    @GetMapping("/{alpha3code}")
    public CountryDto get(@PathVariable("alpha3code") String alpha3Code) {

        return this.countryService.findByAlpha3Code(alpha3Code);
    }

    @GetMapping("/oksm/{oksmCode}")
    public CountryDto findByOksm(@PathVariable("oksmCode") String oksmCode) {

        return this.countryService.findByOksmCode(oksmCode);
    }

    @GetMapping
    public List<CountryDto> getAll() {

        return this.countryService.findAll();
    }

    @DeleteMapping("/cache")
    public void clearCache() {

        this.cacheAspect.clearCache("country");
    }

    //endregion
}
