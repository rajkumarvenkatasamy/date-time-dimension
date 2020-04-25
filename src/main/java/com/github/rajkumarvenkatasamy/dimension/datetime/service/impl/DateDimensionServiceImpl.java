package com.github.rajkumarvenkatasamy.dimension.datetime.service.impl;

import com.github.rajkumarvenkatasamy.dimension.datetime.config.ApplicationConfiguration;
import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimDate;
import com.github.rajkumarvenkatasamy.dimension.datetime.repository.DateDimensionRepository;
import com.github.rajkumarvenkatasamy.dimension.datetime.service.DateDimensionService;
import com.github.rajkumarvenkatasamy.fiscalcalendar.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.*;

@Service
public class DateDimensionServiceImpl implements DateDimensionService {
    private static final Logger LOGGER = LogManager.getLogger(DateDimensionServiceImpl.class);

    private String inputStartDate;
    private String inputEndDate;
    private DayOfWeek startDayOfWeek;
    private DayOfWeek weekdayStartsOn;
    private DayOfWeek weekendStartsOn;
    private int dateKeyStartingValue;
    private Month fiscalCalendarStartMonth;

    @Autowired
    DateDimensionRepository dateDimensionRepository;

    DateDimensionServiceImpl(ApplicationConfiguration applicationConfiguration){
        inputStartDate = applicationConfiguration.getInputStartDate();
        inputEndDate = applicationConfiguration.getInputEndDate();
        startDayOfWeek = applicationConfiguration.getStartDayOfWeek();
        weekdayStartsOn = applicationConfiguration.getWeekdayStartsOn();
        weekendStartsOn = applicationConfiguration.getWeekendStartsOn();
        dateKeyStartingValue = applicationConfiguration.getDateKeyStartingValue();
        fiscalCalendarStartMonth = applicationConfiguration.getFiscalCalendarStartMonth();
    }

