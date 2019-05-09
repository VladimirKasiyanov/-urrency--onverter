package com.kasiyanov.currencyconverter.bean;

import com.kasiyanov.currencyconverter.Dto.NbRateDto;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.net.URI;

@FeignClient(name = "FeignProxy")
public interface FeignClientTemplate {

    @RequestLine("GET")
    NbRateDto getRateAndScale(URI baseUri);
}
