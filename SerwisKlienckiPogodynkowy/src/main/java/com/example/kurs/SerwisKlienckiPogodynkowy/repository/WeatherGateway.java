package com.example.kurs.SerwisKlienckiPogodynkowy.repository;

import com.example.kurs.SerwisKlienckiPogodynkowy.model.WeatherDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

// strzelam do serwera i przekazuje serwisowi informacji
@Service
public class WeatherGateway {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.endpointBaseUrl}")
    private String serverUrl;

    public WeatherDTO getForecast(Integer regionId, Integer aura) {
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
                serverUrl + "/forecast/generate"
                + getUriParamsPlaceholderAsString(requestParams),  //+ "?region={region}&aura={aura}",
                WeatherDTO.class,
                requestParams);
    }

    public Collection<WeatherDTO> getSavedForecasts(Integer regionId, Integer aura) {
        return restTemplate.exchange(
                serverUrl + "/forecasts?region={regionId}&aura={aura}", // dynamicznie buduje sobie urla zaleznie od parametrow
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<WeatherDTO>>() {
                },
                regionId, //param1
                aura // param2
        ).getBody();
    }

    public WeatherDTO updateForecast(Long forecastId, WeatherDTO weatherDTO) {
        // Set HTTP header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Create new JSON Object
        JSONObject forecastJsonObject = new JSONObject();
        forecastJsonObject.put("aura", weatherDTO.getAura());
        // Form Http Request
        HttpEntity<String> request = new HttpEntity<>(forecastJsonObject.toString(), headers);
        // Run
        return restTemplate.postForObject(
                serverUrl + "/forecast/{id}/update",
                request,
                WeatherDTO.class,
                forecastId
                );
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