package christmas.constants;

import java.util.Arrays;
import java.util.List;

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
}
