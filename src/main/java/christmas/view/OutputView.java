package christmas.view;

import java.text.DecimalFormat;
import java.util.List;

import static christmas.constants.EventConstants.*;

public class OutputView {

    public void printWelcomeMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printEventPreviewMessage(int visitingDate) {
        System.out.println("12월" + SPACING_STRING + visitingDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    public void printUserMenusInfo(List<String> orderedMenusInfo, int orderedMenusTotalAmount) {
        printOrderedMenus(orderedMenusInfo);
        printOrderedMenusTotalAmount(orderedMenusTotalAmount);
    }

    private void printOrderedMenus(List<String> orderedMenus) {
        System.out.println("<주문 메뉴>");
        for (String orderedMenu : orderedMenus) {
            System.out.println(orderedMenu);
        }
        System.out.println();
    }

    private void printOrderedMenusTotalAmount(int orderedMenusTotalAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(new DecimalFormat("###,###").format(orderedMenusTotalAmount) + PRICE_AMOUNT_UNIT);
        System.out.println();
    }
}
