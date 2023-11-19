package christmas.model;

import christmas.constants.ExceptionMessages;
import christmas.constants.MenusConstants;

import java.util.*;

import static christmas.constants.EventConstants.*;

public class Menus {
    private final Map<MenusConstants, Integer> menus;

    public Menus(Map<String, Integer> convertedMenus) {
        validate(convertedMenus);
        Map<MenusConstants, Integer> Menus = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> convertedMenu : convertedMenus.entrySet()) {
            MenusConstants menuConstant = MenusConstants.getMenusByName(convertedMenu.getKey());
            Integer menuAmount = convertedMenu.getValue();
            Menus.put(menuConstant, menuAmount);
        }
        this.menus = Map.copyOf(Menus);
    }

    private void validate(Map<String, Integer> convertedMenus) {
        int totalAmount = 0;
        for (Map.Entry<String, Integer> convertedMenu : convertedMenus.entrySet()) {
            if (isNonExistingMenu(convertedMenu.getKey())) {
                ExceptionMessages.NON_EXISTING_MENU.throwException();
            }
            if (isWrongEachMenuAmount(convertedMenu.getValue())) {
                ExceptionMessages.WRONG_EACH_MENU_AMOUNT.throwException();
            }
            totalAmount = calculateTotalAmount(totalAmount, convertedMenu.getValue());
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

    public int provideMenusTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<MenusConstants, Integer> orderedMenu : menus.entrySet()) {
            totalPrice += MenusConstants.calculateEachMenuPrice(orderedMenu.getKey(), orderedMenu.getValue());
        }
        return totalPrice;
    }

    public List<String> provideMenusCategories() {
        List<String> categories = new ArrayList<>();
        for (MenusConstants menuConstant : menus.keySet()) {
            categories.add(menuConstant.category());
        }
        return categories;
    }

    public int provideDessertAmount() {
        int totalDessertAmount = 0;
        for (MenusConstants menuConstant : menus.keySet()) {
            if (menuConstant.category().equals(DESSERT_CATEGORY_STRING)) {
                totalDessertAmount += menus.get(menuConstant);
            }
        }
        return totalDessertAmount;
    }

    public int provideMainAmount() {
        int totalMainAmount = 0;
        for (MenusConstants menuConstant : menus.keySet()) {
            if (menuConstant.category().equals(MAIN_CATEGORY_STRING)) {
                totalMainAmount += menus.get(menuConstant);
            }
        }
        return totalMainAmount;
    }

    public List<String> provideMenusInfo() {
        List<String> menusInfo = new ArrayList<>();
        for (Map.Entry<MenusConstants, Integer> menu : menus.entrySet()) {
            String menuInfo = MenusConstants.getNameByMenus(menu.getKey())+ " " + menu.getValue() + MENU_AMOUNT_UNIT;
            menusInfo.add(menuInfo);
        }
        return menusInfo;
    }
}
