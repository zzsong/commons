package com.zss.commons.support.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;

public class LocalDateUtils {

    public static int calcMinusDays(int tradeDate, int days){
        LocalDate localDate = transferLocalDate(tradeDate);
        localDate = localDate.minusDays(days);
        return transferYYYYMMDD(localDate);
    }

    public static int calcPlusDays(int tradeDate, int days){
        LocalDate localDate = transferLocalDate(tradeDate);
        localDate = localDate.plusDays(days);
        return transferYYYYMMDD(localDate);
    }

    public static LocalDateTime getLastTimeFromTradeDate(int tradeDate){
        LocalDate localDate = transferLocalDate(tradeDate);
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    //LocalDateTime.now().with(LocalTime.MIN)
    public static LocalDateTime getFirstTimeFromTradeDate(int tradeDate){
        LocalDate localDate = transferLocalDate(tradeDate);
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    public static int transferYYYYMMDD(LocalDate localDate){
        return Integer.parseInt(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
    }

    public static LocalDate transferLocalDate(int tradeDate){
        if (tradeDate <= 0){
            return null;
        }
        return LocalDate.parse(String.valueOf(tradeDate), DateTimeFormatter.BASIC_ISO_DATE);
    }

    public static int betweenDays(int d1, int d2){
        LocalDate p1 = transferLocalDate(d1);
        LocalDate p2 = transferLocalDate(d2);
        return betweenDays(p1, p2);
    }

    public static int betweenDays(LocalDate d1, LocalDate d2){
        return (int) ChronoUnit.DAYS.between(d1,d2);
    }

    public static long betweenMonths(int d1, int d2){
        LocalDate p1 = transferLocalDate(d1);
        LocalDate p2 = transferLocalDate(d2);
        return  ChronoUnit.MONTHS.between(p1, p2);
    }
    public static long betweenMonths(LocalDate d1, LocalDate d2){
        return ChronoUnit.MONTHS.between(d1,d2);
    }

    public static long between(LocalDateTime t1, LocalDateTime t2, TemporalUnit unit){
        return unit.between(t1, t2);
    }

    public static int getWeekOfYear(LocalDate localDate){
        localDate = localDate==null?LocalDate.now():localDate;
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,7);
        return localDate.get(weekFields.weekOfWeekBasedYear());
    }

    public static int lastDayOfMonth(int tradeDate){
        return transferYYYYMMDD(transferLocalDate(tradeDate).with(TemporalAdjusters.lastDayOfMonth()));
    }
    public static int firstDayOfNextMonth(int tradeDate){
        return transferYYYYMMDD(transferLocalDate(tradeDate).with(TemporalAdjusters.firstDayOfNextMonth()));
    }

}
