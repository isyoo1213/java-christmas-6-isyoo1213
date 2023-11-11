package christmas.utils;

import christmas.constants.ExceptionMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputValidator {
    private final String MENU_ORDER_SEPERATOR = ",";
    private final String MENU_AMOUNT_SEPERATOR = "-";

    public String preprocessInput(String userInput) {
        if (isNull(userInput)) {
            ExceptionMessages.NULL_INPUT.throwException();
        }
        userInput = removeSpacing(userInput);
        if (isEmpty(userInput)) {
            ExceptionMessages.EMPTY_INPUT.throwException();
        }
        return userInput;
    }

    public Integer convertInputToDate(String preprocessedInput) {
        return castStringToInteger(preprocessedInput);
    }

    public Map<String, Integer> convertInputToMenuOrder(String preprocessedInput) {
        List<String> menuOrders = castStringToStringList(preprocessedInput);
        return castStringListToMap(menuOrders);
    }

    private List<String> castStringToStringList(String preprocessedInput) {
        String[] menuOrders = preprocessedInput.split(MENU_ORDER_SEPERATOR);
        isNonMenuAmountSeparator(menuOrders);
        return Stream.of(menuOrders)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> castStringListToMap(List<String> menuOrders) {
        Map<String, Integer> convertedMenuOrders = new ConcurrentHashMap<>();
        List<String[]> separatedMenuOrders = separateMenuAndAmount(menuOrders);
        for (String[] separatedMenuOrder : separatedMenuOrders) {
            if (separatedMenuOrder.length != 2 || isEmpty(separatedMenuOrder[0]) || isEmpty(separatedMenuOrder[1])) {
                ExceptionMessages.EMPTY_INPUT.throwException();
            }
            convertedMenuOrders.put(separatedMenuOrder[0], Integer.parseInt(separatedMenuOrder[1]));
        }
        if (menuOrders.size() != convertedMenuOrders.size()) {
            ExceptionMessages.DUPLICATED_MENU_ORDER.throwException();
        }
        return convertedMenuOrders;
    }

    private List<String[]> separateMenuAndAmount(List<String> menuOrders) {
        return menuOrders.stream()
                .map((menuOrder) -> menuOrder.split(MENU_AMOUNT_SEPERATOR))
                .collect(Collectors.toList());
    }

    private boolean isNonMenuAmountSeparator(String[] menuOrders) {
        return Stream.of(menuOrders)
                .anyMatch((menuOrder) -> !menuOrder.contains(MENU_AMOUNT_SEPERATOR));
    }

    private Integer castStringToInteger(String preprocessedInput) {
        isNonNumeric(preprocessedInput);
        return Integer.parseInt(preprocessedInput);
    }

    private void isNonNumeric(String preprocessedInput) {
        try {
            Integer.parseInt(preprocessedInput);
        } catch (NumberFormatException e) {
            ExceptionMessages.NON_NUMERIC_INPUT.throwException();
        }
    }

    private boolean isNull(String userInput) {
        return userInput == null;
    }

    private String removeSpacing(String userInput) {
        return userInput.replaceAll(" ", "");
    }

    private boolean isEmpty(String userInput) {
        return userInput.isEmpty();
    }
}
