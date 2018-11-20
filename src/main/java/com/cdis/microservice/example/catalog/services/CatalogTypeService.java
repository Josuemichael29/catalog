package com.cdis.microservice.example.catalog.services;

import com.cdis.microservice.example.catalog.models.CatalogType;

import java.util.List;


public interface CatalogTypeService {

    // Adds Types to the database

    /**
     * @param catalogType
     * @return CatalogType
     */
    CatalogType addCatalogType(CatalogType catalogType);

    // Deleting Type from catalog using Id

    /**
     * @param id
     * @return null
     */
    void deletingCatalogTypeById(final Long id);

    // Retrive Types from catalog

    /**
     * @param
     * @return List<CatalogType>
     */
    List<CatalogType> getAllCatalogTypes();

    // Retrieve Types by Id

    /**
     * @param id
     * @return CatalogType
     */
    CatalogType getTypebyId(final Long id);


}
