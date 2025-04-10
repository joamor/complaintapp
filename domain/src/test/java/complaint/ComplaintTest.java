package complaint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComplaintTest {

    @Test
    void testNewInstanceCreation() {
        Complaint result = Complaint.createInstance("XYZ-20250413", "Jan Kowalski", "Test");

        assertNotNull(result.complaintId());
        assertEquals("XYZ-20250413", result.productId());
        assertEquals("Jan Kowalski", result.declarant());
        assertEquals("Test", result.description());
        assertNotNull(result.creationDate());
        assertEquals(1, result.counter());
    }

    @Test
    void testDescriptionUpdate() {
        Complaint complaint = Complaint.createInstance("XYZ-20250413", "Jan Kowalski", "Test");

        Complaint result = Complaint.updateDescription(complaint, "TestXYZ ");

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals("TestXYZ", result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.counter(), result.counter());
    }

    @Test
    void testCounterBumpUp() {
        Complaint complaint = Complaint.createInstance("XYZ-20250413", "Jan Kowalski", "Test");

        Complaint result = Complaint.bumpUpCounter(complaint);

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals(complaint.description(), result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.counter() + 1, result.counter());
    }

    @Test
    void testInstanceCreationFromExistingOne() {
        Complaint complaint = Complaint.createInstance("XYZ-20250413", "Jan Kowalski", "Test");

        Complaint result = Complaint.fromExisting(
                complaint.complaintId(),
                complaint.productId(),
                complaint.declarant(),
                complaint.description(),
                complaint.creationDate(),
                complaint.counter());

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals(complaint.description(), result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.counter(), result.counter());
    }

}
