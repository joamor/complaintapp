package complaint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddComplaintCommandFactoryTest {

    @Test
    void shouldCorrectlyCreateCommand() {
        ComplaintRequestDto dto = new ComplaintRequestDto("ABC-202022", "Test", "Jan Kowalski");

        AddComplaintCommand result = AddComplaintCommandFactory.fromRequest(dto, "77.1.2.4");

        assertEquals(result.productId(), dto.productId());
        assertEquals(result.declarant(), dto.declarant());
        assertEquals(result.description(), dto.description());
    }

}
