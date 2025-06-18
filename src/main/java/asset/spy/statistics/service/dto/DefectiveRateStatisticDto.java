package asset.spy.statistics.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DefectiveRateStatisticDto {
    private String group;
    private Double defectRatePercent;
}
