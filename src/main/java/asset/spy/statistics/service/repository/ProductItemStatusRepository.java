package asset.spy.statistics.service.repository;

import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ProductItemStatusRepository extends JpaRepository<ProductItemStatusEntity, Long> {

    @Query("""
        SELECT s FROM ProductItemStatusEntity s
        JOIN FETCH s.productItem pi
        JOIN FETCH pi.product p
        JOIN FETCH p.vendor v
    """)
    Stream<ProductItemStatusEntity> streamAllWithRelations();
}
