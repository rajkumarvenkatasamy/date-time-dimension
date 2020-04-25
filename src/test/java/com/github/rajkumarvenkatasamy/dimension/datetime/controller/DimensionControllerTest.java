package com.github.rajkumarvenkatasamy.dimension.datetime.controller;

import com.github.rajkumarvenkatasamy.dimension.datetime.service.DateDimensionService;
import com.github.rajkumarvenkatasamy.dimension.datetime.service.TimeDimensionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class DimensionControllerTest {

    private DimensionController dimensionControllerUnderTest;

    @BeforeEach
    void setUp() {
        dimensionControllerUnderTest = new DimensionController();
        dimensionControllerUnderTest.timeDimensionService = mock(TimeDimensionService.class);
        dimensionControllerUnderTest.dateDimensionService = mock(DateDimensionService.class);
    }

    @Test
    void testPopulateTimeDimension() {
        /*
        // Setup
        when(dimensionControllerUnderTest.timeDimensionService.populateTimeDimensionMinutePrecision()).thenReturn(false);
        when(dimensionControllerUnderTest.timeDimensionService.populateTimeDimensionSecondsPrecision()).thenReturn(false);

        // Run the test
        final HttpStatus result = dimensionControllerUnderTest.populateTimeDimension();

        // Verify the results
        assertEquals(HttpStatus.CONTINUE, result);

         */
    }

    @Test
    void testPopulateDateDimension() {
        /*
        // Setup
        when(dimensionControllerUnderTest.dateDimensionService.populateDateDimension()).thenReturn(false);

        // Run the test
        final HttpStatus result = dimensionControllerUnderTest.populateDateDimension();

        // Verify the results
        assertEquals(HttpStatus.CONTINUE, result);

         */
    }
}
