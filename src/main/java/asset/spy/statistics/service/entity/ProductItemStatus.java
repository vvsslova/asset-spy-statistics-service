package asset.spy.statistics.service.entity;

import java.util.Optional;

public enum ProductItemStatus {
    DEFECTIVE,
    LOST;

    public static Optional<ProductItemStatus> fromString(String rawStatus) {
        try {
            return Optional.of(valueOf(rawStatus.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
