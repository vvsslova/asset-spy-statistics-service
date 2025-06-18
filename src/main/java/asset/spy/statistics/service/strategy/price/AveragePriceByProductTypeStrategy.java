package asset.spy.statistics.service.strategy.price;

import asset.spy.statistics.service.entity.ProductEntity;

import asset.spy.statistics.service.service.StatisticsDataProvider;
import asset.spy.statistics.service.strategy.StatisticsStrategy;
import asset.spy.statistics.service.strategy.calculator.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AveragePriceByProductTypeStrategy implements StatisticsStrategy<Map<String, BigDecimal>> {

    private final StatisticsDataProvider dataProvider;
    private final PriceCalculator priceCalculator;

    @Override
    @Transactional(readOnly = true)
    public Map<String, BigDecimal> calculate() {
        try (Stream<ProductEntity> stream = dataProvider.getAllProductsAsStream()) {
            return priceCalculator.calculateAveragePrice(stream, ProductEntity::getType);
        } catch (Exception e) {
            throw new RuntimeException("Error calculating average price by product type", e);
        }
    }
}
