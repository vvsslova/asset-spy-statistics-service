package asset.spy.statistics.service.util;

import java.time.Duration;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DurationFormatter {

    public static String formatDurationToReadable(Duration duration) {
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return Stream.of(
                        hours > 0 ? hours + "h" : null,
                        minutes > 0 ? minutes + "m" : null,
                        (seconds > 0 || (hours == 0 && minutes == 0)) ? seconds + "s" : null
                ).filter(Objects::nonNull)
                .collect(Collectors.joining(" "));
    }
}
