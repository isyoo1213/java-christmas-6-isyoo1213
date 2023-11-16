package christmas.model;

import christmas.constants.MenusConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class OrderedMenusTest {

    @DisplayName("메뉴 생성 유효성 검사 - 존재하지 않는 메뉴 데이터 - 오류")
    @Test
    void validateNonExistingMenuTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("빼빼로", 10);
        assertThatThrownBy(() -> new OrderedMenus(testOrderedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 메뉴입니다.");
    }

    @DisplayName("메뉴 생성 유효성 검사 - 0개의 주문 수량 데이터 - 오류")
    @Test
    void validateWrongEachMenuAmountTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 0);
        assertThatThrownBy(() -> new OrderedMenus(testOrderedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 각 메뉴는 1개 이상을 주문해주세요.");
    }

    @DisplayName("메뉴 생성 유효성 검사 - 20개가 넘는 총 주문 수량 데이터 - 오류")
    @Test
    void validateWrongTotalMenuAmountTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 10);
        testOrderedMenus.put("양송이수프", 10);
        testOrderedMenus.put("티본스테이크", 10);
        assertThatThrownBy(() -> new OrderedMenus(testOrderedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 총 메뉴 주문 수량이 20개 이하여야 이벤트를 참여할 수 있습니다.");
    }

    @DisplayName("메뉴 총 주문 금액 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideTotalPriceTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 2);
        testOrderedMenus.put("양송이수프", 1);
        testOrderedMenus.put("티본스테이크", 3);
        OrderedMenus testMenus = new OrderedMenus(testOrderedMenus);
        int expectedResult = 0;
        expectedResult += MenusConstants.calculateEachMenuPrice(MenusConstants.TAPAS, 2);
        expectedResult += MenusConstants.calculateEachMenuPrice(MenusConstants.MUSHROOM_SOUP, 1);
        expectedResult += MenusConstants.calculateEachMenuPrice(MenusConstants.TBONE_STEAK, 3);

        assertThat(testMenus.provideTotalPrice()).isEqualTo(expectedResult);
    }

    @DisplayName("디저트 카테고리 수량 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideDesertCategoryAmountTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 2);
        testOrderedMenus.put("초코케이크", 1);
        testOrderedMenus.put("티본스테이크", 3);
        testOrderedMenus.put("아이스크림", 5);
        OrderedMenus testMenus = new OrderedMenus(testOrderedMenus);
        int expectedResult = 6;

        assertThat(testMenus.provideDessertAmount()).isEqualTo(expectedResult);
    }

    @DisplayName("메인 카테고리 수량 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void provideMainCategoryAmountTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 2);
        testOrderedMenus.put("초코케이크", 1);
        testOrderedMenus.put("티본스테이크", 3);
        testOrderedMenus.put("크리스마스파스타", 4);
        OrderedMenus testMenus = new OrderedMenus(testOrderedMenus);
        int expectedResult = 7;

        assertThat(testMenus.provideMainAmount()).isEqualTo(expectedResult);
    }

    @DisplayName("주문 메뉴 및 수량 반환 - 정상 데이터 - 성공")
    @Test
    void provideOrderedMenusTest() {
        Map<String, Integer> testOrderedMenus = new LinkedHashMap<>();
        testOrderedMenus.put("타파스", 3);
        testOrderedMenus.put("양송이수프", 2);
        testOrderedMenus.put("티본스테이크", 4);
        OrderedMenus testMenus = new OrderedMenus(testOrderedMenus);
        List<String> expectedResult = Arrays.asList("타파스 3개", "양송이수프 2개", "티본스테이크 4개");

        assertThat(testMenus.provideOrderedMenus()).containsAll(expectedResult);
    }
}
