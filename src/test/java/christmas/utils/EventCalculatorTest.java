package christmas.utils;

import christmas.model.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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
        Menu testMenus = new Menu(testMenuOrder);
        boolean expectedResult = false;
        assertThat(eventCalculator.isParticipate(testMenus)).isEqualTo(expectedResult);
    }

    @DisplayName("이벤트 참여 가능 여부 테스트 - 음료만 주문한 데이터 - 성공")
    @Test
    void isParticipateOnlyBeverageCategoryTest() {
        Map<String, Integer> testMenuOrder = new HashMap<>();
        testMenuOrder.put("제로콜라", 10);
        Menu testMenus = new Menu(testMenuOrder);
        boolean expectedResult = false;
        assertThat(eventCalculator.isParticipate(testMenus)).isEqualTo(expectedResult);
    }
}
