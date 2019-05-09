package com.kasiyanov.currencyconverter.controller;

import com.kasiyanov.currencyconverter.Dto.ExchangeInputDto;
import com.kasiyanov.currencyconverter.service.ConvertCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConvertCurrencyController {

    private final ConvertCurrencyService convertCurrencyService;

    @GetMapping(value = "/resttemplale/convert", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BigDecimal convertCurrency(@RequestBody ExchangeInputDto exchangeInputDto) {
        return convertCurrencyService.getConvertedAmount(exchangeInputDto);
    }
}
