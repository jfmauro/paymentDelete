package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository Spring Data JPA pour les opérations de persistance sur les commandes.
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    
    /**
     * Recherche une commande par son UUID.
     */
    Optional<PaymentEntity> findByUuid(UUID uuid);
}