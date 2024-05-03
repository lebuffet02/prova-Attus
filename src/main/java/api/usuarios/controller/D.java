package api.usuarios.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class D {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("adm")
    public String endpointPrivadoAdm() {
        return "role adm";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("user")
    public String endpointPrivadoUser() {
        return "role user";
    }
}
