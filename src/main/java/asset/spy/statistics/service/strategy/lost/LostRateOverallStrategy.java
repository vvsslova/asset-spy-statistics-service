package asset.spy.statistics.service.strategy.lost;

import asset.spy.statistics.service.entity.ProductItemStatus;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.service.StatisticsDataProvider;
import asset.spy.statistics.service.strategy.StatisticsStrategy;
import asset.spy.statistics.service.strategy.calculator.ItemRateCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LostRateOverallStrategy implements StatisticsStrategy<Double> {

    private final StatisticsDataProvider dataProvider;
    private final ItemRateCalculator calculator;

    @Override
    @Transactional(readOnly = true)
    public Double calculate() {
        try (Stream<ProductItemStatusEntity> stream = dataProvider.getAllStatusesAsStream()) {
            return calculator.calculateOverall(
                    stream,
                    s -> ProductItemStatus.LOST == ProductItemStatus.fromString(s.getStatus())
            );
        } catch (Exception e) {
            throw new RuntimeException("Error calculating lost rate for all products", e);
        }
    }
}
