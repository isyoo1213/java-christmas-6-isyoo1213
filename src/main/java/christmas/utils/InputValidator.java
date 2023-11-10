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
