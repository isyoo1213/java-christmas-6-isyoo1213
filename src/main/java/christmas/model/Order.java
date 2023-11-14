package christmas.model;

import christmas.constants.EventConstants;
import christmas.constants.Events;

import java.text.DecimalFormat;
import java.util.*;

public class Order {
    private final Map<String, List<String>> orderInfo;

    public Order(Date date, Menu menu, Map<Events, Integer> eventsResult) {
        Map<String, List<String>> orderInfo = new HashMap<>();
        List<String> dateInfo = date.provideDateInfo();
        List<String> orderedMenusInfo = List.copyOf(menu.provideOrderedMenusInfo());
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
            return List.of("없음");
        }
        for (Map.Entry<Events, Integer> eventResult : eventsResult.entrySet()) {
            String eventName = Events.provideEventName(eventResult.getKey());
            String benefitAmount = new DecimalFormat("###,###").format(eventsResult.get(eventResult.getKey()));
            eventsResultInfo.add(eventName + EventConstants.COLONS_STRING+ " -" + benefitAmount + "원");
        }
        return eventsResultInfo;
    }
}
