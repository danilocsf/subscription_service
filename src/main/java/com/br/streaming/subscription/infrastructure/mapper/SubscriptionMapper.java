package com.br.streaming.subscription.infrastructure.mapper;

import com.br.streaming.subscription.api.dto.SubscriptionResponse;
import com.br.streaming.subscription.domain.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "plan.name", target = "planName")
    SubscriptionResponse toResponse(Subscription subscription);
}