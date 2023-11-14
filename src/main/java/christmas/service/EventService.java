package christmas.service;

import christmas.constants.EventConstants;
import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.utils.EventCalculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {
    EventCalculator eventCalculator = new EventCalculator();

    public Order saveOrder(Date visitingDate, Menu orderedMenu, Map<Events, Integer> eventResult) {
        return new Order(visitingDate, orderedMenu, eventResult);
    }

    public Date saveVisitingDate(Integer convertedDate) {
        return new Date(convertedDate);
    }

    public Menu saveOrderedMenu(Map<String, Integer> convertedOrderedMenu) {
        return new Menu(convertedOrderedMenu);
    }

    public Map<Events, Integer> calculateEvents(Date visitingDate, Menu orderedMenu) {
        if (!eventCalculator.isParticipate(orderedMenu)) {
            return new HashMap<>();
        }
        return eventCalculator.calculateEvents(orderedMenu, visitingDate);
    }
}
