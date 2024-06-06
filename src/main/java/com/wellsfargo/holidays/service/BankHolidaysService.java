package com.wellsfargo.holidays.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class BankHolidaysService {
    private Set<LocalDate> wellsFargoHolidays;

    // Bank holidays at Wells Fargo for the year 2024
    public BankHolidaysService() {
        wellsFargoHolidays = new HashSet<>();

        wellsFargoHolidays.add(LocalDate.of(2024, 1, 1));  // New Year's Day
        wellsFargoHolidays.add(LocalDate.of(2024, 1, 15));  // Martin Luther King Day
        wellsFargoHolidays.add(LocalDate.of(2024, 2, 19));  // President Day
        wellsFargoHolidays.add(LocalDate.of(2024, 5, 27));  // Memorial Day
        wellsFargoHolidays.add(LocalDate.of(2024, 6, 19));  // Juneteenth Day
        wellsFargoHolidays.add(LocalDate.of(2024, 7, 4));  // Independence Day
        wellsFargoHolidays.add(LocalDate.of(2024, 9, 2));  // Labor Day
        wellsFargoHolidays.add(LocalDate.of(2024, 10, 14));  // Columbus Day
        wellsFargoHolidays.add(LocalDate.of(2024, 11, 11));  // Veteran's Day
        wellsFargoHolidays.add(LocalDate.of(2024, 11, 28));  // Thanksgivings Day
        wellsFargoHolidays.add(LocalDate.of(2024, 12, 25)); // Christmas Day
    }

    public boolean isWellsFargoHoliday(LocalDate date) {
        return wellsFargoHolidays.contains(date);
    }
}
