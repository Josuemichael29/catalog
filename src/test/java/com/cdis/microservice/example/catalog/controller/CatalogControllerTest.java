package com.cdis.microservice.example.catalog.controller;

import com.cdis.microservice.example.catalog.controllers.CatalogController;
import com.cdis.microservice.example.catalog.models.CatalogBrand;
import com.cdis.microservice.example.catalog.models.CatalogItem;
import com.cdis.microservice.example.catalog.models.CatalogType;
import com.cdis.microservice.example.catalog.services.CatalogBrandService;
import com.cdis.microservice.example.catalog.services.CatalogItemService;
import com.cdis.microservice.example.catalog.services.CatalogTypeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CatalogController.class)
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogBrandService catalogBrandService;

    @MockBean
    private CatalogItemService catalogItemService;

    @MockBean
    private CatalogTypeService catalogTypeService;

    private JacksonTester<CatalogBrand> jsonCatalogBrand;
    private JacksonTester<List<CatalogBrand>> jsonCatalogBrandList;

    private JacksonTester<CatalogItem> jsonCatalogItem;
    private JacksonTester<List<CatalogItem>> jsonCatalogItemList;

    private JacksonTester<CatalogType> jsonCatalogType;
    private JacksonTester<List<CatalogType>> jsonCatalogTypeList;

    @Before
    public void setUp() throws Exception {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    /**
     * Test para obtener item por id
     * @throws Exception
     */
    @Test
    public void getItemById() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name TEST";
        String pictureUri = "Picture Uri TEST";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        given(catalogItemService.getItembyId(itemId))
                .willReturn(new CatalogItem(itemId, name, description, price,
                                pictureFileName, pictureUri, catalogType, catalogBrand));

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/" + itemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItem.write(new CatalogItem(itemId, name, description, price,
                        pictureFileName, pictureUri, catalogType, catalogBrand)).getJson());
    }

    @Test
    public void getItemBrand() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        given(catalogItemService.getItembyId(itemId))
                .willReturn(item1);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/" + itemId + "/brand")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogBrand.write(new CatalogBrand(catalogBrand.getId(), catalogBrand.getName())).getJson());
    }

    @Test
    public void getItemType() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        given(catalogItemService.getItembyId(itemId))
                .willReturn(item1);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/" + itemId + "/type")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogType.write(new CatalogType(catalogType.getId(), catalogType.getType())).getJson());
    }

    @Test
    public void getItemAllItemsByBrand() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);
        CatalogItem item2 = new CatalogItem( 2L,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        List<CatalogItem> listaItem = new ArrayList<CatalogItem>();
        listaItem.add(item1);
        listaItem.add(item2);

        given(catalogItemService.getAllCatalogItemsByBrand(catalogBrand.getId()))
                .willReturn(listaItem);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/brand/" + catalogBrand.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItemList.write(listaItem).getJson());
    }

    @Test
    public void getItemAllItemsByType() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);
        CatalogItem item2 = new CatalogItem( 2L,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        List<CatalogItem> listaItem = new ArrayList<CatalogItem>();
        listaItem.add(item1);
        listaItem.add(item2);

        given(catalogItemService.getAllCatalogItemsByType(catalogType.getId()))
                .willReturn(listaItem);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/type/" + catalogType.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItemList.write(listaItem).getJson());
    }

    @Test
    public void getItemAllFiltered() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);
        CatalogItem item2 = new CatalogItem( 2L,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        List<CatalogItem> listaItem = new ArrayList<CatalogItem>();
        listaItem.add(item1);
        listaItem.add(item2);

        given(catalogItemService.getAllCatalogItemsFiltered(catalogBrand.getId(), catalogType.getId()))
                .willReturn(listaItem);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/filter/" + catalogBrand.getId() + "/" + catalogType.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItemList.write(listaItem).getJson());
    }

    @Test
    public void getBrandsById() throws Exception {
        CatalogType catalogType = new CatalogType(1L, "Type TEST");

        given(catalogTypeService.getTypebyId(catalogType.getId()))
                .willReturn(catalogType);

        MockHttpServletResponse response = mockMvc.perform(
                get("/type/" + catalogType.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogType.write(new CatalogType(catalogType.getId(), catalogType.getType())).getJson());
    }

    @Test
    public void getTypesById() throws Exception {
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        given(catalogBrandService.getBrandbyId(catalogBrand.getId()))
                .willReturn(catalogBrand);

        MockHttpServletResponse response = mockMvc.perform(
                get("/brand/" + catalogBrand.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogBrand.write(new CatalogBrand(catalogBrand.getId(), catalogBrand.getName())).getJson());
    }

    @Test
    public void getAllItems() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);
        CatalogItem item2 = new CatalogItem( 2L,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        List<CatalogItem> listaItem = new ArrayList<CatalogItem>();
        listaItem.add(item1);
        listaItem.add(item2);

        given(catalogItemService.getAllCatalogItems())
                .willReturn(listaItem);

        MockHttpServletResponse response = mockMvc.perform(
                get("/item/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItemList.write(listaItem).getJson());
    }

    @Test
    public void getAllBrands() throws Exception {
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogType catalogType2 = new CatalogType(2L, "Type TEST");

        List<CatalogType> listaType = new ArrayList<CatalogType>();
        listaType.add(catalogType);
        listaType.add(catalogType2);

        given(catalogTypeService.getAllCatalogTypes())
                .willReturn(listaType);

        MockHttpServletResponse response = mockMvc.perform(
                get("/type/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogTypeList.write(listaType).getJson());
    }

    @Test
    public void getAllTypes() throws Exception {
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");
        CatalogBrand catalogBrand2 = new CatalogBrand(2L, "Brand TEST");

        List<CatalogBrand> listaBrand = new ArrayList<CatalogBrand>();
        listaBrand.add(catalogBrand);
        listaBrand.add(catalogBrand2);

        given(catalogBrandService.getAllCatalogBrands())
                .willReturn(listaBrand);

        MockHttpServletResponse response = mockMvc.perform(
                get("/brand/")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogBrandList.write(listaBrand).getJson());
    }
/*
    @Test
    public void deleteCatalogItem() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        given(catalogItemService.addCatalogItem(item1))
                .willReturn(item1);

        MockHttpServletResponse response = mockMvc.perform(
                post("/item/" + itemId)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(ResponseEntity.status(202).build());

    }
*/

    @Test
    public void updateCatalogItem() throws Exception {
        Long itemId = 1L;
        String name = "Item TEST";
        String description = "Description TEST";
        double price = 123.456;
        String pictureFileName = "Picture File Name Test";
        String pictureUri = "Picture Uri Test";
        CatalogType catalogType = new CatalogType(1L, "Type TEST");
        CatalogBrand catalogBrand = new CatalogBrand(1L, "Brand TEST");

        CatalogItem item1 = new CatalogItem( itemId,  name, description,  price,  pictureFileName,  pictureUri,
                catalogType,  catalogBrand);

        given(catalogItemService.addCatalogItem(item1))
                .willReturn(item1);


        MockHttpServletResponse response = mockMvc.perform(
                MockMvcRequestBuilders.post("/item/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"itemId\": 1, \"name\": \"Item TEST\", \"description\":\"Description TEST\"," +
                                "\"price\":123.456,\"pictureFileName\": \"Picture File Name Test\", \"pictureUri\":\"Picture Uri Test\"," +
                                "\"catalogType\":{\"id\": 1,\"type\":\"Type TEST\"}, \"catalogBrand\":{\"id\":1, \"name\":\"Brand TEST\"}}")
                        .accept(MediaType.APPLICATION_JSON));

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonCatalogItem.write(item1).getJson());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
