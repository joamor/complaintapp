package complaint;

import static java.util.Objects.requireNonNull;

public record ComplaintRequestDto(String productId,
                                  String description,
                                  String declarant) {

    public ComplaintRequestDto {
        requireNonNull(productId, "productId must not be null");
        requireNonNull(description, "description must not be null");
        requireNonNull(declarant, "declarant must not be null");
    }

}
