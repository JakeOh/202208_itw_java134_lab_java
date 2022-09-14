package edu.java.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeMain {

    public static void main(String[] args) {
        // Java 8 버전에서 새로 생긴 날짜/시간 관련 클래스(java.time 패키지)
        LocalDate today = LocalDate.now(); // 현재 날짜
        System.out.println(today);
        System.out.println(today.getYear());
        System.out.println(today.getMonth()); // Month enum을 리턴
        System.out.println(today.getMonthValue()); // 월을 숫자(int)로 리턴
        System.out.println(today.getDayOfMonth());
        System.out.println(today.getDayOfWeek());
        System.out.println(today.plusDays(1));
        System.out.println(today.minusDays(1));
        
        LocalDate date = LocalDate.of(2022, 2, 28);
        System.out.println(date);
        System.out.println(date.plusDays(1));

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        
        
    }

}
