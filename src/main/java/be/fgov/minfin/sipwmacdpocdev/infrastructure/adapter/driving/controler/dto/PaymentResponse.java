package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO pour la réponse contenant les informations d'une commande.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID uuid;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}