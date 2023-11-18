package christmas.utils;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class EventCalculatorTest {
    EventCalculator eventCalculator = new EventCalculator();
    private static final Map<String, Integer> testMenuOrder = new HashMap<>();

    @BeforeAll
    static void setTestMenuOrder() {
        testMenuOrder.put("타파스", 1);
        testMenuOrder.put("제로콜라", 1);
    }

    @DisplayName("이벤트 참여 가능 여부 테스트 - 최소 주문 금액 이하 데이터 - 성공")
    @Test
    void isParticipateUnderMinimumTotalPriceTest() {
        Menus testMenus = new Menus(testMenuOrder);
        boolean expectedResult = false;
        assertThat(eventCalculator.isParticipate(testMenus)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 참여 가능 여부 테스트 - 음료만 주문한 데이터 - 성공")
    @Test
    void isParticipateOnlyBeverageCategoryTest() {
        Map<String, Integer> testMenuOrder = new HashMap<>();
        testMenuOrder.put("제로콜라", 10);
        Menus testMenus = new Menus(testMenuOrder);
        boolean expectedResult = false;
        assertThat(eventCalculator.isParticipate(testMenus)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 계산 테스트 - 증정 이벤트 미참여 데이터 - 성공")
    @Test
    void calculateEventsNonGiftTest() {
        Map<String, Integer> testMenuOrder = new LinkedHashMap<>();
        testMenuOrder.put("타파스", 2);
        testMenuOrder.put("양송이수프", 2);
        testMenuOrder.put("초코케이크", 3);
        Menus testMenus = new Menus(testMenuOrder);
        Date testDate = new Date(25);

        Map<Events, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 3400);
        expectedResult.put(Events.WEEKDAY_DISCOUNT, 6069);
        expectedResult.put(Events.SPECIAL_DISCOUNT, 1000);
        assertThat(eventCalculator.calculateEvents(testMenus, testDate)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 계산 테스트 - 크리스마스 디데이 할인 미참여 데이터 - 성공")
    @Test
    void calculateEventNonDdayTest() {
        Map<String, Integer> testMenuOrder = new LinkedHashMap<>();
        testMenuOrder.put("타파스", 2);
        testMenuOrder.put("양송이수프", 2);
        testMenuOrder.put("초코케이크", 3);
        Menus testMenus = new Menus(testMenuOrder);
        Date testDate = new Date(26);

        Map<Events, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(Events.WEEKDAY_DISCOUNT, 6069);
        assertThat(eventCalculator.calculateEvents(testMenus, testDate)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 계산 테스트 - 특별 할인 미참여 데이터 - 성공")
    @Test
    void calculateEventNonSpcialTest() {
        Map<String, Integer> testMenuOrder = new LinkedHashMap<>();
        testMenuOrder.put("타파스", 2);
        testMenuOrder.put("양송이수프", 2);
        testMenuOrder.put("초코케이크", 3);
        Menus testMenus = new Menus(testMenuOrder);
        Date testDate = new Date(18);

        Map<Events, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 2700);
        expectedResult.put(Events.WEEKDAY_DISCOUNT, 6069);
        assertThat(eventCalculator.calculateEvents(testMenus, testDate)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 계산 테스트 - 증정 이벤트 참여 데이터 - 성공")
    @Test
    void calculateEventWithGiftTest() {
        Map<String, Integer> testMenuOrder = new LinkedHashMap<>();
        testMenuOrder.put("타파스", 2);
        testMenuOrder.put("양송이수프", 2);
        testMenuOrder.put("초코케이크", 3);
        testMenuOrder.put("티본스테이크", 10);
        Menus testMenus = new Menus(testMenuOrder);
        Date testDate = new Date(18);

        Map<Events, Integer> expectedResult = new LinkedHashMap<>();
        expectedResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 2700);
        expectedResult.put(Events.WEEKDAY_DISCOUNT, 6069);
        expectedResult.put(Events.GIFT_PRESENTATION, 25000);
        assertThat(eventCalculator.calculateEvents(testMenus, testDate)).isEqualTo(expectedResult);
    }
}
