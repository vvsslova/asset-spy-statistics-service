package asset.spy.statistics.service.service;

import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.mapper.StatisticsDtoMapper;
import asset.spy.statistics.service.calculator.StatusDurationCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatusDurationService implements GroupedStatisticsService<StatusDurationStatisticDto> {

    private final StatusDurationCalculator statusDurationCalculator;
    private final StatisticsDataProvider dataProvider;
    private final StatisticsDtoMapper mapper;

    @Override
    public List<StatusDurationStatisticDto> getByVendor() {
        return mapper.mapGroupedStatusDurations(calculateGroupedStatusDurations(ProductItemStatusEntity::getVendorKey));
    }

    @Override
    public List<StatusDurationStatisticDto> getByProductType() {
        return mapper.mapGroupedStatusDurations(calculateGroupedStatusDurations(
                ProductItemStatusEntity::getProductTypeKey));
    }

    @Override
    public List<StatusDurationStatisticDto> getByVendorAndProductType() {
        return mapper.mapGroupedStatusDurations(calculateGroupedStatusDurations(
                ProductItemStatusEntity::getVendorAndProductTypeKey));
    }

    public List<StatusDurationStatisticDto> getOverallStatusDuration() {
        return mapper.mapOverallStatusDurations(
                statusDurationCalculator.calculateOverall(
                        dataProvider.getAllStatuses(),
                        s -> true
                )
        );
    }

    private Map<String, Map<String, Duration>> calculateGroupedStatusDurations(Function<ProductItemStatusEntity,
            String> groupKeyMapper) {
        return statusDurationCalculator.calculateGrouped(
                dataProvider.getAllStatuses(),
                s -> true,
                groupKeyMapper
        );
    }
}
