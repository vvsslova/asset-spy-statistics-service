package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.DefectiveRateStatisticDto;
import asset.spy.statistics.service.service.DefectRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/defective")
@RequiredArgsConstructor
public class DefectiveStatisticsController {

    private final DefectRateService defectRateService;

    @GetMapping("/overall")
    public Double getOverallDefectRate() {
        return defectRateService.getOverallDefectRate();
    }

    @GetMapping("/vendor")
    public List<DefectiveRateStatisticDto> getDefectRateByVendor() {
        return defectRateService.getByVendor();
    }

    @GetMapping("/product-type")
    public List<DefectiveRateStatisticDto> getDefectRateByProductType() {
        return defectRateService.getByProductType();
    }

    @GetMapping("/vendor-product")
    public List<DefectiveRateStatisticDto> getDefectRateByVendorAndProductType() {
        return defectRateService.getByVendorAndProductType();
    }
}
