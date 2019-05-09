package com.kasiyanov.currencyconverter.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeInputDto {

    String from;
    String to;
    BigDecimal amount;
}
