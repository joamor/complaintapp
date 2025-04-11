package com.complaint.infrastructure.location;

import com.complaint.domain.location.LocationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class LocationRepositoryRest implements LocationRepository {

    private static final String LOCATION_URL = "https://country.is/";

    private final RestTemplate restTemplate;

    public LocationRepositoryRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getCountryByIp(String ip) {
        String url = buildRequestUrl(ip);
        try {
            ResponseEntity<CountryIsResponse> response = restTemplate.getForEntity(url, CountryIsResponse.class);
            return Optional.ofNullable(response.getBody())
                    .map(CountryIsResponse::getCountry)
                    .orElse("N/A");
        } catch (HttpClientErrorException exception) {
            return "N/A";
        }
    }

    private String buildRequestUrl(String ip) {
        return LOCATION_URL + ip;
    }
}
