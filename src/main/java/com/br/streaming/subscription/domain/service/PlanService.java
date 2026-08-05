package com.br.streaming.subscription.domain.service;

import com.br.streaming.subscription.domain.entity.Plan;
import com.br.streaming.subscription.domain.exception.NotFoundException;
import com.br.streaming.subscription.domain.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanService {
    private final PlanRepository planRepository;

    @Cacheable(value = "plans", key = "#id")
    public Plan getPlanById(UUID id) {
        log.info("Buscando pelo plano {} - (cache miss)", id);
        return planRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Plano {0} não encontrado.", id));
    }
}
