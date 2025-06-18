package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/average-price")
@RequiredArgsConstructor
public class AveragePriceStatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/product-type")
    public List<AveragePriceStatisticDto> getAveragePriceByProductType() {
        return statisticsService.getAveragePriceByProductType();
    }

    @GetMapping("/vendor")
    public List<AveragePriceStatisticDto> getAveragePriceByVendor() {
        return statisticsService.getAveragePriceByVendor();
    }

    @GetMapping("/vendor-product")
    public List<AveragePriceStatisticDto> getAveragePriceByVendorAndProductType() {
        return statisticsService.getAveragePriceByVendorAndProductType();
    }
}
