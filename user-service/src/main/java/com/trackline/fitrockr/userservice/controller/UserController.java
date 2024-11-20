package com.trackline.fitrockr.userservice.controller;

import com.trackline.fitrockr.userservice.handler.UserHandler;
import com.trackline.fitrockr.userservice.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "user")
@CrossOrigin(origins = "http://example.com")
public class UserController {

    private final UserHandler userHandler;

    public UserController(UserHandler userHandler) {
        this.userHandler = userHandler;
    }

    @Operation(summary = "add a new user")
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseTypes
    public ResponseEntity<ResponseUserModel> createUser(@RequestBody UserCreateModel model) {
        ResponseUserModel response = userHandler.createUser(model);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "get a user by id")
    @GetMapping(value = "/users/{userId}")
    @ResponseTypes
    public ResponseEntity<ResponseUserModel> getUser(@PathVariable("userId") Long userId) {
        ResponseUserModel response = userHandler.getUserById(userId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "list existing users")
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseTypes
    public ResponseEntity<ResponseUsersModel> listUsers(
            @RequestParam(value = "filterName", required = false) String filterName,
            @RequestParam(value = "filterEmail", required = false) String filterEmail)
    {
        ResponseUsersModel response = userHandler.getUsersFiltered(filterName, filterEmail);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "delete a user by id")
    @DeleteMapping(value = "/users/{userId}")
    @ResponseTypes
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userHandler.deleteUserById(userId);
        return ResponseEntity.ok(null);
    }

}
