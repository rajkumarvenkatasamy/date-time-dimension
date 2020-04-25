package com.github.rajkumarvenkatasamy.dimension.datetime.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@SpringBootConfiguration
class DateTimeDimensionInitializationServiceTest {

    private DateTimeDimensionInitializationService dateTimeDimensionInitializationServiceUnderTest;

    @BeforeEach
    void setUp() {
        dateTimeDimensionInitializationServiceUnderTest = new DateTimeDimensionInitializationService();
        dateTimeDimensionInitializationServiceUnderTest.timeDimensionService = mock(TimeDimensionService.class);
        dateTimeDimensionInitializationServiceUnderTest.dateDimensionService = mock(DateDimensionService.class);
    }

    @Test
    void testPopulateDateTimeDimensionOnStartup() {
        // Setup
        when(dateTimeDimensionInitializationServiceUnderTest.dateDimensionService.populateDateDimension()).thenReturn(false);
        when(dateTimeDimensionInitializationServiceUnderTest.timeDimensionService.populateTimeDimensionMinutePrecision()).thenReturn(false);
        when(dateTimeDimensionInitializationServiceUnderTest.timeDimensionService.populateTimeDimensionSecondsPrecision()).thenReturn(false);

        // Run the test
        //dateTimeDimensionInitializationServiceUnderTest.populateDateTimeDimensionOnStartup();

        // Verify the results
    }
}
