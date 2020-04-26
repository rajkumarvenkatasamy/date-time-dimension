package com.github.rajkumarvenkatasamy.dimension.datetime.service.impl;

import com.github.rajkumarvenkatasamy.dimension.datetime.config.ApplicationConfiguration;
import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateDimensionServiceImplTest {

    private ApplicationConfiguration mockApplicationConfiguration= new ApplicationConfiguration("minute" ,
                                                                  "2020-07-10",
                                                                              "2020-07-10",
                                                                  DayOfWeek.MONDAY,
                                                                  DayOfWeek.MONDAY,
                                                                  DayOfWeek.SATURDAY,
                                                                  Month.APRIL);

    private DateDimensionServiceImpl dateDimensionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        dateDimensionServiceImplUnderTest = new DateDimensionServiceImpl(mockApplicationConfiguration);
    }

    @Test
    void testPopulateDateDimension() {
        // Setup

        // Run the test
        final boolean result = dateDimensionServiceImplUnderTest.populateDateDimension();

        // Verify the results
        // assertTrue(result);
    }

    @Test
    void testProcessDateDimension() {
        // Setup
        final DimDate dimDate = new DimDate();
        dimDate.setDateKey((long) 18453);
        dimDate.setDate(LocalDate.of(2020, 07, 10));
        dimDate.setDateInNumber(20200710);
        dimDate.setDayName("Friday");
        dimDate.setShortDayName("Fri");
        dimDate.setIsWeekday('Y');
        dimDate.setIsWeekend('N');
        dimDate.setDayOfWeek(5);
        dimDate.setDayOfWeekInMonth(2);
        dimDate.setDayOfMonth(10);
        dimDate.setDayOfYear(192);
        dimDate.setWeekStartDate(LocalDate.of(2020, 7, 6));
        dimDate.setWeekEndDate(LocalDate.of(2020, 7, 12));
        dimDate.setWeekKey((long) 2636);
        dimDate.setWeekOfMonth(2);
        dimDate.setWeekOfYear(28);
        dimDate.setMonthName("July");
        dimDate.setShortMonthName("Jul");
        dimDate.setYearMonth("2020-07");
        dimDate.setMonthOfYear(7);
        dimDate.setTrailing3Months("2020-05 : 2020-07");
        dimDate.setLast3Months("2020-04 : 2020-06");
        dimDate.setLast6Months("2020-01 : 2020-06");
        dimDate.setNumWeeksInMonth(5);
        dimDate.setMonthKey((long) 606);
        dimDate.setQuarter(3);
        dimDate.setQuarterName("Q3");
        dimDate.setYearQuarterName("2020-Q3");
        dimDate.setQuarterKey((long) 20203);
        dimDate.setHalfYearNumber(2);
        dimDate.setHalfYearName("H2");
        dimDate.setHalfYearKey((long) 20202);
        dimDate.setYearHalfYearName("2020-H2");
        dimDate.setYear(2020);
        dimDate.setYearKey((long) 50);
        dimDate.setFiscalWeekOfYear(15);
        dimDate.setFiscalMonthOfYear(4);
        dimDate.setFiscalQuarter(2);
        dimDate.setFiscalHalf(1);
        dimDate.setFiscalYear(2020);
        dimDate.setFiscalYearQuarterName("2020-Q2");
        dimDate.setFiscalYearHalfYearName("2020-H1");
        dimDate.setFiscalDayOfYear(101);

        final List<DimDate> expectedResult = Arrays.asList(dimDate);

        // Run the test
        final List<DimDate> result = dateDimensionServiceImplUnderTest.processDateDimension();

        // Verify the results
        assertEquals(expectedResult, result);
    }

}
