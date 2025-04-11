package com.complaint.application;

import com.complaint.api.complaint.ComplaintRequestDto;
import com.complaint.api.complaint.ComplaintResponseDto;
import com.complaint.domain.complaint.Complaint;
import com.complaint.domain.complaint.ComplaintRepository;
import com.complaint.domain.location.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.complaint.application.ComplaintFaker.COMPLAINT_1;
import static com.complaint.application.ComplaintFaker.COMPLAINT_2;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = ComplaintApp.class)
class ComplaintControllerFT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockitoBean
    private ComplaintRepository complaintRepository;

    @MockitoBean
    private LocationRepository locationRepository;

    @Test
    void contextLoads() {}

    @Test
    void shouldAddNewComplaint() {
        ComplaintRequestDto body = new ComplaintRequestDto("XY253465", "Test", "Jan Kowalski");
        HttpEntity<ComplaintRequestDto> entity = new HttpEntity<>(body);
        when(complaintRepository.findComplaintByProductIdAndDeclarant(anyString(), anyString())).thenReturn(Optional.empty());
        when(complaintRepository.save(any())).thenReturn(COMPLAINT_1);
        when(locationRepository.getCountryByIp(anyString())).thenReturn("PL");

        var response = restTemplate.exchange(
                "http://localhost:" + port + "/complaints",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ComplaintResponseDto>() {}
        );

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void shouldBumpUpExistingComplaint() {
        ComplaintRequestDto body = new ComplaintRequestDto("XY253465", "Test", "Jan Kowalski");
        HttpEntity<ComplaintRequestDto> entity = new HttpEntity<>(body);
        when(complaintRepository.findComplaintByProductIdAndDeclarant(anyString(), anyString())).thenReturn(Optional.of(COMPLAINT_1));
        when(complaintRepository.save(any())).thenReturn(COMPLAINT_1);
        when(locationRepository.getCountryByIp(anyString())).thenReturn("PL");

        var response = restTemplate.exchange(
                "http://localhost:" + port + "/complaints",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<ComplaintResponseDto>() {}
        );

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void shouldEditComplaint() {
        when(complaintRepository.findComplaintByUuid(any())).thenReturn(Optional.of(COMPLAINT_1));
        when(complaintRepository.save(any())).thenReturn(COMPLAINT_1);

        var response = restTemplate.exchange(
                "http://localhost:" + port + "/complaints/123e4567-e89b-12d3-a456-426614174000?description=UpdatedDescription",
                HttpMethod.PATCH,
                null,
                new ParameterizedTypeReference<ComplaintResponseDto>() {}
        );

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void shouldReturnComplaintsList() {
        List<Complaint> complaintList = List.of(COMPLAINT_1, COMPLAINT_2);
        when(complaintRepository.findAll()).thenReturn(complaintList);

        var response = restTemplate.exchange(
                "http://localhost:" + port + "/complaints",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ComplaintResponseDto>>() {}
        );

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
    }

    @Test
    void shouldReturnEmptyListOnNoComplaints() {
        when(complaintRepository.findAll()).thenReturn(Collections.emptyList());

        var response = restTemplate.exchange(
                "http://localhost:" + port + "/complaints",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ComplaintResponseDto>>() {}
        );

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(0, response.getBody().size());
    }

}
