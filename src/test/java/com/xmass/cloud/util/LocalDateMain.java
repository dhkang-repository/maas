package com.xmass.cloud.util;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.ValueRange;

public class LocalDateMain {
    public static void main(String[] args) {
        // [LocalDate]
        LocalDate nowDate = LocalDate.now();
        LocalDate ofDate = LocalDate.of(2013, 11, 21);
        System.out.println("오늘 날짜 = " + nowDate);
        System.out.println("지정 날짜 = " + ofDate);

        // [LocalDate] 계산(불변)
        LocalDate plusDays = ofDate.plusDays(10);
        System.out.println("지정 날짜+10d = " + plusDays);

        // LocalTime
        LocalTime nowTime = LocalTime.now();
        LocalTime ofTime = LocalTime.of(9, 10, 30);

        System.out.println("현재 시간 = "+ nowTime);
        System.out.println("지정 시간 = " + ofTime);

        LocalTime plusTime = ofTime.plusSeconds(10);
        System.out.println("지정 시간+10s = "+ plusTime);

        // [LocalDateTime]
        LocalDateTime nowDt = LocalDateTime.now();
        LocalDateTime ofDt = LocalDateTime.of(2016, 8, 16, 8, 10, 1);

        System.out.println("현재 날짜시간 = " + nowDt);
        System.out.println("지정 날짜시간 = " + ofDt);

        // 날짜와 시간 분리
        LocalDate localDate = ofDt.toLocalDate();
        LocalTime localTime = ofDt.toLocalTime();
        System.out.println("localDate = " + localDate);
        System.out.println("localTime = " + localTime);

        // 날짜 시간 병합
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        System.out.println("localDateTime = " + localDateTime);

        // 계산 (불변)
        LocalDateTime ofDtPlus = ofDt.plusDays(1000);
        System.out.println("지정 날짜시간+1000d = " + ofDtPlus);
        LocalDateTime ofDtPlus1Year = ofDt.plusYears(1);
        System.out.println("지정 날짜시간+1년 + " + ofDtPlus1Year);

        System.out.println("isBefore() = " + ofDt.isBefore(ofDtPlus1Year));
        System.out.println("isAfter() = " + ofDt.isAfter(ofDtPlus1Year));
        System.out.println("isEqual() = " + ofDt.isEqual(ofDtPlus1Year));

        // [ZoneId]
        for(String availableZoneId : ZoneId.getAvailableZoneIds()) {
            ZoneId zoneId = ZoneId.of(availableZoneId);
            System.out.println(zoneId + " | " + zoneId.getRules());
        }

        // system default
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("ZoneId.systemDefault = " + zoneId);

        ZoneId seoulZoneId = ZoneId.of("Asia/Seoul");
        System.out.println("ZoneId.AsiaSeoul " + seoulZoneId);

        ZonedDateTime nowZdt = ZonedDateTime.now();
        System.out.println("nowZdt = " + nowZdt);

        LocalDateTime ldt = LocalDateTime.of(2030, 1, 1, 13, 30, 50);
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt, ZoneId.of("Asia/Seoul"));
        System.out.println("zdt1 = " + zdt1);

        ZonedDateTime zdt2 = ZonedDateTime.of(2030, 1, 1, 13, 30, 50, 0, ZoneId.of("Asia/Seoul"));
        System.out.println("zdt2 = " + zdt2);

        ZonedDateTime utcZdt = zdt2.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("utcZdt = " + utcZdt);

        OffsetDateTime nowOdt = OffsetDateTime.now();
        System.out.println("nowOdt = " + nowOdt);

        // [Offset]
        OffsetDateTime odt = OffsetDateTime.of(ldt, ZoneOffset.of("+01:00"));
        System.out.println(odt);

        // [Instance]
        Instant iNow = Instant.now();//UTC
        System.out.println("instance now() " + iNow);

        ZonedDateTime zdt = ZonedDateTime.now();
        Instant from = Instant.from(zdt);
        System.out.println("from = " + from);

        Instant epochStart = Instant.ofEpochSecond(0);
        System.out.println("epochStart = " + epochStart);

        Instant later = epochStart.plusSeconds(3600);
        System.out.println("later = " + later);

        long epochSecond = later.getEpochSecond();
        System.out.println("epochSecond = " + epochSecond);

        // [Period]
        Period period = Period.ofDays(10);
        System.out.println("period = " + period);

        LocalDate currentDate = LocalDate.of(2030, 1, 1);
        LocalDate plusDate = currentDate.plus(period);
        System.out.println("currentDate = " + currentDate);
        System.out.println("plusDate = " + plusDate);

