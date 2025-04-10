package complaint;

import java.util.List;

public interface ComplaintService {

    Complaint addOrBumpUpComplaint(AddComplaintCommand command);

    Complaint editComplaint(EditComplaintCommand command);

    List<Complaint> listAllComplaints();

}
