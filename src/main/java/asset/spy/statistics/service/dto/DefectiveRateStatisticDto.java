package asset.spy.statistics.service.dto;

public record DefectiveRateStatisticDto(String group, Double defectRatePercent) implements GroupedDto {
}
