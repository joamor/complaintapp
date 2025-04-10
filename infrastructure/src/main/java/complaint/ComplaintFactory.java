package complaint;

public class ComplaintFactory {

    protected static Complaint create(ComplaintEntity complaint) {
        return Complaint.fromExisting(
                complaint.getId(),
                complaint.getProductId(),
                complaint.getDeclarant(),
                complaint.getDescription(),
                complaint.getCountry(),
                complaint.getCreationDate(),
                complaint.getCounter());
    }

}
