package christmas.controller;

import christmas.constants.Events;
import christmas.model.Date;
import christmas.model.Menus;
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
        Menus menus = saveMenus();
        Order order = applyEvents(visitingDate, menus);
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

    private Menus saveMenus() {
        try {
            String preprocessedMenus = inputValidator.preprocessInput(inputView.askMenuOrdering());
            Map<String, Integer> convertedMenus = inputValidator.convertInputToMenuOrder(preprocessedMenus);
            Menus menus = eventService.saveMenus(convertedMenus);
            amounts.put(ORDERED_MENU_TOTAL_PRICE, menus.provideMenusTotalPrice());
            return menus;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return saveMenus();
        }
    }

    private Order applyEvents(Date visitingDate, Menus menus) {
        eventsResult.putAll(eventService.calculateEvents(visitingDate, menus));
        amounts.put(TOTAL_BENEFITS_AMOUNT, eventService.calculateTotalBenefitsAmount(eventsResult));
        amounts.put(DISCOUNTED_TOTAL_PRICE, eventService.calculateDiscountedTotalAmount(amounts, eventsResult));
        return eventService.saveOrder(visitingDate, menus, eventsResult);
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
        List<String> orderedMenusInfo = order.provideOrderedMenusInfo();
        int menusTotalPrice = amounts.get(ORDERED_MENU_TOTAL_PRICE);
        outputView.printUserMenusInfo(orderedMenusInfo, menusTotalPrice);
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
