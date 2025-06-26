package asset.spy.statistics.service.service;

import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
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
public class DefectRateService implements GroupedStatisticsService<DefectiveRateStatisticDto> {

    private final StatisticsDataProvider dataProvider;
    private final ItemRateCalculator rateCalculator;
    private final StatisticsDtoMapper mapper;

    @Override
    public List<DefectiveRateStatisticDto> getByVendor() {
        return mapper.mapDefectRates(calculateGroupedDefectRate(ProductItemStatusEntity::getVendorKey));
    }

    @Override
    public List<DefectiveRateStatisticDto> getByProductType() {
        return mapper.mapDefectRates(calculateGroupedDefectRate(ProductItemStatusEntity::getProductTypeKey));
    }

    @Override
    public List<DefectiveRateStatisticDto> getByVendorAndProductType() {
        return mapper.mapDefectRates(calculateGroupedDefectRate(ProductItemStatusEntity::getVendorAndProductTypeKey));
    }

    public Double getOverallDefectRate() {
        return rateCalculator.calculateOverall(
                dataProvider.getAllStatuses(),
                StatusPredicates.IS_DEFECTIVE
        );
    }

    private Map<String, Double> calculateGroupedDefectRate(Function<ProductItemStatusEntity, String> keyMapper) {
        return rateCalculator.calculateGrouped(
                dataProvider.getAllStatuses(),
                StatusPredicates.IS_DEFECTIVE,
                keyMapper
        );
    }
}
