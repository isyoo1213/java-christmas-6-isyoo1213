package christmas.utils;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Events, Integer> calculateEvents(Menu menu, Date date) {
        Map<Events, Integer> availableEvents = new LinkedHashMap<>();
        calculateDiscount(menu, date, availableEvents);
        calculateGiftPresentation(menu, availableEvents);
        return availableEvents;
    }

    private void calculateGiftPresentation(Menu menu, Map<Events, Integer> availableEvents) {
        if (menu.provideTotalPrice() >= GIFT_PRESENTATION_EVENT_AMOUNT) {
            availableEvents.put(Events.GIFT_PRESENTATION, GIFT_PRESENTATION_BENEFIT);
        }
    }

    private void calculateDiscount(Menu menu, Date date, Map<Events, Integer> availableEvents) {
        applyXmasDdayDiscount(date, availableEvents);
        applyWeekdayDiscount(menu, date, availableEvents);
        applyWeekendDiscount(menu, date, availableEvents);
        applySpecialdayDiscount(date, availableEvents);
    }

    private void applySpecialdayDiscount(Date date, Map<Events, Integer> availableEvents) {
        if (date.isSpecialday()) {
            availableEvents.put(Events.SPECIAL_DISCOUNT, SPECIALDAY_DISCOUNT_AMOUNT);
        }
    }

    private void applyWeekendDiscount(Menu menu, Date date, Map<Events, Integer> availableEvents) {
        if (date.isWeekend()) {
            Integer discountAmount = WEEKEND_DISCOUNT_AMOUNT * menu.provideDessertAmount();
            availableEvents.put(Events.WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private void applyWeekdayDiscount(Menu menu, Date date, Map<Events, Integer> availableEvents) {
        if (date.isWeekday()) {
            Integer discountAmount = WEEKDAY_DISCOUNT_AMOUNT * menu.provideDessertAmount();
            availableEvents.put(Events.WEEKDAY_DISCOUNT, discountAmount);
        }
    }

    private void applyXmasDdayDiscount(Date date, Map<Events, Integer> availableEvents) {
        if (date.isFirstToXmas()) {
            Integer discountAmount = BASIC_DDAY_DISCOUNT_AMOUNT + (DDAY_DISCOUNT_UNIT_AMOUNT * date.calculateDaysFromStartDayOfMonth());
            availableEvents.put(Events.CHRISTMAS_DDAY_DISCOUNT, discountAmount);
        }
    }
}
