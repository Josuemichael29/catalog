package com.cdis.microservice.example.catalog.repositories;

import com.cdis.microservice.example.catalog.models.CatalogType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogTypeRepository extends JpaRepository<CatalogType, Long> {
}
