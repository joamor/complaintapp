package com.complaint.application;

import com.complaint.domain.complaint.Complaint;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComplaintFaker {

    final static Complaint COMPLAINT_1 = Complaint.fromExisting(
            UUID.randomUUID(),
            "ABC3455",
            "Jan Kowalski",
            "Test",
            "PL",
            LocalDateTime.now(),
            1);

    final static Complaint COMPLAINT_2 = Complaint.fromExisting(
            UUID.randomUUID(),
            "XYZ647566",
            "Krzysztof Krawczyk",
            "XYZ",
            "DE",
            LocalDateTime.now(),
            3);

}
