package christmas.utils;

import christmas.constants.ExceptionMessages;

public class InputValidator {

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
