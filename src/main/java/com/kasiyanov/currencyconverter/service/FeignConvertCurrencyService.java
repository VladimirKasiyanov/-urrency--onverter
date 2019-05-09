package com.kasiyanov.currencyconverter.service;

import com.kasiyanov.currencyconverter.Dto.ExchangeInputDto;
import com.kasiyanov.currencyconverter.Dto.NbRateDto;
import com.kasiyanov.currencyconverter.bean.FeignClientTemplate;
import feign.Feign;
import feign.Target;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@Import(FeignClientsConfiguration.class)
public class FeignConvertCurrencyService {

    private FeignClientTemplate feignClientTemplate;

    @Autowired
    public FeignConvertCurrencyService(Decoder decoder, Encoder encoder) {
        this.feignClientTemplate = Feign.builder().encoder(encoder).decoder(decoder)
                .target(Target.EmptyTarget.create(FeignClientTemplate.class));
    }

    public BigDecimal getConvertedAmount(ExchangeInputDto exchangeInputDto) throws URISyntaxException {
        return exchangeInputDto.getAmount()
                .multiply(getRate(createUri(exchangeInputDto.getFrom())))
                .divide(getRate(createUri(exchangeInputDto.getTo())), 2);
    }

    private String createUri(String currency) {
        return "http://www.nbrb.by/API/ExRates/Rates/" + currency + "?ParamMode=2";
    }

    private BigDecimal getRate(String uri) throws URISyntaxException {
        NbRateDto nbRateDto = feignClientTemplate.getRateAndScale(new URI(uri));
        return nbRateDto.getCur_OfficialRate()
                .divide(nbRateDto.getCur_Scale(), 2);
    }
}
