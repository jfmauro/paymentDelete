package be.fgov.minfin.sipwmacdpocdev.application.service;


import be.fgov.minfin.sipwmacdpocdev.application.port.in.CreatePaymentUseCase;
import be.fgov.minfin.sipwmacdpocdev.application.port.out.SavePaymentPort;
import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;
import be.fgov.minfin.sipwmacdpocdev.domain.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Service d'application qui implémente les cas d'utilisation et orchestre les interactions.
 * C'est le point central qui connecte la logique métier du domaine avec l'infrastructure.
 */
@Slf4j
public class PaymentApplicationService implements CreatePaymentUseCase {

    private final PaymentService paymentService;
    private final SavePaymentPort savePaymentPort;
    //private final LoadOrderPort loadOrderPort;

    public PaymentApplicationService(PaymentService paymentService, SavePaymentPort savePaymentPort) {
        this.paymentService = paymentService;
        this.savePaymentPort = savePaymentPort;
    }
    /*
    public PaymentApplicationService(OrderServiceImpl orderService, SaveOrderPort saveOrderPort, LoadOrderPort loadOrderPort) {
        this.orderService = orderService;
        this.saveOrderPort = saveOrderPort;
        this.loadOrderPort = loadOrderPort;
    }*/

    @Override
    @Transactional
    public Payment createPayment(BigDecimal amount) {
        // Utilise le service de domaine pour créer une commande
        Payment newPayment = paymentService.createPayment(amount);
        
        // Utilise le port de sortie pour sauvegarder la commande
        return savePaymentPort.savePayment(newPayment);
    }

    /*

    @Override
    public boolean updateOrderStatus(UUID orderUuid, OrderStatus newStatus) {
        log.info("Updating order status for UUID: {} to status: {}", orderUuid, newStatus);

        // Charge la commande existante
        Optional<Order> orderOptional = loadOrderPort.loadOrderByUuid(orderUuid);

        if (orderOptional.isEmpty()) {
            log.warn("Order with UUID: {} not found", orderUuid);
            return false;
        }

        // Met à jour le statut dans l'entité du domaine
        Order order = orderOptional.get();
        order.updateStatus(newStatus);

        // Sauvegarde la commande mise à jour
        saveOrderPort.saveOrder(order);
        log.info("Order status updated successfully");

        return true;
    }

     */
}