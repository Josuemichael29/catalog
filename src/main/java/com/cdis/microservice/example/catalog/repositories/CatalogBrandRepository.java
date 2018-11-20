package com.cdis.microservice.example.catalog.repositories;

import com.cdis.microservice.example.catalog.models.CatalogBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogBrandRepository extends JpaRepository<CatalogBrand, Long> {
}
