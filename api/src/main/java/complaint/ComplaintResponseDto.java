package complaint;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComplaintResponseDto(UUID complaintId,
                                   String productId,
                                   String description,
                                   String declarant,
                                   LocalDateTime creationDate,
                                   String declarantCountry,
                                   Integer complaintCounter) {
}