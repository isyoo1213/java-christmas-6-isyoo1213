package christmas.model;

import christmas.constants.Events;

import java.text.DecimalFormat;
import java.util.*;

import static christmas.constants.EventConstants.*;

public class Order {
    private final Map<String, List<String>> orderInfo;

    public Order(Date date, Menus menus, Map<Events, Integer> eventsResult) {
        Map<String, List<String>> orderInfo = new HashMap<>();
        List<String> dateInfo = date.provideDateInfo();
        List<String> menusInfo = List.copyOf(menus.provideMenusInfo());
        List<String> menusTotalPrice = List.of(new DecimalFormat(PRICE_PATTERN).format(menus.provideMenusTotalPrice()));
        List<String> eventsResultInfo = calculateEventsResultInfo(eventsResult);
        orderInfo.put(VISITING_DATE, dateInfo);
        orderInfo.put(ORDERED_MENUS, menusInfo);
        orderInfo.put(ORDERED_MENU_TOTAL_PRICE, menusTotalPrice);
        orderInfo.put(APPLIED_BENEFITS, eventsResultInfo);

        this.orderInfo = Map.copyOf(orderInfo);
    }

    private List<String> calculateEventsResultInfo(Map<Events, Integer> eventsResult) {
        List<String> eventsResultInfo = new ArrayList<>();
        if (eventsResult.isEmpty()) {
            return List.of(NON_APPLIED_STRING);
        }
        for (Map.Entry<Events, Integer> eventResult : eventsResult.entrySet()) {
            String eventName = Events.provideEventName(eventResult.getKey());
            String benefitAmount = new DecimalFormat(PRICE_PATTERN).format(eventsResult.get(eventResult.getKey()) * -1);
            eventsResultInfo.add(eventName + COLONS_STRING + SPACING_STRING + benefitAmount + PRICE_AMOUNT_UNIT);
        }
        return eventsResultInfo;
    }

    public List<String> provideDateInfo() {
        return orderInfo.get(VISITING_DATE);
    }

    public List<String> provideMenusInfo() {
        return orderInfo.get(ORDERED_MENUS);
    }

    public List<String> provideMenusTotalPriceInfo() {
        return orderInfo.get(ORDERED_MENU_TOTAL_PRICE);
    }

    public List<String> provideEventsResultInfo() {
        return orderInfo.get(APPLIED_BENEFITS);
    }
}
