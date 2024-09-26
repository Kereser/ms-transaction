package com.emazon.ms_transaction.application.mapper;

import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.model.SupplyArticle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionDTOMapper {

    SupplyReqDTO.ItemQuantity toDTO(SupplyArticle supplyArticle);
    Set<SupplyReqDTO.ItemQuantity> toSetDTO(Set<SupplyArticle> supplyArticleSet);
    Set<SupplyArticle> toSetModel(Set<SupplyReqDTO.ItemQuantity> setDTO);

    SupplyArticle toModel(SupplyReqDTO.ItemQuantity dto);

    @Mapping(source = "item", target = "supplyArticle")
    Supply toModel(SupplyReqDTO dto);
}
