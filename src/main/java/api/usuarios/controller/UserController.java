package api.usuarios.controller;

import api.usuarios.documentation.UserDocumentation;
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
@RequestMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController implements UserDocumentation {

    @Autowired
    UserServiceImpl serviceImpl;


    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Optional<UserDTO>> saveUser(@RequestBody UserDTO userDTO)  {
        return ResponseEntity.ok(serviceImpl.saveUserServiceImpl(userDTO));
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> updateUser(
            @PathVariable("id") Long id, @RequestBody UserDTO userDTO)  {
        return ResponseEntity.ok(serviceImpl.updateUserServiceImpl(id, userDTO));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(serviceImpl.getAllUsersServiceImpl());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(serviceImpl.getUserByIdServiceImpl(id));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping
    public ResponseEntity<?> deleteAllUsers() {
        serviceImpl.deleteAllUsersServiceImpl();
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        serviceImpl.deleteUserByIdServiceImpl(id);
        return ResponseEntity.noContent().build();
    }
}