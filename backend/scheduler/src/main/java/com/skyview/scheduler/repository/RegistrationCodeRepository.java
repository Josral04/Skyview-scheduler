package com.skyview.scheduler.repository;

import com.skyview.scheduler.model.RegistrationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, Long> {

    Optional<RegistrationCode> findByCodeValue(String codeValue);
}
