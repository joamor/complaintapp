package complaint;

import static java.util.Objects.requireNonNull;

public record AddComplaintCommand(String productId,
                                  String description,
                                  String declarant) {

    public AddComplaintCommand {
        requireNonNull(productId, "productId must not be null");
        requireNonNull(description, "description must not be null");
        requireNonNull(declarant, "declarant must not be null");
    }

    public static AddComplaintCommand of(String productId, String description, String declarant) {
        return new AddComplaintCommand(productId, description, declarant);
    }

}
