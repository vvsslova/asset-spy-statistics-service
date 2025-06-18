package asset.spy.statistics.service.strategy.calculator;

import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class ItemRateCalculator {

    public Map<String, Double> calculateRate(
            Stream<ProductItemStatusEntity> stream,
            Predicate<ProductItemStatusEntity> statusFilter,
            Function<ProductItemStatusEntity, String> keyMapper
    ) {
        Map<String, Set<UUID>> total = new HashMap<>();
        Map<String, Set<UUID>> matching = new HashMap<>();

        stream.filter(s -> s.getProductItem() != null)
                .forEach(status -> {
                    UUID id = status.getProductItem().getId();
                    String key = keyMapper.apply(status);

                    total.computeIfAbsent(key, k -> new HashSet<>()).add(id);
                    if (statusFilter.test(status)) {
                        matching.computeIfAbsent(key, k -> new HashSet<>()).add(id);
                    }
                });

        Map<String, Double> result = new HashMap<>();
        for (String key : total.keySet()) {
            int totalCount = total.get(key).size();
            int matchCount = matching.getOrDefault(key, Set.of()).size();
            result.put(key, totalCount == 0 ? 0.0 : (matchCount * 100.0) / totalCount);
        }
        return result;
    }

    public double calculateOverall(Stream<ProductItemStatusEntity> stream, Predicate<ProductItemStatusEntity> filter) {
        Set<UUID> all = new HashSet<>();
        Set<UUID> matching = new HashSet<>();

        stream.forEach(status -> {
            UUID id = status.getProductItem().getId();
            all.add(id);
            if (filter.test(status)) matching.add(id);
        });
        return all.isEmpty() ? 0.0 : (matching.size() * 100.0) / all.size();
    }
}
