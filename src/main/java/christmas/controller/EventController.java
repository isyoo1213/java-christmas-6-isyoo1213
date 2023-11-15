package christmas.controller;

import christmas.model.Date;
import christmas.model.Menu;
import christmas.service.EventService;
import christmas.utils.InputValidator;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

import static christmas.constants.EventConstants.*;

public class EventController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    InputValidator inputValidator = new InputValidator();
    EventService eventService = new EventService();
    private final Map<String, Integer> amounts = new LinkedHashMap<>();

    public void proceedEvent() {
        outputView.printWelcomeMessage();

        Date visitingDate = saveVisitingDate();
        Menu orderedMenu = saveOrderedMenus();
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
}
