package asset.spy.statistics.service.service;

import asset.spy.statistics.service.entity.ProductEntity;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;

import java.util.List;

public interface StatisticsDataProvider {
    List<ProductEntity> getAllProducts();
    List<ProductItemStatusEntity> getAllStatuses();
}
