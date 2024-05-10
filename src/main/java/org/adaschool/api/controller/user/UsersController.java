package org.adaschool.api.controller.user;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.user.User;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        //TODO implement this method
        URI createdUserUri = URI.create("");
        User user = usersService.save(newUser);
        return ResponseEntity.created(createdUserUri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        //TODO implement this method
        Optional<User> user  = usersService.findById(id);
        return ResponseEntity.ok(user.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@RequestBody User updateUser, @PathVariable("id") String id) {
        //TODO implement this method
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            user.get().setName(updateUser.getName());
            user.get().setLastName(updateUser.getLastName());
            user.get().setEmail(updateUser.getEmail());
             final User updatedUser = usersService.update(user.get(),id);
            return ResponseEntity.ok(updatedUser);
    }
        return ResponseEntity.ok(user.get());
}

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        //TODO implement this method
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
