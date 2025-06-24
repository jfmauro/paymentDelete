package be.fgov.minfin.sipwmacdpocdev.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Entité de domaine représentant une commande.
 * Cette classe est un POJO pur, sans dépendances externes.
 */
public class Payment {
    private final UUID uuid;
    private BigDecimal amount;
    private PaymentStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Payment(UUID uuid, BigDecimal amount, PaymentStatus status,
                    LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.uuid = uuid;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Crée une nouvelle commande avec un statut CREATED.
     */
    public static Payment createNew(BigDecimal amount) {
        validateAmount(amount);
        LocalDateTime now = LocalDateTime.now();
        return new Payment(UUID.randomUUID(), amount, PaymentStatus.CREATED, now, now);
    }

    /**
     * Recrée une commande existante à partir de ses attributs.
     */
    public static Payment reconstitute(UUID uuid, BigDecimal amount, PaymentStatus status,
                                       LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateAmount(amount);
        return new Payment(uuid, amount, status, createdAt, updatedAt);
    }

    private static void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    /**
     * Change le statut de la commande.
     */
    public void updateStatus(PaymentStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Met à jour le montant de la commande.
     */
    public void updateAmount(BigDecimal newAmount) {
        validateAmount(newAmount);
        this.amount = newAmount;
        this.updatedAt = LocalDateTime.now();
    }

    // Getters
    public UUID getUuid() {
        return uuid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment order = (Payment) o;
        return Objects.equals(uuid, order.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Order{" +
                "uuid=" + uuid +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}