package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/status-duration")
@RequiredArgsConstructor
public class StatusDurationStatisticController {

    private final StatisticsService statisticsService;

    @GetMapping("/overall")
    public List<StatusDurationStatisticDto> getAllStatusesDurations() {
        return statisticsService.getAverageStatusDurations();
    }

    @GetMapping("/product-type")
    public List<StatusDurationStatisticDto> getStatusDurationsByType() {
        return statisticsService.getStatusDurationsByType();
    }

    @GetMapping("/vendor")
    public List<StatusDurationStatisticDto> getStatusDurationsByVendor() {
        return statisticsService.getStatusDurationsByVendor();
    }

    @GetMapping("/vendor-product")
    public List<StatusDurationStatisticDto> getStatusDurationsByVendorAndProductType() {
        return statisticsService.getStatusDurationsByVendorAndProductType();
    }
}
