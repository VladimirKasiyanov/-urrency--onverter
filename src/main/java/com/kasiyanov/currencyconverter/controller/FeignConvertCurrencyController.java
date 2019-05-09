package com.kasiyanov.currencyconverter.controller;

import com.kasiyanov.currencyconverter.Dto.ExchangeInputDto;
import com.kasiyanov.currencyconverter.service.FeignConvertCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeignConvertCurrencyController {

    private final FeignConvertCurrencyService feignConvertCurrencyService;

    @GetMapping(value = "/feign/convert", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BigDecimal convertCurrency(@RequestBody ExchangeInputDto exchangeInputDto) throws URISyntaxException {
        return feignConvertCurrencyService.getConvertedAmount(exchangeInputDto);
    }
}
