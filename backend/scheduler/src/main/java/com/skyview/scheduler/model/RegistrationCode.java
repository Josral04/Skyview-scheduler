package com.skyview.scheduler.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration_code")
public class RegistrationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String codeValue;

    @Column(nullable = false)
    private boolean used = false;

    private LocalDateTime expiresAt;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by_staff_id", nullable = false)
    private Staff createdBy;

    @ManyToOne
    @JoinColumn(name = "used_by_staff_id")
    private Staff usedBy;

    public RegistrationCode() {}

    public Long getId() { return id; }

    public String getCodeValue() { return codeValue; }
    public void setCodeValue(String codeValue) { this.codeValue = codeValue; }

    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public Staff getCreatedBy() { return createdBy; }
    public void setCreatedBy(Staff createdBy) { this.createdBy = createdBy; }

    public Staff getUsedBy() { return usedBy; }
    public void setUsedBy(Staff usedBy) { this.usedBy = usedBy; }

    public boolean isValidNow() {
        if (used) return false;
        if (expiresAt == null) return true;
        return LocalDateTime.now().isBefore(expiresAt);
    }
}
