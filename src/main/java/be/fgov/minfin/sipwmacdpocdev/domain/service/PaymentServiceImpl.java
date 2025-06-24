package be.fgov.minfin.sipwmacdpocdev.domain.service;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;


import java.math.BigDecimal;

/**
 * Service du domaine pour les opérations sur les commandes.
 * Contient la logique métier pure, indépendante de l'infrastructure.
 */
public class PaymentServiceImpl implements PaymentService {

    /**
     * Crée un nouveau payment.
     * Contient la logique métier pour la création d'un payment valide.
     */
    public Payment createPayment(BigDecimal amount) {
        validatePaymentCreation(amount);
        return Payment.createNew(amount);
    }

    private void validatePaymentCreation(BigDecimal amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}