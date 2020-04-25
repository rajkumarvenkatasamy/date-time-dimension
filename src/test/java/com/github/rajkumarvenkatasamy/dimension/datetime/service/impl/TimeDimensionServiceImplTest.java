package com.github.rajkumarvenkatasamy.dimension.datetime.service.impl;

import com.github.rajkumarvenkatasamy.dimension.datetime.repository.TimeDimensionRepository;
import com.github.rajkumarvenkatasamy.dimension.datetime.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class TimeDimensionServiceImplTest {

    private TimeDimensionServiceImpl timeDimensionServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        timeDimensionServiceImplUnderTest = new TimeDimensionServiceImpl();
        timeDimensionServiceImplUnderTest.timeDimensionRepository = mock(TimeDimensionRepository.class);
        timeDimensionServiceImplUnderTest.stringUtil = mock(StringUtil.class);
    }

    @Test
    void testPopulateTimeDimensionMinutePrecision() {
        /*
        // Setup
        when(timeDimensionServiceImplUnderTest.stringUtil.padLeftZeros("inputString", 0)).thenReturn("result");
        when(timeDimensionServiceImplUnderTest.timeDimensionRepository.saveAll(Arrays.asList())).thenReturn(Arrays.asList());

        // Run the test
         final boolean result = timeDimensionServiceImplUnderTest.populateTimeDimensionMinutePrecision();

        // Verify the results
         assertTrue(result);

         */
    }

    @Test
    void testPopulateTimeDimensionSecondsPrecision() {
        /*
        // Setup
        when(timeDimensionServiceImplUnderTest.stringUtil.padLeftZeros("inputString", 0)).thenReturn("result");
        when(timeDimensionServiceImplUnderTest.timeDimensionRepository.saveAll(Arrays.asList())).thenReturn(Arrays.asList());

        // Run the test
        final boolean result = timeDimensionServiceImplUnderTest.populateTimeDimensionSecondsPrecision();

        // Verify the results
        assertTrue(result);

         */
    }
}
