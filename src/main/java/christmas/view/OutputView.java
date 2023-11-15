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

    public void printAppliedBenefitsInfo(String giftPresentationMenu, List<String> benefitsInfo, int totalBenefitsAmount) {
        printGiftPresentation(giftPresentationMenu);
        printBenefitsInfo(benefitsInfo);
        printTotalBenefitsAmountInfo(totalBenefitsAmount);
    }

    public void printDiscountedTotalAmountInfo(int discountedTotalAMountInfo) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(new DecimalFormat("###,###").format(discountedTotalAMountInfo) + PRICE_AMOUNT_UNIT);
        System.out.println();
    }

    public void printEventBadgeStatus(String badgeInfo) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeInfo);
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

    private void printGiftPresentation(String giftPresentationMenu) {
        System.out.println("<증정 메뉴>");
        System.out.println(giftPresentationMenu);
        System.out.println();
    }

    private void printBenefitsInfo(List<String> benefitsInfo) {
        System.out.println("<혜택 내역>");
        for (String benefitInfo : benefitsInfo) {
            System.out.println(benefitInfo);
        }
        System.out.println();
    }

    private void printTotalBenefitsAmountInfo(int totalBenefitsAmountInfo) {
        System.out.println("<총혜택 금액>");
        System.out.println(new DecimalFormat("###,###").format(totalBenefitsAmountInfo) + PRICE_AMOUNT_UNIT);
        System.out.println();
    }
}
