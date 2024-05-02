package api.usuarios.controller;

import api.usuarios.documentation.EmailDocumentation;
import api.usuarios.dto.EmailRequestDTO;
import api.usuarios.service.impl.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController implements EmailDocumentation {

    @Autowired
    EmailServiceImpl serviceImpl;


    @Override
    @PreAuthorize("hasRole('USER')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequestDTO emailDTO) {
        serviceImpl.sendEmail(emailDTO);
        return ResponseEntity.ok("Email sent successfully!");
    }
}