package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class MenuTest {

    @DisplayName("메뉴 생성 유효성 검사 - 존재하지 않는 메뉴 데이터 - 오류")
    @Test
    void validateNonExistingMenuTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("빼빼로", 10);
        assertThatThrownBy(() -> new Menu(testOrderedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 메뉴입니다.");
    }

    @DisplayName("메뉴 생성 유효성 검사 - 0개의 주문 수량 데이터 - 오류")
    @Test
    void validateWrongEachMenuAmountTest() {
        Map<String, Integer> testOrderedMenus = new HashMap<>();
        testOrderedMenus.put("타파스", 0);
        assertThatThrownBy(() -> new Menu(testOrderedMenus))
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
        assertThatThrownBy(() -> new Menu(testOrderedMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 총 메뉴 주문 수량이 20개 이하여야 이벤트를 참여할 수 있습니다.");
    }
}
