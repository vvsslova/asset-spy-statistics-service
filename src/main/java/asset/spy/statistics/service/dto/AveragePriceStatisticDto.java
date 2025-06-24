package asset.spy.statistics.service.dto;

import java.math.BigDecimal;

public record AveragePriceStatisticDto(String group, BigDecimal averagePrice) implements GroupedDto {
}
