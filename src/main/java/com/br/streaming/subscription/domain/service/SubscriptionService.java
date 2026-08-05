package com.br.streaming.subscription.domain.service;

import com.br.streaming.subscription.api.dto.CreateSubscriptionRequest;
import com.br.streaming.subscription.api.dto.SubscriptionResponse;
import com.br.streaming.subscription.domain.entity.Plan;
import com.br.streaming.subscription.domain.entity.Subscription;
import com.br.streaming.subscription.domain.entity.User;
import com.br.streaming.subscription.domain.enums.SubscriptionStatus;
import com.br.streaming.subscription.domain.exception.BusinessException;
import com.br.streaming.subscription.domain.exception.NotFoundException;
import com.br.streaming.subscription.domain.repository.SubscriptionRepository;
import com.br.streaming.subscription.domain.repository.UserRepository;
import com.br.streaming.subscription.infrastructure.mapper.SubscriptionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class SubscriptionService {

    private final UserRepository userRepository;
    private final PlanService planService;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper mapper;

    @Transactional
    public SubscriptionResponse createSubscription(CreateSubscriptionRequest request) {
        log.info("Criando assinatura para o usuário: {}", request.userId());
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new NotFoundException("Usuário {0} não encontrado.", request.userId()));

        if (subscriptionRepository.existsByUserIdAndStatus(user.getId(), SubscriptionStatus.ACTIVE)) {
            log.warn("Usuário {} já possui uma assinatura ativa.", user.getId());
            throw new BusinessException("Usuário {0} já possui uma assinatura ativa.", request.userId());
        }

        Plan plan = planService.getPlanById(request.planId());

        LocalDate today = LocalDate.now();
        Subscription subscription = Subscription.builder()
                .user(user)
                .plan(plan)
                .startDate(today)
                .expirationDate(today.plusMonths(1))
                .status(SubscriptionStatus.ACTIVE)
                .retryCount(0)
                .build();

        Subscription newSubscription = subscriptionRepository.save(subscription);
        return mapper.toResponse(newSubscription);
    }
}
