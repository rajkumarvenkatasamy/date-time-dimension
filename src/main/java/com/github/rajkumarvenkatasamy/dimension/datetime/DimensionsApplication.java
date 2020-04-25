package com.github.rajkumarvenkatasamy.dimension.datetime;

import com.github.rajkumarvenkatasamy.dimension.datetime.service.DateDimensionService;
import com.github.rajkumarvenkatasamy.dimension.datetime.service.TimeDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DimensionsApplication {

    @Autowired
    TimeDimensionService timeDimensionService;

    @Autowired
    DateDimensionService dateDimensionService;

    public static void main(String[] args) {
        SpringApplication.run(DimensionsApplication.class, args);
    }

}
