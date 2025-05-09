package com.mifos.gradleproject.repository;

import com.mifos.gradleproject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {}
