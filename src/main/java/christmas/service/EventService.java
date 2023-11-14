package christmas.service;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.utils.EventCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constants.EventConstants.*;

public class EventService {
    public static final Integer GIFT_PRESENTATION_AMOUNT = 1;
    public static final String GIFT_PRESENTATION_UNIT = "개";

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

    public String calculateGiftPresentationMenu(List<String> eventsResult) {
        for (String eventResult : eventsResult) {
            if (eventResult.contains(Events.provideEventName(Events.GIFT_PRESENTATION))) {
                return Events.provideEventName(Events.GIFT_PRESENTATION) + SPACING_STRING + GIFT_PRESENTATION_AMOUNT + GIFT_PRESENTATION_UNIT;
            }
        }
        return NON_APPLIED_STRING;
    }
}
