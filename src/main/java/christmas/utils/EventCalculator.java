package christmas.utils;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.OrderedMenus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static christmas.constants.EventConstants.*;

public class EventCalculator {
    private final Integer MINIMUM_PARTICIPATION_AMOUNT = 10000;
    private final Integer GIFT_PRESENTATION_EVENT_AMOUNT = 120000;
    private final Integer BASIC_DDAY_DISCOUNT_AMOUNT = 1000;
    private final Integer DDAY_DISCOUNT_UNIT_AMOUNT = 100;
    private final Integer WEEKDAY_DISCOUNT_AMOUNT = 2023;
    private final Integer WEEKEND_DISCOUNT_AMOUNT = 2023;
    private final Integer SPECIALDAY_DISCOUNT_AMOUNT = 1000;

    public boolean isParticipate(OrderedMenus orderedMenus) {
        int totalPrice = orderedMenus.provideTotalPrice();
        if (isUnderMinimumAmount(totalPrice) || isOnlyBeverage(orderedMenus)) {
            return false;
        }
        return true;
    }

    private boolean isOnlyBeverage(OrderedMenus orderedMenus) {
        List<String> categories = orderedMenus.provideMenuCategories();
        categories.remove(BEVERAGE_CATEGORY_STRING);
        return categories.size() == 0;
    }

    private boolean isUnderMinimumAmount(int totalAmount) {
        return totalAmount < MINIMUM_PARTICIPATION_AMOUNT;
    }

    public Map<Events, Integer> calculateEvents(OrderedMenus orderedMenus, Date date) {
        Map<Events, Integer> availableEvents = new LinkedHashMap<>();
        calculateDiscount(orderedMenus, date, availableEvents);
        calculateGiftPresentation(orderedMenus, availableEvents);
        return availableEvents;
    }

    private void calculateGiftPresentation(OrderedMenus orderedMenus, Map<Events, Integer> availableEvents) {
        if (orderedMenus.provideTotalPrice() >= GIFT_PRESENTATION_EVENT_AMOUNT) {
            availableEvents.put(Events.GIFT_PRESENTATION, GIFT_PRESENTATION_BENEFIT);
        }
    }

    private void calculateDiscount(OrderedMenus orderedMenus, Date date, Map<Events, Integer> availableEvents) {
        applyXmasDdayDiscount(date, availableEvents);
        applyWeekdayDiscount(orderedMenus, date, availableEvents);
        applyWeekendDiscount(orderedMenus, date, availableEvents);
        applySpecialdayDiscount(date, availableEvents);
    }

    private void applySpecialdayDiscount(Date date, Map<Events, Integer> availableEvents) {
        if (date.isSpecialday()) {
            availableEvents.put(Events.SPECIAL_DISCOUNT, SPECIALDAY_DISCOUNT_AMOUNT);
        }
    }

    private void applyWeekendDiscount(OrderedMenus orderedMenus, Date date, Map<Events, Integer> availableEvents) {
        if (date.isWeekend()) {
            Integer discountAmount = WEEKEND_DISCOUNT_AMOUNT * orderedMenus.provideMainAmount();
            availableEvents.put(Events.WEEKEND_DISCOUNT, discountAmount);
        }
    }

    private void applyWeekdayDiscount(OrderedMenus orderedMenus, Date date, Map<Events, Integer> availableEvents) {
        if (date.isWeekday()) {
            Integer discountAmount = WEEKDAY_DISCOUNT_AMOUNT * orderedMenus.provideDessertAmount();
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
