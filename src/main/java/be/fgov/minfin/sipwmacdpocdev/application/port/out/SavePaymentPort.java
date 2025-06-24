package be.fgov.minfin.sipwmacdpocdev.application.port.out;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;

/**
 * Port de sortie pour la sauvegarde d'une commande.
 * Définit comment l'application peut demander la sauvegarde d'une commande.
 */
public interface SavePaymentPort {
    
    /**
     * Sauvegarde une paiement.
     *
     * @param order La paiement à sauvegarder
     * @return La paiement sauvegardée
     */
    Payment savePayment(Payment order);
}