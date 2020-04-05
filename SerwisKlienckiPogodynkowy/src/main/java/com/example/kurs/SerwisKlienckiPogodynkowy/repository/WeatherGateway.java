package com.example.kurs.SerwisKlienckiPogodynkowy.repository;

import com.example.kurs.SerwisKlienckiPogodynkowy.model.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

// strzelam do serwera i przekazuje serwisowi informacji
@Service
public class WeatherGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.endpointUrl}")
    private String url;

    public WeatherDTO getWeatherForecast(Integer regionId, Integer aura) {
        HashMap<String, String> requestParams = new HashMap<>();
        if (regionId != null) {
            requestParams.put("region", String.valueOf(regionId));
        }
        if (aura != null) {
            requestParams.put("aura", String.valueOf(aura));
        }

        // UriComponentsBuilder - lepsze do wykorzystania, gotowiec do budowania parametrow :)
        // ja sie bawie, wiec korzystam sam
        return restTemplate.getForObject(
                url
                + getUriParamsPlaceholderAsString(requestParams),  //+ "?region={region}&aura={aura}",
                WeatherDTO.class,
                requestParams);
    }

    // zbuduj paramsy do urla
    private String includeParamPlaceholder(String paramName) {
        return paramName + "={" + paramName + "}";
    }

    private String getUriParamsPlaceholderAsString(Map<String, String> params) {
        String uriParamsAsString = "";
        boolean isFirstParam = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
                if (isFirstParam) {
                    isFirstParam = false;
                    uriParamsAsString = "?" + includeParamPlaceholder(entry.getKey());
                    continue;
                }
                uriParamsAsString = uriParamsAsString + "&" + includeParamPlaceholder(entry.getKey());
        }
        return uriParamsAsString;
    }
}