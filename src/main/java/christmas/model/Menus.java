package christmas.model;

import christmas.constants.ExceptionMessages;
import christmas.constants.MenusConstants;

import java.util.*;

import static christmas.constants.EventConstants.*;

public class Menus {
    private final Map<MenusConstants, Integer> orderedMenus;

    public Menus(Map<String, Integer> convertedOrderedMenus) {
        validate(convertedOrderedMenus);
        Map<MenusConstants, Integer> orderedMenus = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> convertedOrderedMenu : convertedOrderedMenus.entrySet()) {
            MenusConstants orderedMenu = MenusConstants.getMenusByName(convertedOrderedMenu.getKey());
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
        return MenusConstants.getMenusByName(menuName).equals(MenusConstants.UNCATEGORIZED);
    }

    public int provideTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<MenusConstants, Integer> orderedMenu : orderedMenus.entrySet()) {
            totalPrice += MenusConstants.calculateEachMenuPrice(orderedMenu.getKey(), orderedMenu.getValue());
        }
        return totalPrice;
    }

    public List<String> provideMenuCategories() {
        List<String> categories = new ArrayList<>();
        for (MenusConstants menuConstant : orderedMenus.keySet()) {
            categories.add(menuConstant.category());
        }
        return categories;
    }

    public int provideDessertAmount() {
        int totalDessertAmount = 0;
        for (MenusConstants menuConstant : orderedMenus.keySet()) {
            if (menuConstant.category().equals(DESSERT_CATEGORY_STRING)) {
                totalDessertAmount += orderedMenus.get(menuConstant);
            }
        }
        return totalDessertAmount;
    }

    public int provideMainAmount() {
        int totalMainAmount = 0;
        for (MenusConstants menuConstant : orderedMenus.keySet()) {
            if (menuConstant.category().equals(MAIN_CATEGORY_STRING)) {
                totalMainAmount += orderedMenus.get(menuConstant);
            }
        }
        return totalMainAmount;
    }

    public List<String> provideOrderedMenus() {
        List<String> orderedMenusInfo = new ArrayList<>();
        for (Map.Entry<MenusConstants, Integer> orderedMenu : orderedMenus.entrySet()) {
            String orderedMenuInfo = MenusConstants.getNameByMenus(orderedMenu.getKey())+ " " + orderedMenu.getValue() + MENU_AMOUNT_UNIT;
            orderedMenusInfo.add(orderedMenuInfo);
        }
        return orderedMenusInfo;
    }
}
