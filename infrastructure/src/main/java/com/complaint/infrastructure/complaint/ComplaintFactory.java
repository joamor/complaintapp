package com.complaint.infrastructure.complaint;

import com.complaint.domain.complaint.Complaint;

public class ComplaintFactory {

    protected static Complaint create(ComplaintEntity complaint) {
        return Complaint.fromExisting(
                complaint.getId(),
                complaint.getProductId(),
                complaint.getDeclarant(),
                complaint.getDescription(),
                complaint.getCountry(),
                complaint.getCreationDate(),
                complaint.getCounter());
    }

}
