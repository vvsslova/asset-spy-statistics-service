package asset.spy.statistics.service.dto;

public record LostRateStatisticDto(String group, Double lostRatePercent) implements GroupedDto {
}
