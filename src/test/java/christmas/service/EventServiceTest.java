package christmas.service;

import christmas.constants.Badges;
import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menus;
import christmas.model.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static christmas.constants.EventConstants.*;
import static christmas.service.EventService.*;
import static org.assertj.core.api.Assertions.*;

public class EventServiceTest {
    private static Menus defaultMenus;
    private static Date defaultDate;
    private static final Map<Events, Integer> defaultEventsResult = new HashMap<>();
    EventService eventService = new EventService();

    @BeforeAll
    static void set() {
        Map<String, Integer> defaultOrderedMenus = new HashMap<>();
        defaultOrderedMenus.put("타파스", 1);
        defaultOrderedMenus.put("제로콜라", 1);
        EventServiceTest.defaultMenus =  new Menus(defaultOrderedMenus);
        defaultDate = new Date(26);
    }

    @DisplayName("방문 날짜 저장 테스트 - 정상 데이터 - 성공")
    @Test
    void saveVisitingDateTest() {
        int testDate = 25;
        List<String> expectedResult = new Date(25).provideDateInfo();

        assertThat(eventService.saveVisitingDate(testDate).provideDateInfo()).
                isEqualTo(expectedResult);
    }

    @DisplayName("메뉴 저장 테스트 - 정상 데이터 - 성공")
    @Test
    void saveOrderedMenuTest() {
        Map<String, Integer> testMenus = new HashMap<>();
        testMenus.put("타파스", 2);
        testMenus.put("제로콜라", 1);
        List<String> expectedResult = new Menus(testMenus).provideMenusInfo();

        assertThat(eventService.saveMenus(testMenus).provideMenusInfo())
                .isEqualTo(expectedResult);
    }

    @DisplayName("주문 저장 테스트 - 정상 데이터 - 성공")
    @Test
    void saveOrderTest() {
        Order resultOrder = new Order(defaultDate, defaultMenus, defaultEventsResult);

        assertThat(eventService.saveOrder(defaultDate, defaultMenus, defaultEventsResult).provideVisitingDateInfo())
                .isEqualTo(resultOrder.provideVisitingDateInfo());
        assertThat(eventService.saveOrder(defaultDate, defaultMenus, defaultEventsResult).provideOrderedMenusInfo())
                .isEqualTo(resultOrder.provideOrderedMenusInfo());
        assertThat(eventService.saveOrder(defaultDate, defaultMenus, defaultEventsResult).provideEventsResultInfo())
                .isEqualTo(resultOrder.provideEventsResultInfo());
    }

    @DisplayName("이벤트 계산 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateEventsTest() {
        Map<Events, Integer> resultEventsResult = new LinkedHashMap<>();

        assertThat(eventService.calculateEvents(defaultDate, defaultMenus))
                .isEqualTo(resultEventsResult);
    }

    @DisplayName("증정 이벤트 결과 내역 계산 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateGiftPresentationMenu() {
        List<String> testEvents = Arrays.asList("특별 할인", "평일 할인", "증정 이벤트");

        assertThat(eventService.calculateGiftPresentationMenu(testEvents))
                .isEqualTo(GIFT_PRESENTATION_MENU + SPACING_STRING + GIFT_PRESENTATION_AMOUNT + MENU_AMOUNT_UNIT);
    }

    @DisplayName("총혜택 금액 계산 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateTotalBenefitsAmountTest() {
        Map<Events, Integer> testEventsResult = new LinkedHashMap<>();
        testEventsResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 1200);
        testEventsResult.put(Events.WEEKDAY_DISCOUNT, 2023);
        testEventsResult.put(Events.SPECIAL_DISCOUNT, 1000);
        testEventsResult.put(Events.GIFT_PRESENTATION, 25000);
        int expectedResult = -29223;

        assertThat(eventService.calculateTotalBenefitsAmount(testEventsResult))
                .isEqualTo(expectedResult);
    }

    @DisplayName("할인 후 예상 결제 금액 계산 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateDiscountedTotalAmountTest() {
        Map<String, Integer> testAmounts = new LinkedHashMap<>();
        Map<Events, Integer> testEventsResult = new LinkedHashMap<>();
        testAmounts.put("할인 전 총주문 금액", 150000);
        testAmounts.put("총혜택 금액", -29223);
        testEventsResult.put(Events.CHRISTMAS_DDAY_DISCOUNT, 1200);
        testEventsResult.put(Events.WEEKDAY_DISCOUNT, 2023);
        testEventsResult.put(Events.SPECIAL_DISCOUNT, 1000);
        testEventsResult.put(Events.GIFT_PRESENTATION, 25000);
        int expectedResult = 145777;

        assertThat(eventService.calculateDiscountedTotalAmount(testAmounts, testEventsResult))
                .isEqualTo(expectedResult);
    }

    @DisplayName("금액에 따른 배지 부여 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateBadgeTest() {
        int testTotalBenefitsAmount1 = 3400;
        int testTotalBenefitsAmount2 = 7500;
        int testTotalBenefitsAmount3 = 15000;
        int testTotalBenefitsAmount4 = 28000;

        assertThat(eventService.calculateBadge(testTotalBenefitsAmount1)).isEqualTo(NON_APPLIED_STRING);
        assertThat(eventService.calculateBadge(testTotalBenefitsAmount2)).isEqualTo(Badges.STAR.badgeName());
        assertThat(eventService.calculateBadge(testTotalBenefitsAmount3)).isEqualTo(Badges.TREE.badgeName());
        assertThat(eventService.calculateBadge(testTotalBenefitsAmount4)).isEqualTo(Badges.SANTA.badgeName());
    }
}
