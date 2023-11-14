package christmas.model;

import christmas.constants.Days;
import christmas.constants.ExceptionMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public int calculateDaysFromStartDayOfMonth() {
        return date - START_DAY_OF_MONTH;
    }

    public boolean isWeekday() {
        return provideDateInfo().contains(Days.WEEKDAY.name());
    }

    public boolean isWeekend() {
        return provideDateInfo().contains(Days.WEEKEND.name());
    }

    public boolean isSpecialday() {
        return provideDateInfo().contains(Days.SPECIALDAY.name());
    }

    public List<String> provideDateInfo() {
        List<String> dateInfo = new ArrayList<>();
        Set<Days> appliedDays = Days.calculateDate(date);
        for (Days appliedDay : appliedDays) {
            dateInfo.add(appliedDay.name());
        }
        dateInfo.add(String.valueOf(date));
        return dateInfo;
    }
}
