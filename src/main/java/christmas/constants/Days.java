package christmas.constants;

import java.util.*;
import java.util.stream.Collectors;

import static christmas.constants.EventConstants.CHRISTMAS_DATE;

public enum Days {
    WEEKDAY("평일", Arrays.asList(0, 3, 4, 5, 6)),
    WEEKEND("주말", Arrays.asList(1, 2)),
    SPECIALDAY("스페셜데이", List.of(3)),
    UNCATEGORIZED("미분류", List.of());

    private static final Integer DAYS_OF_WEEK = 7;

    private final String category;
    private final List<Integer> dividedDays;


    Days(String category, List<Integer> dividedDays) {
        this.category = category;
        this.dividedDays = dividedDays;
    }

    public static Set<Days> calculateDate(Integer date) {
        Set<Days> appliedDays = new LinkedHashSet<>();
        if (Objects.equals(date, CHRISTMAS_DATE)) {
            appliedDays.add(SPECIALDAY);
        }
        int dividedDate = date % DAYS_OF_WEEK;
        List<Days> findDays = Arrays.stream(Days.values())
                .filter((days) -> days.hasDividedDate(dividedDate))
                .collect(Collectors.toList());
        appliedDays.addAll(findDays);
        return appliedDays;
    }

    private boolean hasDividedDate(Integer dividedDate) {
        return dividedDays.stream().anyMatch(dividedDays -> dividedDays.equals(dividedDate));
    }
}
