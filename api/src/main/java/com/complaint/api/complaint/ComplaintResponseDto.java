package com.complaint.api.complaint;

import com.complaint.domain.complaint.Complaint;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public record ComplaintResponseDto(UUID complaintId,
                                   String productId,
                                   String description,
                                   String declarant,
                                   LocalDateTime creationDate,
                                   String country,
                                   Integer counter) {

    public ComplaintResponseDto {
        requireNonNull(complaintId, "complaintId must not be null");
        requireNonNull(productId, "productId must not be null");
        requireNonNull(description, "description must not be null");
        requireNonNull(declarant, "declarant must not be null");
        requireNonNull(creationDate, "creationDate must not be null");
        requireNonNull(country, "country must not be null");
        requireNonNull(counter, "counter must not be null");
    }

    public static ComplaintResponseDto of(Complaint complaint) {
        return new ComplaintResponseDto(
                complaint.complaintId(),
                complaint.productId(),
                complaint.description(),
                complaint.declarant(),
                complaint.creationDate(),
                complaint.country(),
                complaint.counter());
    }

}