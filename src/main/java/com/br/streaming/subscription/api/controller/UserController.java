package com.br.streaming.subscription.api.controller;

import com.br.streaming.subscription.api.config.openapi.ApiStandardErrors;
import com.br.streaming.subscription.api.dto.CreateUserRequest;
import com.br.streaming.subscription.api.dto.ErrorResponse;
import com.br.streaming.subscription.api.dto.UserResponse;
import com.br.streaming.subscription.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "APIs de Gerenciamento de Usuários")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo usuário na plataforma")
    @ApiStandardErrors
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            )
    })
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca dados de um usuário pelo ID")
    @ApiStandardErrors
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuário não encontrado para o ID informado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public UserResponse getUserById(
            @Parameter(
                    name = "id",
                    description = "ID do usuário a ser buscado",
                    example = "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
                    required = true
            )
            @PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }
}