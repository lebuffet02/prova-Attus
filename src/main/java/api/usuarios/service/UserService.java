package api.usuarios.service;

import api.usuarios.dto.EmailRequestDTO;

public interface UserService {

    void sendEmail(EmailRequestDTO emailRequest);
}
