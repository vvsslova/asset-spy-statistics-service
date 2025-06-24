package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.StatusDurationStatisticDto;
import asset.spy.statistics.service.service.StatusDurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/status-duration")
@RequiredArgsConstructor
public class StatusDurationStatisticController {

    private final StatusDurationService statusDurationService;

    @GetMapping("/overall")
    public List<StatusDurationStatisticDto> getAllStatusesDurations() {
        return statusDurationService.getOverallStatusDuration();
    }

    @GetMapping("/product-type")
    public List<StatusDurationStatisticDto> getStatusDurationsByType() {
        return statusDurationService.getByProductType();
    }

    @GetMapping("/vendor")
    public List<StatusDurationStatisticDto> getStatusDurationsByVendor() {
        return statusDurationService.getByVendor();
    }

    @GetMapping("/vendor-product")
    public List<StatusDurationStatisticDto> getStatusDurationsByVendorAndProductType() {
        return statusDurationService.getByVendorAndProductType();
    }
}
