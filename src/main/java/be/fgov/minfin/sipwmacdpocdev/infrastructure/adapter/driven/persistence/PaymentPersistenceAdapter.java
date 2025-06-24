package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence;

import be.fgov.minfin.sipwmacdpocdev.application.port.out.SavePaymentPort;
import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence.mapper.PaymentEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Adaptateur de sortie pour la persistance des commandes.
 * Implémente le port de sortie SaveOrderPort.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentPersistenceAdapter implements SavePaymentPort/*, LoadOrderPort */{

    private final PaymentRepository paymentRepository;
    private final PaymentEntityMapper paymentEntityMapper;

    /**
     * Sauvegarde une commande dans la base de données.
     */
    @Override
    //@Transactional
    public Payment savePayment(Payment payment) {
        log.info("Saving payment with UUID: {}", payment.getUuid());
        
        // Convertit l'entité domaine en entité JPA
        PaymentEntity paymentEntity = paymentEntityMapper.paymentToPaymentEntity(payment);
        
        // Sauvegarde l'entité
        PaymentEntity savedEntity = paymentRepository.save(paymentEntity);

        // Reconvertit l'entité JPA sauvegardée en entité domaine
        return paymentEntityMapper.paymentEntityToPayment(savedEntity);
    }

    /*
    @Override
    @Transactional(readOnly = true)
    public Optional<Order> loadOrderByUuid(UUID uuid) {
        log.info("Loading order with UUID: {}", uuid);

        // Recherche l'entité JPA
        Optional<PaymentEntity> orderEntityOptional = orderRepository.findByUuid(uuid);

        // Convertit l'entité JPA en entité domaine si elle existe
        return orderEntityOptional.map(orderEntityMapper::orderEntityToOrder);
    }
    */

}