package com.complaint.domain.complaint;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public record Complaint(UUID complaintId,
                        String productId,
                        String declarant,
                        String description,
                        String country,
                        LocalDateTime creationDate,
                        Integer counter) {

    public Complaint {
        requireNonNull(productId, "productId must not be null");
        requireNonNull(description, "description must not be null");
        requireNonNull(declarant, "declarant must not be null");
        requireNonNull(country, "country must not be null");

        if (complaintId == null) {
            complaintId = UUID.randomUUID();
        }

        productId = productId.trim();
        declarant = declarant.trim();
        description = description.trim();

        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }

        if (counter != null && counter <= 0) {
            throw new IllegalArgumentException("counter must be higher than zero");
        }
    }

    public static Complaint createInstance(AddComplaintCommand command, String country) {
        return new Complaint(UUID.randomUUID(),
                command.productId(),
                command.declarant(),
                command.description(),
                country,
                LocalDateTime.now(),
                1);
    }

    public static Complaint updateDescription(Complaint complaint, String description) {
        return new Complaint(complaint.complaintId(),
                complaint.productId(),
                complaint.declarant(),
                description,
                complaint.country(),
                complaint.creationDate(),
                complaint.counter());
    }

    public static Complaint bumpUpCounter(Complaint complaint) {
        int counter = complaint.counter() + 1;
        return new Complaint(complaint.complaintId(),
                complaint.productId(),
                complaint.declarant(),
                complaint.description(),
                complaint.country(),
                complaint.creationDate(),
                counter);
    }

    public static Complaint fromExisting(UUID complaintUuid,
                                         String productId,
                                         String declarant,
                                         String description,
                                         String country,
                                         LocalDateTime creationDate,
                                         Integer counter) {
        return new Complaint(complaintUuid, productId, declarant, description, country, creationDate, counter);
    }

}
