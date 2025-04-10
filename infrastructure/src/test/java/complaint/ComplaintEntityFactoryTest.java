package complaint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplaintEntityFactoryTest {

    @Test
    void shouldCorrectlyCreateEntityObject() {
        AddComplaintCommand command = AddComplaintCommand.of("XYZ-20250413", "Jan Kowalski", "Test");
        Complaint complaint = Complaint.createInstance(command);

        ComplaintEntity result = ComplaintEntityFactory.create(complaint);

        assertEquals(complaint.complaintId(), result.getId());
        assertEquals(complaint.creationDate(), result.getCreationDate());
        assertEquals(complaint.productId(), result.getProductId());
        assertEquals(complaint.declarant(), result.getDeclarant());
        assertEquals(complaint.description(), result.getDescription());
        assertEquals(complaint.counter(), result.getCounter());
    }

}
