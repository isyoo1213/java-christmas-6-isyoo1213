package christmas.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class DaysTest {

    @DisplayName("날짜 계산 테스트 - 평일 데이터 - 성공")
    @Test
    void calculateDateWeekdayTest() {
        int testDate = 20;
        Days expectedResult = Days.WEEKDAY;
        assertThat(Days.calculateDate(testDate)).isEqualTo(expectedResult);
    }

    @DisplayName("날짜 계산 테스트 - 주말 데이터 - 성공")
    @Test
    void calculateDateWeekendTest() {
        int testDate = 22;
        Days expectedResult = Days.WEEKEND;
        assertThat(Days.calculateDate(testDate)).isEqualTo(expectedResult);
    }

    @DisplayName("날짜 계산 테스트 - 주말 데이터 - 성공")
    @Test
    void calculateDateSpecialdayTest() {
        int testDate = 25;
        Days expectedResult = Days.SPECIALDAY;
        assertThat(Days.calculateDate(testDate)).isEqualTo(expectedResult);
    }
}
