package asset.spy.statistics.service.calculator;

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
import java.util.function.Predicate;

@Component
public class StatusDurationCalculator implements StatisticsCalculator<ProductItemStatusEntity, Map<String, Duration>> {

    private static final String OVERALL_GROUP_KEY = "OVERALL";

    @Override
    public Map<String, Map<String, Duration>> calculateGrouped(List<ProductItemStatusEntity> statuses,
                                                               Predicate<ProductItemStatusEntity> filter,
                                                               Function<ProductItemStatusEntity, String> groupKeyMapper) {
        return calculate(statuses.stream().filter(filter).toList(), groupKeyMapper);
    }

    @Override
    public Map<String, Duration> calculateOverall(List<ProductItemStatusEntity> statuses,
                                                  Predicate<ProductItemStatusEntity> filter) {
        return calculateOverallDurations(statuses.stream().filter(filter).toList());
    }

    private Map<String, Map<String, Duration>> calculate(
            List<ProductItemStatusEntity> statuses,
            Function<ProductItemStatusEntity, String> groupKeyMapper) {

        Map<UUID, List<ProductItemStatusEntity>> groupedByItem = groupByItemId(statuses);
        Map<String, Map<String, List<Duration>>> durations = groupStatusDurations(groupedByItem, groupKeyMapper);
        return averageDurationByGroup(durations);
    }

    private Map<UUID, List<ProductItemStatusEntity>> groupByItemId(List<ProductItemStatusEntity> statuses) {
        Map<UUID, List<ProductItemStatusEntity>> grouped = new HashMap<>();
        for (ProductItemStatusEntity status : statuses) {
            grouped.computeIfAbsent(status.getProductItem().getId(), k -> new ArrayList<>()).add(status);
        }
        return grouped;
    }

    private Map<String, Map<String, List<Duration>>> groupStatusDurations(
            Map<UUID, List<ProductItemStatusEntity>> groupedByItem,
            Function<ProductItemStatusEntity, String> groupKeyMapper) {

        Map<String, Map<String, List<Duration>>> durations = new HashMap<>();

        for (var entry : groupedByItem.entrySet()) {
            List<ProductItemStatusEntity> list = entry.getValue();
            list.sort(Comparator.comparing(ProductItemStatusEntity::getStatusTransitionTime));

            for (int i = 0; i < list.size() - 1; i++) {
                var current = list.get(i);
                var next = list.get(i + 1);

                String key = groupKeyMapper.apply(current);
                String status = current.getStatus();
                Duration duration = Duration.between(current.getStatusTransitionTime(), next.getStatusTransitionTime());

                durations.computeIfAbsent(key, k -> new HashMap<>())
                        .computeIfAbsent(status, k -> new ArrayList<>())
                        .add(duration);
            }
        }
        return durations;
    }

    private Map<String, Map<String, Duration>> averageDurationByGroup(Map<String, Map<String, List<Duration>>> durations) {
        Map<String, Map<String, Duration>> result = new HashMap<>();

        for (var groupEntry : durations.entrySet()) {
            Map<String, Duration> statusAverages = new HashMap<>();

            for (var statusEntry : groupEntry.getValue().entrySet()) {
                statusAverages.put(statusEntry.getKey(), averageDuration(statusEntry.getValue()));
            }
            result.put(groupEntry.getKey(), statusAverages);
        }
        return result;
    }

    private Map<String, Duration> calculateOverallDurations(List<ProductItemStatusEntity> statuses) {
        Map<String, Map<String, Duration>> result = calculate(statuses, s -> OVERALL_GROUP_KEY);
        return result.get(OVERALL_GROUP_KEY);
    }

    private Duration averageDuration(List<Duration> durations) {
        return durations.stream()
                .reduce(Duration.ZERO, Duration::plus)
                .dividedBy(durations.size());
    }
}
