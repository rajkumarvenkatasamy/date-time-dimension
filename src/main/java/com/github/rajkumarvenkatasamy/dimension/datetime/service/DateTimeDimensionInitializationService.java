package com.github.rajkumarvenkatasamy.dimension.datetime.service;

import com.github.rajkumarvenkatasamy.dimension.datetime.config.ApplicationConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateTimeDimensionInitializationService {

    @Autowired
    TimeDimensionService timeDimensionService;

    @Autowired
    DateDimensionService dateDimensionService;

    @Autowired
    ApplicationConfiguration applicationConfiguration;

    private String timeDimensionPrecision;

    private static final Logger LOGGER = LogManager.getLogger(DateTimeDimensionInitializationService.class);

    public void populateDateTimeDimensionOnStartup(){

        LOGGER.info("Date and Time Dimension initialization starts");

        timeDimensionPrecision = applicationConfiguration.getTimeDimensionPrecision();

        dateDimensionService.populateDateDimension() ;
        LOGGER.info("Date Dimension is ready");

        if (timeDimensionPrecision.equals("minute")) {
            timeDimensionService.populateTimeDimensionMinutePrecision() ;
        } else {
            timeDimensionService.populateTimeDimensionSecondsPrecision() ;
        }
        LOGGER.info("Time Dimension is ready");
        LOGGER.info("Date and Time Dimension initialization completed.");
        LOGGER.info("Please check the tables - dim_date and dim_time in the configure Database");
    }
}
