package api.usuarios.controller;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.service.impl.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    EmailServiceImpl serviceImpl;
    @Mock
    MockMvc mockMvc;
    @InjectMocks
    EmailController controller;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AuthController()).build();
    }

    @DisplayName("Testa chamada da service de envio de email.")
    @Test
    void testaSendEmail() {
        controller.sendEmail(getEmail());
        Mockito.verify(serviceImpl, Mockito.times(1)).sendEmail(getEmail());
    }

    @DisplayName("Status code 404 pois não existe este path")
    @Test
    void deveDevolverStatus404NoEnvioDeEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/send-email"))
                .andExpect(status().is(404));
    }

    private EmailRequestDTO getEmail() {
        return new EmailRequestDTO("teste@gmail.com", "teste1@gmail.com",
                "teste", "blabla");
    }
}