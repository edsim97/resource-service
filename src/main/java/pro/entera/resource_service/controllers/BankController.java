package pro.entera.resource_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.entera.resource_service.aop.CacheAspect;
import pro.entera.resource_service.dtos.BankDto;
import pro.entera.resource_service.services.BankService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {
    //region Fields

    private final BankService bankService;

    private final CacheAspect cacheAspect;

    //endregion
    //region Public

    @GetMapping
    public List<BankDto> find(
        @RequestParam("search") String search,
        @RequestParam(name = "countryCode", required = false) String countryCode
    ) {
        return this.bankService.find(search, countryCode);
    }

    @DeleteMapping("/cache")
    public void clearCache() {

        this.cacheAspect.clearCache("bank");
    }

    //endregion
}
