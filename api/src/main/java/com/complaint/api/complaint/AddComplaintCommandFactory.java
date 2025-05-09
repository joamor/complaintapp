package com.complaint.api.complaint;

import com.complaint.domain.complaint.AddComplaintCommand;

public class AddComplaintCommandFactory {

    protected static AddComplaintCommand fromRequest(ComplaintRequestDto dto, String ip) {
        return AddComplaintCommand.of(dto.productId(), dto.description(), dto.declarant(), ip);
    }

}
