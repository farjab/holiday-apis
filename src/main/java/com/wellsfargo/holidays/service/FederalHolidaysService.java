package com.wellsfargo.holidays.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class FederalHolidaysService {
    private static final String API_URL = "https://date.nager.at/api/v3/publicholidays";

    //get all the federal holidays
    public Set<LocalDate> fetchFederalHolidays(String year, String countryCode) throws IOException {
        Set<LocalDate> federalHolidays = new HashSet<>();

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(API_URL + "/" + year + "/" + countryCode, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode holidaysArray = mapper.readTree(response);

        for (JsonNode holidayNode : holidaysArray) {
           // JsonNode node = holidayNode.get("types");
           // JsonNode nl = node.get(0);

            LocalDate holidayDate = LocalDate.parse(holidayNode.get("date").asText());
            federalHolidays.add(holidayDate);
        }

        return federalHolidays;
    }


    public boolean isFederalHoliday(LocalDate date, String year, String countryCode) throws IOException {
        Set<LocalDate> federalHolidays = fetchFederalHolidays(year, countryCode);
        return federalHolidays.contains(date);
    }
}
