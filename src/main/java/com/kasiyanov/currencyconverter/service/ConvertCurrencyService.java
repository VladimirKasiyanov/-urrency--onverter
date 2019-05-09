package com.kasiyanov.currencyconverter.service;

import com.kasiyanov.currencyconverter.Dto.ExchangeInputDto;
import com.kasiyanov.currencyconverter.Dto.NbRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConvertCurrencyService {

    private final RestTemplate restTemplate;

    public BigDecimal getConvertedAmount(ExchangeInputDto exchangeInputDto) {
        return exchangeInputDto.getAmount()
                .multiply(getRate(createUrl(exchangeInputDto.getFrom())))
                .divide(getRate(createUrl(exchangeInputDto.getTo())), 2);
    }

    private String createUrl(String currency) {
        return "http://www.nbrb.by/API/ExRates/Rates/" + currency + "?ParamMode=2";
    }

    private BigDecimal getRate(String url) {
        ResponseEntity<NbRateDto> response = restTemplate.getForEntity(url, NbRateDto.class);
        return response.getBody().getCur_OfficialRate()
                .divide(response.getBody().getCur_Scale(), 2);
    }
}
