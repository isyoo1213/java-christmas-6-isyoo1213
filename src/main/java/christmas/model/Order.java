package christmas.model;

import christmas.constants.Events;

import java.text.DecimalFormat;
import java.util.*;

import static christmas.constants.EventConstants.*;

public class Order {
    private final Map<String, List<String>> orderInfo;

    public Order(Date date, Menu menu, Map<Events, Integer> eventsResult) {
        Map<String, List<String>> orderInfo = new HashMap<>();
        List<String> dateInfo = date.provideDateInfo();
        List<String> orderedMenusInfo = List.copyOf(menu.provideOrderedMenus());
        List<String> orderedMenusTotalPrice = List.of(new DecimalFormat("###,###").format(menu.provideTotalPrice()));
        List<String> eventsResultInfo = calculateEventsResultInfo(eventsResult);
        orderInfo.put("방문 날짜", dateInfo);
        orderInfo.put("주문 메뉴", orderedMenusInfo);
        orderInfo.put("할인 전 총주문 금액", orderedMenusTotalPrice);
        orderInfo.put("혜택 내역", eventsResultInfo);

        this.orderInfo = Map.copyOf(orderInfo);
    }

    private List<String> calculateEventsResultInfo(Map<Events, Integer> eventsResult) {
        List<String> eventsResultInfo = new ArrayList<>();
        if (eventsResult.isEmpty()) {
            return List.of(NON_APPLIED_STRING);
        }
        for (Map.Entry<Events, Integer> eventResult : eventsResult.entrySet()) {
            String eventName = Events.provideEventName(eventResult.getKey());
            String benefitAmount = new DecimalFormat("###,###").format(eventsResult.get(eventResult.getKey()) * -1);
            eventsResultInfo.add(eventName + COLONS_STRING + SPACING_STRING + benefitAmount + PRICE_AMOUNT_UNIT);
        }
        return eventsResultInfo;
    }

    public List<String> provideVisitingDateInfo() {
        return orderInfo.get("방문 날짜");
    }

    public List<String> provideOrderedMenusInfo() {
        return orderInfo.get("주문 메뉴");
    }

    public List<String> provideOrderedMenusTotalPriceInfo() {
        return orderInfo.get("할인 전 총주문 금액");
    }

    public List<String> provideEventsResultInfo() {
        return orderInfo.get("혜택 내역");
    }
}
