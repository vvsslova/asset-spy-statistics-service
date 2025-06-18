package asset.spy.statistics.service.strategy.calculator;

import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class StatusDurationCalculator {

    public Map<String, Map<String, Duration>> calculateStatusDurations(
            Stream<ProductItemStatusEntity> stream,
            Function<ProductItemStatusEntity, String> groupKeyMapper
    ) {
        Map<UUID, List<ProductItemStatusEntity>> groupedByItem = stream
                .collect(Collectors.groupingBy(
                        s -> s.getProductItem().getId(),
                        Collectors.toList()
                ));

        Map<String, Map<String, List<Duration>>> durations = new HashMap<>();

        for (var entry : groupedByItem.entrySet()) {
            List<ProductItemStatusEntity> statusList = entry.getValue();
            statusList.sort(Comparator.comparing(ProductItemStatusEntity::getStatusTransitionTime));

            for (int i = 0; i < statusList.size() - 1; i++) {
                var current = statusList.get(i);
                var next = statusList.get(i + 1);

                String key = groupKeyMapper.apply(current);
                String stage = current.getStatus();

                Duration duration = Duration.between(
                        current.getStatusTransitionTime(),
                        next.getStatusTransitionTime()
                );

                durations.computeIfAbsent(key, k -> new HashMap<>())
                        .computeIfAbsent(stage, k -> new ArrayList<>())
                        .add(duration);
            }
        }

        return durations.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream()
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        ee -> averageDuration(ee.getValue())
                                ))
                ));
    }

    private Duration averageDuration(List<Duration> durations) {
        return durations.stream()
                .reduce(Duration.ZERO, Duration::plus)
                .dividedBy(durations.size());
    }
}
