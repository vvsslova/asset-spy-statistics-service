package asset.spy.statistics.service.repository;

import asset.spy.statistics.service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("""
        SELECT p FROM ProductEntity p
        JOIN FETCH p.vendor
    """)
    Stream<ProductEntity> streamAllWithVendors();
}
