package com.complaint.infrastructure.location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationRepositoryRestTest {

    private final RestTemplate restTemplate = mock(RestTemplate.class);

    private final LocationRepositoryRest classUnderTests = new LocationRepositoryRest(restTemplate);

    @Test
    void shouldReturnCountry() {
        String ip = "77.8.10.2";
        CountryIsResponse responseBody = new CountryIsResponse(ip, "DE");
        ResponseEntity<CountryIsResponse> serviceResponse = ResponseEntity.ofNullable(responseBody);

        when(restTemplate.getForEntity(anyString(), eq(CountryIsResponse.class))).thenReturn(serviceResponse);

        String response = classUnderTests.getCountryByIp(ip);

        Assertions.assertEquals("DE", response);
    }

    @Test
    void shouldReturnNAOnException() {
        String ip = "77.8.10.2";

        when(restTemplate.getForEntity(anyString(), eq(CountryIsResponse.class))).thenThrow(HttpClientErrorException.class);

        String response = classUnderTests.getCountryByIp(ip);

        Assertions.assertEquals("N/A", response);
    }

}
