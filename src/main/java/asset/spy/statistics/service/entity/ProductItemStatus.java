package asset.spy.statistics.service.entity;

public enum ProductItemStatus {
    DEFECTIVE,
    LOST;

    public static ProductItemStatus fromString(String rawStatus) {
        try {
            return ProductItemStatus.valueOf(rawStatus.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
