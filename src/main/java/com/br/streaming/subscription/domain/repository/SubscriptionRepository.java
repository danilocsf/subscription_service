package com.br.streaming.subscription.domain.repository;

import com.br.streaming.subscription.domain.entity.Subscription;
import com.br.streaming.subscription.domain.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    boolean existsByUserIdAndStatus(UUID userId, SubscriptionStatus status);

    Optional<Subscription> findByUserIdAndStatus(UUID userId, SubscriptionStatus status);

    @Query("""
            SELECT s FROM Subscription s
            JOIN FETCH s.user
            JOIN FETCH s.plan
            WHERE s.status = :status
            AND s.expirationDate = :expirationDate
            """)
    List<Subscription> findAllByStatusAndExpirationDate(
            @Param("status") SubscriptionStatus status,
            @Param("expirationDate") LocalDate expirationDate
    );
}