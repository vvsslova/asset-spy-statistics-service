package asset.spy.statistics.service.service;

import asset.spy.statistics.service.entity.ProductEntity;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import asset.spy.statistics.service.repository.ProductItemStatusRepository;
import asset.spy.statistics.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StatisticsDataProviderImpl implements StatisticsDataProvider {

    private final ProductRepository productRepository;
    private final ProductItemStatusRepository statusRepository;

    @Override
    @Transactional(readOnly = true)
    public Stream<ProductEntity> getAllProductsAsStream() {
        return productRepository.streamAllWithVendors();
    }

    @Override
    @Transactional(readOnly = true)
    public Stream<ProductItemStatusEntity> getAllStatusesAsStream() {
        return statusRepository.streamAllWithRelations();
    }
}
