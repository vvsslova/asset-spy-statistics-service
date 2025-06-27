package asset.spy.statistics.service.service;


import java.util.List;

public interface GroupedStatisticsService<T> {
    List<T> getByVendor();
    List<T> getByProductType();
    List<T> getByVendorAndProductType();
}
