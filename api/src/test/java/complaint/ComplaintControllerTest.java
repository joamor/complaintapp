package complaint;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ComplaintControllerTest {

    private final ComplaintService complaintService = Mockito.mock(ComplaintService.class);

    private final ComplaintController classUnderTests = new ComplaintController(complaintService);

    @Test
    void shouldReturnNullWhileAddingComplaint() {
        ComplaintRequestDto body = new ComplaintRequestDto("20250403", "Test", "Jan Kowalski");

        ResponseEntity<ComplaintResponseDto> response = classUnderTests.addComplaint(body);

        assertNull(response);
    }

    @Test
    void shouldReturnNullWhileUpdatingComplaint() {
        UUID complaintId = UUID.randomUUID();

        ResponseEntity<ComplaintResponseDto> response = classUnderTests.editComplaint(complaintId, "Test XYZ");

        assertNull(response);
    }

    @Test
    void shouldReturnEmptyList() {
        ResponseEntity<List<ComplaintResponseDto>> response = classUnderTests.listAll();

        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(), 0);
    }

}
