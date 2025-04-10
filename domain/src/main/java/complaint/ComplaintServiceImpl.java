package complaint;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintServiceImpl(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint addOrBumpUpComplaint(AddComplaintCommand command) {
        return complaintRepository.findComplaintByProductIdAndDeclarant(command.productId(), command.declarant())
                .map(this::bumpUpComplaint)
                .orElseGet(() -> addComplaint(command));
    }

    @Override
    public Complaint editComplaint(EditComplaintCommand command) {
         return complaintRepository.findComplaintByUuid(command.complaintId())
                .map(complaint -> editComplaint(complaint, command))
                 .orElseThrow(() -> new IllegalArgumentException("Complaint not found"));
    }

    @Override
    public List<Complaint> listAllComplaints() {
        return complaintRepository.findAll();
    }

    private Complaint addComplaint(AddComplaintCommand command) {
        Complaint complaint = Complaint.createInstance(command);
        return complaintRepository.save(complaint);
    }

    private Complaint bumpUpComplaint(Complaint complaint) {
        Complaint updatedComplaint = Complaint.bumpUpCounter(complaint);
        return complaintRepository.save(updatedComplaint);
    }

    private Complaint editComplaint(Complaint complaint, EditComplaintCommand command) {
        Complaint updatedComplaint = Complaint.updateDescription(complaint, command.description());
        return complaintRepository.save(updatedComplaint);
    }

}
