package christmas.constants;

public enum ExceptionMessages {
    NULL_INPUT("null은 입력할 수 없습니다."),
    EMPTY_INPUT("값을 입력해주세요."),
    NON_NUMERIC_DATE_INPUT("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NON_NUMERIC_AMOUNT_INPUT("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NON_EXISTING_MENU("존재하지 않는 메뉴입니다."),
    DUPLICATED_MENU_ORDER("중복되는 메뉴 주문이 존재합니다. 수량을 조정해주세요."),
    WRONG_EACH_MENU_AMOUNT("각 메뉴는 1개 이상을 주문해주세요."),
    WRONG_MENU_TOTAL_AMOUNT("총 메뉴 주문 수량이 20개 이하여야 이벤트를 참여할 수 있습니다."),
    UNDER_MINIMUM_TOTAL_AMOUNT("총 주문 금액이 10,000원 이상이어야 이벤트를 참여할 수 있습니다."),
    WRONG_DAY_NUMBER_RANGE("날짜는 1 ~ 31 사이의 숫자를 입력해주세요."),
    ONLY_BEVERAGE_ORDER("음료만 주문할 경우 이벤트를 참여할 수 없습니다.");

    private static final String ERROR_TAG = "[ERROR] ";
    private final String exceptionMessages;

    ExceptionMessages(String exceptionMessages) {
        this.exceptionMessages = ERROR_TAG + exceptionMessages;
    }

    public void throwException() {
        if (this.name().equals(NON_NUMERIC_DATE_INPUT.name())) {
            throw new NumberFormatException(exceptionMessages);
        }
        throw new IllegalArgumentException(exceptionMessages);
    }
}
