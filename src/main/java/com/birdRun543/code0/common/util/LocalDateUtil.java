package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * handle some LocalDateTime format
 * @author hanbing
 */
@Slf4j
public class LocalDateUtil {

    public static LocalDateTime getMonthDay1(LocalDateTime localDateTime) {
        LocalDateTime monthDay1 = localDateTime.withDayOfMonth(1);
        return LocalDateTime.of(monthDay1.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getYearDay1(LocalDateTime localDateTime) {
        LocalDateTime yearDay1 = localDateTime.withDayOfYear(1);
        return LocalDateTime.of(yearDay1.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getMondayZero(LocalDateTime localDateTime) {
        LocalDateTime monday = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN).with(DayOfWeek.MONDAY);
        return LocalDateTime.of(monday.toLocalDate(), LocalTime.MIN);
    }

    public static LocalDateTime getDayZero(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
    }

    /**
     * calculate remain days
     * @param validDays valid days
     * @param startTime start time
     * @param currentTime current time
     * @return int
     */
    public static int countRemainDay(Integer validDays, LocalDateTime startTime,LocalDateTime currentTime) {
        long betweenDays = Duration.between(startTime, currentTime).toDays();
        return betweenDays > validDays ? 0 : validDays - Math.toIntExact(betweenDays);
    }
}
