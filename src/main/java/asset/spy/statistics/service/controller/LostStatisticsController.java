package asset.spy.statistics.service.controller;


import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.service.LostRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/lost")
@RequiredArgsConstructor
public class LostStatisticsController {

    private final LostRateService lostRateService;

    @GetMapping("/overall")
    public Double getOverallLostRate() {
        return lostRateService.getOverallLostRate();
    }

    @GetMapping("/vendor")
    public List<LostRateStatisticDto> getLostRateByVendor() {
        return lostRateService.getByVendor();
    }

    @GetMapping("/product-type")
    public List<LostRateStatisticDto> getLostRateByProductType() {
        return lostRateService.getByProductType();
    }

    @GetMapping("/vendor-product")
    public List<LostRateStatisticDto> getLostRateByVendorAndProductType() {
        return lostRateService.getByVendorAndProductType();
    }
}
