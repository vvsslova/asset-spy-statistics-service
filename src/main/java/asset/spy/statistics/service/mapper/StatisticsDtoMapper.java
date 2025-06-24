package asset.spy.statistics.service.mapper;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
import asset.spy.statistics.service.dto.GroupedDto;
import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.util.DurationFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatisticsDtoMapper {

    public List<DefectiveRateStatisticDto> mapDefectRates(Map<String, Double> data) {
        return mapRates(data, DefectiveRateStatisticDto::new);
    }

    public List<LostRateStatisticDto> mapLostRates(Map<String, Double> data) {
        return mapRates(data, LostRateStatisticDto::new);
    }

    public List<AveragePriceStatisticDto> mapAveragePrices(Map<String, java.math.BigDecimal> data) {
        return mapRates(data, AveragePriceStatisticDto::new);
    }

    public List<StatusDurationStatisticDto> mapStatusesDurations(Map<String, Map<String, Duration>> raw) {
        return raw.entrySet().stream()
                .flatMap(e -> e.getValue().entrySet().stream()
                        .map(status -> new StatusDurationStatisticDto(
                                e.getKey(),
                                status.getKey(),
                                DurationFormatter.formatDurationToReadable(status.getValue())
                        ))
                ).collect(Collectors.toList());
    }

    public List<StatusDurationStatisticDto> mapSingleStatusDuration(Map<String, Duration> raw) {
        return raw.entrySet().stream()
                .map(e -> new StatusDurationStatisticDto("overall", e.getKey(),
                        DurationFormatter.formatDurationToReadable(e.getValue())))
                .collect(Collectors.toList());
    }

    private <T extends GroupedDto, V> List<T> mapRates(Map<String, V> data, BiFunction<String, V, T> constructor) {
        return data.entrySet().stream()
                .map(e -> constructor.apply(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
