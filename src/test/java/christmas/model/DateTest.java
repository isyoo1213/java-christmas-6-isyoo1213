package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

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

    @DisplayName("1일부터 경과한 날짜 반환 테스트 - 정상 데이터 - 성공")
    @Test
    void calculateDaysFromStartDayOfMonthTest() {
        Date testDate = new Date(20);
        int expectedResult = 19;
        assertThat(testDate.calculateDaysFromStartDayOfMonth()).isEqualTo(expectedResult);
    }

    @DisplayName("크리스마스 내의 기간 확인 테스트 - 정상 데이터 - 성공")
    @Test
    void isFIrstToXmasTest() {
        Date testDate = new Date(20);
        boolean expectedResult = true;
        assertThat(testDate.isFirstToXmas()).isEqualTo(expectedResult);
    }

    @DisplayName("평일 확인 테스트 - 정상 데이터 - 성공")
    @Test
    void isWeekdayTest() {
        Date testDate = new Date(20);
        boolean expectedResult = true;
        assertThat(testDate.isWeekday()).isEqualTo(expectedResult);
    }

    @DisplayName("평일 확인 테스트 - 평일이 아닌 데이터 - 성공")
    @ValueSource(ints = {1, 9, 23, 29})
    @ParameterizedTest
    void isWeekdayNonWeekdayTest(int input) {

        assertThat(new Date(input).isWeekday()).isEqualTo(false);
    }

    @DisplayName("주말 확인 테스트 - 주말이 아닌 데이터 - 성공")
    @ValueSource(ints = {5, 12, 17, 25})
    @ParameterizedTest
    void isWeekendNonWeekendTest(int input) {

        assertThat(new Date(input).isWeekend()).isEqualTo(false);
    }

    @DisplayName("스페셜데이 확인 테스트 - 스페셜데이가 아닌 데이터 - 성공")
    @ValueSource(ints = {8, 18, 22, 26, 30})
    @ParameterizedTest
    void isSpecialDayNonSpecialTest(int input) {

        assertThat(new Date(input).isSpecialday()).isEqualTo(false);
    }

    @DisplayName("날짜 정보 반환 테스트 - 크리스마스 데이터 - 성공")
    @Test
    void provideDateInfoTest() {
        Date testDate = new Date(25);
        List<String> expectedResult = Arrays.asList("SPECIALDAY", "WEEKDAY", "25");
        assertThat(testDate.provideDateInfo()).isEqualTo(expectedResult);
    }

    //25일이 아닌 경우, SPECIALDAY 순서가 평일 or 주말 보다 뒤에서 추가됨
    @DisplayName("날짜 정보 반환 테스트 - 크리스마스가 아닌 데이터 - 성공")
    @Test
    void provideDateInfoNonXmasTest() {
        Date testDate = new Date(17);
        List<String> expectedResult = Arrays.asList("WEEKDAY", "SPECIALDAY", "17");
        assertThat(testDate.provideDateInfo()).isEqualTo(expectedResult);
    }
}
