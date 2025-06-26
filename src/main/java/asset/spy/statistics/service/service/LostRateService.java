package asset.spy.statistics.service.service;

import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.mapper.StatisticsDtoMapper;
import asset.spy.statistics.service.calculator.ItemRateCalculator;
import asset.spy.statistics.service.util.StatusPredicates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class LostRateService implements GroupedStatisticsService<LostRateStatisticDto> {

    private final StatisticsDataProvider dataProvider;
    private final ItemRateCalculator rateCalculator;
    private final StatisticsDtoMapper mapper;

    @Override
    public List<LostRateStatisticDto> getByVendor() {
        return mapper.mapLostRates(calculateGroupedLostRate(ProductItemStatusEntity::getVendorKey));
    }

    @Override
    public List<LostRateStatisticDto> getByProductType() {
        return mapper.mapLostRates(calculateGroupedLostRate(ProductItemStatusEntity::getProductTypeKey));
    }

    @Override
    public List<LostRateStatisticDto> getByVendorAndProductType() {
        return mapper.mapLostRates(calculateGroupedLostRate(ProductItemStatusEntity::getVendorAndProductTypeKey));
    }

    public Double getOverallLostRate() {
        return rateCalculator.calculateOverall(
                dataProvider.getAllStatuses(),
                StatusPredicates.IS_LOST
        );
    }

    private Map<String, Double> calculateGroupedLostRate(Function<ProductItemStatusEntity, String> keyMapper) {
        return rateCalculator.calculateGrouped(
                dataProvider.getAllStatuses(),
                StatusPredicates.IS_LOST,
                keyMapper
        );
    }
}
