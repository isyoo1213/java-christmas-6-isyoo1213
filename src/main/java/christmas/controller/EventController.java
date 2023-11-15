package christmas.controller;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.service.EventService;
import christmas.utils.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static christmas.constants.EventConstants.*;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    InputValidator inputValidator = new InputValidator();
    EventService eventService = new EventService();
    private final Map<Events, Integer> eventsResult = new LinkedHashMap<>();
    private final Map<String, Integer> amounts = new LinkedHashMap<>();

    public void proceedEvent() {
        outputView.printWelcomeMessage();

        Date visitingDate = saveVisitingDate();
        Menu orderedMenu = saveOrderedMenus();
        Order order = applyEvents(visitingDate, orderedMenu);
        printEventResult(order);
    }

    private Date saveVisitingDate() {
        try {
            String preprocessedDate = inputValidator.preprocessInput(inputView.askVisitingDate());
            Integer convertedDate = inputValidator.convertInputToDate(preprocessedDate);
            return eventService.saveVisitingDate(convertedDate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return saveVisitingDate();
        }
    }

    private Menu saveOrderedMenus() {
        try {
            String preprocessedOrderedMenus = inputValidator.preprocessInput(inputView.askMenuOrdering());
            Map<String, Integer> convertedOrderedMenus = inputValidator.convertInputToMenuOrder(preprocessedOrderedMenus);
            Menu orderedMenu = eventService.saveOrderedMenu(convertedOrderedMenus);
            amounts.put(ORDERED_MENU_TOTAL_PRICE, orderedMenu.provideTotalPrice());
            return orderedMenu;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return saveOrderedMenus();
        }
    }

    private Order applyEvents(Date visitingDate, Menu orderedMenu) {
        eventsResult.putAll(eventService.calculateEvents(visitingDate, orderedMenu));
        amounts.put(TOTAL_BENEFITS_AMOUNT, eventService.calculateTotalBenefitsAmount(eventsResult));
        amounts.put(DISCOUNTED_TOTAL_PRICE, eventService.calculateDiscountedTotalAmount(amounts, eventsResult));
        return eventService.saveOrder(visitingDate, orderedMenu, eventsResult);
    }

    private void printEventResult(Order order) {
        printVisitingPreview(order);
        printUserMenusInfo(order);
        printAppliedBenefitsInfo(order);
        printDiscountedTotalAmountInfo();
        printEventBadgeStatus();
    }

    private void printVisitingPreview(Order order) {
        int DateIndex = order.provideVisitingDateInfo().size() - 1;
        int visitingDate = Integer.parseInt(order.provideVisitingDateInfo().get(DateIndex));
        outputView.printEventPreviewMessage(visitingDate);
    }

    private void printUserMenusInfo(Order order) {
        List<String> orderedMenusIinfo = order.provideOrderedMenusInfo();
        int orderedMenusTotalPrice = amounts.get(ORDERED_MENU_TOTAL_PRICE);
        outputView.printUserMenusInfo(orderedMenusIinfo, orderedMenusTotalPrice);
    }

    private void printAppliedBenefitsInfo(Order order) {
        String giftPresentationMenu = eventService.calculateGiftPresentationMenu(order.provideEventsResultInfo());
        List<String> benefitsInfo = order.provideEventsResultInfo();
        int totalBenefitsAmount = amounts.get(TOTAL_BENEFITS_AMOUNT);

        outputView.printAppliedBenefitsInfo(giftPresentationMenu, benefitsInfo, totalBenefitsAmount);
    }

    private void printDiscountedTotalAmountInfo() {
        int discountedTotalPrice = amounts.get(DISCOUNTED_TOTAL_PRICE);
        outputView.printDiscountedTotalAmountInfo(discountedTotalPrice);
    }

    private void printEventBadgeStatus() {
        String badge = eventService.calculateBadge(amounts.get(TOTAL_BENEFITS_AMOUNT) * -1);
        outputView.printEventBadgeStatus(badge);
    }
}