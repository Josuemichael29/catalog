package com.cdis.microservice.example.catalog.repositories;

import com.cdis.microservice.example.catalog.models.CatalogItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogItemRepository extends JpaRepository<CatalogItem, Long> {
    public List<CatalogItem> findCatalogItemsByCatalogBrand_Id(Long id);

    public List<CatalogItem> findCatalogItemsByCatalogType_Id(Long id);

    public List<CatalogItem> findCatalogItemByCatalogBrand_IdAndCatalogType_Id(Long brand_id, Long type_id);

    public List<CatalogItem> findCatalogItemByName(String name);
}
