package asset.spy.statistics.service.strategy.lost;

import asset.spy.statistics.service.entity.ProductItemStatus;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.service.StatisticsDataProvider;
import asset.spy.statistics.service.strategy.StatisticsStrategy;
import asset.spy.statistics.service.strategy.calculator.ItemRateCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class LostRateByProductTypeStrategy implements StatisticsStrategy<Map<String, Double>> {

    private final StatisticsDataProvider dataProvider;
    private final ItemRateCalculator calculator;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Double> calculate() {
        try (Stream<ProductItemStatusEntity> stream = dataProvider.getAllStatusesAsStream()) {
            return calculator.calculateRate(
                    stream,
                    s -> ProductItemStatus.LOST == ProductItemStatus.fromString(s.getStatus()),
                    s -> s.getProductItem().getProduct().getType()
            );
        } catch (Exception e) {
            throw new RuntimeException("Error calculating lost rate by product type", e);
        }
    }
}
