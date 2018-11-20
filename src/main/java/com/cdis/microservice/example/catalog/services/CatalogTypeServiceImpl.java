package com.cdis.microservice.example.catalog.services;

import com.cdis.microservice.example.catalog.exceptions.ResourceNotFoundException;
import com.cdis.microservice.example.catalog.models.CatalogType;
import com.cdis.microservice.example.catalog.repositories.CatalogTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogTypeServiceImpl implements CatalogTypeService {

    private CatalogTypeRepository catalogTypeRepository;

    @Autowired
    public CatalogTypeServiceImpl(final CatalogTypeRepository catalogTypeRepository) {
        this.catalogTypeRepository = catalogTypeRepository;
    }

    @Override
    public CatalogType addCatalogType(CatalogType catalogType) {
        return catalogTypeRepository.save(catalogType);
    }

    @Override
    public void deletingCatalogTypeById(Long id) {
        catalogTypeRepository.deleteById(id);
    }

    @Override
    public List<CatalogType> getAllCatalogTypes() {
        return catalogTypeRepository.findAll();
    }

    @Override
    public CatalogType getTypebyId(Long id) {
        return catalogTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog Type", "id", id));
    }
}
