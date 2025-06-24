package be.fgov.minfin.sipwmacdpocdev.domain.service;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;

import java.math.BigDecimal;

/**
 * Interface définissant les opérations de service du domaine pour les commandes.
 * Représente le contrat que la couche application utilisera pour interagir avec la logique de domaine.
 */
public interface PaymentService {
    
    /**
     * Crée une nouvelle paiement avec validation métier.
     *
     * @param amount Le montant du paiement
     * @return Une nouvelle instance de paiement valide
     * @throws IllegalArgumentException si les règles métier ne sont pas respectées
     */
    Payment createPayment(BigDecimal amount);
}