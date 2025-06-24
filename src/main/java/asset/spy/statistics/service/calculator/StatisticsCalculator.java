package asset.spy.statistics.service.calculator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public interface StatisticsCalculator<T, R> {
    Map<String, R> calculateGrouped(List<T> data, Predicate<T> filter, Function<T, String> keyMapper);
    R calculateOverall(List<T> data, Predicate<T> filter);
}
