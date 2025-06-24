package asset.spy.statistics.service.util;

import asset.spy.statistics.service.entity.ProductEntity;
import asset.spy.statistics.service.entity.ProductItemStatusEntity;

import java.util.function.Function;

public final class ProductKeyMappers {

    // For ProductEntity
    public static Function<ProductEntity, String> byVendorForProduct() {
        return p -> p.getVendor().getName();
    }

    public static Function<ProductEntity, String> byProductTypeForProduct() {
        return ProductEntity::getType;
    }

    public static Function<ProductEntity, String> byVendorAndProductTypeForProduct() {
        return p -> byVendorForProduct().apply(p) + " - " + byProductTypeForProduct().apply(p);
    }

    // For ProductItemStatusEntity
    public static Function<ProductItemStatusEntity, String> byVendorForStatus() {
        return s -> s.getProductItem().getProduct().getVendor().getName();
    }

    public static Function<ProductItemStatusEntity, String> byProductTypeForStatus() {
        return s -> s.getProductItem().getProduct().getType();
    }

    public static Function<ProductItemStatusEntity, String> byVendorAndProductTypeForStatus() {
        return s -> byVendorForStatus().apply(s) + " - " + byProductTypeForStatus().apply(s);
    }
}
