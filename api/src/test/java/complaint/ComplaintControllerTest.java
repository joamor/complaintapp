package complaint;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ComplaintControllerTest {

    private final ComplaintController classUnderTests = new ComplaintController();

    @Test
    void shouldReturnNullWhileAddingComplaint() {
        ComplaintRequestDto body = new ComplaintRequestDto("20250403", "Test", "Jan Kowalski");

        ResponseEntity<ComplaintResponseDto> response = classUnderTests.addComplaint(body);

        assertNull(response);
    }

    @Test
    void shouldReturnNullWhileUpdatingComplaint() {
        UUID complaintId = UUID.randomUUID();
        ComplaintRequestDto body = new ComplaintRequestDto("20250403", "Test", "Adam Kowalski");

        ResponseEntity<ComplaintResponseDto> response = classUnderTests.editComplaint(complaintId, body);

        assertNull(response);
    }

    @Test
    void shouldReturnEmptyList() {
        ResponseEntity<List<ComplaintResponseDto>> response = classUnderTests.listAll();

        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(), 0);
    }

}
