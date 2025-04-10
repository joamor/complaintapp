package complaint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComplaintTest {

    @Test
    void testNewInstanceCreation() {
        AddComplaintCommand command = AddComplaintCommand.of("XYZ-20250413", "Jan Kowalski", "Test", "77.1.2.4");
        Complaint result = Complaint.createInstance(command, "PL");

        assertNotNull(result.complaintId());
        assertEquals(command.productId(), result.productId());
        assertEquals(command.declarant(), result.declarant());
        assertEquals(command.description(), result.description());
        assertNotNull(result.creationDate());
        assertEquals("PL", result.country());
        assertEquals(1, result.counter());
    }

    @Test
    void testDescriptionUpdate() {
        AddComplaintCommand command = AddComplaintCommand.of("XYZ-20250413", "Jan Kowalski", "Test", "77.1.2.4");
        Complaint complaint = Complaint.createInstance(command, "PL");

        Complaint result = Complaint.updateDescription(complaint, "TestXYZ ");

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals("TestXYZ", result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.country(), result.country());
        assertEquals(complaint.counter(), result.counter());
    }

    @Test
    void testCounterBumpUp() {
        AddComplaintCommand command = AddComplaintCommand.of("XYZ-20250413", "Jan Kowalski", "Test", "77.1.2.4");
        Complaint complaint = Complaint.createInstance(command, "PL");

        Complaint result = Complaint.bumpUpCounter(complaint);

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals(complaint.description(), result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.country(), result.country());
        assertEquals(complaint.counter() + 1, result.counter());
    }

    @Test
    void testInstanceCreationFromExistingOne() {
        AddComplaintCommand command = AddComplaintCommand.of("XYZ-20250413", "Jan Kowalski", "Test", "77.1.2.4");
        Complaint complaint = Complaint.createInstance(command, "PL");

        Complaint result = Complaint.fromExisting(
                complaint.complaintId(),
                complaint.productId(),
                complaint.declarant(),
                complaint.description(),
                complaint.country(),
                complaint.creationDate(),
                complaint.counter());

        assertEquals(complaint.complaintId(), result.complaintId());
        assertEquals(complaint.productId(), result.productId());
        assertEquals(complaint.declarant(), result.declarant());
        assertEquals(complaint.description(), result.description());
        assertEquals(complaint.creationDate(), result.creationDate());
        assertEquals(complaint.country(), result.country());
        assertEquals(complaint.counter(), result.counter());
    }

}
