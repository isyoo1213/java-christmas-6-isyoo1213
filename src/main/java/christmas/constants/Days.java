package christmas.constants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Days {
    WEEKDAY("평일", Arrays.asList(0, 4, 5, 6)),
    WEEKEND("주말", Arrays.asList(1, 2)),
    SPECIALDAY("스페셜데이", List.of(3)),
    UNCATEGORIZED("미분류", List.of());


    private final String category;
    private final List<Integer> dividedDays;


    Days(String category, List<Integer> dividedDays) {
        this.category = category;
        this.dividedDays = dividedDays;
    }

    public static Days calculateDate(Integer date) {
        if (Objects.equals(date, EventConstants.CHRISTMAS_DATE)) {
            return SPECIALDAY;
        }
        int dividedDate = date % EventConstants.DAYS_OF_WEEK;
        return Arrays.stream(Days.values())
                .filter((dividedDays) -> dividedDays.hasDividedDate(dividedDate))
                .findAny()
                .orElse(UNCATEGORIZED);
    }

    private boolean hasDividedDate(Integer dividedDate) {
        return dividedDays.stream().anyMatch(dividedDays -> dividedDays.equals(dividedDate));
    }
}
