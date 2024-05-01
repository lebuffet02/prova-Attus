package api.usuarios.controller;

import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl serviceImpl;

    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "send-email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDTO emailDTO) {
        serviceImpl.sendEmail(emailDTO);
        return ResponseEntity.ok("Email sent successfully by AWS SES!");
    }



}
