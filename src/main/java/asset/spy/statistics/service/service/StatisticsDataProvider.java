package asset.spy.statistics.service.service;

import asset.spy.statistics.service.entity.ProductEntity;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;

import java.util.stream.Stream;

public interface StatisticsDataProvider {
    Stream<ProductEntity> getAllProductsAsStream();
    Stream<ProductItemStatusEntity> getAllStatusesAsStream();
}
