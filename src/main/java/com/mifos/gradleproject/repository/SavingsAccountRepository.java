package com.mifos.gradleproject.repository;

import com.mifos.gradleproject.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {}
