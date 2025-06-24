package asset.spy.statistics.service.repository;

import asset.spy.statistics.service.entity.ProductItemStatusEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductItemStatusRepository extends JpaRepository<ProductItemStatusEntity, Long> {

    @EntityGraph(attributePaths = {
            "productItem",
            "productItem.product",
            "productItem.product.vendor"
    })
    List<ProductItemStatusEntity> findAll();
}
