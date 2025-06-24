package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler;

import be.fgov.minfin.sipwmacdpocdev.application.port.in.CreatePaymentUseCase;
import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence.PaymentEntity;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driven.persistence.PaymentRepository;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.dto.PaymentCreateRequest;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.dto.PaymentResponse;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.mapper.PaymentDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Adaptateur d'entrée pour les requêtes REST liées aux commandes.
 */
@Slf4j
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final PaymentDtoMapper paymentDtoMapper;
    /**
     * Point d'entrée pour la création d'une commande.
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentCreateRequest request) {
        log.info("Received request to create payment with amount: {}", request.getAmount());
        
        // Appelle le cas d'utilisation pour créer une commande
        Payment createdPayment = createPaymentUseCase.createPayment(request.getAmount());
        
        // Convertit l'entité domaine en DTO de réponse
        PaymentResponse response = paymentDtoMapper.paymentToPaymentResponse(createdPayment);
        
        log.info("Created payment with UUID: {}", response.getUuid());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/test-direct")
    public ResponseEntity<String> testDirect() {
        try {
            log.info("=== Test direct démarré");
            Payment payment = createPaymentUseCase.createPayment(new BigDecimal("999.99"));
            log.info("=== Test direct réussi: {}", payment);
            return ResponseEntity.ok("SUCCESS: Payment créé avec UUID " + payment.getUuid());
        } catch (Exception e) {
            log.error("=== Test direct échoué", e);
            return ResponseEntity.status(500).body("ERROR: " + e.getMessage());
        }
    }

    @GetMapping("/debug")
    public ResponseEntity<Map<String, Object>> debug() {
        Map<String, Object> info = new HashMap<>();
        info.put("createPaymentUseCase_class", createPaymentUseCase.getClass().getName());
        info.put("is_spring_proxy", createPaymentUseCase.getClass().getName().contains("CGLIB") ||
                createPaymentUseCase.getClass().getName().contains("Proxy"));
        info.put("mapper_class", paymentDtoMapper.getClass().getName());
        return ResponseEntity.ok(info);
    }
    // AJOUTEZ dans PaymentController

    @Autowired
    private PaymentRepository paymentRepository; // Injection directe pour test

    @GetMapping("/db-check")
    public ResponseEntity<Map<String, Object>> checkDatabase() {
        Map<String, Object> result = new HashMap<>();

        try {
            // Compter les payments
            long count = paymentRepository.count();
            result.put("total_payments", count);

            // Lister tous les payments
            List<PaymentEntity> allPayments = paymentRepository.findAll();
            result.put("payments", allPayments.stream()
                    .map(p -> Map.of(
                            "id", p.getId(),
                            "uuid", p.getUuid().toString(),
                            "amount", p.getAmount(),
                            "status", p.getStatus(),
                            "created", p.getCreatedAt()
                    ))
                    .collect(Collectors.toList()));

            result.put("status", "SUCCESS");

        } catch (Exception e) {
            result.put("status", "ERROR");
            result.put("error", e.getMessage());
            log.error("Erreur lors de la vérification DB", e);
        }

        return ResponseEntity.ok(result);
    }
}