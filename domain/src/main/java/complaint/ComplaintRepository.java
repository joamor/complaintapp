package complaint;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComplaintRepository {

    Complaint save(Complaint complaint);

    Optional<Complaint> findComplaintByUuid(UUID uuid);

    Optional<Complaint> findComplaintByProductIdAndDeclarant(String productId, String declarant);

    List<Complaint> findAll();

}
