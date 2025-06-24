package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence.mapper;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;
import be.fgov.minfin.sipwmacdpocdev.domain.model.PaymentStatus;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper pour convertir entre les entités de domaine et les entités JPA.
 */
@Mapper
public interface PaymentEntityMapper {

    /**
     * Convertit une entité de domaine en entité JPA.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    PaymentEntity paymentToPaymentEntity(Payment payment);

    /**
     * Convertit une entité JPA en entité de domaine.
     * Utilisation d'une méthode par défaut pour utiliser la méthode de reconstitution.
     */
    default Payment paymentEntityToPayment(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }

        return Payment.reconstitute(
                paymentEntity.getUuid(),
                paymentEntity.getAmount(),
                mapStatusEntity(paymentEntity.getStatus()),
                paymentEntity.getCreatedAt(),
                paymentEntity.getUpdatedAt()
        );
    }

    /**
     * Convertit un statut de domaine en statut d'entité.
     */
    @Named("mapStatus")
    default PaymentEntity.PaymentStatusEntity mapStatus(PaymentStatus status) {
        return PaymentEntity.PaymentStatusEntity.valueOf(status.name());
    }

    /**
     * Convertit un statut d'entité en statut de domaine.
     */
    @Named("mapStatusEntity")
    default PaymentStatus mapStatusEntity(PaymentEntity.PaymentStatusEntity statusEntity) {
        return PaymentStatus.valueOf(statusEntity.name());
    }
}