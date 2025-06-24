package be.fgov.minfin.sipwmacdpocdev.application.port.in;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;

import java.math.BigDecimal;

/**
 * Port d'entrée (use case) pour la création d'un paiement.
 * Définit comment les adaptateurs d'entrée peuvent demander la création d'un paiement.
 */
public interface CreatePaymentUseCase {
    
    /**
     * Crée un nouveau paiement avec le montant spécifié.
     *
     * @param amount Le montant paiement
     * @return Le payemnt créé
     * @throws IllegalArgumentException si le montant est invalide
     */
    Payment createPayment(BigDecimal amount);
}