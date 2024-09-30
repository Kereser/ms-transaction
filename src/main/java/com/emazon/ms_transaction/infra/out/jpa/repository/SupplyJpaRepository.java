package com.emazon.ms_transaction.infra.out.jpa.repository;

import com.emazon.ms_transaction.infra.out.jpa.entity.SupplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyJpaRepository extends JpaRepository<SupplyEntity, Long> {
}
