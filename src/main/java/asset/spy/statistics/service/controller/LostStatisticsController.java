package asset.spy.statistics.service.controller;


import asset.spy.statistics.service.dto.LostRateStatisticDto;
import asset.spy.statistics.service.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/lost")
@RequiredArgsConstructor
public class LostStatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/overall")
    public Double getOverallLostRate() {
        return statisticsService.getLostRateOverall();
    }

    @GetMapping("/vendor")
    public List<LostRateStatisticDto> getLostRateByVendor() {
        return statisticsService.getLostRateByVendor();
    }

    @GetMapping("/product-type")
    public List<LostRateStatisticDto> getLostRateByProductType() {
        return statisticsService.getLostRateByProductType();
    }

    @GetMapping("/vendor-product")
    public List<LostRateStatisticDto> getLostRateByVendorAndProductType() {
        return statisticsService.getLostRateByVendorAndProductType();
    }
}
