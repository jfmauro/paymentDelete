package be.fgov.minfin.sipwmacdpocdev.domain.model;

/**
 * Enumération des statuts possibles d'une commande.
 * Cette classe fait partie du domaine et est indépendante de toute infrastructure.
 */
public enum PaymentStatus {
    CREATED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELLED
}