package asset.spy.statistics.service.service;

import asset.spy.statistics.service.dto.AveragePriceStatisticDto;
import asset.spy.statistics.service.entity.ProductEntity;
import asset.spy.statistics.service.mapper.StatisticsDtoMapper;
import asset.spy.statistics.service.calculator.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AveragePriceService implements GroupedStatisticsService<AveragePriceStatisticDto> {

    private final StatisticsDataProvider dataProvider;
    private final PriceCalculator priceCalculator;
    private final StatisticsDtoMapper mapper;

    @Override
    public List<AveragePriceStatisticDto> getByVendor() {
        return mapper.mapAveragePrices(calculateGroupedAveragePrice(ProductEntity::getVendorKey));
    }

    @Override
    public List<AveragePriceStatisticDto> getByProductType() {
        return mapper.mapAveragePrices(calculateGroupedAveragePrice(ProductEntity::getProductTypeKey));
    }

    @Override
    public List<AveragePriceStatisticDto> getByVendorAndProductType() {
        return mapper.mapAveragePrices(calculateGroupedAveragePrice(ProductEntity::getVendorAndProductTypeKey));
    }

    private Map<String, BigDecimal> calculateGroupedAveragePrice(Function<ProductEntity, String> keyMapper) {
        return priceCalculator.calculateGrouped(
                dataProvider.getAllProducts(),
                p -> true,
                keyMapper
        );
    }
}
