package christmas.constants;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Events {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIFT_PRESENTATION("증정 이벤트");

    private static final Map<String, Events> eventNames =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Events::eventName, Function.identity())));
    private final String eventName;

    Events(String eventName) {
        this.eventName = eventName;
    }

    private String eventName() {
        return eventName;
    }

}
