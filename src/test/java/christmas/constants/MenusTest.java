package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class MenusTest {

    @DisplayName("메뉴 해당 여부 테스트 - 메뉴에 없는 데이터 - 오류")
    @ValueSource(strings = {"빼빼로", "갈비찜", "나시르막"})
    @ParameterizedTest
    void hasMenuWrongMenuTest(String input) {
        boolean testResult = false;
        boolean expectedResult = false;
        for (Menus menu : Menus.values()) {
            if (menu.hasMenu(input)) {
                testResult =  true;
            }
        }
        assertThat(testResult).isEqualTo(expectedResult);
    }

    @DisplayName("메뉴 해당 여부 테스트 - 정상 데이터 - 성공")
    @ValueSource(strings = {"타파스", "아이스크림", "샴페인"})
    @ParameterizedTest
    void hasMenuCorrectMenuTest(String input) {
        boolean testResult = false;
        boolean expectedResult = true;
        for (Menus menu : Menus.values()) {
            if (menu.hasMenu(input)) {
                testResult =  true;
            }
        }
        assertThat(testResult).isEqualTo(expectedResult);
    }
}
