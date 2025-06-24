package be.fgov.minfin.sipwmacdpocdev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("local")
@TestPropertySource(properties = "spring.main.lazy-initialization=true")
class SipwmAcdPocDevApplicationTest {

    @Test
    void contextLoads() {
        // Ce test vérifie que le contexte Spring se charge correctement
    }
}