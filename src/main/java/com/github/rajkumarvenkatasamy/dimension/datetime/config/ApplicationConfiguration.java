package com.github.rajkumarvenkatasamy.dimension.datetime.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Month;

@Component
public class ApplicationConfiguration {
    private String timeDimensionPrecision;
    private String inputStartDate;
    private String inputEndDate;
    private DayOfWeek startDayOfWeek;
    private DayOfWeek weekdayStartsOn;
    private DayOfWeek weekendStartsOn;
    private int dateKeyStartingValue;
    private Month fiscalCalendarStartMonth;

    @Autowired
    public ApplicationConfiguration(@Value("${time.dimension.precision}") String timeDimensionPrecision,
                                    @Value("${start.date}")  String inputStartDate,
                                    @Value("${end.date}")  String inputEndDate,
                                    @Value("${start.day.of.week}")  DayOfWeek startDayOfWeek,
                                    @Value("${weekday.starts.on}")  DayOfWeek weekdayStartsOn,
                                    @Value("${weekend.starts.on}")  DayOfWeek weekendStartsOn,
                                    @Value("${date.skey.starting.value}")  int dateKeyStartingValue,
                                    @Value("${fiscal.calendar.start.month}")  Month fiscalCalendarStartMonth){
        this.timeDimensionPrecision = timeDimensionPrecision;
        this.inputStartDate = inputStartDate;
        this.inputEndDate = inputEndDate;
        this.startDayOfWeek = startDayOfWeek;
        this.weekdayStartsOn = weekdayStartsOn;
        this.weekendStartsOn = weekendStartsOn;
        this.dateKeyStartingValue = dateKeyStartingValue;
        this.fiscalCalendarStartMonth = fiscalCalendarStartMonth;
    }

    public String getTimeDimensionPrecision() {
        return timeDimensionPrecision;
    }

    public String getInputStartDate() {
        return inputStartDate;
    }

    public String getInputEndDate() {
        return inputEndDate;
    }

    public DayOfWeek getStartDayOfWeek() {
        return startDayOfWeek;
    }

    public DayOfWeek getWeekdayStartsOn() {
        return weekdayStartsOn;
    }

    public DayOfWeek getWeekendStartsOn() {
        return weekendStartsOn;
    }

    public int getDateKeyStartingValue() {
        return dateKeyStartingValue;
    }

    public Month getFiscalCalendarStartMonth() {
        return fiscalCalendarStartMonth;
    }
}
