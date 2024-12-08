package pro.entera.resource_service.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entera.resource_service.aop.Cacheable;
import pro.entera.resource_service.models.Unit;
import pro.entera.resource_service.repositories.UnitRepository;

import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UnitServiceImpl implements UnitService {
    // region Constants

    /**
     * <p>Паттерн незначащих нулей в ОКЕИ коде.</p>
     *
     * <p>Например для кода 050 (квадратный миллиметр) первый ноль незначащий</p>
     */
    private static final Pattern INSIGNIFICANT_ZEROES_IN_CODE = Pattern.compile("^0+");

    //endregion
    //region Fields

    private final UnitRepository unitRepository;

    //endregion
    //region Public


    @Cacheable(ttl = 24 * 60 * 60)
    @Override
    public List<Unit> findAll() {

        return this.unitRepository.findAll();
    }

    @Cacheable(ttl = 24 * 60 * 60)
    @Override
    public Unit findByOkeiCode(String unitCode) {

        final String formattedOkeiCode = INSIGNIFICANT_ZEROES_IN_CODE.matcher(unitCode).replaceFirst("");

        return this.unitRepository.findByCode(formattedOkeiCode).orElse(null);
    }

    @Cacheable(ttl = 24 * 60 * 60)
    @Override
    public Unit findByName(String name) {

        return this.unitRepository.findByName(name).orElse(null);
    }

    //endregion
}
