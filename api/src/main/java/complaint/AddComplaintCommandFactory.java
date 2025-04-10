package complaint;

public class AddComplaintCommandFactory {

    protected static AddComplaintCommand fromRequest(ComplaintRequestDto dto) {
        return AddComplaintCommand.of(dto.productId(), dto.description(), dto.declarant());
    }

}
