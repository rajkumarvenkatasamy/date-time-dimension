package com.github.rajkumarvenkatasamy.dimension.datetime.controller;

import com.github.rajkumarvenkatasamy.dimension.datetime.service.DateDimensionService;
import com.github.rajkumarvenkatasamy.dimension.datetime.service.TimeDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DimensionController {

    @Autowired
    TimeDimensionService timeDimensionService;

    @Autowired
    DateDimensionService dateDimensionService;

    @Value("${time.dimension.precision}")
    private String timeDimensionPrecision;

    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public HttpStatus populateTimeDimension() {
        if (timeDimensionPrecision.equals("minute")) {
            return timeDimensionService.populateTimeDimensionMinutePrecision() ?
                    HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        } else {
            return timeDimensionService.populateTimeDimensionSecondsPrecision() ?
                    HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        }
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST)
    public HttpStatus populateDateDimension() {
        return dateDimensionService.populateDateDimension() ?
                HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }
}
