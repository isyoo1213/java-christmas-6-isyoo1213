package christmas.model;

import christmas.constants.Days;
import christmas.constants.Events;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class OrderTest {
    private static Menu defaultMenu;
    private static Date defaultDate;
    private static Map<Events, Integer> defaultEventsResult = new LinkedHashMap<>();

    @BeforeAll
    static void set() {
        Map<String, Integer> defaultOrderedMenus = new LinkedHashMap<>();
        defaultOrderedMenus.put("타파스", 1);
        defaultOrderedMenus.put("제로콜라", 1);
        defaultMenu =  new Menu(defaultOrderedMenus);
        defaultDate = new Date(26);
    }

    @DisplayName("방문 날짜 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideVisitingDateInfoTest() {
        Order testOrder = new Order(defaultDate, defaultMenu, defaultEventsResult);
        List<String> expectedResult = Arrays.asList("WEEKDAY", "26");
        Assertions.assertThat(testOrder.provideVisitingDateInfo()).isEqualTo(expectedResult);
    }

    @DisplayName("메뉴 주문 내역 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideOrderedMenusInfoTest() {
        Order testOrder = new Order(defaultDate, defaultMenu, defaultEventsResult);
        List<String> expectedResult = Arrays.asList("타파스 1개", "제로콜라 1개");
        Assertions.assertThat(testOrder.provideOrderedMenusInfo()).c(expectedResult);
    }

    @DisplayName("할인 전 총주문 금액 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideOrderedMenusTotalPriceInfoTest() {
        Order testOrder = new Order(defaultDate, defaultMenu, defaultEventsResult);
        List<String> expectedResult = Arrays.asList("8,500");
        Assertions.assertThat(testOrder.provideOrderedMenusTotalPriceInfo()).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 적용 결과 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateEventsResultInfoTest() {
        Date testVisitingDate = new Date(25);
        Map<String, Integer> testOrderedMenus = new LinkedHashMap<>();
        testOrderedMenus.put("티본스테이크", 2);
        testOrderedMenus.put("초코케이크", 2);
        testOrderedMenus.put("샴페인", 3);
        Menu testMenu = new Menu(testOrderedMenus);
        Map<Events, Integer> testEventsResult = new LinkedHashMap<>();
        testEventsResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 3400);
        testEventsResult.put(Events.WEEKDAY_DISCOUNT, 4046);
        testEventsResult.put(Events.SPECIAL_DISCOUNT, 1000);
        testEventsResult.put(Events.GIFT_PRESENTATION, 25000);
        Order testOrder = new Order(testVisitingDate, testMenu, testEventsResult);
        List<String> expectedResult = Arrays.asList("크리스마스 디데이 할인: -3,400원", "평일 할인: -4,046원", "특별 할인: -1,000원", "증정 이벤트: -25,000원");

        Assertions.assertThat(testOrder.provideEventsResultInfo()).isEqualTo(expectedResult);
    }
}
