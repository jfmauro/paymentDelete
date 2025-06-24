package be.fgov.minfin.sipwmacdpocdev.config;

import be.fgov.minfin.sipwmacdpocdev.application.port.out.SavePaymentPort;
import be.fgov.minfin.sipwmacdpocdev.application.service.PaymentApplicationService;
import be.fgov.minfin.sipwmacdpocdev.domain.service.PaymentService;
import be.fgov.minfin.sipwmacdpocdev.domain.service.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PaymentServiceImpl paymentService() {
        return new PaymentServiceImpl();
    }

    // Le bean paymentDomainService peut être conservé si nécessaire ailleurs
    @Bean
    public PaymentService paymentDomainService(PaymentServiceImpl paymentService) {
        return paymentService;
    }

    @Bean
    public PaymentApplicationService paymentApplicationService(
            PaymentServiceImpl paymentService,
            SavePaymentPort savePaymentPort) {
        return new PaymentApplicationService(paymentService, savePaymentPort);
    }
}