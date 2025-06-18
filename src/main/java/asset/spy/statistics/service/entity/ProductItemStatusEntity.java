package asset.spy.statistics.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.OffsetDateTime;

@Entity
@Table(name = "product_item_status")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemStatusEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String status;
    private OffsetDateTime statusTransitionTime;

    @ManyToOne
    @JoinColumn(name = "product_item_id", nullable = false)
    private ProductItemEntity productItem;
}
