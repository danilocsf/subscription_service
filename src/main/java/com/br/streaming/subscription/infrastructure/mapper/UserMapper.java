package com.br.streaming.subscription.infrastructure.mapper;

import com.br.streaming.subscription.api.dto.UserResponse;
import com.br.streaming.subscription.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponse toResponse(User user);
}
