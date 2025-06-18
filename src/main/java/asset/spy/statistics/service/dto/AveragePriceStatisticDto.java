package asset.spy.statistics.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AveragePriceStatisticDto {
    private String group;
    private BigDecimal averagePrice;
}
