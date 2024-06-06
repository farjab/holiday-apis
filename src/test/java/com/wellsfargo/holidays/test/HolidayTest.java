package com.wellsfargo.holidays.test;

import static org.junit.jupiter.api.Assertions.*;

import com.wellsfargo.holidays.service.BankHolidaysService;
import com.wellsfargo.holidays.service.FederalHolidaysService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class HolidayTest {

    @Autowired
    private FederalHolidaysService federalHolidaysService;

    @Autowired
    private BankHolidaysService bankHolidaysService;

    @Test
    public void testWellsFargoHoliday() {
        assertTrue(bankHolidaysService.isWellsFargoHoliday(LocalDate.of(2024, 1, 1)));  // New Year's Day
        assertFalse(bankHolidaysService.isWellsFargoHoliday(LocalDate.of(2024, 1, 2))); // Day after New Year's Day
    }

    @Test
    public void testFederalHoliday() throws IOException {
        Set<LocalDate> holidays = federalHolidaysService.fetchFederalHolidays();

         assertTrue(holidays.contains(LocalDate.of(2024, 7, 4)));
         assertFalse(holidays.contains(LocalDate.of(2024, 1, 2)));
    }
}
