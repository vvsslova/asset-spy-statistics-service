package asset.spy.statistics.service.strategy.calculator;

import asset.spy.statistics.service.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class PriceCalculator {

    public Map<String, BigDecimal> calculateAveragePrice(
            Stream<ProductEntity> stream,
            Function<ProductEntity, String> keyMapper
    ) {
        Map<String, List<BigDecimal>> pricesByKey = new HashMap<>();

        stream.forEach(product -> {
            String key = keyMapper.apply(product);
            pricesByKey.computeIfAbsent(key, k -> new ArrayList<>()).add(product.getPrice());
        });

        return pricesByKey.entrySet().stream()
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
