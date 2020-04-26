package com.github.rajkumarvenkatasamy.dimension.datetime.service.impl;

import com.github.rajkumarvenkatasamy.dimension.datetime.config.ApplicationConfiguration;
import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class DateDimensionServiceImplTest {

    @Mock
    private ApplicationConfiguration mockApplicationConfiguration;

    private DateDimensionServiceImpl dateDimensionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        dateDimensionServiceImplUnderTest = new DateDimensionServiceImpl(mockApplicationConfiguration);
    }

    @Test
    void testPopulateDateDimension() {
        // Setup

        // Run the test
        final boolean result = dateDimensionServiceImplUnderTest.populateDateDimension();

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testProcessDateDimension() {
        // Setup
        final DimDate dimDate = new DimDate();
        dimDate.setDateKey(0L);
        dimDate.setDate(LocalDate.of(2017, 1, 1));
        dimDate.setDateInNumber(0);
        dimDate.setDayName("dayName");
        dimDate.setShortDayName("shortDayName");
        dimDate.setIsWeekday('a');
        dimDate.setIsWeekend('a');
        dimDate.setDayOfWeek(0);
        dimDate.setDayOfWeekInMonth(0);
        dimDate.setDayOfMonth(0);
        final List<DimDate> expectedResult = Arrays.asList(dimDate);

        // Run the test
        final List<DimDate> result = dateDimensionServiceImplUnderTest.processDateDimension();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testToString() {
        // Setup

        // Run the test
        final String result = dateDimensionServiceImplUnderTest.toString();

        // Verify the results
        assertEquals("result", result);
    }
}
