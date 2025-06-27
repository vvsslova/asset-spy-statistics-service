package asset.spy.statistics.service.repository;

import asset.spy.statistics.service.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = {"vendor"})
    List<ProductEntity> findAll();
}
