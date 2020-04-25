package com.github.rajkumarvenkatasamy.dimension.datetime.service;

import org.springframework.stereotype.Service;

@Service
public interface TimeDimensionService {

    boolean populateTimeDimensionMinutePrecision();

    boolean populateTimeDimensionSecondsPrecision();
}
