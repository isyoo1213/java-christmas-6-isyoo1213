package christmas.service;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;

import java.util.Map;

public class EventService {

    public Order saveOrder(Date visitingDate, Menu orderedMenu, Map<Events, Integer> eventResult) {
        return new Order(visitingDate, orderedMenu, eventResult);
    }

    public Date saveVisitingDate(Integer convertedDate) {
        return new Date(convertedDate);
    }

    public Menu saveOrderedMenu(Map<String, Integer> convertedOrderedMenu) {
        return new Menu(convertedOrderedMenu);
    }
}
