package test.contract.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
/**
 * Created by Павел on 19.01.2020.
 */
@MappedSuperclass
public class AuditEntity extends ModelEntity {
    private static final long serialVersionUID = 2274475354035340097L;
    protected String createdBy;
    protected LocalDateTime createdWhen;
    protected String updatedBy;
    protected LocalDateTime updatedWhen;

    public AuditEntity() {
    }

    public AuditEntity(Long id) {
        super(id);
    }

    public AuditEntity(Long id, String createdBy, LocalDateTime createdWhen, String updatedBy, LocalDateTime updatedWhen) {
        super(id);
        this.createdBy = createdBy;
        this.createdWhen = createdWhen;
        this.updatedBy = updatedBy;
        this.updatedWhen = updatedWhen;
    }

    public void setAuditParamsForCreation(String creator) {
        this.createdBy = creator;
        this.updatedBy = creator;
        this.createdWhen = LocalDateTime.now();
        this.updatedWhen = this.createdWhen;
    }

    public void setAuditParamsForUpdate(String updater) {
        this.updatedBy = updater;
        this.updatedWhen = LocalDateTime.now();
    }

    @Column(name = "CREATED_BY", length = 32)
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "CREATED_WHEN", columnDefinition = "TIMESTAMP NOT NULL DEFAULT now()")
    public LocalDateTime getCreatedWhen() {
        return this.createdWhen;
    }
    public void setCreatedWhen(LocalDateTime createdWhen) {
        this.createdWhen = createdWhen;
    }

    @Column(name = "UPDATED_BY", length = 32)
    public String getUpdatedBy() {
        return this.updatedBy;
    }
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "UPDATED_WHEN", columnDefinition = "TIMESTAMP NOT NULL DEFAULT now()")
    public LocalDateTime getUpdatedWhen() {
        return this.updatedWhen;
    }
    public void setUpdatedWhen(LocalDateTime updatedWhen) {
        this.updatedWhen = updatedWhen;
    }
}
