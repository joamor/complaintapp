package complaint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplaintEntityFactoryTest {

    @Test
    void shouldCorrectlyCreateEntityObject() {
        Complaint complaint = Complaint.createInstance("AD202504", "Jan Kowalski", "Test");

        ComplaintEntity result = ComplaintEntityFactory.create(complaint);

        assertEquals(complaint.complaintId(), result.getId());
        assertEquals(complaint.creationDate(), result.getCreationDate());
        assertEquals(complaint.productId(), result.getProductId());
        assertEquals(complaint.declarant(), result.getDeclarant());
        assertEquals(complaint.description(), result.getDescription());
        assertEquals(complaint.counter(), result.getCounter());
    }

}
