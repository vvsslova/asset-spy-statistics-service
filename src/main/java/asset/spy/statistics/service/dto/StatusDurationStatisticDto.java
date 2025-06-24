package asset.spy.statistics.service.dto;

public record StatusDurationStatisticDto(String group, String status, String duration) implements GroupedDto {
}
