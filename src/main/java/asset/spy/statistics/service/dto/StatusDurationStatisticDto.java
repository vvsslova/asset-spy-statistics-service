package asset.spy.statistics.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusDurationStatisticDto {
    private String group;
    private String status;
    private String duration;
}
