package asset.spy.statistics.service.calculator;

import asset.spy.statistics.service.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class PriceCalculator implements StatisticsCalculator<ProductEntity, BigDecimal> {

    @Override
    public Map<String, BigDecimal> calculateGrouped(List<ProductEntity> products,
                                                    Predicate<ProductEntity> filter,
                                                    Function<ProductEntity, String> keyMapper) {
        return calculateAveragePrice(products.stream().filter(filter).toList(), keyMapper);
    }

    @Override
    public BigDecimal calculateOverall(List<ProductEntity> products,
                                       Predicate<ProductEntity> filter) {
        return average(products.stream()
                .filter(filter)
                .map(ProductEntity::getPrice)
                .toList());
    }

    private Map<String, BigDecimal> calculateAveragePrice(List<ProductEntity> products,
                                                          Function<ProductEntity, String> keyMapper) {
        Map<String, List<BigDecimal>> grouped = new HashMap<>();

        for (ProductEntity product : products) {
            grouped.computeIfAbsent(keyMapper.apply(product), k -> new ArrayList<>())
                    .add(product.getPrice());
        }
        return grouped.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> average(e.getValue())
                ));
    }

    private BigDecimal average(List<BigDecimal> values) {
        if (values.isEmpty()) return BigDecimal.ZERO;
        return values.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(values.size()), 2, RoundingMode.HALF_UP);
    }
}
