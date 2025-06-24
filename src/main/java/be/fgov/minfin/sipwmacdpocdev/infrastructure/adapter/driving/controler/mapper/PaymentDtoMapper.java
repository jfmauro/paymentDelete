package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.mapper;

import be.fgov.minfin.sipwmacdpocdev.domain.model.Payment;
import be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler.dto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * Mapper pour convertir entre les entités de domaine et les DTOs.
 */
@Mapper
public interface PaymentDtoMapper {
    
    /**
     * Convertit une entité Order en DTO PaymentResponse.
     */
    @Mapping(target = "status", expression = "java(payment.getStatus().name())")
    PaymentResponse paymentToPaymentResponse(Payment payment);
}