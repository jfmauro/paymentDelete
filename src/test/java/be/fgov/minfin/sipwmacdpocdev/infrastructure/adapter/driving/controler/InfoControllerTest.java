package be.fgov.minfin.sipwmacdpocdev.infrastructure.adapter.driving.controler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InfoController.class)
class InfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInfo_ShouldReturnCorrectResponse() throws Exception {
        mockMvc.perform(get("/api/test/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("API REST Spring Boot"))
                .andExpect(jsonPath("$.version").value("1.0.2"))
                .andExpect(jsonPath("$.status").value("Opérationnel"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}