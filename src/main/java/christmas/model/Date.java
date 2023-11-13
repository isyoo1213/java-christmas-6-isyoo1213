package christmas.model;

import christmas.constants.Days;
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

    public boolean isFirstToXmas() {
        return date >= 1 && date <= 25;
    }

    public int calculateDaysToXmas() {
        return CHRISTMAS_DATE - date;
    }

    public boolean isWeekday() {
        return Days.calculateDate(date).equals(Days.WEEKDAY);
    }

    public boolean isWeekend() {
        return Days.calculateDate(date).equals(Days.WEEKEND);
    }

    public boolean isSpecialday() {
        return Days.calculateDate(date).equals(Days.SPECIALDAY);
    }
}
