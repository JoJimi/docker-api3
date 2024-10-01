package com.example.dashboard.controller;

import com.example.dashboard.model.crypto.CryptoPriceResponse;
import com.example.dashboard.service.CurrencyMonitorService;
import com.example.dashboard.service.PriceMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crypto-prices")
public class PriceController {

    @Autowired private PriceMonitorService priceMonitorService;
    @Autowired private CurrencyMonitorService currencyMonitorService;

    @GetMapping("/{cryptoUnit}")
    public ResponseEntity<CryptoPriceResponse> getCryptoPrice(@PathVariable String cryptoUnit){
        var priceResponse = priceMonitorService.getPrice(cryptoUnit);
        var currencyResponse = currencyMonitorService.getCurrency("USD");
        var cryptoPriceResponse =
                new CryptoPriceResponse(cryptoUnit,priceResponse.amount() * currencyResponse.rate());
        return ResponseEntity.ok(cryptoPriceResponse);
    }

}
