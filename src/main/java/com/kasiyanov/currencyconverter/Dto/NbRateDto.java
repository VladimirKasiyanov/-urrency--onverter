package com.kasiyanov.currencyconverter.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NbRateDto {

    @JsonProperty("Cur_Scale")
    BigDecimal Cur_Scale;

    @JsonProperty("Cur_OfficialRate")
    BigDecimal Cur_OfficialRate;
}