        // 기간 차이
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 4, 2);
        Period between = Period.between(startDate, endDate);
        System.out.println("기간 = " + between.getMonths() + " 개월, " + between.getDays() + " 일");

        // [Duration]
        Duration duration = Duration.ofMinutes(30);
        System.out.println("duration = " + duration);

        LocalTime lt = LocalTime.of(1, 0);
        System.out.println("lt = " + lt);

        LocalTime plt = lt.plus(duration);
        System.out.println("plt = " + plt);

        LocalTime dstartTime = LocalTime.of(9, 0);
        LocalTime dendTime = LocalTime.of(10, 0);
        Duration dbetween = Duration.between(dendTime, dstartTime);
        System.out.println("dbetween = " + dbetween);

        // Chrono Unit
        ChronoUnit[] values = ChronoUnit.values();
        for (ChronoUnit value : values) {
            System.out.println(value);
        }
        long seconds = ChronoUnit.HOURS.getDuration().getSeconds();
        System.out.println("seconds = " + seconds);

        long seconds1 = ChronoUnit.DAYS.getDuration().getSeconds();
        System.out.println("seconds1 = " + seconds1);

        LocalTime lt1 = LocalTime.of(1, 10, 0);
        LocalTime lt2 = LocalTime.of(1, 20, 0);

        long between1 = ChronoUnit.SECONDS.between(lt1, lt2);
        System.out.println("between1 = " + between1);

        ChronoField[] values1 = ChronoField.values();
        for (ChronoField chronoField : values1) {
            System.out.println(chronoField + " / " + chronoField.range());
        }

        ValueRange range = ChronoField.MONTH_OF_YEAR.range();
        System.out.println(range);
        ValueRange range1 = ChronoField.DAY_OF_MONTH.range();
        System.out.println("range1 = " + range1);
    }

    @Test
    void getTime() {
        LocalDateTime dt = LocalDateTime.of(2030, 1, 1, 13, 30, 59);
        System.out.println("dt.get(ChronoField.YEAR) = " + dt.get(ChronoField.YEAR));
        System.out.println("dt.get(ChronoField.YEAR) = " + dt.getYear());
        System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.getMonthValue());
        System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.getHour());
        System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.getMinute());
        System.out.println("dt.get(ChronoField.MONTH_OF_YEAR) = " + dt.getSecond());

        System.out.println("MINUTE_OF_DAY = " + dt.get(ChronoField.MINUTE_OF_DAY));
        System.out.println("SECOND_OF_DAY = " + dt.get(ChronoField.SECOND_OF_DAY));

    }

    @Test
    void changeTimePlus() {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
        System.out.println("dt = " + dt);
        LocalDateTime plusDt1 = dt.plus(10, ChronoUnit.YEARS);
        System.out.println("plusDt1 = " + plusDt1);

        LocalDateTime plusDt2 = dt.plusYears(10);
        System.out.println("plusDt2 = " + plusDt2);

        Period period3 = Period.ofYears(10);
        System.out.println("period3 = " + period3);


    }

    @Test
    void isSupported(){
        LocalDate now = LocalDate.now();
        boolean supported = now.isSupported(ChronoField.SECOND_OF_MINUTE);
        if(supported){
            System.out.println("supported = " + supported);
            int i = now.get(ChronoField.SECOND_OF_MINUTE);
            System.out.println("i = " + i);
        }


    }

    @Test
    void changeTimeWith() {
        LocalDateTime dt = LocalDateTime.of(2018, 1, 1, 13, 30, 59);
        LocalDateTime changedDt1 = dt.with(ChronoField.YEAR, 2020);
        System.out.println("changedDt1 = " + changedDt1);

        LocalDateTime chagnedDt2 = dt.withYear(2020);
        System.out.println("chagnedDt2 = " + chagnedDt2);

        // TemporalAdjuster
        // next week friday
        LocalDateTime with1 = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("기준날짜 = " + dt);
        System.out.println("다음주 금요일 = " + with1);

        LocalDateTime with2 = dt.with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
        System.out.println("같은 달의 마지막 일요일 = " + with2);
    }

    @Test
    void formatting() {
        LocalDate date = LocalDate.of(2024, 12, 31);
        System.out.println("date = " + date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        String formattedDate = date.format(formatter);
        System.out.println("formattedDate = " + formattedDate);

        // 파싱: 문자를 날짜로
        String input = "2030년 01월 01일";
        LocalDate parseDate = LocalDate.parse(input, formatter);
        System.out.println("문자열 파싱 날짜와 시간: " + parseDate);

        // 포맷팅 : 날짜와 시간을 문자로
        LocalDateTime now = LocalDateTime.of(2024, 12, 31, 13, 30, 59);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate1 = now.format(formatter1);
        System.out.println("formattedDate1 = " + formattedDate1);

        // 파싱: 문자를 날짜와 시간으로
        String dateTimeString = "2030-01-01 11:30:00";
        LocalDateTime parseedDateTime = LocalDateTime.parse(dateTimeString, formatter1);
        System.out.println("parseedDateTime = " + parseedDateTime);

    }

}
