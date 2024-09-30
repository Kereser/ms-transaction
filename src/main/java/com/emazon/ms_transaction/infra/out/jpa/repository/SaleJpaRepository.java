package com.emazon.ms_transaction.infra.out.jpa.repository;

import com.emazon.ms_transaction.infra.out.jpa.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleJpaRepository extends JpaRepository<SaleEntity, Long> {
}