    @Override
    @Transactional
    public boolean populateDateDimension() {

        LocalTime localTime1 = LocalTime.now();
        List<DimDate> dimDateList = new ArrayList<>();

        try{
            dimDateList = processDateDimension();
            dateDimensionRepository.saveAll(dimDateList);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        LocalTime localTime2 = LocalTime.now();
        LOGGER.info("Elapsed Time (seconds) : " + MINUTES.between(localTime1, localTime2));

        return true;
    }

    public List<DimDate> processDateDimension(){
        FiscalDate fiscalDateObj = new FiscalDate();
        FiscalWeek fiscalWeekObj = new FiscalWeek();
        FiscalMonth fiscalMonthObj = new FiscalMonth();
        FiscalQuarter fiscalQuarterObj = new FiscalQuarter();
        FiscalHalfYear fiscalHalfYearObj = new FiscalHalfYear();
        FiscalYear fiscalYearObj = new FiscalYear();


        LocalDate startDate = LocalDate.parse(inputStartDate);
        LocalDate endDate = LocalDate.parse(inputEndDate);
        LocalDate dateInLoop = startDate;
        LocalDate weekStartDate, weekEndDate;
        LocalDate epochDate = LocalDate.ofEpochDay(0);

        DayOfWeek dayOfWeek;
        int dateKey = dateKeyStartingValue;
        int diffBetweenWeekStartDayAndToday;
        int halfYearNumber;
        String firstDateOfMonth, firstDateOfTrailing3rdMonth, firstDateOfLastMonth, firstDateOfLast3rdMonth,
                firstDateOfLast6thMonth;
        String quarterName, quarterString, yearString;
        DateTimeFormatter yearMonth = DateTimeFormatter.ofPattern("YYYY-MM");
        LOGGER.info("Start and End Date is : " + startDate + " and " + endDate);
        Map<DayOfWeek, Integer> dayAndValue;
        int fiscalMonthOfYear, fiscalYear, fiscalQuarterOfYear, fiscalHalfOfYear;
        String fiscalYearString, fiscalQuarterName, fiscalHalfName;
        List<DimDate> dimDateList = new ArrayList<>();
        dayAndValue = createDayAndValueMap();

        while (dateInLoop.compareTo(endDate) <= 0) {
            DimDate dimDate = new DimDate();
            yearString = dateInLoop.toString().substring(0, 4);

            dimDate.setDateKey(dateKey);
            dimDate.setDate(dateInLoop);
            dimDate.setDateInNumber(Integer.valueOf(dateInLoop.toString().replaceAll("-", "")));
            dimDate.setDaySinceEpoch(dateInLoop.toEpochDay());

            dayOfWeek = dateInLoop.getDayOfWeek();
            diffBetweenWeekStartDayAndToday = Math.abs(dayAndValue.get(
                    startDayOfWeek) - dayAndValue.get(dayOfWeek));
            weekStartDate = dateInLoop.minusDays(diffBetweenWeekStartDayAndToday);
            dimDate.setWeekStartDate(weekStartDate);

            weekEndDate = weekStartDate.plusDays(6);
            dimDate.setWeekEndDate(weekEndDate);

            dimDate.setDayName(dayOfWeek);
            dimDate.setShortDayName(dayOfWeek.toString().substring(0, 3));
            dimDate.setIsWeekday(dayOfWeek.equals(startDayOfWeek) || dayOfWeek.equals(startDayOfWeek.plus(1))
                    || dayOfWeek.equals(startDayOfWeek.plus(2)) || dayOfWeek.equals(startDayOfWeek.plus(3))
                    || dayOfWeek.equals(startDayOfWeek.plus(4))
                    ? 'Y' : 'N');
            dimDate.setIsWeekend(dayOfWeek.equals(startDayOfWeek.plus(5)) || dayOfWeek.equals(startDayOfWeek.plus(6))
                    ? 'Y' : 'N');
            dimDate.setDayOfWeek((dayAndValue.get(dayOfWeek) - dayAndValue.get(startDayOfWeek)) + 1);
            dimDate.setDayOfMonth(dateInLoop.getDayOfMonth());
            dimDate.setDayOfYear(dateInLoop.getDayOfYear());
            dimDate.setDayOfWeekInMonth(findDayOfWeekInMonth(dateInLoop));

            dimDate.setNumWeeksInMonth(YearMonth.of(dateInLoop.getYear(), dateInLoop.getMonthValue()).atEndOfMonth()
                    .get(WeekFields.ISO.weekOfMonth()));
            dimDate.setWeekOfMonth(dateInLoop.get(WeekFields.ISO.weekOfMonth()));
            dimDate.setWeeksSinceEpoch(WEEKS.between(epochDate, dateInLoop));
            dimDate.setWeekOfYear(dateInLoop.get(WeekFields.ISO.weekOfYear()));
            dimDate.setMonthName(dateInLoop.getMonth());
            dimDate.setShortMonthName(dateInLoop.getMonth().toString().substring(0, 3));
            dimDate.setMonthOfYear(dateInLoop.getMonthValue());
            dimDate.setMonthsSinceEpoch(MONTHS.between(epochDate, dateInLoop));
            dimDate.setYearMonth(String.valueOf(YearMonth.of(dateInLoop.getYear(), dateInLoop.getMonthValue())));

            firstDateOfMonth = dateInLoop.with(TemporalAdjusters.firstDayOfMonth()).format(yearMonth);
            firstDateOfLastMonth = dateInLoop.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1)
                    .format(yearMonth);
            firstDateOfTrailing3rdMonth = dateInLoop.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2)
                    .format(yearMonth);
            firstDateOfLast3rdMonth = dateInLoop.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(3)
                    .format(yearMonth);
            firstDateOfLast6thMonth = dateInLoop.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(6)
                    .format(yearMonth);

            dimDate.setTrailing3Months(firstDateOfTrailing3rdMonth.concat(" : ").concat(firstDateOfMonth));
            dimDate.setLast3Months(firstDateOfLast3rdMonth.concat(" : ").concat(firstDateOfLastMonth));
            dimDate.setLast6Months(firstDateOfLast6thMonth.concat(" : ").concat(firstDateOfLastMonth));

            dimDate.setQuarter(String.valueOf(dateInLoop.get(IsoFields.QUARTER_OF_YEAR)));
            quarterName = "Q" +
                    dateInLoop.get(IsoFields.QUARTER_OF_YEAR);
            quarterString = String.valueOf(dateInLoop.get(IsoFields.QUARTER_OF_YEAR));
            dimDate.setQuarterId(Integer.valueOf((yearString)
                    .concat(quarterString)));
            dimDate.setQuarterName(quarterName);
            dimDate.setYearQuarterName(yearString +
                    "-" +
                    quarterName);

            halfYearNumber = dateInLoop.getMonthValue() <= 6 ? 1 : 2;
            dimDate.setHalfYearNumber(halfYearNumber);
            dimDate.setHalfYearName(halfYearNumber == 1 ? "H1" : "H2");
            dimDate.setYearHalfYearName(yearString +
                    "-" +
                    (halfYearNumber == 1 ? "H1" : "H2"));
            dimDate.setHalfYearId(Integer.valueOf(yearString.concat(String.valueOf(halfYearNumber))));
            dimDate.setYear(dateInLoop.getYear());
            dimDate.setYearsSinceEpoch(YEARS.between(epochDate, dateInLoop));

            fiscalMonthOfYear = fiscalMonthObj.getFiscalMonthOfYear(fiscalCalendarStartMonth, dateInLoop);
            fiscalQuarterOfYear = fiscalQuarterObj.getFiscalQuarterOfYear(fiscalCalendarStartMonth, dateInLoop);
            fiscalHalfOfYear = fiscalHalfYearObj.getFiscalHalfOfYear(fiscalCalendarStartMonth, dateInLoop);
            fiscalYear = fiscalYearObj.getFiscalYear(fiscalCalendarStartMonth, dateInLoop);


            fiscalYearString = String.valueOf(fiscalYear);
            fiscalQuarterName = "Q".concat(String.valueOf(fiscalQuarterOfYear));
            fiscalHalfName = "H".concat(String.valueOf(fiscalHalfOfYear));

            dimDate.setFiscalWeekOfYear(fiscalWeekObj.getFiscalWeekOfYear(fiscalCalendarStartMonth, dateInLoop));
            dimDate.setFiscalMonthOfYear(fiscalMonthOfYear);
            dimDate.setFiscalQuarter(fiscalQuarterOfYear);
            dimDate.setFiscalHalf(fiscalHalfOfYear);
            dimDate.setFiscalYear(fiscalYear);
            dimDate.setFiscalYearQuarterName(fiscalYearString.concat("-").concat(fiscalQuarterName));
            dimDate.setFiscalYearHalfYearName(fiscalYearString.concat("-").concat(fiscalHalfName));
            dimDate.setFiscalDayOfYear(fiscalDateObj.getFiscalDayOfYear(fiscalCalendarStartMonth, dateInLoop));

            dateInLoop = dateInLoop.plusDays(1);
            dateKey = dateKey + 1;
            LOGGER.debug("dimDate object values : " + dimDate.toString());
            dimDateList.add(dimDate);
        }
        return dimDateList;
    }

    private Map<DayOfWeek, Integer> createDayAndValueMap() {
        Map<DayOfWeek, Integer> dayAndValue = new HashMap<>();
        switch (startDayOfWeek) {
            case MONDAY: {
                dayAndValue.put(DayOfWeek.MONDAY, 1);
                dayAndValue.put(DayOfWeek.TUESDAY, 2);
                dayAndValue.put(DayOfWeek.WEDNESDAY, 3);
                dayAndValue.put(DayOfWeek.THURSDAY, 4);
                dayAndValue.put(DayOfWeek.FRIDAY, 5);
                dayAndValue.put(DayOfWeek.SATURDAY, 6);
                dayAndValue.put(DayOfWeek.SUNDAY, 7);
                break;
            }
            case SUNDAY: {
                dayAndValue.put(DayOfWeek.SUNDAY, 1);
                dayAndValue.put(DayOfWeek.MONDAY, 2);
                dayAndValue.put(DayOfWeek.TUESDAY, 3);
                dayAndValue.put(DayOfWeek.WEDNESDAY, 4);
                dayAndValue.put(DayOfWeek.THURSDAY, 5);
                dayAndValue.put(DayOfWeek.FRIDAY, 6);
                dayAndValue.put(DayOfWeek.SATURDAY, 7);
                break;
            }
            case SATURDAY: {
                dayAndValue.put(DayOfWeek.SATURDAY, 1);
                dayAndValue.put(DayOfWeek.SUNDAY, 2);
                dayAndValue.put(DayOfWeek.MONDAY, 3);
                dayAndValue.put(DayOfWeek.TUESDAY, 4);
                dayAndValue.put(DayOfWeek.WEDNESDAY, 5);
                dayAndValue.put(DayOfWeek.THURSDAY, 6);
                dayAndValue.put(DayOfWeek.FRIDAY, 7);
                break;
            }
            default: {
                LOGGER.error("Invalid Start Day");
            }
        }
        return dayAndValue;
    }

    private int findDayOfWeekInMonth(LocalDate inputDate) {
        int dayOfWeekInMonth = 0;
        LocalDate firstDateOfMonth = inputDate.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate runningDate = firstDateOfMonth;
        while (runningDate.compareTo(inputDate) <= 0) {
            for (int i = 1; i <= 7; i++) {
                if (inputDate.getDayOfWeek().equals(DayOfWeek.of(i))) {
                    dayOfWeekInMonth = dayOfWeekInMonth + 1;
                }
                runningDate = runningDate.plusDays(1);
            }

        }
        return dayOfWeekInMonth;
    }

    @Override
    public String toString() {
        return "DateDimensionServiceImpl{" +
                "inputStartDate='" + inputStartDate + '\'' +
                ", inputEndDate='" + inputEndDate + '\'' +
                ", startDayOfWeek=" + startDayOfWeek +
                ", weekdayStartsOn=" + weekdayStartsOn +
                ", weekendStartsOn=" + weekendStartsOn +
                ", dateKeyStartingValue='" + dateKeyStartingValue + '\'' +
                ", fiscalCalendarStartMonth=" + fiscalCalendarStartMonth +
                ", dateDimensionRepository=" + dateDimensionRepository +
                '}';
    }
}
