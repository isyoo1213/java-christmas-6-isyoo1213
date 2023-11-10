package christmas.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class InputValidatorTest {
    InputValidator inputValidator = new InputValidator();

    @DisplayName("입력값 전처리 테스트 - null 데이터 - 오류")
    @Test
    void preprocessInputNullTest() {
        String testInput = null;
        assertThatThrownBy(() -> inputValidator.preprocessInput(testInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] null은 입력할 수 없습니다.");
    }

    @DisplayName("입력값 전처리 테스트 - empty 데이터 - 오류")
    @Test
    void preprocessInputEmptyTest() {
        String testInput = "";
        assertThatThrownBy(() -> inputValidator.preprocessInput(testInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 값을 입력해주세요.");
    }

    @DisplayName("입력값 공백 제거 테스트 - 공백 포함 데이터 - 성공")
    @Test
    void preprocessInputSpacingTest() {
        String testInput = "abcd ef ";
        String expectedResult = "abcdef";
        assertThat(inputValidator.preprocessInput(testInput)).isEqualTo(expectedResult);
    }

    @DisplayName("입력값 날짜 변환 테스트 - NonNumeric 데이터 - 성공")
    @Test
    void convertInputToDateNonNumericTest() {
        String testInput = "a";
        assertThatThrownBy(() -> inputValidator.convertInputToDate(testInput))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage("[ERROR] 숫자를 입력해주세요.");
    }

    @DisplayName("입력값 날짜 변환 테스트 - 정상 데이터 - 성공")
    @Test
    void convertInputToDateCorrectTest() {
        String testInput = "25";
        Integer expectedResult = 25;
        assertThat(inputValidator.convertInputToDate(testInput)).isEqualTo(expectedResult);
    }
}
