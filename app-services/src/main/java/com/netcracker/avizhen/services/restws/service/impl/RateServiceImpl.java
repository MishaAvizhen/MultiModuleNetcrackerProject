package com.netcracker.avizhen.services.restws.service.impl;

import com.netcracker.avizhen.services.restws.entity.Rate;
import com.netcracker.avizhen.services.restws.service.RateService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by Александр on 29.10.2016.
 */
@Service
public class RateServiceImpl implements RateService {
    private RestTemplate restTemplate;
    private Map<String, Integer> currencyAbbreviationToId;
    private List<Rate> cashRates = new ArrayList<>();

    public RateServiceImpl() {
        currencyAbbreviationToId = new HashMap<>();
        currencyAbbreviationToId.put("RUB", 298);
        currencyAbbreviationToId.put("USD", 145);
        currencyAbbreviationToId.put("EUR", 292);
        currencyAbbreviationToId.put("PLN", 293);
        restTemplate = new RestTemplate();
    }

    @Override
    public Rate getRate(String currencyAbbreviation, LocalDate localDate) {
        Rate res = null;
        try {
            res = restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/" +
                    currencyAbbreviationToId.get(currencyAbbreviation) +
                    "?onDate=" + localDate, Rate.class);

        } catch (RestClientException e) {
            return findRateInList(currencyAbbreviation, cashRates);
        }

        return res;
    }

    private Rate findRateInList(String currencyAbbreviation, List<Rate> rates) {
        for (Rate rate : rates) {
            if (rate.getCur_Abbreviation().equalsIgnoreCase(currencyAbbreviation)) {
                return rate;
            }
        }
        return null;
    }

    @Override
    public List<Rate> getAllRates(LocalDate localDate) {
        List<Rate> result = new ArrayList<>();
        for (String abbr : currencyAbbreviationToId.keySet()) {
            result.add(getRate(abbr, localDate));
        }
        cashRates = result;
        return result;
    }

    @Override
    public Set<String> getAllCurrencyAbbreviation() {
        return currencyAbbreviationToId.keySet();
    }

    @Override
    public double convertPriceTo(double price, String abbr) {
        Rate rate = getRate(abbr, LocalDate.now());
        if (rate == null) {
            return 0;
        }
        return price / rate.getCur_OfficialRate() * rate.getCur_Scale();
    }

    public void addCurrencyAbbreviation(String currencyAbbreviation, int id) {
        currencyAbbreviationToId.put(currencyAbbreviation, id);
    }

}
