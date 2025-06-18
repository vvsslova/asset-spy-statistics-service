package asset.spy.statistics.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Entity
@Table(name = "product_item")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
