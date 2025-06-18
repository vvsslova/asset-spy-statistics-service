package asset.spy.statistics.service.service;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.mapper.StatisticsDtoMapper;
import asset.spy.statistics.service.strategy.StatisticsStrategy;
import asset.spy.statistics.service.strategy.defective.DefectiveRateByProductTypeStrategy;
import asset.spy.statistics.service.strategy.defective.DefectiveRateByVendorAndProductTypeStrategy;
import asset.spy.statistics.service.strategy.defective.DefectiveRateByVendorStrategy;
import asset.spy.statistics.service.strategy.defective.DefectiveRateOverallStrategy;
import asset.spy.statistics.service.strategy.duration.AverageStatusDurationByTypeStrategy;
import asset.spy.statistics.service.strategy.duration.AverageStatusDurationByVendorAndProductTypeStrategy;
import asset.spy.statistics.service.strategy.duration.AverageStatusDurationByVendorStrategy;
import asset.spy.statistics.service.strategy.duration.AverageStatusDurationStrategy;
import asset.spy.statistics.service.strategy.lost.LostRateByProductTypeStrategy;
import asset.spy.statistics.service.strategy.lost.LostRateByVendorAndProductTypeStrategy;
import asset.spy.statistics.service.strategy.lost.LostRateByVendorStrategy;
import asset.spy.statistics.service.strategy.lost.LostRateOverallStrategy;
import asset.spy.statistics.service.strategy.price.AveragePriceByProductTypeStrategy;
import asset.spy.statistics.service.strategy.price.AveragePriceByVendorAndProductTypeStrategy;
import asset.spy.statistics.service.strategy.price.AveragePriceByVendorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final List<StatisticsStrategy<?>> strategies;
    private final StatisticsDtoMapper mapper;

    public <T> T calculate(Class<? extends StatisticsStrategy<T>> clazz) {
        return strategies.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .map(clazz::cast)
                .map(StatisticsStrategy::calculate)
                .orElseThrow(() -> new IllegalArgumentException("No strategy found for " + clazz));
    }

    // Defect Rate
    public Double getDefectRateOverall() {
        return calculate(DefectiveRateOverallStrategy.class);
    }

    public List<DefectiveRateStatisticDto> getDefectRateByVendor() {
        return mapper.mapDefectRates(calculate(DefectiveRateByVendorStrategy.class));
    }

    public List<DefectiveRateStatisticDto> getDefectRateByProductType() {
        return mapper.mapDefectRates(calculate(DefectiveRateByProductTypeStrategy.class));
    }

    public List<DefectiveRateStatisticDto> getDefectRateByVendorAndProductType() {
        return mapper.mapDefectRates(calculate(DefectiveRateByVendorAndProductTypeStrategy.class));
    }

    // Lost Rate
    public Double getLostRateOverall() {
        return calculate(LostRateOverallStrategy.class);
    }

    public List<LostRateStatisticDto> getLostRateByVendor() {
        return mapper.mapLostRates(calculate(LostRateByVendorStrategy.class));
    }

    public List<LostRateStatisticDto> getLostRateByProductType() {
        return mapper.mapLostRates(calculate(LostRateByProductTypeStrategy.class));
    }

    public List<LostRateStatisticDto> getLostRateByVendorAndProductType() {
        return mapper.mapLostRates(calculate(LostRateByVendorAndProductTypeStrategy.class));
    }

    // Stage Durations
    public List<StatusDurationStatisticDto> getAverageStatusDurations() {
        return mapper.mapSingleStatusDuration(calculate(AverageStatusDurationStrategy.class));
    }

    public List<StatusDurationStatisticDto> getStatusDurationsByType() {
        return mapper.mapStatusesDurations(calculate(AverageStatusDurationByTypeStrategy.class));
    }

    public List<StatusDurationStatisticDto> getStatusDurationsByVendor() {
        return mapper.mapStatusesDurations(calculate(AverageStatusDurationByVendorStrategy.class));
    }

    public List<StatusDurationStatisticDto> getStatusDurationsByVendorAndProductType() {
        return mapper.mapStatusesDurations(calculate(AverageStatusDurationByVendorAndProductTypeStrategy.class));
    }

    // Average Prices
    public List<AveragePriceStatisticDto> getAveragePriceByProductType() {
        return mapper.mapAveragePrices(calculate(AveragePriceByProductTypeStrategy.class));
    }

    public List<AveragePriceStatisticDto> getAveragePriceByVendor() {
        return mapper.mapAveragePrices(calculate(AveragePriceByVendorStrategy.class));
    }

    public List<AveragePriceStatisticDto> getAveragePriceByVendorAndProductType() {
        return mapper.mapAveragePrices(calculate(AveragePriceByVendorAndProductTypeStrategy.class));
    }
}
