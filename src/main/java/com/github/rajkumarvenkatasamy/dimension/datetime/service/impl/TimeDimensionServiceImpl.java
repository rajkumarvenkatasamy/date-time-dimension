package com.github.rajkumarvenkatasamy.dimension.datetime.service.impl;

import com.github.rajkumarvenkatasamy.dimension.datetime.model.DimTime;
import com.github.rajkumarvenkatasamy.dimension.datetime.repository.TimeDimensionRepository;
import com.github.rajkumarvenkatasamy.dimension.datetime.service.TimeDimensionService;
import com.github.rajkumarvenkatasamy.dimension.datetime.utils.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.*;

import static java.time.temporal.ChronoUnit.*;

@Service
public class TimeDimensionServiceImpl implements TimeDimensionService {

    private static final Logger LOGGER = LogManager.getLogger(TimeDimensionServiceImpl.class);

    @Autowired
    TimeDimensionRepository timeDimensionRepository;

    @Autowired
    StringUtil stringUtil;

    private DimTime callTimeDimensionUtility(int hour, int minute, int second) {

        int hour12;
        int startMinuteIn15MinSlab, endMinuteIn15MinSlab, startMinuteIn30MinSlab, endMinuteIn30MinSlab;
        String slabFor15Minutes, slabFor30Minutes;
        String paddedHour, paddedMinute, paddedSecond, paddedHour12, hour24FullString;

        DimTime dimTime = new DimTime();

        paddedHour = stringUtil.padLeftZeros(String.valueOf(hour), 2);
        paddedMinute = stringUtil.padLeftZeros(String.valueOf(minute), 2);
        paddedSecond = stringUtil.padLeftZeros(String.valueOf(second), 2);
        hour12 = hour < 12 ? hour : hour - 12;
        paddedHour12 = stringUtil.padLeftZeros(String.valueOf(hour12), 2);
        hour24FullString = paddedHour +
                ':' +
                paddedMinute +
                ':' +
                paddedSecond;
        switch (minute) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14: {
                startMinuteIn15MinSlab = 0;
                endMinuteIn15MinSlab = 14;
                slabFor15Minutes = "01-15";
                break;
            }
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29: {
                startMinuteIn15MinSlab = 15;
                endMinuteIn15MinSlab = 29;
                slabFor15Minutes = "16-30";
                break;
            }
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44: {
                startMinuteIn15MinSlab = 30;
                endMinuteIn15MinSlab = 44;
                slabFor15Minutes = "31-45";
                break;
            }
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59: {
                startMinuteIn15MinSlab = 45;
                endMinuteIn15MinSlab = 59;
                slabFor15Minutes = "46-60";
                break;
            }
            default: {
                startMinuteIn15MinSlab = 999;
                endMinuteIn15MinSlab = 999;
                slabFor15Minutes = "999";
                LOGGER.error("Invalid Minute");
                break;
            }
        }

        switch (minute) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29: {
                startMinuteIn30MinSlab = 0;
                endMinuteIn30MinSlab = 29;
                slabFor30Minutes = "01-30";
                break;
            }
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59: {
                startMinuteIn30MinSlab = 30;
                endMinuteIn30MinSlab = 59;
                slabFor30Minutes = "31-60";
                break;
            }
            default: {
                startMinuteIn30MinSlab = 999;
                endMinuteIn30MinSlab = 999;
                slabFor30Minutes = "999";
                LOGGER.error("Invalid Minute");
                break;
            }
        }

        dimTime.setHour24(hour);
        dimTime.setHour24ShortString(paddedHour);
        dimTime.setHour24MinuteString(paddedHour +
                ':' +
                paddedMinute);
        dimTime.setHour24FullString(hour24FullString);
        // dimTime.setTimeKey(Integer.valueOf(hour24FullString.replaceAll(":","")));
        dimTime.setAmPM(hour < 12 ? "AM" : "PM");
        dimTime.setMinute(minute);
        dimTime.setStartMinuteIn15MinSlab(minute);
        dimTime.setHour12(hour12);
        dimTime.setHour12ShortString(paddedHour12);
        dimTime.setHour12MinuteString(paddedHour12 +
                ':' +
                paddedMinute);
        dimTime.setHour12FullString(paddedHour12 +
                ':' +
                paddedMinute +
                ':' +
                paddedSecond +
                ' ' +
                (hour < 12 ? "AM" : "PM"));

        dimTime.setStartMinuteIn15MinSlab(startMinuteIn15MinSlab);
        dimTime.setEndMinuteIn15MinSlab(endMinuteIn15MinSlab);
        dimTime.setStartMinuteIn30MinSlab(startMinuteIn30MinSlab);
        dimTime.setEndMinuteIn30MinSlab(endMinuteIn30MinSlab);
        dimTime.setSlabFor15Minutes(slabFor15Minutes);
        dimTime.setSlabFor30Minutes(slabFor30Minutes);
        return dimTime;
    }

    @Transactional
    public boolean populateTimeDimensionMinutePrecision() {
        LocalTime localTime1 = LocalTime.now();
        int hour, minute, second = 0;
        int timeKeySequence = 1;
        List<DimTime> dimTimeList = new ArrayList<>();

        for (hour = 0; hour <= 23; hour++) {
            for (minute = 0; minute <= 59; minute++) {
                DimTime dimTime = callTimeDimensionUtility(hour, minute, second);

                dimTime.setTimeKey(timeKeySequence);
                timeKeySequence = timeKeySequence + 1;
                dimTimeList.add(dimTime);
                LOGGER.debug("DimTime obj is : " + dimTime.toString());
            }
        }
        timeDimensionRepository.saveAll(dimTimeList);
        LocalTime localTime2 = LocalTime.now();
        LOGGER.info("Elapsed Time (seconds) : " + SECONDS.between(localTime1, localTime2));
        return true;
    }

    @Transactional
    public boolean populateTimeDimensionSecondsPrecision() {
        LocalTime localTime1 = LocalTime.now();
        int hour, minute, second;
        int timeKeySequence = 1;
        List<DimTime> dimTimeList = new ArrayList<>();

        for (hour = 0; hour <= 23; hour++) {
            for (minute = 0; minute <= 59; minute++) {
                for (second = 0; second <= 59; second++) {
                    DimTime dimTime = callTimeDimensionUtility(hour, minute, second);
                    dimTime.setSecond(second);
                    dimTime.setTimeKey(timeKeySequence);
                    timeKeySequence = timeKeySequence + 1;
                    LOGGER.debug("DimTime obj is : " + dimTime.toString());
                    dimTimeList.add(dimTime);
                }
            }
        }
        timeDimensionRepository.saveAll(dimTimeList);
        LocalTime localTime2 = LocalTime.now();
        LOGGER.info("Elapsed Time (seconds) : " + SECONDS.between(localTime1, localTime2));
        return true;
    }

}
