package christmas.constants;

public enum Menus {
    MUSHROOM_SOUP("양송이수프", 6000, "애피타이저"),
    TAPAS("타파스", 5500, "애피타이저"),
    CEASAR_SALAD("시저샐러드", 8000, "애피타이저"),
    TBONE_STEAK("티본스테이크", 55000, "메인"),
    BARBEQUE_LIB("바비큐립", 54000, "메인"),
    SEAFOOD_PASTA("해산물파스타", 35000, "메인"),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, "메인"),
    CHOCOLATE_CAKE("초코케이크", 15000, "디저트"),
    ICECREAM("아이스크림", 5000, "디저트"),
    ZERO_COKE("제로콜라", 3000, "음료"),
    RED_WINE("레드와인", 60000, "음료"),
    CHAMPAGNE("샴페인", 25000, "음료");

    private final String menuName;
    private final int price;
    private final String category;

    Menus(String menuName, int price, String category) {
        this.menuName = menuName;
        this.price = price;
        this.category = category;
    }

    public static int calculateEachMenuPrice(Menus menu, Integer amount) {
        return menu.price * amount;
    }

    public boolean hasMenu(String menuName) {
        return this.menuName.equals(menuName);
    }

    public String category() {
        return category;
    }
}
