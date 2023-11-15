package christmas.view;

import static christmas.constants.EventConstants.*;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewMessage(int visitingDate) {
        System.out.println("12월" + SPACING_STRING + visitingDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }
}
