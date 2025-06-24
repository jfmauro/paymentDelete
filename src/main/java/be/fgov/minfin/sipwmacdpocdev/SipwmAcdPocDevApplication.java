package be.fgov.minfin.sipwmacdpocdev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Slf4j
public class SipwmAcdPocDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(SipwmAcdPocDevApplication.class, args);
    }
}