package complaint;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

public record EditComplaintCommand(UUID complaintId,
                                   String description) {

    public EditComplaintCommand {
        requireNonNull(complaintId, "complaintId must not be null");
        requireNonNull(description, "description must not be null");
    }

    public static EditComplaintCommand of(UUID complaintId, String description) {
        return new EditComplaintCommand(complaintId, description);
    }

}
