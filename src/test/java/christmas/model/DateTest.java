package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class DateTest {

    @DisplayName("Date 생성 테스트 - 1~31 외의 데이터 - 오류")
    @ValueSource(ints = {0, 45})
    @ParameterizedTest
    void createDateWrongRangeTest(int input) {
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 날짜는 1 ~ 31 사이의 숫자를 입력해주세요.");
    }
}
