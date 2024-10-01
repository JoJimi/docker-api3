package com.example.dashboard.service;

import com.example.dashboard.model.price.PriceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PriceMonitorService {

    private final String apiUri;

    public PriceMonitorService(@Value("${price-monitor.api-uri}") String apiUri){
        this.apiUri = apiUri;
    }

    private static final RestClient restClient = RestClient.create();

    public PriceResponse getPrice(String priceUnit){
        return restClient.get().uri(apiUri, priceUnit).retrieve().body(PriceResponse.class);
    }

}
