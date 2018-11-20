package com.cdis.microservice.example.catalog.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Items")
public class CatalogItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // Database fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "pictureFileName", nullable = false)
    private String pictureFileName;

    @Column(name = "pictureFileUri", nullable = false)
    private String pictureFileUri;

    // References to other tables
    @ManyToOne(optional = false)
    @JoinColumn(name = "item_type", nullable = false, referencedColumnName = "id")
    private CatalogType catalogType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "item_brand", nullable = false, referencedColumnName = "id")
    private CatalogBrand catalogBrand;

    public CatalogItem() {
    }

    public CatalogItem(Long id, String name, String description, double price, String pictureFileName, String pictureFileUri, CatalogType catalogType, CatalogBrand catalogBrand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureFileName = pictureFileName;
        this.pictureFileUri = pictureFileUri;
        this.catalogType = catalogType;
        this.catalogBrand = catalogBrand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureFileUri() {
        return pictureFileUri;
    }

    public void setPictureFileUri(String pictureFileUri) {
        this.pictureFileUri = pictureFileUri;
    }

    public CatalogType getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }

    public CatalogBrand getCatalogBrand() {
        return catalogBrand;
    }

    public void setCatalogBrand(CatalogBrand catalogBrand) {
        this.catalogBrand = catalogBrand;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", pictureFileName='" + pictureFileName + '\'' +
                ", pictureFileUri='" + pictureFileUri + '\'' +
                ", catalogType=" + catalogType +
                ", catalogBrand=" + catalogBrand +
                '}';
    }


}
