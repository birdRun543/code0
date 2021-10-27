package com.birdRun543.code0.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * handle some LocalDateTime format
 * @author hanbing
 */
@Slf4j
public class LocalDateUtil {

    public static LocalDateTime getMonthDay1(LocalDateTime localDateTime) {
        LocalDateTime monthDay1 = localDateTime.withDayOfMonth(1);
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(df1.format(monthDay1), df2);
    }

    public static LocalDateTime getDayZero(LocalDateTime localDateTime) {

        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(df1.format(localDateTime), df2);
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
