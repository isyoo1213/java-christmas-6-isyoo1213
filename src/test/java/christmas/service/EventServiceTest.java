package christmas.service;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class EventServiceTest {
    private static Menu defaultMenu;
    private static Date defaultDate;
    private static Map<Events, Integer> defaultEventsResult = new HashMap<>();
    EventService eventService = new EventService();

    @BeforeAll
    static void set() {
        Map<String, Integer> defaultOrderedMenus = new HashMap<>();
        defaultOrderedMenus.put("타파스", 1);
        defaultOrderedMenus.put("제로콜라", 1);
        defaultMenu =  new Menu(defaultOrderedMenus);
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
        List<String> expectedResult = new Menu(testMenus).provideOrderedMenus();

        assertThat(eventService.saveOrderedMenu(testMenus).provideOrderedMenus())
                .isEqualTo(expectedResult);
    }

    @DisplayName("주문 저장 테스트 - 정상 데이터 - 성공")
    @Test
    void saveOrderTest() {
        Order resultOrder = new Order(defaultDate, defaultMenu, defaultEventsResult);

        assertThat(eventService.saveOrder(defaultDate, defaultMenu, defaultEventsResult).provideVisitingDateInfo())
                .isEqualTo(resultOrder.provideVisitingDateInfo());
        assertThat(eventService.saveOrder(defaultDate, defaultMenu, defaultEventsResult).provideOrderedMenusInfo())
                .isEqualTo(resultOrder.provideOrderedMenusInfo());
        assertThat(eventService.saveOrder(defaultDate, defaultMenu, defaultEventsResult).provideEventsResultInfo())
                .isEqualTo(resultOrder.provideEventsResultInfo());
    }
}
