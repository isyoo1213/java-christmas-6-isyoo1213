package christmas.model;

import christmas.constants.ExceptionMessages;

import static christmas.constants.EventConstants.*;

public class Date {
    private final Integer date;

    public Date(Integer date) {
        validate(date);
        this.date = date;
    }

    private void validate(Integer date) {
        if (date <= ZERO || date >= END_DAY_OF_MONTH) {
            ExceptionMessages.WRONG_DAY_NUMBER_RANGE.throwException();
        }
    }
}
