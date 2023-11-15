package christmas.model;

import christmas.constants.ExceptionMessages;
import christmas.constants.Menus;

import java.util.*;

import static christmas.constants.EventConstants.*;

public class Menu {
    private final Map<Menus, Integer> orderedMenus;

    public Menu(Map<String, Integer> convertedOrderedMenus) {
        validate(convertedOrderedMenus);
        Map<Menus, Integer> orderedMenus = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> convertedOrderedMenu : convertedOrderedMenus.entrySet()) {
            Menus orderedMenu = Menus.getMenusByName(convertedOrderedMenu.getKey());
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
        return Menus.getMenusByName(menuName).equals(Menus.UNCATEGORIZED);
    }

    public int provideTotalPrice() {
        int totalPrice = 0;
        for (Map.Entry<Menus, Integer> orderedMenu : orderedMenus.entrySet()) {
            totalPrice += Menus.calculateEachMenuPrice(orderedMenu.getKey(), orderedMenu.getValue());
        }
        return totalPrice;
    }

    public List<String> provideMenuCategories() {
        List<String> categories = new ArrayList<>();
        for (Menus menus : orderedMenus.keySet()) {
            categories.add(menus.category());
        }
        return categories;
    }

    public int provideDessertAmount() {
        int totalDessertAmount = 0;
        for (Menus menu : orderedMenus.keySet()) {
            if (menu.category().equals(DESSERT_CATEGORY_STRING)) {
                totalDessertAmount += orderedMenus.get(menu);
            }
        }
        return totalDessertAmount;
    }

    public int provideMainAmount() {
        int totalMainAmount = 0;
        for (Menus menus : orderedMenus.keySet()) {
            if (menus.category().equals(MAIN_CATEGORY_STRING)) {
                totalMainAmount += orderedMenus.get(menus);
            }
        }
        return totalMainAmount;
    }

    public List<String> provideOrderedMenus() {
        List<String> orderedMenusInfo = new ArrayList<>();
        for (Map.Entry<Menus, Integer> orderedMenu : orderedMenus.entrySet()) {
            String orderedMenuInfo = Menus.getNameByMenus(orderedMenu.getKey())+ " " + orderedMenu.getValue()+ "개";
            orderedMenusInfo.add(orderedMenuInfo);
        }
        return orderedMenusInfo;
    }
}
