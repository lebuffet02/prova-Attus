package api.usuarios.controller;

import api.usuarios.dto.UserDTO;
import api.usuarios.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserServiceImpl serviceImpl;


    @PreAuthorize("hasRole('USER')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(serviceImpl.getAllUsersServiceImpl());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceImpl.getUserByIdServiceImpl(id));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUserById() {
        serviceImpl.deleteAllUsersServiceImpl();
        return ResponseEntity.noContent().build();
    }
}
