package com.complaint.infrastructure.complaint;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComplaintRepositoryJpa extends JpaRepository<ComplaintEntity, UUID> {

    ComplaintEntity save(ComplaintEntity complaint);

    Optional<ComplaintEntity> findById(UUID complaintId);

    Optional<ComplaintEntity> findByProductIdAndDeclarant(String productId, String declarant);

    List<ComplaintEntity> findAll();

}
