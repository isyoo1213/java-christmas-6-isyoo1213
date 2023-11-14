package christmas.utils;

import christmas.model.Menu;

import java.util.List;

import static christmas.constants.EventConstants.*;

public class EventCalculator {

    public boolean isParticipate(Menu menu) {
        int totalPrice = menu.provideTotalPrice();
        if (isUnderMinimumAmount(totalPrice) || isOnlyBeverage(menu)) {
            return false;
        }
        return true;
    }

    private boolean isOnlyBeverage(Menu menu) {
        List<String> categories = menu.provideMenuCategories();
        categories.remove(BEVERAGE_CATEGORY_STRING);
        return categories.size() == 0;
    }

    private boolean isUnderMinimumAmount(int totalAmount) {
        return totalAmount < MINIMUM_PARTICIPATION_AMOUNT;
    }
}
