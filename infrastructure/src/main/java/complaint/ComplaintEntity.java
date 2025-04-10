package complaint;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "complaints")
public class ComplaintEntity implements Serializable {

    @Id
    private UUID id;

    @Version
    private Integer version;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "declarant", nullable = false)
    private String declarant;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "counter", nullable = false)
    private Integer counter;

    private ComplaintEntity() {}

    private ComplaintEntity(UUID complaintId,
                            LocalDateTime creationDate,
                            String productId,
                            String declarant,
                            String description,
                            Integer counter) {
        this.id = complaintId;
        this.creationDate = creationDate;
        this.productId = productId;
        this.declarant = declarant;
        this.description = description;
        this.counter = counter;
    }

    protected static ComplaintEntity createInstance(UUID complaintId,
                                                    LocalDateTime creationDate,
                                                    String productId,
                                                    String declarant,
                                                    String description,
                                                    Integer counter) {
        return new ComplaintEntity(complaintId, creationDate, productId, declarant, description, counter);
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getDeclarant() {
        return declarant;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCounter() {
        return counter;
    }

}
