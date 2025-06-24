package asset.spy.statistics.service.controller;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.service.AveragePriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/statistics/average-price")
@RequiredArgsConstructor
public class AveragePriceStatisticsController {

    private final AveragePriceService averagePriceService;

    @GetMapping("/product-type")
    public List<AveragePriceStatisticDto> getAveragePriceByProductType() {
        return averagePriceService.getByProductType();
    }

    @GetMapping("/vendor")
    public List<AveragePriceStatisticDto> getAveragePriceByVendor() {
        return averagePriceService.getByVendor();
    }

    @GetMapping("/vendor-product")
    public List<AveragePriceStatisticDto> getAveragePriceByVendorAndProductType() {
        return averagePriceService.getByVendorAndProductType();
    }
}
