package com.cdis.microservice.example.catalog.controllers;


import com.cdis.microservice.example.catalog.exceptions.ResourceNotFoundException;
import com.cdis.microservice.example.catalog.models.CatalogBrand;
import com.cdis.microservice.example.catalog.models.CatalogItem;
import com.cdis.microservice.example.catalog.models.CatalogType;
import com.cdis.microservice.example.catalog.services.CatalogBrandService;
import com.cdis.microservice.example.catalog.services.CatalogItemService;
import com.cdis.microservice.example.catalog.services.CatalogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping
public class CatalogController {
    private final CatalogItemService catalogItemService;
    private final CatalogBrandService catalogBrandService;
    private final CatalogTypeService catalogTypeService;

    @Autowired
    public CatalogController(final CatalogItemService catalogItemService, CatalogBrandService catalogBrandService, CatalogTypeService catalogTypeService) {
        this.catalogItemService = catalogItemService;
        this.catalogBrandService = catalogBrandService;
        this.catalogTypeService = catalogTypeService;
    }

    @GetMapping("/")
    public String home(){
        return "Catalog Service - Home";
    }

    // Catalog Item Routes
    /*  Get all catalogItems
     *
     *   @param no params
     *   @return List<CatalogItem>
     *
     */
    @GetMapping("/item")
    public List<CatalogItem> getAllItems() {
        return catalogItemService.getAllCatalogItems();
    }

    /*  Get a catalogItem record by Id
     *
     *   @param id
     *   @return catalogItem
     *
     */
    @GetMapping("/item/{id}")
    public CatalogItem getItemById(@PathVariable(value = "id") final Long id) {
        CatalogItem optionalCatalogItem = catalogItemService.getItembyId(id);

        if (optionalCatalogItem == null) {
            throw new ResourceNotFoundException("CatalogItem", "id", id);
        }

        return optionalCatalogItem;
    }

    /*  Get an single item found by the Id Brand
     *
     *   @param id
     *   @return catalogItem
     *
     */
    @GetMapping("/item/{id}/brand")
    public CatalogBrand getItemBrand(@PathVariable(value = "id") final Long id) {
        CatalogBrand catalogItemBrand = catalogItemService.getItembyId(id).getCatalogBrand();

        if (catalogItemBrand == null) {
            throw new ResourceNotFoundException("CatalogItem", "id", id);
        }

        return catalogItemBrand;
    }

    /*  Get an all item found by type_id
     *
     *   @param type_id
     *   @return catalogItem
     *
     */
    @GetMapping("/item/brand/{id}")
    public List<CatalogItem> getItemAllItemsByBrand( @PathVariable(value = "id") final Long brand_id) {
        return catalogItemService.getAllCatalogItemsByBrand(brand_id);
    }


    /*  Get an single item found by the Id Type
     *
     *   @param id
     *   @return catalogType
     *
     */
    @GetMapping("/item/{id}/type")
    public CatalogType getItemType(@PathVariable(value = "id") final Long id) {
        CatalogType catalogItemType = catalogItemService.getItembyId(id).getCatalogType();
        if (catalogItemType == null) {
            throw new ResourceNotFoundException("CatalogItem", "id", id);
        }
        return catalogItemType;
    }

    /*  Get an all item found by type_id
     *
     *   @param type_id
     *   @return catalogItem
     *
     */
    @GetMapping("/item/type/{id}")
    public List<CatalogItem> getItemAllItemsByType(@PathVariable(value = "id") final Long id) {
        return catalogItemService.getAllCatalogItemsByType(id);
    }

    /*  Get an all item found by type_id and brand_id
     *
     *   @param type_id and
     *   @return catalogItem
     *
     */
    @GetMapping("/item/filter/{b_id}/{t_id}")
    public List<CatalogItem> getItemAllFiltered(@PathVariable(value = "b_id") final Long brand_id, @PathVariable(value = "t_id") final Long type_id) {
        return catalogItemService.getAllCatalogItemsFiltered(brand_id, type_id);
    }


    /*  Post an item
     *
     *   @param RequestBody [jsonObject]
     *   @return response Code
     *
     */
    @PostMapping("/item")
    public ResponseEntity<Object> postCatalogItem(@Valid @RequestBody CatalogItem item) {
        CatalogItem savedItem = catalogItemService.addCatalogItem(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*  Update an item
     *
     *   @param RequestBody CatalogItem and PathVariable Long id
     *   @return response Code
     *
     */
    @PutMapping("/item/{id}")
    public CatalogItem updateCatalogItem(@Valid @RequestBody CatalogItem item, @PathVariable(value = "id") final Long id) {
        item.setId(id);

        if (getItemById(id) == null) {
            throw new ResourceNotFoundException("CatalogItem", "id", id);
        }

        CatalogItem updatedItem = catalogItemService.addCatalogItem(item);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(updatedItem.getId())
                .toUri();
        return updatedItem;
        //return ResponseEntity.noContent().build();
    }


    /*  Delete an item
     *
     *   @param PathVariable Long id
     *   @return response Code
     *
     */
    @DeleteMapping("/item/{id}")
    public ResponseEntity<CatalogItem> deleteCatalogItem(@PathVariable(value = "id") final Long id) {
        CatalogItem itemToBeDeleted = catalogItemService.getItembyId(id);

        if (itemToBeDeleted == null) {
            throw new ResourceNotFoundException("CatalogItem", "id", id);
        } else {
            catalogItemService.deletingCatalogItemById(id);
        }

        return ResponseEntity.status(202).build();
    }

    // Catalog Brand Routes
    @GetMapping("/brand")
    public List<CatalogBrand> getAllBrands() {
        return catalogBrandService.getAllCatalogBrands();
    }

    @GetMapping("/brand/{id}")
    public CatalogBrand getBrandsById(@PathVariable(value = "id") final Long id) {
        return catalogBrandService.getBrandbyId(id);
    }

    @PostMapping("/brand")
    public ResponseEntity<CatalogBrand> postCatalogBrand(@Valid @RequestBody CatalogBrand catalogBrand) {
        catalogBrandService.addCatalogBrand(catalogBrand);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<CatalogBrand> deleteCatalogBrand(@PathVariable(value = "id") final Long id) {
        catalogBrandService.deletingCatalogBrandById(id);
        return ResponseEntity.status(202).build();
    }

    // Catalog Type Routes
    @GetMapping("/type")
    public List<CatalogType> getAllTypes() {
        return catalogTypeService.getAllCatalogTypes();
    }

    @GetMapping("/type/{id}")
    public CatalogType getTypesById(@PathVariable(value = "id") final Long id) {
        return catalogTypeService.getTypebyId(id);
    }

    @PostMapping("/type")
    public ResponseEntity<CatalogType> postCatalogType(@Valid @RequestBody CatalogType catalogType) {
        catalogTypeService.addCatalogType(catalogType);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/Type/{id}")
    public ResponseEntity<CatalogType> deleteCatalogType(@PathVariable(value = "id") final Long id) {
        catalogTypeService.deletingCatalogTypeById(id);
        return ResponseEntity.status(202).build();
    }

}
