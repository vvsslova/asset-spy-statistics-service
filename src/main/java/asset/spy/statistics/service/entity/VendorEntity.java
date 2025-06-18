package asset.spy.statistics.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "vendor")
@Immutable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {

    @Id
    private Long id;
    private String name;
}
