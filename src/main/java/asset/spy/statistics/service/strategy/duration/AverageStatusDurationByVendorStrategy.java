package asset.spy.statistics.service.strategy.duration;

import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.service.StatisticsDataProvider;
import asset.spy.statistics.service.strategy.StatisticsStrategy;
import asset.spy.statistics.service.strategy.calculator.StatusDurationCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AverageStatusDurationByVendorStrategy implements StatisticsStrategy<Map<String, Map<String, Duration>>> {

    private final StatisticsDataProvider dataProvider;
    private final StatusDurationCalculator calculator;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Map<String, Duration>> calculate() {
        try (Stream<ProductItemStatusEntity> stream = dataProvider.getAllStatusesAsStream()) {
            return calculator.calculateStatusDurations(
                    stream,
                    s -> s.getProductItem().getProduct().getVendor().getName()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error calculating average status duration by vendor", e);
        }
    }
}
