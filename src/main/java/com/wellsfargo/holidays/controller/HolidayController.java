package com.wellsfargo.holidays.controller;

import com.wellsfargo.holidays.service.BankHolidaysService;
import com.wellsfargo.holidays.service.FederalHolidaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/holidays")
public class HolidayController {

    @Autowired
    private BankHolidaysService bankHolidaysService;

    @Autowired
    private FederalHolidaysService federalHolidaysService;

    // checks if today is a bank holiday
    // @param year (the year for which the query is made for)
    // @countryCode (the country code for which the query is made for)
    @GetMapping("/checkBankHoliday")
    public ResponseEntity<String> checkBankHoliday(@RequestParam String year, @RequestParam String countryCode) {
        LocalDate today = LocalDate.now();

        try {
            boolean isBankHoliday = bankHolidaysService.isWellsFargoHoliday(today);
            boolean isFederalHoliday = federalHolidaysService.isFederalHoliday(today, year, countryCode);

            if (isBankHoliday || isFederalHoliday) {
                return ResponseEntity.status(HttpStatus.OK).body("Today is a bank holiday");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("Today is not a bank holiday");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error handling the request");
        }
    }
}
