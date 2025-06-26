package asset.spy.statistics.service.mapper;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
import asset.spy.statistics.service.dto.GroupedDto;
import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.util.DurationFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatisticsDtoMapper {

    public List<DefectiveRateStatisticDto> mapDefectRates(Map<String, Double> defectRatesByGroup) {
        return mapStatisticsByGroup(defectRatesByGroup, DefectiveRateStatisticDto::new);
    }

    public List<LostRateStatisticDto> mapLostRates(Map<String, Double> lostRatesByGroup) {
        return mapStatisticsByGroup(lostRatesByGroup, LostRateStatisticDto::new);
    }

    public List<AveragePriceStatisticDto> mapAveragePrices(Map<String, BigDecimal> averagePricesByGroup) {
        return mapStatisticsByGroup(averagePricesByGroup, AveragePriceStatisticDto::new);
    }

    public List<StatusDurationStatisticDto> mapGroupedStatusDurations(Map<String, Map<String,
            Duration>> statusDurationsByGroup) {
        return statusDurationsByGroup.entrySet().stream()
                .flatMap(groupEntry -> groupEntry.getValue().entrySet().stream()
                        .map(statusEntry -> new StatusDurationStatisticDto(
                                groupEntry.getKey(),
                                statusEntry.getKey(),
                                DurationFormatter.formatDurationToReadable(statusEntry.getValue())
                        ))
                ).collect(Collectors.toList());
    }

    public List<StatusDurationStatisticDto> mapOverallStatusDurations(Map<String, Duration> overallDurationsByStatus) {
        return overallDurationsByStatus.entrySet().stream()
                .map(entry -> new StatusDurationStatisticDto("overall", entry.getKey(),
                        DurationFormatter.formatDurationToReadable(entry.getValue())))
                .collect(Collectors.toList());
    }

    private <T extends GroupedDto, V> List<T> mapStatisticsByGroup(Map<String, V> valuesByGroup ,
                                                                   BiFunction<String, V, T> dtoFactory ) {
        return valuesByGroup.entrySet().stream()
                .map(entry -> dtoFactory.apply(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
