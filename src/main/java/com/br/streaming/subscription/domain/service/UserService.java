package com.br.streaming.subscription.domain.service;

import com.br.streaming.subscription.api.dto.CreateUserRequest;
import com.br.streaming.subscription.api.dto.UserResponse;
import com.br.streaming.subscription.domain.entity.User;
import com.br.streaming.subscription.domain.exception.BusinessException;
import com.br.streaming.subscription.domain.exception.NotFoundException;
import com.br.streaming.subscription.domain.repository.UserRepository;
import com.br.streaming.subscription.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException("Já existe um usuário cadastrado com o e-mail: " + request.email());
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .cardToken(request.cardToken())
                .build();

        User newUser = userRepository.saveAndFlush(user);
        return mapper.toResponse(newUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado para o ID: " + id));

        return  mapper.toResponse(user);
    }
}