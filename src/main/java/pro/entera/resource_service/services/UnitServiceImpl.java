package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.models.Unit;
import pro.entera.resource_service.repositories.UnitRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UnitServiceImpl implements UnitService {
    // region Constants

    //endregion
    //region Fields

    private final UnitRepository unitRepository;

    //endregion
    //region Public


    @Override
    public Flux<Unit> findAll() {

        return this.unitRepository.findAll();
    }

    @Override
    public Mono<Unit> findByOkeiCode(String unitCode) {

        return this.unitRepository.findByCode(unitCode);
    }

    @Override
    public Mono<Unit> findByName(String name) {

        return this.unitRepository.findByName(name);
    }

    //endregion
}
