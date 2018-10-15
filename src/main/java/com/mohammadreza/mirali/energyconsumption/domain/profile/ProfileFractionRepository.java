package com.mohammadreza.mirali.energyconsumption.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileFractionRepository extends JpaRepository<ProfileFractionEntity,ProfileFractionId> {
}
