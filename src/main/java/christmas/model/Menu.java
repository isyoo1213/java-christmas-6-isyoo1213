package christmas.model;

import christmas.constants.ExceptionMessages;
import christmas.constants.Menus;

import java.util.EnumMap;
import java.util.Map;

public class Menu {
    private final Map<Menus, Integer> orderedMenus;

    public Menu(Map<String, Integer> convertedOrderedMenus) {
        validate(convertedOrderedMenus);
        Map<Menus, Integer> orderedMenus = new EnumMap<>(Menus.class);
        for (Map.Entry<String, Integer> convertedOrderedMenu : convertedOrderedMenus.entrySet()) {
            Menus orderedMenu = Menus.valueOf(convertedOrderedMenu.getKey());
            Integer orderedMenuAmount = convertedOrderedMenu.getValue();
            orderedMenus.put(orderedMenu, orderedMenuAmount);
        }
        this.orderedMenus = Map.copyOf(orderedMenus);
    }

    private void validate(Map<String, Integer> convertedOrderedMenus) {
        int totalAmount = 0;
        for (Map.Entry<String, Integer> convertedOrderedMenu : convertedOrderedMenus.entrySet()) {
            if (isNonExistingMenu(convertedOrderedMenu.getKey())) {
                ExceptionMessages.NON_EXISTING_MENU.throwException();
            }
            if (isWrongEachMenuAmount(convertedOrderedMenu.getValue())) {
                ExceptionMessages.WRONG_EACH_MENU_AMOUNT.throwException();
            }
            totalAmount = calculateTotalAmount(totalAmount, convertedOrderedMenu.getValue());
        }
        if (isWrongMenuTotalAmount(totalAmount)) {
            ExceptionMessages.WRONG_MENU_TOTAL_AMOUNT.throwException();
        }
    }

    private int calculateTotalAmount(int totalAmount, Integer amount) {
        return totalAmount + amount;
    }

    private boolean isWrongMenuTotalAmount(int totalAmount) {
        return totalAmount > 20;
    }

    private boolean isWrongEachMenuAmount(Integer amount) {
        return amount < 1;
    }

    private boolean isNonExistingMenu(String menuName) {
        for (Menus menu : Menus.values()) {
            if (menu.hasMenu(menuName)) {
                return false;
            }
        }
        return true;
    }
}
