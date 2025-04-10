package complaint;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ComplaintRepositoryDb implements ComplaintRepository {

    private final ComplaintRepositoryJpa complaintRepositoryJpa;

    protected ComplaintRepositoryDb(ComplaintRepositoryJpa complaintRepositoryJpa) {
        this.complaintRepositoryJpa = complaintRepositoryJpa;
    }

    @Override
    public Complaint save(Complaint complaint) {
        ComplaintEntity complaintEntity = ComplaintEntityFactory.create(complaint);
        complaintEntity = complaintRepositoryJpa.save(complaintEntity);
        return ComplaintFactory.create(complaintEntity);
    }

    @Override
    public Optional<Complaint> findComplaintByUuid(UUID uuid) {
        return complaintRepositoryJpa.findById(uuid)
                .map(ComplaintFactory::create);
    }

    @Override
    public Optional<Complaint> findComplaintByProductIdAndDeclarant(String productId, String declarant) {
        return complaintRepositoryJpa.findByProductIdAndDeclarant(productId, declarant)
                .map(ComplaintFactory::create);
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepositoryJpa.findAll()
                .stream()
                .map(ComplaintFactory::create)
                .toList();
    }

}
