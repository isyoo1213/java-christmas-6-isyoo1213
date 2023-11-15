package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 모든_타이틀_출력_예제_주문데이터() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>", "타파스 1개", "제로콜라 1개",
                    "<할인 전 총주문 금액>", "8,500원",
                    "<증정 메뉴>", "없음",
                    "<혜택 내역>", "없음",
                    "<총혜택 금액>", "0원",
                    "<할인 후 예상 결제 금액>", "8,500원",
                    "<12월 이벤트 배지>", "없음"
            );
        });
    }

    @Test
    void 모든_타이틀_출력_새로운_주문데이터() {
        assertSimpleTest(() -> {
            run("15", "크리스마스파스타-3,바비큐립-1,타파스-2,아이스크림-3,레드와인-1");
            assertThat(output()).contains(
                    "<주문 메뉴>", "크리스마스파스타 3개", "바비큐립 1개", "타파스 2개", "레드와인 1개", "아이스크림 3개",
                    "<할인 전 총주문 금액>", "215,000원",
                    "<증정 메뉴>", "샴페인 1개",
                    "<혜택 내역>", "크리스마스 디데이 할인: -2,400원", "주말 할인: -8,092원", "증정 이벤트: -25,000원",
                    "<총혜택 금액>", "-35,492원",
                    "<할인 후 예상 결제 금액>", "204,508원",
                    "<12월 이벤트 배지>", "산타"
            );
        });
    }

    @Test
    void 모든_타이틀_출력_실제생일_주문데이터() {
        assertSimpleTest(() -> {
            run("13", "제로콜라-10,티본스테이크-10");
            assertThat(output()).contains(
                    "<주문 메뉴>", "제로콜라 10개", "티본스테이크 10개",
                    "<할인 전 총주문 금액>", "580,000원",
                    "<증정 메뉴>", "샴페인 1개",
                    "<혜택 내역>", "크리스마스 디데이 할인: -2,200원", "평일 할인: 0원", "증정 이벤트: -25,000원",
                    "<총혜택 금액>", "-27,200원",
                    "<할인 후 예상 결제 금액>", "577,800원",
                    "<12월 이벤트 배지>", "산타"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
