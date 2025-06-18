package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
import asset.spy.statistics.service.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/defective")
@RequiredArgsConstructor
public class DefectiveStatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/overall")
    public Double getOverallDefectRate() {
        return statisticsService.getDefectRateOverall();
    }

    @GetMapping("/vendor")
    public List<DefectiveRateStatisticDto> getDefectRateByVendor() {
        return statisticsService.getDefectRateByVendor();
    }

    @GetMapping("/product-type")
    public List<DefectiveRateStatisticDto> getDefectRateByProductType() {
        return statisticsService.getDefectRateByProductType();
    }

    @GetMapping("/vendor-product")
    public List<DefectiveRateStatisticDto> getDefectRateByVendorAndProductType() {
        return statisticsService.getDefectRateByVendorAndProductType();
    }
}
