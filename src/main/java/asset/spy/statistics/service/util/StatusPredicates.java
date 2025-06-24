package asset.spy.statistics.service.util;

import asset.spy.statistics.service.entity.ProductItemStatus;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;

import java.util.function.Predicate;

public class StatusPredicates {

    public static final Predicate<ProductItemStatusEntity> IS_DEFECTIVE = hasStatus(ProductItemStatus.DEFECTIVE);
    public static final Predicate<ProductItemStatusEntity> IS_LOST = hasStatus(ProductItemStatus.LOST);

    public static Predicate<ProductItemStatusEntity> hasStatus(ProductItemStatus target) {
        return s -> ProductItemStatus.fromString(s.getStatus()).filter(st -> st == target).isPresent();
    }
}
