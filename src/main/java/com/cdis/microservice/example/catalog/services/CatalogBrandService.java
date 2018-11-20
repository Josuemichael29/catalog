package com.cdis.microservice.example.catalog.services;

import com.cdis.microservice.example.catalog.models.CatalogBrand;

import java.util.List;


public interface CatalogBrandService {

    // Adds Brands to the database

    /**
     * @param catalogBrand
     * @return CatalogBrand
     */
    CatalogBrand addCatalogBrand(CatalogBrand catalogBrand);

    // Deleting Brand from catalog using Id

    /**
     * @param id
     * @return null
     */
    void deletingCatalogBrandById(final Long id);

    // Retrive Brands from catalog

    /**
     * @param
     * @return List<CatalogBrand>
     */
    List<CatalogBrand> getAllCatalogBrands();

    // Retrieve Brands by Id

    /**
     * @param id
     * @return CatalogBrand
     */
    CatalogBrand getBrandbyId(final Long id);


}
