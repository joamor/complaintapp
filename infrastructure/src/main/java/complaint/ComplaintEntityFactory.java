package complaint;

public class ComplaintEntityFactory {

    protected static ComplaintEntity create(Complaint complaint) {
      return ComplaintEntity.createInstance(
              complaint.complaintId(),
              complaint.creationDate(),
              complaint.productId(),
              complaint.declarant(),
              complaint.description(),
              complaint.country(),
              complaint.counter());
    }

}
