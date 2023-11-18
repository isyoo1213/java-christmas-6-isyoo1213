package christmas.service;

import christmas.constants.Badges;
import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menus;
import christmas.model.Order;
import christmas.utils.EventCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.constants.EventConstants.*;

public class EventService {
    public static final Integer STAR_BADGE_AMOUNT = 5000;
    public static final Integer TREE_BADGE_AMOUNT = 10000;
    public static final Integer SANTA_BADGE_AMOUNT = 20000;
    public static final Integer GIFT_PRESENTATION_AMOUNT = 1;
    public static final String GIFT_PRESENTATION_MENU = "샴페인";

    EventCalculator eventCalculator = new EventCalculator();

    public Order saveOrder(Date visitingDate, Menus menus, Map<Events, Integer> eventResult) {
        return new Order(visitingDate, menus, eventResult);
    }

    public Date saveVisitingDate(Integer convertedDate) {
        return new Date(convertedDate);
    }

    public Menus saveOrderedMenu(Map<String, Integer> convertedOrderedMenu) {
        return new Menus(convertedOrderedMenu);
    }

    public Map<Events, Integer> calculateEvents(Date visitingDate, Menus menus) {
        if (!eventCalculator.isParticipate(menus)) {
            return new HashMap<>();
        }
        return eventCalculator.calculateEvents(menus, visitingDate);
    }

    public String calculateGiftPresentationMenu(List<String> eventsResult) {
        for (String eventResult : eventsResult) {
            if (eventResult.contains(Events.provideEventName(Events.GIFT_PRESENTATION))) {
                return GIFT_PRESENTATION_MENU + SPACING_STRING + GIFT_PRESENTATION_AMOUNT + MENU_AMOUNT_UNIT;
            }
        }
        return NON_APPLIED_STRING;
    }

    public int calculateTotalBenefitsAmount(Map<Events, Integer> eventsResult) {
        int totalBenefitsAmount = 0;
        for (Events event : eventsResult.keySet()) {
            totalBenefitsAmount += eventsResult.get(event);
        }
        totalBenefitsAmount = totalBenefitsAmount * -1;
        return totalBenefitsAmount;
    }

    public int calculateDiscountedTotalAmount(Map<String, Integer> amounts, Map<Events, Integer> eventsResult) {
        int discountedTotalAMount = amounts.get("할인 전 총주문 금액") + amounts.get("총혜택 금액");
        if (eventsResult.containsKey(Events.GIFT_PRESENTATION)) {
            discountedTotalAMount += GIFT_PRESENTATION_BENEFIT;
        }
        return discountedTotalAMount;
    }

    public String calculateBadge(int totalBenefitsAmount) {
        if (totalBenefitsAmount >= SANTA_BADGE_AMOUNT) {
            return Badges.SANTA.badgeName();
        }
        if (totalBenefitsAmount >= TREE_BADGE_AMOUNT) {
            return Badges.TREE.badgeName();
        }
        if (totalBenefitsAmount >= STAR_BADGE_AMOUNT) {
            return Badges.STAR.badgeName();
        }
        return NON_APPLIED_STRING;
    }
}
