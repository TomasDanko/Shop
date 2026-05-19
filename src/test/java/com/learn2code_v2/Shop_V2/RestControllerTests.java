package com.learn2code_v2.Shop_V2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn2code_v2.Shop_V2.domain.Customer;
import com.learn2code_v2.Shop_V2.domain.Merchant;
import com.learn2code_v2.Shop_V2.domain.Product;
import com.learn2code_v2.Shop_V2.request.UpdateProductRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private Merchant merchant;

    @Before
    public void createMerchant() throws Exception {
        if (merchant == null) {
            merchant = new Merchant("Janko", "email", "address_2");

            String id = mockMvc.perform(post("/merchant")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(merchant)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();

            merchant.setId(objectMapper.readValue(id, Integer.class));
        }

    }


    @Test
    public void customer() throws Exception {
        Customer customer = new Customer("Tomas", "Danko", "email", "address", 21, "xxx");

        // add customer
        String id = mockMvc.perform(post("/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        customer.setId(objectMapper.readValue(id, Integer.class));


        // get customer
        String customerJson = mockMvc.perform(get("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Customer returnedCustomer = objectMapper.readValue(customerJson, Customer.class);
        Assert.assertEquals(customer, returnedCustomer);



        //get all
        String listJson = mockMvc.perform(get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Customer> customers = objectMapper.readValue(listJson, new TypeReference<List<Customer>>() {});
//        assert customers.size() == 1;
        Assert.assertEquals(customer, customers.get(0));


    }


    @Test
    public void merchant() throws Exception{
        Merchant merchant = new Merchant("Janko", "email", "address_2");

       String id = mockMvc.perform(post("/merchant")
                .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(merchant)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

        merchant.setId(objectMapper.readValue(id, Integer.class));

        // get merchant

        String merchantJson = mockMvc.perform(get("/merchant/" + merchant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Merchant returnedMerchant = objectMapper.readValue(merchantJson, Merchant.class);
        Assert.assertEquals(merchant, returnedMerchant);

        //get all merchants
        String listJson = mockMvc.perform(get("/merchant")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Merchant> allMerchant = objectMapper.readValue(listJson, new TypeReference<List<Merchant>>() {});
        Assert.assertEquals(merchant, allMerchant.get(0));

    }

    @Test
    public void product() throws Exception {
        Product product = new Product(merchant.getId(), "PC", "dobre PC",1000.0, 10);

        // Add product
       String id = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();

       product.setId(objectMapper.readValue(id, Integer.class));

       // Get product
       String productJson = mockMvc.perform(get("/product/" + product.getId())
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn().getResponse().getContentAsString();

      Product returnedProduct = objectMapper.readValue(productJson, Product.class);
      Assert.assertEquals(returnedProduct, product);

      // get all

        String listJson = mockMvc.perform(get("/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<Product> productList = objectMapper.readValue(listJson, new TypeReference<List<Product>>() {});
        Assert.assertEquals(product, productList);


        // update product

        double updatePrice = product.getPrice() + 1;
        int updatedAvailable = product.getAvailable() + 5;
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(), product.getDescription(), updatePrice, updatedAvailable);

        mockMvc.perform(patch("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProductRequest)))
                .andExpect(status().isOk());

        String returnedUpdatedProduct = mockMvc.perform(get("/product/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Product updatedProduct = objectMapper.readValue(returnedUpdatedProduct, Product.class);

        assert  updatePrice == updatedProduct.getPrice();
        assert  updatedAvailable == updatedProduct.getAvailable();

        //delete product
        mockMvc.perform(delete("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());


    }


}
