package com.emazon.ms_transaction.infra.out.jpa.mapper;

import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.infra.out.jpa.entity.SupplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionalEntityMapper {

    @Mapping(source = "supplyArticle", target = "supplyArticleEntity")
    SupplyEntity toEntity(Supply model);
}
