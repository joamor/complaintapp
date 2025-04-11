package com.complaint.infrastructure.complaint;

import com.complaint.domain.complaint.Complaint;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplaintFactoryTest {

    @Test
    void shouldCorrectlyCreateDomainObject() {
        ComplaintEntity complaintEntity = ComplaintEntity.createInstance(
                UUID.randomUUID(),
                LocalDateTime.now(),
                "XYZ20251234",
                "Jan Kowalski",
                "Test",
                "PL",
                3);

        Complaint result = ComplaintFactory.create(complaintEntity);

        assertEquals(complaintEntity.getId(), result.complaintId());
        assertEquals(complaintEntity.getCreationDate(), result.creationDate());
        assertEquals(complaintEntity.getProductId(), result.productId());
        assertEquals(complaintEntity.getDeclarant(), result.declarant());
        assertEquals(complaintEntity.getDescription(), result.description());
        assertEquals(complaintEntity.getCountry(), result.country());
        assertEquals(complaintEntity.getCounter(), result.counter());
    }

}
